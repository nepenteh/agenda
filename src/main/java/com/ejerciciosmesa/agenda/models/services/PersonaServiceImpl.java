package com.ejerciciosmesa.agenda.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.ejerciciosmesa.agenda.models.dao.PersonaDAO;
import com.ejerciciosmesa.agenda.models.entities.Persona;


@Service
public class PersonaServiceImpl implements PersonaService {

	private final PersonaDAO personaDAO;
	
	
	
	public PersonaServiceImpl(
			
			PersonaDAO personaDAO
			) {
		
		this.personaDAO = personaDAO;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Persona> findAll() {
		return (List<Persona>) personaDAO.findAll();
	}
	
	@Transactional(readOnly=true)
	@Override
	public Page<Persona> findAll(Pageable pageable) {
		return personaDAO.findAll(pageable);
	}

	@Transactional(readOnly=true)
	@Override
	public Persona findOne(Long id) {
		return personaDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void save(Persona persona) {
		personaDAO.save(persona);
	}

	@Transactional
	@Override
	public void remove(Long id) {
		personaDAO.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Long count() {
		return personaDAO.count();
	}
	
	
	
	
}
