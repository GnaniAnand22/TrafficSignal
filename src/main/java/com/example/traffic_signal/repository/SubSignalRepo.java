package com.example.traffic_signal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.traffic_signal.module.SubSignal;

@Repository
public interface SubSignalRepo extends JpaRepository<SubSignal, Long>{

	List<SubSignal> findBySignalId(long signalId);
	
	//public Object getSignalById(long signalId);

}
