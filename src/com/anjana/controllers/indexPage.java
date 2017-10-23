package com.anjana.controllers;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class indexPage {

	@RequestMapping(method = RequestMethod.POST, value = "/index")
	public String Index() {
		
		return "index";
	}
	
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/uploadFilePage")
	public String uploadPage() {
		
		return "UploadPage";
	}
	
}
