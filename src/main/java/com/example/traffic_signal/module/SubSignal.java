package com.example.traffic_signal.module;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="Sub_Signal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubSignal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "signal_id")
	private long signalId;
	
	private String type;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "traffic_signal_id")
	private TrafficSignal trafficSignal;

}
