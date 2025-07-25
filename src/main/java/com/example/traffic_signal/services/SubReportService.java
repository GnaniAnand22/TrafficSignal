package com.example.traffic_signal.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.traffic_signal.module.SubSignal;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class SubReportService {
	
	public void generateSubSignalReport(List<SubSignal> subSignalList ) throws Exception {
		
		JasperReport report = JasperCompileManager.compileReport("src/main/resources/reports/Sub_Signal_Report.jrxml");
		
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(subSignalList);
		
		Map<String, Object> parameters = new HashMap<>();
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, datasource);
		
		JasperExportManager.exportReportToPdfFile(jasperPrint,"subSignalReport.pdf");
		
		System.out.println("Sub Signal PDF Generated");
	}

}
