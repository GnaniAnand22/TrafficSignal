package com.example.traffic_signal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {
	
	@GetMapping("/ui")
	public String uiRedirect() {
		return "redirect:/traffic-signal-ui.html";
	}

}
