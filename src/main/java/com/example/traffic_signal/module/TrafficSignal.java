package com.example.traffic_signal.module;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Traffic Signal")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrafficSignal {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "signal_id")
	public int signalId;
	
	@Column(name = "location")
	public String location;
	
	@Column(name = "cycle_Duration")
	public String cycleDuration;
	
	@Column(name = "state")
	public String state;
	
	

}
