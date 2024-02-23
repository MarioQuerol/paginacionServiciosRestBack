package com.helloworld.paginacion_servicios_rest.controllers;

import com.helloworld.paginacion_servicios_rest.models.Product;
import com.helloworld.paginacion_servicios_rest.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
	@Autowired
	ProductsRepository repository;
	
	@GetMapping("/products")
	public @ResponseBody ResponseEntity<List<Product>> getProducts(){
		HttpHeaders headers = new HttpHeaders();
		
		List<Product> productos = repository.obtenerProductos();

		return ResponseEntity.status(HttpStatus.OK)
							 .headers(headers)
							 .body(productos);
	}
}
