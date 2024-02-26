package com.helloworld.paginacion_servicios_rest.controllers;

import com.helloworld.paginacion_servicios_rest.models.Pagination;
import com.helloworld.paginacion_servicios_rest.models.Product;
import com.helloworld.paginacion_servicios_rest.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/shop")
public class ShopController {
	@Autowired
	ProductsRepository repository;
	
	// Response sin datos adicionales
	@CrossOrigin(origins = "*")
	@GetMapping("/products-con-querystring")
	public @ResponseBody ResponseEntity<List<Product>> getProductsConQueryString(@RequestParam("page") Integer pageNumber, 
																   @RequestParam("size") Integer pageSize){
		HttpHeaders headers = new HttpHeaders();

		Pagination pagination = new Pagination(pageNumber, pageSize, null);
		List<Product> productos = repository.obtenerProductos(pagination);

		return ResponseEntity.status(HttpStatus.OK)
							 .headers(headers)
							 .body(productos);
	}

	// Response sin datos adicionales
	@GetMapping("/products-con-headers")
	public @ResponseBody ResponseEntity<List<Product>> getProductsConHeaders(@RequestHeader("x-pagination-page") Integer pageNumber, 
																   @RequestHeader("x-pagination-size") Integer pageSize){
		HttpHeaders headers = new HttpHeaders();

		Pagination pagination = new Pagination(pageNumber, pageSize, null);
		List<Product> productos = repository.obtenerProductos(pagination);

		return ResponseEntity.status(HttpStatus.OK)
							 .headers(headers)
							 .body(productos);
	}
	
	// Request con headers
	@GetMapping("/products-con-headers-en-response")
	public @ResponseBody ResponseEntity<List<Product>> getProductsResponseConHeaders(@RequestHeader("x-pagination-page") Integer pageNumber, 
																   @RequestHeader("x-pagination-size") Integer pageSize){
		HttpHeaders headers = new HttpHeaders();
		headers.add("x-current-page", pageNumber.toString());
		headers.add("x-items-per-page", pageSize.toString());

		Pagination pagination = new Pagination(pageNumber, pageSize, null);
		List<Product> productos = repository.obtenerProductos(pagination);
		
		headers.add("x-total-results", pagination.getTotalResult().toString());

		return ResponseEntity.status(HttpStatus.OK)
							 .headers(headers)
							 .body(productos);
	}
}
