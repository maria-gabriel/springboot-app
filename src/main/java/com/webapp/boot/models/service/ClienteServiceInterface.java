package com.webapp.boot.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webapp.boot.models.entity.Cliente;

public interface ClienteServiceInterface {

	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public Cliente findOne(Long id);
	
	public void save(Cliente cliente);
	
	public void delete(Long id);
	
}
