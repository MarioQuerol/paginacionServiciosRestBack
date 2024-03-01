package com.helloworld.paginacion_servicios_rest.repositories;

import com.helloworld.paginacion_servicios_rest.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepositoryJPA extends JpaRepository<Product, Long> {
	Page<Product> findAll(Pageable pageable);
}
