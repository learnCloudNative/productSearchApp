package net.containercrush.enceladus.productsearchapp;

import javax.sql.DataSource;
import org.hibernate.Query;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductsearchappApplication {

	@Autowired
	private DataSource dataSource;
	
	public static void main(String[] args) {
		SpringApplication.run(ProductsearchappApplication.class, args);
		
	}

	
	


}
