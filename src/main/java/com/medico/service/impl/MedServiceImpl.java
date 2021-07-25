package com.medico.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medico.domain.Med;
import com.medico.repository.MedRepository;
import com.medico.service.MedService;

@Service
public class MedServiceImpl implements MedService{
	@Autowired
	private MedRepository medRepository;
	
	public List<Med> findAll() {
		List<Med> medList = (List<Med>) medRepository.findAll();
		List<Med> activeMedList = new ArrayList<>();
		
		for (Med med: medList) {
			if(med.isActive()) {
				activeMedList.add(med);
			}
		}
		
		return activeMedList;
	}
	
	public Med findOne(Long id) {
		return medRepository.findById(id).get();
	}

	public List<Med> findByCategory(String category){
		List<Med> medList = medRepository.findByCategory(category);
		
		List<Med> activeMedList = new ArrayList<>();
		
		for (Med med: medList) {
			if(med.isActive()) {
				activeMedList.add(med);
			}
		}
		
		return activeMedList;
	}
	
	public List<Med> blurrySearch(String title) {
		List<Med> medList = medRepository.findByTitleContaining(title);
List<Med> activeMedList = new ArrayList<>();
		
		for (Med med: medList) {
			if(med.isActive()) {
				activeMedList.add(med);
			}
		}
		
		return activeMedList;
	}
}
