package com.helloworld.paginacion_servicios_rest.repositories;

import com.helloworld.paginacion_servicios_rest.models.Pagination;
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
	
	public List<Product> obtenerProductos(Pagination pagination){
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM " + TABLE);
		query.append(" ORDER BY ID ASC ");
		query.append(aniadirPaginacion(pagination));
		
		pagination.setTotalResult(getTotalResults());
		
		return jdbcTemplate.query(query.toString(), (rs,rowNum) -> {
			Product product = new Product();
			
			product.setId(rs.getInt("ID"));
			product.setNombre(rs.getString("NAME"));
			product.setPrecio(rs.getDouble("PRICE"));
			
			return product;
		});
	}

	private int getTotalResults() {
		return jdbcTemplate.queryForObject("select count(*) from products", null, Integer.class);
	}

	private String aniadirPaginacion(Pagination pagination) {
		if(pagination.getPageSize() == null){
			return "";
		}
	
		int numRowStart = 0;
		if(pagination.getPageSize() != 0){
			numRowStart = (pagination.getPageNumber() - 1) * pagination.getPageSize();
		}
		
		return " LIMIT " + numRowStart + ", " + pagination.getPageSize();
	}
}
