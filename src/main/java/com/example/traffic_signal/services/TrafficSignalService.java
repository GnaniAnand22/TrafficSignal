package com.example.traffic_signal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.traffic_signal.TrafficSignalApplication;
import com.example.traffic_signal.module.TrafficSignal;
import com.example.traffic_signal.repository.TrafficSignalrepo;

@Service
public class TrafficSignalService  {
	
	@Autowired
	TrafficSignalrepo trafficSignalrepo;
	
	public List<TrafficSignal> getAllSignals(){
		return trafficSignalrepo.findAll();
	}
	
	public TrafficSignal createSignal(TrafficSignal newSignal) {
		return trafficSignalrepo.save(newSignal);
	}
	
	public TrafficSignal getSignalsById(int signalId) {
		return trafficSignalrepo.findById(signalId).get();
	}
	
	
	public TrafficSignal updateSignal(int signalId,TrafficSignal traffic_Signal) {
		return trafficSignalrepo.save(traffic_Signal);
	}
	
	public void deleteSignalById(int signalId) {
		trafficSignalrepo.deleteById(signalId);
	}
	public void deleteSignals() {
		trafficSignalrepo.deleteAll();
	}

}
