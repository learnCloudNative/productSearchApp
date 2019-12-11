package net.containercrush.enceladus.productsearchapp.search;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.containercrush.enceladus.productsearchapp.model.Product;
import net.containercrush.enceladus.productsearchapp.model.ProductCatalogue;
import net.containercrush.enceladus.productsearchapp.service.ProductCatalogueSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ProductSearchRestController {
	
	

	 @Autowired
	 private ProductCatalogueSearchService productCatalogueSearchService;


    @GetMapping(path = "/hello", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sayHello()
    {
         //Get data from service layer into entityList.

        List<Product> entities = new ArrayList<Product>();
        Product product = new Product() ;
        product.setName("Zodiac Linen Blue");
        product.setProductMaterial("Linen");
        product.setProductType("Shirt");
        product.setProductColour("Blue");
        entities.add(product);
        
        product = new Product() ;
        product.setName("Grey Cotton Trouser");
        product.setProductMaterial("Cotton");
        product.setProductType("Touser");
        product.setProductColour("Grey");
        entities.add(product);
        
        
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
    

	@GetMapping(path = "/getProductDetailsCommodity/{commodityName}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProductDetailsByCommodityName(@PathVariable String commodityName) throws SQLException
    {
         //Get data from service layer into entityList.
		List<ProductCatalogue> entities = new ArrayList<ProductCatalogue>();
		ProductCatalogue prodCat=productCatalogueSearchService.getProdCatalogueDetByCommName(commodityName);
		entities.add(prodCat);
        
        
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
	
	
	
	@GetMapping(path = "/getProductDetailsFamily/{familyName}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProductDetailsByFamilyName(@PathVariable String familyName) throws SQLException
    {
         //Get data from service layer into entityList.
		List<ProductCatalogue> entities = new ArrayList<ProductCatalogue>();
		List<ProductCatalogue> prodCat=productCatalogueSearchService.getProdCatalogueDetByFamilyName(familyName);
		
		for(ProductCatalogue prod: prodCat)
		{
			entities.add(prod);
		}
	
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
	

    

    }


