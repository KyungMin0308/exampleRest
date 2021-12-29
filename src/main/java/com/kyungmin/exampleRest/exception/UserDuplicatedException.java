package com.kyungmin.exampleRest.exception;

public class UserDuplicatedException extends RuntimeException {

	private static final long serialVersionUID = 1556130294716808470L;
	
	private String userName;

	//생성자
	public UserDuplicatedException(String userName) {
		super();
		this.userName = userName;
	}
	
	//Getter
	public String getUserName() {
		return userName;
	}
	
}
