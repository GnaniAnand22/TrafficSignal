package com.example.traffic_signal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.traffic_signal.module.UserInfo;
import com.example.traffic_signal.response.ResponseHandler;
import com.example.traffic_signal.services.UserInfoService;

@RestController
@RequestMapping("/user_info")
public class UserInfoController {
	
	@Autowired
	UserInfoService userInfoService;
	
	@PostMapping("/register")
	public ResponseEntity<Object> createUserInfo(@RequestBody UserInfo userInfo){
		userInfoService.createUser(userInfo);
		
		return ResponseHandler.responseBuilder("User "+userInfo.getUserName()+" is created",
												HttpStatus.CREATED,userInfo);
	}
	
	@GetMapping("/users")
	public List<UserInfo>  getAllusers(){
		return userInfoService.getAllUsers();
	}
}
