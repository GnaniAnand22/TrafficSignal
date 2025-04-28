package com.example.traffic_signal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.traffic_signal.module.TrafficSignal;
import com.example.traffic_signal.services.TrafficSignalService;

@RestController
@RequestMapping("/signals")
public class TrafficSignalController {
	
	@Autowired
	TrafficSignalService trafficSignalService;
	
	@GetMapping("/getallsignals")
	public List<TrafficSignal> getAllSignals(){
		return trafficSignalService.getAllSignals();
	}
	
	@PostMapping("/addsignal")
	public TrafficSignal createSignal(@RequestBody TrafficSignal trafficSignal){
		
		return trafficSignalService.createSignal(trafficSignal);
		
	}

}
