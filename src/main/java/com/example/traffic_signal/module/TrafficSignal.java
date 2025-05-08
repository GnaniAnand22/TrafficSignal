package com.example.traffic_signal.module;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Traffic Signal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrafficSignal {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long signalId;
	
	private String location;
	
	@Column(name = "cycle_duration")
	private String cycleDuration;

	private String state;
	
	

}
