package com.kyungmin.exampleRest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse { //에러 메시지 클래스
	
	private String errorCode;
	private String errorMsg;
	private String requestURL;

}
