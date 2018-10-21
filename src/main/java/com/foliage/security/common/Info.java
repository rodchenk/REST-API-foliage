package com.foliage.security.common;

public class Info {
	
	private boolean status;
	private Object description;
	
	public Info() {}
	
	public Info(boolean pStatus) {
		this.setStatus(pStatus);
	}
	
	public Info(boolean pStatus, String pDescription) {
		this.setStatus(pStatus);
		this.setDescription(pDescription);
	}

	public final boolean getStatus() {
		return this.status;
	}
	
	public final Object getDescription() {
		return this.description;
	}
	
	public final void setStatus(boolean pStatus) {
		this.status = pStatus;
	}
	
	public final void setDescription(Object pDescription) {
		this.description = pDescription;
	}
	
	@Override
	//TODO
	public String toString() {
		return "{\" status\":" + this.status + 
				",\"description\":\"" + this.description + 
				"\"}";
	}
}
