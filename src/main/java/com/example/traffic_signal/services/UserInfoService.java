package com.example.traffic_signal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.traffic_signal.module.UserInfo;
import com.example.traffic_signal.repository.UserInfoRepository;

@Service
public class UserInfoService {
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	public String createUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfoRepository.save(userInfo);
		
		return "User Created";
	}

}
