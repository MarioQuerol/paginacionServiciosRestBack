package com.helloworld.paginacion_servicios_rest.repositories;

import com.helloworld.paginacion_servicios_rest.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String TABLE = "PRODUCTS";
	
	public List<Product> obtenerProductos(){
		return jdbcTemplate.query("SELECT * FROM " + TABLE, (rs,rowNum) -> {
			Product product = new Product();
			
			product.setId(rs.getInt("ID"));
			product.setNombre(rs.getString("NAME"));
			product.setPrecio(rs.getDouble("PRICE"));
			
			return product;
		});
	} 
}
