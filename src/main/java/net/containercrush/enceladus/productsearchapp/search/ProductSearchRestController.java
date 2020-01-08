package net.containercrush.enceladus.productsearchapp.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	 
    /*@GetMapping(path = "/commodities", produces = MediaType.APPLICATION_JSON_VALUE)
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
*/
    
    
    @GetMapping(path = "/commodities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCommodities() {
        // Get data from service layer into entityList.

        List<ProductCommodity> entities = new ArrayList<ProductCommodity>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling
        System.out.println("Received Request for Product Commodities...") ;
        System.out.println("Received Request for Product Commodities (yes it refreshed image)...") ;
        Set<String> uniqueCommodity= new HashSet<String>();
        Connection con = null ;
        PreparedStatement prstmt = null;
        ResultSet rs =null;
        ResultSet rs1 = null;
        try {

                       
            String sql1 = "select  pc.commodity_name, pc.commodity,pc.class,pc.class_name,pc.family,pc.family_name,pp.list_price,"
            		+ "pp.discount,pp.in_stock,psk.description," + 
            		"psk.sku_attribute_value1,psk.sku_attribute_value2 " + 
            		"from XXIBM_PRODUCT_CATALOGUE pc, XXIBM_PRODUCT_SKU psk," + 
            		"XXIBM_PRODUCT_PRICING pp where pc.commodity=psk.catalogue_category and " + 
            		"psk.item_number=pp.item_number";
            
            con = dataSource.getConnection();
            System.out.println("Database connection obtained : " + con) ;
            prstmt=con.prepareStatement(sql1);
            rs = prstmt.executeQuery();
            while(rs.next()){
                ProductCommodity productCommodity = new ProductCommodity() ;
                uniqueCommodity.add(rs.getString("commodity"));
                               
                
                productCommodity.setCommodity(rs.getString("commodity"));
                productCommodity.setCommodityName(rs.getString("commodity_name"));
                productCommodity.setClassID(rs.getString("class"));
                productCommodity.setClassName(rs.getString("class_name"));
                productCommodity.setFamily(rs.getString("family"));
                productCommodity.setFamilyName(rs.getString("family_name"));
                productCommodity.setCommodityColour(rs.getString("sku_attribute_value2"));
                productCommodity.setCommodityPrice(rs.getString("list_price"));
                productCommodity.setCommoditySize(rs.getString("sku_attribute_value1"));
                productCommodity.setCommodityDiscount(rs.getString("discount"));
                productCommodity.setCommodityDescription(rs.getString("description"));
                productCommodity.setCommodityInStock(rs.getString("in_stock"));
                entities.add(productCommodity);

			}
            System.out.println("After first query: "+entities.size());
            
          
            int count=1;
            String queryPart="";
            for (String var : uniqueCommodity) 
            { 
            	if(count==uniqueCommodity.size())
            	{
            		queryPart+= "'" + var + "'";
            	}
            	else
            	{
            		queryPart+= "'" + var + "',";
            	}
                count++;
            }
            
            
            String sql = "select   distinct(commodity_name), commodity ,class, class_name, family, family_name from XXIBM_PRODUCT_CATALOGUE where commodity"
            		+ " not in ("+queryPart+")";
            
            
            prstmt=con.prepareStatement(sql);
            rs1 = prstmt.executeQuery();
            System.out.println(queryPart);
            System.out.println(sql);
            while(rs1.next()){
                ProductCommodity productCommodity = new ProductCommodity() ;
                uniqueCommodity.add(rs1.getString("commodity"));
                productCommodity.setCommodity(rs1.getString("commodity"));
                productCommodity.setCommodityName(rs1.getString("commodity_name"));
                productCommodity.setClassID(rs1.getString("class"));
                productCommodity.setClassName(rs1.getString("class_name"));
                productCommodity.setFamily(rs1.getString("family"));
                productCommodity.setFamilyName(rs1.getString("family_name"));
                productCommodity.setCommodityColour("NA");
                productCommodity.setCommodityPrice("NA");
                productCommodity.setCommoditySize("NA");
                productCommodity.setCommodityDiscount("NA");
                productCommodity.setCommodityDescription("NA");
                productCommodity.setCommodityInStock("NA");
                entities.add(productCommodity);

			}
            System.out.println("After second query: "+entities.size());
            

        } catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
            	prstmt.close();
                con.close(); 
                rs.close();
                rs1.close();
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
