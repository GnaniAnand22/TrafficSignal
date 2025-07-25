package com.example.traffic_signal.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.traffic_signal.module.TrafficSignal;
import com.example.traffic_signal.response.ResponseHandler;
import com.example.traffic_signal.services.JasperReportService;
import com.example.traffic_signal.services.TrafficSignalService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

@RestController
@EnableMethodSecurity
@RequestMapping("/signals")
public class TrafficSignalController {

	@Autowired
	TrafficSignalService trafficSignalService;

	@Autowired
	JasperReportService jasperReportService;

	public TrafficSignalController(TrafficSignalService trafficSignalService) {
		super();
		this.trafficSignalService = trafficSignalService;
	}

	@GetMapping("/getallsignals")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<TrafficSignal>> getAllSignals() {
		return ResponseEntity.ok(trafficSignalService.getAllSignals());
	}

	@PostMapping("/addsignal")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> createSignal(@RequestBody TrafficSignal trafficSignal) {
		
		if(trafficSignal.getSubSignals() != null) {
			trafficSignal.getSubSignals().forEach(sub -> sub.setTrafficSignal(trafficSignal));
		}

		String signal = trafficSignalService.createSignal(trafficSignal);

		return ResponseHandler.responseBuilder("Signal Created", HttpStatus.CREATED, signal);
	}

	@GetMapping("/{signalId}")
	@PreAuthorize("hasRole('USER','ADMIN')")
	public ResponseEntity<Object> getSignalById(@PathVariable long signalId) {

		Optional<TrafficSignal> signal = trafficSignalService.getSignalsById(signalId);
		if (signal.isPresent()) {
			return ResponseHandler.responseBuilder("Signal FOUND", HttpStatus.OK, signal.get());
			
		} else
			return ResponseHandler.responseBuilder("Signal NOT FOUND", HttpStatus.NOT_FOUND, null);
	}

	@PutMapping("/update/{signalId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> updateSignal(@PathVariable long signalId, @RequestBody TrafficSignal signal) {
		boolean isUpdate = trafficSignalService.updateSignal(signalId, signal);
		if (isUpdate) {
			return ResponseHandler.responseBuilder("Signal Update Successful", HttpStatus.OK,
					"Signal " + signalId + " Updated");
		}
		return ResponseHandler.responseBuilder("Signal Update Unsuccessful", HttpStatus.NOT_FOUND,
				"Signal " + signalId + " Not Updated");
	}

	@DeleteMapping("/{signalId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteSignalById(@PathVariable long signalId) {

		boolean isDelete = trafficSignalService.deleteSignalById(signalId);
		if (isDelete)
			return ResponseHandler.responseBuilder("signal " + signalId + " is deleted", HttpStatus.OK,
					"signal  is deleted");
		else {
			return ResponseHandler.responseBuilder("Signal NOT FOUND", HttpStatus.NOT_FOUND, "Signal Not Found");
		}
	}

	@DeleteMapping("/deleteall")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteAll() {
		trafficSignalService.deleteSignals();
		return ResponseHandler.responseBuilder("Delete All", HttpStatus.OK, "All Signals Deleted");
	}

	@GetMapping(value = "/pdf_report" , produces = MediaType.APPLICATION_PDF_VALUE)
	//@PreAuthorize("hasRole(ADMIN)")
	public ResponseEntity<byte[]> createPDF(HttpServletResponse response)  throws IOException, JRException  {
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		
		byte[] pdfbytes=baos.toByteArray();
		
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
  
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=traffic_report_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
  
        jasperReportService.exportJasperReport(response);
		return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + headerValue)
	            .contentType(MediaType.APPLICATION_PDF)
	            .body(pdfbytes);
    }
}
