package ru.outofrange.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.outofrange.exc.SessionNotFoundException;
import ru.outofrange.model.Session;
import ru.outofrange.repository.SessionRepository;
import ru.outofrange.specification.SessionSpecification;

@Service
public class SessionServiceImpl implements SessionService {

	@Resource
    private SessionRepository sessionRepository;
	
	@Override
    @Transactional(readOnly = true)
	public Session getSessionByString(String sessionId){
		SessionSpecification spec = new SessionSpecification(sessionId);
		return sessionRepository.findOne(spec);
	}
	
	@Override
    @Transactional
	public Session create(Session session) {
		Session createdSession = session;
	    return sessionRepository.save(createdSession);

	}
	
    @Override
    @Transactional(rollbackFor=SessionNotFoundException.class)
	public Session delete(long id) throws SessionNotFoundException {
        Session deletedSession = sessionRepository.findOne(id);
        
        if (deletedSession == null)
            throw new SessionNotFoundException();
         
        sessionRepository.delete(deletedSession);
        return deletedSession;
	}

    @Override
    @Transactional(readOnly = true)
	public List<Session> findAll() {
		return sessionRepository.findAll();
	}
    
    @Override
    @Transactional(rollbackFor=SessionNotFoundException.class)
	public Session update(Session session)
			throws SessionNotFoundException {
        Session updatedSession = sessionRepository.findOne(session.getId());
        
        if (updatedSession == null)
            throw new SessionNotFoundException();
         
        
        return null;
	}
    
    @Override
    @Transactional
	public Session findById(long id) {
    	return sessionRepository.findOne(id);
	}

	@Override
	@Transactional
	public void save(Session session) {
		sessionRepository.save(session);
	}
}
