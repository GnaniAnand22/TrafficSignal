package com.example.traffic_signal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.traffic_signal.module.TrafficSignal;
import com.example.traffic_signal.response.ResponseHandler;
import com.example.traffic_signal.services.TrafficSignalService;

@RestController
@RequestMapping("/signals")
public class TrafficSignalController {
	
	@Autowired
	TrafficSignalService trafficSignalService;	

	public TrafficSignalController(TrafficSignalService trafficSignalService) {
		super();
		this.trafficSignalService = trafficSignalService;
	}

	@GetMapping("/getallsignals")
	public ResponseEntity<List<TrafficSignal>> getAllSignals(){
		return ResponseEntity.ok( trafficSignalService.getAllSignals());
	}
	
	@PostMapping("/addsignal")
	public ResponseEntity<Object> createSignal(@RequestBody TrafficSignal trafficSignal){
		
		String signal=trafficSignalService.createSignal(trafficSignal);
				
		return  ResponseHandler.responseBuilder("Signal Created", HttpStatus.CREATED, signal);
	}
	
	  @GetMapping("/{signalId}") 
	  public ResponseEntity<Object> getSignalById(@PathVariable long signalId){ 
		  
		  Optional<TrafficSignal>signal=trafficSignalService.getSignalsById(signalId); 
		  if(signal.isPresent()){ 
			  	return ResponseHandler.responseBuilder("Signal FOUND", HttpStatus.OK, signal.get()); 
		  }else 
				return ResponseHandler.responseBuilder("Signal NOT FOUND", HttpStatus.NOT_FOUND, null); 
		  }
	  
	  @PutMapping("/update/{signalId}")
	  public ResponseEntity<Object> updateSignal(@PathVariable long signalId,@RequestBody TrafficSignal signal){
		  boolean isUpdate=trafficSignalService.updateSignal(signalId, signal);
		  if(isUpdate) {
			  return ResponseHandler.responseBuilder("Signal Update Successful", HttpStatus.OK, "Signal "+signalId+" Updated");
		  }
		  return ResponseHandler.responseBuilder("Signal Update Unsuccessful",HttpStatus.NOT_FOUND,"Signal "+signalId+" Not Updated");
	  }
	  
	  @DeleteMapping("/delete/{signalId}")
	  public ResponseEntity<Object> deleteSignalById(@PathVariable long signalId){
		  
		  	boolean isDelete=trafficSignalService.deleteSignalById(signalId);
		  	if(isDelete)
		  	return ResponseHandler.responseBuilder("signal "+signalId+" is deleted", HttpStatus.OK, "signal  is deleted");
		  	else {
			  return ResponseHandler.responseBuilder("Signal NOT FOUND", HttpStatus.NOT_FOUND, "Signal Not Found");
		  }
}
	  @DeleteMapping("/deleteall")
	  public ResponseEntity<Object> deleteAll() {
		  trafficSignalService.deleteSignals();
		  return ResponseHandler.responseBuilder("Delete All", HttpStatus.OK, "All Signals Deleted");
	  }
}
