package ru.outofrange.model;

import java.util.Map;

public class JsonMessageWrapper {
	
	private MessageType type;
	private String sequence_id;
	private Map<String, String> data;
	
	public JsonMessageWrapper() {
		
	}
	public JsonMessageWrapper(MessageType type, String sequence_id, Map<String, String> data){
		this.type = type;
		this.sequence_id = sequence_id;
		this.data = data;
	}
	
	public JsonMessageWrapper(JsonMessage jm) {
		this.type = jm.getType();
		this.sequence_id = jm.getSequence_id();
		this.data = jm.getData();
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public String getSequence_id() {
		return sequence_id;
	}
	public void setSequence_id(String sequence_id) {
		this.sequence_id = sequence_id;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		sb.append(type + " " + sequence_id + " data: ");
		
		if (data != null) {
			for (Map.Entry<String, String> e: data.entrySet()){
				sb.append(e.getKey() + " " + e.getValue() + "; ");
			}
		} else {
			sb.append("NULL");
		}
		return ( sb.toString() );
	}
}
