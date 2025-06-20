package com.example.traffic_signal.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.traffic_signal.mapper.UserInfoUserDetailMapper;
import com.example.traffic_signal.module.UserInfo;
import com.example.traffic_signal.repository.UserInfoRepository;

@Service
public class UserInfoUserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userinfo=userInfoRepository.findByUserName(username);
		
		return userinfo.map(UserInfoUserDetailMapper::new)
				.orElseThrow(() -> new UsernameNotFoundException("User "+username+" is not found"));
	}

}
