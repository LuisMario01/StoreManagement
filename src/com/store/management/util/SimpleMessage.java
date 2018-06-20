package com.store.management.util;

public class SimpleMessage {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public class SimpleMessageBuilder{
		private String message;
		
		public SimpleMessageBuilder() {
			
		}
		
		public SimpleMessageBuilder message(String messsage) {
			this.message = message;
			return this;
		}
	}
	
}
