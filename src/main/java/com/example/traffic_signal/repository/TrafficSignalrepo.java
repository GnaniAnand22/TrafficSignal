package com.example.traffic_signal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.traffic_signal.module.TrafficSignal;

@Repository
public interface TrafficSignalrepo extends JpaRepository<TrafficSignal, Long> {

}
