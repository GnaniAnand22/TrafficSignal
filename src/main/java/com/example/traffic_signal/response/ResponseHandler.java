package com.example.traffic_signal.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	
	public static ResponseEntity<Object> responseBuilder(String message,HttpStatus httpStatus,Object responseObject){
		
		Map<String, Object> response=new HashMap<>();
		response.put("message", message);
		response.put("HttpStatus", httpStatus);
		response.put("Data", responseObject);
		
		return new ResponseEntity<>(response,httpStatus);
		
	}

}
