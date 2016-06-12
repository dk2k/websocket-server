package ru.outofrange.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import ru.outofrange.exc.UnsupportedJsonMessage;
import ru.outofrange.model.JsonMessage;
import ru.outofrange.model.JsonMessageWrapper;
import ru.outofrange.model.MessageType;
import ru.outofrange.model.Session;
import ru.outofrange.model.Token;
import ru.outofrange.model.User;
import ru.outofrange.service.MessageService;
import ru.outofrange.service.SessionService;
import ru.outofrange.service.TokenService;
import ru.outofrange.service.UserService;

import org.springframework.transaction.annotation.Transactional;

@Controller
public class WSController {

	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	
	private static final String ERROR_DESCRIPTION = "error_description";
	private static final String ERROR_DESCRIPTION_VALUE = "Customer not found";
	
	private static final String ERROR_CODE = "error_code";
	private static final String ERROR_CODE_VALUE = "customer.notFound";
	
	private static final String API_TOKEN = "api_token";
	private static final String API_TOKEN_EXPIRATION_DATE = "api_token_expiration_date";
	
	private static final int EXPIRATION_PERIOD = 2; // 2 days
	
	private DateTimeFormatter fmt =
		       DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZoneUTC();
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private TokenService tokenService;
	
	@Autowired
    private MessageService messageService;
	
	@Autowired
    private SessionService sessionService;

    @MessageMapping("/user")
    @SendToUser("/topic/auth")
    public JsonMessageWrapper process(JsonMessageWrapper jmw,
    		SimpMessageHeaderAccessor headerAccessor) throws Exception {
    	
    	System.out.println(jmw);
    	
    	if (jmw.getType() == null || jmw.getSequence_id() == null
    			|| jmw.getData() == null) {
    		// exit early?
    	} 
    	
    	String sessionId = headerAccessor.getSessionId();
    	
    	Session session = sessionService.getSessionByString(sessionId);
    	if (session == null) {
    		session = new Session();
    		session.setSessionId(sessionId);
    		sessionService.save(session);
    		// now we have id for this session 
    	}
    	
		// saving request in DB
		JsonMessage req = new JsonMessage(jmw);
		req.setSession(session);
		messageService.save(req);
    	
    	JsonMessage jm = new JsonMessage(jmw);
    	
    	JsonMessage response;
    	
		switch(jm.getType()){
			case LOGIN_CUSTOMER:	
				response =  processLoginMessage(jm, session);
				response.setSession(session);
				messageService.save(response);
				break;
			default:
				throw new UnsupportedJsonMessage();
		}
    	
        return new JsonMessageWrapper(response);
    }  
    
    @Transactional
	private JsonMessage processLoginMessage(JsonMessage jm, Session session) {
		
		User dbUser = userService.findByUsername(jm.getData().get(EMAIL));
		
		JsonMessage response = new JsonMessage();
		response.setSession(session);
		response.setSequence_id(jm.getSequence_id());
		
		if (authenticateUserFromMessage(jm.getData(), dbUser)) {
			
			String tokenString = String.valueOf(UUID.randomUUID());
			// save this token and invalidate all previous ones
			Token token = new Token();
			
			token.setToken(tokenString);
			token.setValid(true);
			token.setUser(dbUser);
			
			// invalidates the previous token 
			tokenService.invalidateAllValidForUser(dbUser);
			tokenService.save(token);
			
			Date now = new Date();
			Calendar futureDate = new GregorianCalendar();
			futureDate.setTime(now);
			futureDate.set(Calendar.DAY_OF_YEAR, futureDate.get(Calendar.DAY_OF_YEAR) + EXPIRATION_PERIOD);
			Date expireDate = futureDate.getTime();
			
			// thread safety for formatting of a date
			String sDate = fmt.print(new DateTime(expireDate));
			
			response.setType(MessageType.CUSTOMER_API_TOKEN);
			response.getData().put(API_TOKEN, tokenString);
			response.getData().put(API_TOKEN_EXPIRATION_DATE, sDate);
		} else {
			response.setType(MessageType.CUSTOMER_ERROR);
			response.getData().put(ERROR_DESCRIPTION, ERROR_DESCRIPTION_VALUE);
			response.getData().put(ERROR_CODE,ERROR_CODE_VALUE);
		}
		
		return response;
	}

	private boolean authenticateUserFromMessage(Map<String, String> data, User foundUser) {
		
		String password = data.get(PASSWORD);
		
		if (foundUser == null || !foundUser.getPassword().equals(password)) {
			return false;
		}
		
		return true;

	}

}
