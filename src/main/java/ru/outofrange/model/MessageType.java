package ru.outofrange.model;

public enum MessageType {
	CUSTOMER_API_TOKEN("CUSTOMER_API_TOKEN"),
	LOGIN_CUSTOMER("LOGIN_CUSTOMER"), 
	CUSTOMER_ERROR("CUSTOMER_ERROR")
			;
    
    private String value;
    
    private MessageType (String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}
