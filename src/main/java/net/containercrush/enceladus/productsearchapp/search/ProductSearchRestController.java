package net.containercrush.enceladus.productsearchapp.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.containercrush.enceladus.productsearchapp.model.Product;
import net.containercrush.enceladus.productsearchapp.model.ProductFamily;

@RestController
@RequestMapping("/api")
public class ProductSearchRestController {

    @Autowired
    DataSource dataSource;

    @GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sayHello() {
        // Get data from service layer into entityList.

        List<ProductFamily> entities = new ArrayList<ProductFamily>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling

        Connection con = null ;
        Statement stmt = null ;

        try {

            String sql = "select distinct(family), family_name from XXIBM_PRODUCT_CATALOGUE";
            con = dataSource.getConnection();
            System.out.println("Database connection obtained : " + con) ;
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                
                ProductFamily productFamily = new ProductFamily() ;
                productFamily.setFamilyID(rs.getString("family"));
                productFamily.setFamilyName(rs.getString("family_name"));
                entities.add(productFamily);

			}

        } catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
                stmt.close();
                con.close(); 
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }

}
