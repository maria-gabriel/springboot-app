package com.webapp.boot.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.boot.models.entity.Cliente;

public interface ClienteDaoInterface extends JpaRepository<Cliente, Long> {

	
}
