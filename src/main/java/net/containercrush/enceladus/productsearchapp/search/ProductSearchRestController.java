package net.containercrush.enceladus.productsearchapp.search;


import java.util.ArrayList;
import java.util.List;

import net.containercrush.enceladus.productsearchapp.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ProductSearchRestController {

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

    }


