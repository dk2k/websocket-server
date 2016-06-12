package ru.outofrange.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JsonMessage implements Serializable {
	
	private static final long serialVersionUID = 995269108415593695L;

	private Long id;

	private MessageType type;
	private Session session;
	private String sequence_id; 
	
	private Map<String, String> data = new HashMap<String, String>();
	
	public JsonMessage(){
		
	}
	
	public JsonMessage(JsonMessageWrapper jmw){
		this.data = jmw.getData();
		this.type = jmw.getType();
		this.sequence_id = jmw.getSequence_id();
	}
	
	public JsonMessage(MessageType type, String sequence_id, Session session, Map<String, String> data){
		this.type = type;
		this.session = session;
		this.data = data;
		this.sequence_id = sequence_id;
	}
	
	
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		sb.append(type + " " + session.getSessionId() + " " + sequence_id + " data: ");
		
		if (data != null) {
			for (Map.Entry<String, String> e: data.entrySet()){
				sb.append(e.getKey() + " " + e.getValue() + "; ");
			}
		} else {
			sb.append("NULL");
		}
		return ( sb.toString() );
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getSequence_id() {
		return sequence_id;
	}

	public void setSequence_id(String sequence_id) {
		this.sequence_id = sequence_id;
	}

	
}
