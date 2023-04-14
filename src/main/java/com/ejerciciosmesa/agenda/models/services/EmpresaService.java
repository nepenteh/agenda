package com.ejerciciosmesa.agenda.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import com.ejerciciosmesa.agenda.models.entities.Empresa;

public interface EmpresaService {
	
	public List<Empresa> findAll();
	public Page<Empresa> findAll(Pageable pageable);
	public Empresa findOne(Long id);
	public void save(Empresa empresa);
	public void remove(Long id);
	public Long count();
	
	
	
}
