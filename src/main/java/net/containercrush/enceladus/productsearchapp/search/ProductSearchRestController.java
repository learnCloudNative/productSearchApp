package net.containercrush.enceladus.productsearchapp.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.containercrush.enceladus.productsearchapp.model.Product;
import net.containercrush.enceladus.productsearchapp.model.ProductCommodity;
import net.containercrush.enceladus.productsearchapp.model.ProductFamily;
@CrossOrigin
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
        System.out.println("Received Request for Product Families...") ;
        System.out.println("Received Request for Product Families (yes it refreshed image)...") ;

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
                productFamily.setTeamName("Enceladus") ;
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
	
	 
    @GetMapping(path = "/commodities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCommodities() {
        // Get data from service layer into entityList.

        List<ProductCommodity> entities = new ArrayList<ProductCommodity>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling
        System.out.println("Received Request for Product Commodities...") ;
        System.out.println("Received Request for Product Commodities (yes it refreshed image)...") ;

        Connection con = null ;
        PreparedStatement prstmt = null;
        try {

            String sql = "select   distinct(commodity_name), commodity ,class, class_name, family, family_name from XXIBM_PRODUCT_CATALOGUE";
            con = dataSource.getConnection();
            System.out.println("Database connection obtained : " + con) ;
            //stmt = con.createStatement();
            prstmt=con.prepareStatement(sql);
            ResultSet rs = prstmt.executeQuery();
            while(rs.next()){
                
                ProductCommodity productCommodity = new ProductCommodity() ;
                productCommodity.setCommodity(rs.getString("commodity"));
                productCommodity.setCommodityName(rs.getString("commodity_name"));
                productCommodity.setClassID(rs.getString("class"));
                productCommodity.setClassName(rs.getString("class_name"));
                productCommodity.setFamily(rs.getString("family"));
                productCommodity.setFamilyName(rs.getString("family_name"));
                productCommodity.setTeamName("Enceladus") ;
                entities.add(productCommodity);

			}

        } catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
            	prstmt.close();
                con.close(); 
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }

    
    
    @GetMapping(path = "/commodities/commoditybyClassID/{classID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCommoditiesByClassID(@PathVariable String classID) {
        // Get data from service layer into entityList.

        List<ProductCommodity> entities = new ArrayList<ProductCommodity>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling
        System.out.println("Received Request for Product Commodities by Class ID...") ;
        System.out.println("Received Request for Product Commodities by Class ID (yes it refreshed image)...") ;

        Connection con = null ;
        PreparedStatement prstmt = null;

        try {

            String sql = "select  distinct(commodity_name), commodity, class, class_name, family, family_name from XXIBM_PRODUCT_CATALOGUE where class = ?";
            con = dataSource.getConnection();
            System.out.println("Database connection obtained : " + con) ;
            //stmt = con.createStatement();
            prstmt=con.prepareStatement(sql);
            prstmt.setString(1, classID);
            ResultSet rs = prstmt.executeQuery();
            while(rs.next()){
                
                ProductCommodity productCommodity = new ProductCommodity() ;
                productCommodity.setCommodity(rs.getString("commodity"));
                productCommodity.setCommodityName(rs.getString("commodity_name"));
                productCommodity.setClassID(rs.getString("class"));
                productCommodity.setClassName(rs.getString("class_name"));
                productCommodity.setFamily(rs.getString("family"));
                productCommodity.setFamilyName(rs.getString("family_name"));
                productCommodity.setTeamName("Enceladus") ;
                entities.add(productCommodity);

			}

        } catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
            	prstmt.close();
                con.close(); 
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }

    @GetMapping(path = "/commodities/commoditybyFamilyID/{familyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCommoditiesByFamilyID(@PathVariable String familyID) {
        // Get data from service layer into entityList.

        List<ProductCommodity> entities = new ArrayList<ProductCommodity>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling
        System.out.println("Received Request for Product Commodities by Class ID...") ;
        System.out.println("Received Request for Product Commodities by Class ID (yes it refreshed image)...") ;

        Connection con = null ;
        PreparedStatement prstmt = null;

        try {

            String sql = "select  distinct(commodity_name), commodity, class, class_name, family, family_name from XXIBM_PRODUCT_CATALOGUE where family = ?";
            con = dataSource.getConnection();
            System.out.println("Database connection obtained : " + con) ;
            //stmt = con.createStatement();
            prstmt=con.prepareStatement(sql);
            prstmt.setString(1, familyID);
            ResultSet rs = prstmt.executeQuery();
            while(rs.next()){
                
                ProductCommodity productCommodity = new ProductCommodity() ;
                productCommodity.setCommodity(rs.getString("commodity"));
                productCommodity.setCommodityName(rs.getString("commodity_name"));
                productCommodity.setClassID(rs.getString("class"));
                productCommodity.setClassName(rs.getString("class_name"));
                productCommodity.setFamily(rs.getString("family"));
                productCommodity.setFamilyName(rs.getString("family_name"));
                productCommodity.setTeamName("Enceladus") ;
                entities.add(productCommodity);

			}

        } catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
            	prstmt.close();
                con.close(); 
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }

    

}
