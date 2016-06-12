package ru.outofrange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ru.outofrange.model.JsonMessage;

public interface MessageRepository extends JpaRepository<JsonMessage, Long>, JpaSpecificationExecutor<JsonMessage> {
	 
}
