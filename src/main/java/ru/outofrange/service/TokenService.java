package ru.outofrange.service;

import ru.outofrange.model.Token;
import ru.outofrange.model.User;

public interface TokenService {

	public void save(Token token);

	public void invalidateAllValidForUser(User user);

}
