package ru.outofrange.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.outofrange.model.User;
import ru.outofrange.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Resource 
    private UserRepository userRepository;

	@Override
	@Transactional
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	
	
}
