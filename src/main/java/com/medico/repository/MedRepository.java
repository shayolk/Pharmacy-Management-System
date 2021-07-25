package com.medico.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.medico.domain.Med;

public interface MedRepository extends CrudRepository<Med, Long>{
	List<Med> findByCategory(String category);
	
	List<Med> findByTitleContaining(String title);
}
