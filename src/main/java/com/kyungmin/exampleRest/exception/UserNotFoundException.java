package com.kyungmin.exampleRest.exception;

public class UserNotFoundException extends RuntimeException {

	//Serialization Version이 일치해야하기 때문에
	private static final long serialVersionUID = 3814410352408941726L;
	
	private long userId;

	//생성자
	public UserNotFoundException(long userId) {
		super();
		this.userId = userId;
	}
	
	//Getter
	public long getUserId() {
		return userId;
	}
	
}
