package com.medico.service;

import java.util.List;

import com.medico.domain.Med;

public interface MedService {
	List<Med> findAll ();
	
	Med findOne(Long id);
	
	List<Med> findByCategory(String category);
	
	List<Med> blurrySearch(String title);
}
