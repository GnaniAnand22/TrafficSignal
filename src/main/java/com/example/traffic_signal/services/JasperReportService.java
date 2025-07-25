package com.example.traffic_signal.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.traffic_signal.module.SubSignal;
import com.example.traffic_signal.module.TrafficSignal;
import com.example.traffic_signal.repository.SubSignalRepo;
import com.example.traffic_signal.repository.TrafficSignalrepo;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JasperReportService {
	
	 @Autowired
	    private TrafficSignalrepo trafficSignalrepo;
	 
	 	@Autowired
	 	private SubSignalRepo subSignalRepo;
	  
	  
	    public void exportJasperReport(HttpServletResponse response) throws JRException, IOException {
	        List<TrafficSignal> signals = trafficSignalrepo.findAll();
	        
	        for(TrafficSignal signal : signals) {
	        	List<SubSignal> subsignals= subSignalRepo.getSignalById(signal.getTrafficSignalId());
	        	signal.setSubSignals(subsignals);
	        }
	       
	        //Get file and compile it
	        File file = ResourceUtils.getFile("classpath:Traffic_Signal_Report.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(signals);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "TG Police");
	      
	        //Fill Jasper report
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	       
	        //Export report
	        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
	    }

}
