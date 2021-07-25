package com.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.domain.Med;
import com.adminportal.service.MedService;

@Controller
@RequestMapping("/med")
public class MedController {

	@Autowired
	private MedService medService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addMed(Model model) {
		Med med = new Med();
		model.addAttribute("med", med);
		return "addMed";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addMedPost(@ModelAttribute("med") Med med, HttpServletRequest request) {
		medService.save(med);

		MultipartFile medImage = med.getMedImage();

		try {
			byte[] bytes = medImage.getBytes();
			String name = med.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/med/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:medList";
	}
	
	@RequestMapping("/medInfo")
	public String medInfo(@RequestParam("id") Long id, Model model) {
		Med med = medService.findOne(id);
		model.addAttribute("med", med);
		
		return "medInfo";
	}
	
	@RequestMapping("/updateMed")
	public String updateMed(@RequestParam("id") Long id, Model model) {
		Med med = medService.findOne(id);
		model.addAttribute("med", med);
		
		return "updateMed";
	}
	
	@RequestMapping(value="/updateMed", method=RequestMethod.POST)
	public String updateMedPost(@ModelAttribute("med") Med med, HttpServletRequest request) {
		medService.save(med);
		
		MultipartFile medImage = med.getMedImage();
		
		if(!medImage.isEmpty()) {
			try {
				byte[] bytes = medImage.getBytes();
				String name = med.getId() + ".png";
				
				Files.delete(Paths.get("src/main/resources/static/image/med/"+name));
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/med/" + name)));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/med/medInfo?id="+med.getId();
	}
	
	@RequestMapping("/medList")
	public String medList(Model model) {
		List<Med> medList = medService.findAll();
		model.addAttribute("medList", medList);		
		return "medList";
		
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model
			) {
		medService.removeOne(Long.parseLong(id.substring(7)));
		List<Med> medList = medService.findAll();
		model.addAttribute("medList", medList);
		
		return "redirect:/med/medList";
	}

}
