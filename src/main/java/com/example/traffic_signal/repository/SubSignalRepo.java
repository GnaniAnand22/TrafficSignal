package com.example.traffic_signal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.traffic_signal.module.SubSignal;

public interface SubSignalRepo extends JpaRepository<SubSignal, Long>{

}
