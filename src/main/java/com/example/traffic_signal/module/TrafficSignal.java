package com.example.traffic_signal.module;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Traffic_Signal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrafficSignal {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "traffic_signal_id")
	private long trafficSignalId;
	
	private String location;
	
	@Column(name = "cycle_duration")
	private String cycleDuration;

	private String state;
	
	@OneToMany(mappedBy = "trafficSignal",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<SubSignal> subSignals;
	
	

}
