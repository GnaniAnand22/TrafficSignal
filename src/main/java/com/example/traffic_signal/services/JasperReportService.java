package com.example.traffic_signal.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
	        	List<SubSignal> subsignals= subSignalRepo.findBySignalId(signal.getTrafficSignalId());
	        	signal.setSubSignals(subsignals);
	        }
	       
	        //Get file and compile it
	        InputStream reportStream = new ClassPathResource("reports/Traffic_Signal_Report.jrxml").getInputStream();
	        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
	       
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(signals);
	       
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "TG Police");
	      
	        //Fill Jasper report
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	       
	        //Export report
	        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
	    }

}
