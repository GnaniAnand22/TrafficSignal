package com.example.traffic_signal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.traffic_signal.module.TrafficSignal;
import com.example.traffic_signal.repository.TrafficSignalrepo;

@Service
public class TrafficSignalService  {
	
	@Autowired
	TrafficSignalrepo trafficSignalrepo;
	
	public List<TrafficSignal> getAllSignals(){
		return trafficSignalrepo.findAll();
	}
	
	public String createSignal(TrafficSignal trafficSignal) {
		trafficSignalrepo.save(trafficSignal);
		return "signal created";
	}
	
	public Optional<TrafficSignal>  getSignalsById(long signalId) {
		Optional<TrafficSignal>  signal= trafficSignalrepo.findById(signalId);
		return signal;
	}
	
	public boolean updateSignal(long signalId,TrafficSignal signal) {
		Optional<TrafficSignal> signals=trafficSignalrepo.findById(signalId);
		if(signals.isPresent() && signal.getTrafficSignalId()==signalId) {
			trafficSignalrepo.save(signal);
			return true;
		}
		return false;
		
	}
	
	public boolean deleteSignalById(long signalId) {
		Optional< TrafficSignal> signal=trafficSignalrepo.findById(signalId);
		if(signal.isPresent()) {
			 trafficSignalrepo.deleteById(signalId);
			return true; 
		}
		return false;
	}
	public void deleteSignals() {
		trafficSignalrepo.deleteAll();
	}

}
