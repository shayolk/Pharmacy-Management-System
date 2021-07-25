package com.adminportal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.Med;
import com.adminportal.repository.MedRepository;
import com.adminportal.service.MedService;

@Service
public class MedServiceImpl implements MedService{
	
	@Autowired
	private MedRepository medRepository;
	
	public Med save(Med med) {
		return medRepository.save(med);
	}
	
	public List<Med> findAll() {
		return (List<Med>) medRepository.findAll();
	}
	
	public Med findOne(Long id) {
		return medRepository.findById(id).get();
	}
	
	public void removeOne(Long id) {
		medRepository.deleteById(id);
	}
}
