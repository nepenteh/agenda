package com.ejerciciosmesa.agenda.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import com.ejerciciosmesa.agenda.models.entities.Persona;

public interface PersonaService {
	
	public List<Persona> findAll();
	public Page<Persona> findAll(Pageable pageable);
	public Persona findOne(Long id);
	public void save(Persona persona);
	public void remove(Long id);
	public Long count();
	
	
	
}
