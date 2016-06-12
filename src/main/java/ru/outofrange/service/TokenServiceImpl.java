package ru.outofrange.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.outofrange.model.Token;
import ru.outofrange.model.User;
import ru.outofrange.repository.TokenRepository;
import ru.outofrange.specification.TokenSpecification;

@Service
public class TokenServiceImpl implements TokenService {

	@Resource
    private TokenRepository tokenRepository;
	
	
	@Override
	@Transactional
	public void save(Token token) {
		tokenRepository.save(token);
	}

	@Override
	@Transactional
	public void invalidateAllValidForUser(User user) { 
		TokenSpecification ts = new TokenSpecification(user);
		List<Token> tokens = tokenRepository.findAll(ts);
		for (Token token : tokens) {
			token.setValid(false);
			tokenRepository.save(token);
		}
	}

}
