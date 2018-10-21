package com.foliage.api.common;

public class Info<T> {
	
	private boolean status;
	
	private String description;
	
	private T subject;
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public T getSubject() {
		return subject;
	}
	
	public void setSubject(T subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return "{'status':" + this.status + ", 'description': " + this.description + ", 'subject': " + this.subject.toString() + "}";
	}
}
