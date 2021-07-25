package com.adminportal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adminportal.service.MedService;

@RestController
public class ResourceController {

	@Autowired
	private MedService medService;
	
	@RequestMapping(value="/med/removeList", method=RequestMethod.POST)
	public String removeList(
			@RequestBody ArrayList<String> medIdList, Model model
			){
		
		for (String id : medIdList) {
			String medId =id.substring(8);
			medService.removeOne(Long.parseLong(medId));
		}
		
		return "delete success";
	}
}
