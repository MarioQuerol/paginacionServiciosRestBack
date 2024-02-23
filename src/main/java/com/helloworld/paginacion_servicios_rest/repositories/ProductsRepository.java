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
		
		
		return jdbcTemplate.query(query.toString(), (rs,rowNum) -> {
			Product product = new Product();
			
			product.setId(rs.getInt("ID"));
			product.setNombre(rs.getString("NAME"));
			product.setPrecio(rs.getDouble("PRICE"));
			
			return product;
		});
	}

	private String aniadirPaginacion(Pagination pagination) {
		if(pagination.getPageSize() == null){
			return "";
		}
		
		/* TODO 
		    Pregunta si recibimos la página como le indicamos que es el registro x, 
		    por ejemplo si recibimos la pag 2 y longitud 5 en LIMIT tendremos que poner LIMIT 6, 5
		    o no?
		 */
		return " LIMIT " + pagination.getPageNumber() + ", " + pagination.getPageSize();
	}
}
