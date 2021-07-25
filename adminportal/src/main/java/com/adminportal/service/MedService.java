package com.adminportal.service;

import java.util.List;

import com.adminportal.domain.Med;

public interface MedService {
	
	Med save(Med med);

	List<Med> findAll();
	
	Med findOne(Long id);
	
	void removeOne(Long id);
}
