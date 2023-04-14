package com.ejerciciosmesa.agenda.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.ejerciciosmesa.agenda.models.dao.EmpresaDAO;
import com.ejerciciosmesa.agenda.models.entities.Empresa;


@Service
public class EmpresaServiceImpl implements EmpresaService {

	private final EmpresaDAO empresaDAO;
	
	
	
	public EmpresaServiceImpl(
			
			EmpresaDAO empresaDAO
			) {
		
		this.empresaDAO = empresaDAO;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Empresa> findAll() {
		return (List<Empresa>) empresaDAO.findAll();
	}
	
	@Transactional(readOnly=true)
	@Override
	public Page<Empresa> findAll(Pageable pageable) {
		return empresaDAO.findAll(pageable);
	}

	@Transactional(readOnly=true)
	@Override
	public Empresa findOne(Long id) {
		return empresaDAO.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void save(Empresa empresa) {
		empresaDAO.save(empresa);
	}

	@Transactional
	@Override
	public void remove(Long id) {
		empresaDAO.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Long count() {
		return empresaDAO.count();
	}
	
	
	
	
}
