package ru.outofrange.service;

import java.util.List;

import ru.outofrange.exc.SessionNotFoundException;
import ru.outofrange.model.Session;

public interface SessionService {

	public Session getSessionByString(String sessionId);
	public Session create(Session user);
	public Session delete(long id) throws SessionNotFoundException;
	public List<Session> findAll();
	public Session update(Session session)
			throws SessionNotFoundException;
	public Session findById(long id);
	public void save(Session session);
}
