package ru.outofrange.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.outofrange.model.JsonMessage;
import ru.outofrange.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Resource
    private MessageRepository messageRepository;
	
	@Override
	@Transactional
	public void save(JsonMessage response) {
		messageRepository.save(response);
	}

}
