package net.containercrush.enceladus.productsearchapp.search;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
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
import net.containercrush.enceladus.productsearchapp.model.ProductClass;
import net.containercrush.enceladus.productsearchapp.model.ProductCommodity;
import net.containercrush.enceladus.productsearchapp.model.ProductFamily;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductSearchRestController {

    @Autowired
    DataSource dataSource;
	
    @GetMapping(path = "/uiSearchPOST/{searchString}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> uiSearchPOST(@PathVariable String searchString) {
        // Get data from service layer into entityList.

        List<String> entities = new ArrayList<String>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling
        System.out.println("Received Request for Product Families...") ;
        System.out.println("Received Request for Product Families (yes it refreshed image)...") ;
	    
        try {
        	Properties p = new Properties();
        	p.setProperty("item", searchString);
        	p.store(new FileWriter("search.properties"),"");
  
	} catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
                
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
    

    @GetMapping(path = "/uiSearchGET/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> uiSearchGET() {
        // Get data from service layer into entityList.

    	 HashMap<String, String> entities 
         = new HashMap<>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling
        System.out.println("Received Request for Product Families...") ;
        System.out.println("Received Request for Product Families (yes it refreshed image)...") ;


        try {
        	Properties p = new Properties();
        	p.load(new FileReader("search.properties"));
        	String returnSearch=p.getProperty("item");
        	entities.put("item",returnSearch);
        		

        } catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
                
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }

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
        ResultSet rs=null;
        Connection con = null ;
        PreparedStatement prstmt = null;
        try {

            String sql = "select   distinct(commodity_name), commodity ,class, class_name, family, family_name from XXIBM_PRODUCT_CATALOGUE";
            con = dataSource.getConnection();
            System.out.println("Database connection obtained : " + con) ;
            //stmt = con.createStatement();
            prstmt=con.prepareStatement(sql);
            rs = prstmt.executeQuery();
            while(rs.next()){
                
                ProductCommodity productCommodity = new ProductCommodity() ;
                productCommodity.setCommodity(rs.getString("commodity"));
                productCommodity.setCommodityName(rs.getString("commodity_name"));
                productCommodity.setClassID(rs.getString("class"));
                productCommodity.setClassName(rs.getString("class_name"));
                productCommodity.setFamily(rs.getString("family"));
                productCommodity.setFamilyName(rs.getString("family_name"));
                productCommodity.setCommodityColour("NA");
                productCommodity.setCommodityPrice("NA");
                productCommodity.setCommoditySize("NA");
                productCommodity.setCommodityDiscount("NA");
                productCommodity.setCommodityDescription("NA");
                productCommodity.setCommodityInStock("NA");
                productCommodity.setTeamName("Enceladus") ;
                entities.add(productCommodity);

			}

        } catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
            	if(null!=prstmt)
                {
            	prstmt.close();
                }
            	if(null!=con)
                {
                con.close();
                }
                if(null!=rs)
                {
                	rs.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }

    
    
  /*  @GetMapping(path = "/commodities", produces = MediaType.APPLICATION_JSON_VALUE)
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
    */
    
    
    @GetMapping(path = "/commodities/commoditybyClassID/{classID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCommoditiesByClassID(@PathVariable String classID) {
        // Get data from service layer into entityList.

        List<ProductCommodity> entities = new ArrayList<ProductCommodity>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling
        System.out.println("Received Request for Product Commodities by Class ID...") ;
        System.out.println("Received Request for Product Commodities by Class ID (yes it refreshed image)...") ;

        Connection con = null ;
        PreparedStatement prstmt = null;
        ResultSet rs=null;
        try {

            String sql = "select  distinct(commodity_name), commodity, class, class_name, family, family_name from XXIBM_PRODUCT_CATALOGUE where class = ?";
            con = dataSource.getConnection();
            System.out.println("Database connection obtained : " + con) ;
            //stmt = con.createStatement();
            prstmt=con.prepareStatement(sql);
            prstmt.setString(1, classID);
            rs = prstmt.executeQuery();
            while(rs.next()){
                
                ProductCommodity productCommodity = new ProductCommodity() ;
                productCommodity.setCommodity(rs.getString("commodity"));
                productCommodity.setCommodityName(rs.getString("commodity_name"));
                productCommodity.setClassID(rs.getString("class"));
                productCommodity.setClassName(rs.getString("class_name"));
                productCommodity.setFamily(rs.getString("family"));
                productCommodity.setFamilyName(rs.getString("family_name"));
                productCommodity.setCommodityColour("NA");
                productCommodity.setCommodityPrice("NA");
                productCommodity.setCommoditySize("NA");
                productCommodity.setCommodityDiscount("NA");
                productCommodity.setCommodityDescription("NA");
                productCommodity.setCommodityInStock("NA");
                productCommodity.setTeamName("Enceladus") ;
                entities.add(productCommodity);

			}

        } catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
            	if(null!=prstmt)
                {
            	prstmt.close();
                }
            	if(null!=con)
                {
                con.close();
                }
                if(null!=rs)
                {
                	rs.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
    
    
    
  /*  @GetMapping(path = "/commodities/commoditybyClassID/{classID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCommoditiesByClassID(@PathVariable String classID) {
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
        HashMap<String,ProductCommodity> hm=new HashMap<String,ProductCommodity>();
        try {

        	
        	 String sql = "select   distinct(commodity_name), commodity ,class, class_name, family, family_name "
             		+ "from XXIBM_PRODUCT_CATALOGUE where class = ?";
             
        	 con = dataSource.getConnection();
             System.out.println("Database connection obtained : " + con) ;
             prstmt=con.prepareStatement(sql);
             prstmt.setString(1, classID);
             rs1 = prstmt.executeQuery();
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
                 hm.put(productCommodity.getCommodity(), productCommodity);
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
             
             con.close();
          	prstmt.close();
        	
                       
            String sql1 = "select  pc.commodity_name, pc.commodity,pc.class,pc.class_name,pc.family,pc.family_name,pp.list_price,"
            		+ "pp.discount,pp.in_stock,psk.description," + 
            		"psk.sku_attribute_value1,psk.sku_attribute_value2 " + 
            		"from XXIBM_PRODUCT_CATALOGUE pc, XXIBM_PRODUCT_SKU psk," + 
            		"XXIBM_PRODUCT_PRICING pp where pc.commodity=psk.catalogue_category and " + 
            		"psk.item_number=pp.item_number and pc.commodity in ("+ queryPart+")";
            
            con = dataSource.getConnection();
            System.out.println("Database connection obtained : " + con) ;
            prstmt=con.prepareStatement(sql1);
            rs = prstmt.executeQuery();
            while(rs.next()){
                ProductCommodity productCommodity = new ProductCommodity() ;
                productCommodity=hm.get(rs.getString("commodity"));
               
                if(productCommodity!=null)
                {
                	System.out.println("Inside productCommodity loop");
                	 entities.remove(productCommodity);
                	 System.out.println("sku_attribute_value2: "+rs.getString("sku_attribute_value2"));
                	 productCommodity.setCommodityColour(rs.getString("sku_attribute_value2"));
                	 System.out.println("list_price: "+rs.getString("list_price"));
                     productCommodity.setCommodityPrice(rs.getString("list_price"));
                     System.out.println("sku_attribute_value1: "+rs.getString("sku_attribute_value1"));
                     productCommodity.setCommoditySize(rs.getString("sku_attribute_value1"));
                     productCommodity.setCommodityDiscount(rs.getString("discount"));
                     productCommodity.setCommodityDescription(rs.getString("description"));
                     productCommodity.setCommodityInStock(rs.getString("in_stock"));
                     entities.add(productCommodity);
                }
                
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
    */
    

    @GetMapping(path = "/commodities/commoditybyFamilyID/{familyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCommoditiesByFamilyID(@PathVariable String familyID) {
        // Get data from service layer into entityList.

        List<ProductCommodity> entities = new ArrayList<ProductCommodity>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling
        System.out.println("Received Request for Product Commodities by Class ID...") ;
        System.out.println("Received Request for Product Commodities by Class ID (yes it refreshed image)...") ;

        Connection con = null ;
        PreparedStatement prstmt = null;
        ResultSet rs=null;
        try {

            String sql = "select  distinct(commodity_name), commodity, class, class_name, family, family_name from XXIBM_PRODUCT_CATALOGUE where family = ?";
            con = dataSource.getConnection();
            System.out.println("Database connection obtained : " + con) ;
            //stmt = con.createStatement();
            prstmt=con.prepareStatement(sql);
            prstmt.setString(1, familyID);
            rs = prstmt.executeQuery();
            while(rs.next()){
                
                ProductCommodity productCommodity = new ProductCommodity() ;
                productCommodity.setCommodity(rs.getString("commodity"));
                productCommodity.setCommodityName(rs.getString("commodity_name"));
                productCommodity.setClassID(rs.getString("class"));
                productCommodity.setClassName(rs.getString("class_name"));
                productCommodity.setFamily(rs.getString("family"));
                productCommodity.setFamilyName(rs.getString("family_name"));
                productCommodity.setCommodityColour("NA");
                productCommodity.setCommodityPrice("NA");
                productCommodity.setCommoditySize("NA");
                productCommodity.setCommodityDiscount("NA");
                productCommodity.setCommodityDescription("NA");
                productCommodity.setCommodityInStock("NA");
                productCommodity.setTeamName("Enceladus") ;
                entities.add(productCommodity);

			}

        } catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
            	if(null!=prstmt)
                {
            	prstmt.close();
                }
            	if(null!=con)
                {
                con.close();
                }
                if(null!=rs)
                {
                	rs.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
    
    
  /*  
    @GetMapping(path = "/commodities/commoditybyFamilyID/{familyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCommoditiesByFamilyID(@PathVariable String familyID) {
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
         HashMap<String,ProductCommodity> hm=new HashMap<String,ProductCommodity>();
         try {

         	
         	 String sql = "select   distinct(commodity_name), commodity ,class, class_name, family, family_name "
              		+ "from XXIBM_PRODUCT_CATALOGUE where family = ?";
              
         	 con = dataSource.getConnection();
              System.out.println("Database connection obtained : " + con) ;
              prstmt=con.prepareStatement(sql);
              prstmt.setString(1, familyID);
              rs1 = prstmt.executeQuery();
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
                  hm.put(productCommodity.getCommodity(), productCommodity);
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
              
         	
         	con.close();
         	prstmt.close();
                        
             String sql1 = "select  pc.commodity_name, pc.commodity,pc.class,pc.class_name,pc.family,pc.family_name,pp.list_price,"
             		+ "pp.discount,pp.in_stock,psk.description," + 
             		"psk.sku_attribute_value1,psk.sku_attribute_value2 " + 
             		"from XXIBM_PRODUCT_CATALOGUE pc, XXIBM_PRODUCT_SKU psk," + 
             		"XXIBM_PRODUCT_PRICING pp where pc.commodity=psk.catalogue_category and " + 
             		"psk.item_number=pp.item_number and pc.commodity in ("+ queryPart+")";
             
             con = dataSource.getConnection();
             System.out.println("Database connection obtained : " + con) ;
             prstmt=con.prepareStatement(sql1);
             rs = prstmt.executeQuery();
             while(rs.next()){
                 ProductCommodity productCommodity = new ProductCommodity() ;
                 productCommodity=hm.get(rs.getString("commodity"));
                
                 if(productCommodity!=null)
                 {
                 	 entities.remove(productCommodity);
                 	 productCommodity.setCommodityColour(rs.getString("sku_attribute_value2"));
                      productCommodity.setCommodityPrice(rs.getString("list_price"));
                      productCommodity.setCommoditySize(rs.getString("sku_attribute_value1"));
                      productCommodity.setCommodityDiscount(rs.getString("discount"));
                      productCommodity.setCommodityDescription(rs.getString("description"));
                      productCommodity.setCommodityInStock(rs.getString("in_stock"));
                      entities.add(productCommodity);
                 }
                 
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
    */

    
    
    @GetMapping(path = "/commodities/familyBySegmentId/{segmentID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getFamilyBySegmentId(@PathVariable String segmentID) {
        // Get data from service layer into entityList.

        List<ProductFamily> entities = new ArrayList<ProductFamily>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling
        System.out.println("Received Request for Product Commodities by Class ID...") ;
        System.out.println("Received Request for Product Commodities by Class ID (yes it refreshed image)...") ;

        Connection con = null ;
        PreparedStatement prstmt = null;
        ResultSet rs=null;
        try {

            String sql = "select  distinct(family), family_name from XXIBM_PRODUCT_CATALOGUE where segment= ? ";
            con = dataSource.getConnection();
            System.out.println("Database connection obtained : " + con) ;
            //stmt = con.createStatement();
            prstmt=con.prepareStatement(sql);
            prstmt.setString(1, segmentID);
            rs = prstmt.executeQuery();
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
            	if(null!=prstmt)
                {
            	prstmt.close();
                }
            	if(null!=con)
                {
                con.close();
                }
                if(null!=rs)
                {
                	rs.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
    
    
    
    @GetMapping(path = "/commodities/classByFamilyId/{familyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getClassByFamilyId(@PathVariable String familyID) {
        // Get data from service layer into entityList.

        List<ProductClass> entities = new ArrayList<ProductClass>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling
        System.out.println("Received Request for Product Commodities by Class ID...") ;
        System.out.println("Received Request for Product Commodities by Class ID (yes it refreshed image)...") ;

        Connection con = null ;
        PreparedStatement prstmt = null;
        ResultSet rs=null;
        try {

            String sql = "select  distinct(class), class_name, family,family_name from XXIBM_PRODUCT_CATALOGUE where family= ? ";
            con = dataSource.getConnection();
            System.out.println("Database connection obtained : " + con) ;
            //stmt = con.createStatement();
            prstmt=con.prepareStatement(sql);
            prstmt.setString(1, familyID);
            rs = prstmt.executeQuery();
            while(rs.next()){
                
                ProductClass productClass = new ProductClass() ;
                productClass.setClassID(rs.getString("class"));
                productClass.setClassName(rs.getString("class_name"));
                productClass.setFamily(rs.getString("family"));
                productClass.setFamilyName(rs.getString("family_name"));
                entities.add(productClass);

			}

        } catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
            	if(null!=prstmt)
                {
            	prstmt.close();
                }
            	if(null!=con)
                {
                con.close();
                }
                if(null!=rs)
                {
                	rs.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
    
    
    
    @GetMapping(path = "/commodities/itembyCommodityID/{commodityID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getItembyCommodityID(@PathVariable String commodityID) {
        // Get data from service layer into entityList.

        List<ProductCommodity> entities = new ArrayList<ProductCommodity>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling
        System.out.println("Received Request for Product Commodities...") ;
        System.out.println("Received Request for Product Commodities (yes it refreshed image)...") ;
        Connection con = null ;
        PreparedStatement prstmt = null;
        ResultSet rs =null;
        ResultSet rs1 = null;
        int count=0;
        try {
        	  String sql1 = "select  pc.commodity_name, pc.commodity,pc.class,pc.class_name,pc.family,pc.family_name,pp.list_price,"
              		+ "pp.discount,pp.in_stock,psk.description," + 
              		"psk.sku_attribute_value1,psk.sku_attribute_value2 " + 
              		"from XXIBM_PRODUCT_CATALOGUE pc, XXIBM_PRODUCT_SKU psk," + 
              		"XXIBM_PRODUCT_PRICING pp where pc.commodity=psk.catalogue_category and " + 
              		"psk.item_number=pp.item_number and psk.catalogue_category = ? ";
              
              con = dataSource.getConnection();
              System.out.println("Database connection obtained : " + con) ;
              prstmt=con.prepareStatement(sql1);
              prstmt.setString(1, commodityID);
              rs1 = prstmt.executeQuery();
              while(rs1.next()){
            	  count++;
            	  ProductCommodity productCommodity = new ProductCommodity() ;
                  productCommodity.setCommodity(rs1.getString("commodity"));
                  productCommodity.setCommodityName(rs1.getString("commodity_name"));
                  productCommodity.setClassID(rs1.getString("class"));
                  productCommodity.setClassName(rs1.getString("class_name"));
                  productCommodity.setFamily(rs1.getString("family"));
                  productCommodity.setFamilyName(rs1.getString("family_name"));
                  productCommodity.setCommodityColour(rs1.getString("sku_attribute_value2"));
                  productCommodity.setCommodityPrice(rs1.getString("list_price"));
                  productCommodity.setCommoditySize(rs1.getString("sku_attribute_value1"));
                  productCommodity.setCommodityDiscount(rs1.getString("discount"));
                  productCommodity.setCommodityDescription(rs1.getString("description"));
                  productCommodity.setCommodityInStock(rs1.getString("in_stock"));
                  entities.add(productCommodity);
                  }
              con.close();
              prstmt.close();
              
              if(count==0)
              {
            	  String sql = "select   distinct(commodity_name), commodity ,class, class_name, family, family_name "
                   		+ "from XXIBM_PRODUCT_CATALOGUE where commodity = ?";
                   
              	   con = dataSource.getConnection();
                   System.out.println("Database connection obtained : " + con) ;
                   prstmt=con.prepareStatement(sql);
                   prstmt.setString(1, commodityID);
                   rs = prstmt.executeQuery();
                   System.out.println(sql);
                   while(rs.next()){
                       ProductCommodity productCommodity = new ProductCommodity() ;
                       productCommodity.setCommodity(rs.getString("commodity"));
                       productCommodity.setCommodityName(rs.getString("commodity_name"));
                       productCommodity.setClassID(rs.getString("class"));
                       productCommodity.setClassName(rs.getString("class_name"));
                       productCommodity.setFamily(rs.getString("family"));
                       productCommodity.setFamilyName(rs.getString("family_name"));
                       productCommodity.setCommodityColour("NA");
                       productCommodity.setCommodityPrice("NA");
                       productCommodity.setCommoditySize("NA");
                       productCommodity.setCommodityDiscount("NA");
                       productCommodity.setCommodityDescription("NA");
                       productCommodity.setCommodityInStock("NA");
                       entities.add(productCommodity);

       			}
                   System.out.println("After second query: "+entities.size());
            	  
              }
              
        } catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
            	if(null!=prstmt)
                {
            	prstmt.close();
                }
            	if(null!=con)
                {
                con.close();
                }
                if(null!=rs)
                {
                	rs.close();
                }
                if(null!=rs1)
                {
                rs1.close();
                }
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
    

    @GetMapping(path = "/commodities/itemByFilter/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getItemByFilter(@PathVariable String filter) {
        // Get data from service layer into entityList.

        List<ProductCommodity> entities = new ArrayList<ProductCommodity>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling
        System.out.println("Received Request for Product Commodities...") ;
        System.out.println("Received Request for Product Commodities (yes it refreshed image)...") ;
        Connection con = null ;
        PreparedStatement prstmt = null;
        ResultSet rs =null;
        ResultSet rs1 = null;
        Set<String> uniqueCommodity_colour= new HashSet<String>();
        Set<String> uniqueCommodity_size= new HashSet<String>();
        Set<String> uniqueCommodity_brand= new HashSet<String>();
        try {
        	
        	  String queryPart_color="";
              String queryPart_size="";
              String queryPart_brand="";
              
              
              String sql_colour ="select distinct(sku_attribute_value2) from XXIBM_PRODUCT_SKU where sku_attribute_value2 is not null";
         	 con = dataSource.getConnection();
              System.out.println("Database connection obtained : " + con) ;
              prstmt=con.prepareStatement(sql_colour);
              rs = prstmt.executeQuery();
              while(rs.next()){
                  
             	 uniqueCommodity_colour.add(rs.getString("sku_attribute_value2"));
  			}
              con.close();
              prstmt.close();
              rs.close();
              
              String sql_size ="select distinct(sku_attribute_value1) from XXIBM_PRODUCT_SKU where sku_attribute_value1 is not null";
         	 con = dataSource.getConnection();
              System.out.println("Database connection obtained : " + con) ;
              prstmt=con.prepareStatement(sql_size);
              rs = prstmt.executeQuery();
              while(rs.next()){
                  
             	 uniqueCommodity_size.add(rs.getString("sku_attribute_value1"));
  			}
         	
              con.close();
              prstmt.close();
              rs.close();
         	
              String sql_brand ="select distinct(brand) from XXIBM_PRODUCT_STYLE";
         	 con = dataSource.getConnection();
              System.out.println("Database connection obtained : " + con) ;
              prstmt=con.prepareStatement(sql_brand);
              rs = prstmt.executeQuery();
              while(rs.next()){
                  
             	 uniqueCommodity_brand.add(rs.getString("brand"));
  			}
         	
              con.close();
              prstmt.close();
              rs.close();
              
              

        	 String[] arrOfStr = filter.split("-"); 
        	  
             for (String var : arrOfStr)
             {
            	 if(null!=var)
                 {
                	 if(uniqueCommodity_colour.contains(var))
                	 {
                		 queryPart_color=" and psk.sku_attribute_value2 =  '"+var+"'";
                	 }
                	 else if(uniqueCommodity_size.contains(var))
                	 {
                		 queryPart_size=" and psk.sku_attribute_value1 = '"+var+"'";
                	 }
                	 else if(uniqueCommodity_brand.contains(var))
                	 {
                		 queryPart_brand=" and pst.brand = '"+var+"'";
                	 }
                 }
             }
             
        	  String sql1 = "select  pc.commodity_name, pc.commodity,pc.class,pc.class_name,pc.family,pc.family_name,pp.list_price,"
              		+ "pp.discount,pp.in_stock,psk.description,psk.item_number," + 
              		"psk.sku_attribute_value1,psk.sku_attribute_value2,pst.brand " + 
              		"from XXIBM_PRODUCT_CATALOGUE pc, XXIBM_PRODUCT_SKU psk," + 
              		"XXIBM_PRODUCT_PRICING pp, XXIBM_PRODUCT_STYLE pst where pc.commodity=psk.catalogue_category and " + 
              		"psk.item_number=pp.item_number and pc.commodity=pst.catalogue_category and psk.style_item=pst.item_number "
              		+ queryPart_color + queryPart_size + queryPart_brand;
              
              con = dataSource.getConnection();
              System.out.println("Database connection obtained : " + con) ;
              prstmt=con.prepareStatement(sql1);
              
              rs1 = prstmt.executeQuery();
              while(rs1.next()){
            	  ProductCommodity productCommodity = new ProductCommodity() ;
                  productCommodity.setCommodity(rs1.getString("commodity"));
                  productCommodity.setCommodityName(rs1.getString("commodity_name"));
                  productCommodity.setClassID(rs1.getString("class"));
                  productCommodity.setClassName(rs1.getString("class_name"));
                  productCommodity.setFamily(rs1.getString("family"));
                  productCommodity.setFamilyName(rs1.getString("family_name"));
                  productCommodity.setCommodityColour(rs1.getString("sku_attribute_value2"));
                  productCommodity.setCommodityPrice(rs1.getString("list_price"));
                  productCommodity.setCommoditySize(rs1.getString("sku_attribute_value1"));
                  productCommodity.setCommodityDiscount(rs1.getString("discount"));
                  productCommodity.setCommodityDescription(rs1.getString("description"));
                  productCommodity.setCommodityInStock(rs1.getString("in_stock"));
                  productCommodity.setCommodityBrand(rs1.getString("brand"));
                  productCommodity.setItemNumber(rs1.getString("item_number"));
                  entities.add(productCommodity);
                  }
                      
              
        } catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
            	if(null!=prstmt)
                {
            	prstmt.close();
                }
            	if(null!=con)
                {
                con.close();
                }
                if(null!=rs)
                {
                	rs.close();
                }
                if(null!=rs1)
                {
                rs1.close();
                }
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
    
    
    @GetMapping(path = "/commodities/itemsDynamicSearch/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getItemsDynamicSearch(@PathVariable String filter) {
        // Get data from service layer into entityList.

        List<ProductCommodity> entities = new ArrayList<ProductCommodity>();

        System.out.println("Connection Polling datasource : " + dataSource); // check connection pooling
        System.out.println("Received Request for Product Commodities...") ;
        System.out.println("Received Request for Product Commodities (yes it refreshed image)...") ;
        Connection con = null ;
        PreparedStatement prstmt = null;
        ResultSet rs =null;
        ResultSet rs1 = null;
        Set<String> uniqueCommodity_colour= new HashSet<String>();
        Set<String> uniqueCommodity_size= new HashSet<String>();
        Set<String> uniqueCommodity_brand= new HashSet<String>();
        int count=0;
        try {
        	
        	  String queryPart_color="";
              String queryPart_size="";
              String queryPart_brand="";
              
              
              String sql_colour ="select distinct(sku_attribute_value2) from XXIBM_PRODUCT_SKU where sku_attribute_value2 is not null";
         	  con = dataSource.getConnection();
              System.out.println("Database connection obtained : " + con) ;
              prstmt=con.prepareStatement(sql_colour);
              rs = prstmt.executeQuery();
              while(rs.next()){
                  
             	 uniqueCommodity_colour.add(rs.getString("sku_attribute_value2").toUpperCase());
  			}
              prstmt.close();
              rs.close();
              
              String sql_size ="select distinct(sku_attribute_value1) from XXIBM_PRODUCT_SKU where sku_attribute_value1 is not null";
              System.out.println("Database connection obtained : " + con) ;
              prstmt=con.prepareStatement(sql_size);
              rs = prstmt.executeQuery();
              while(rs.next()){
                  
             	 uniqueCommodity_size.add(rs.getString("sku_attribute_value1").toUpperCase());
  			}
         	
              prstmt.close();
              rs.close();
         	
              String sql_brand ="select distinct(brand) from XXIBM_PRODUCT_STYLE";
              System.out.println("Database connection obtained : " + con) ;
              prstmt=con.prepareStatement(sql_brand);
              rs = prstmt.executeQuery();
              while(rs.next()){
                  
             	 uniqueCommodity_brand.add(rs.getString("brand").toUpperCase());
  			}
         	
              prstmt.close();
              rs.close();
              
              
             String queryPart_desc="";
             String queryPart_Longdesc="";
             String queryPart_family="";
        	 String[] arrOfStr = filter.split(" "); 
        	 String sql1 =null;
        	 ArrayList<String> arr=new ArrayList<String>();
        	 int count_long_desc=0;
             for (String var : arrOfStr)
             {
            	 if(null!=var)
                 {
                	 if(uniqueCommodity_colour.contains(var.toUpperCase()))
                	 {
                		 queryPart_color=" and lower(psk.sku_attribute_value2) like  '%"+var.toLowerCase()+"%'";
                	 }
                	 else if(uniqueCommodity_size.contains(var.toUpperCase()))
                	 {
                		 queryPart_size=" and lower(psk.sku_attribute_value1) like '%"+var.toLowerCase()+"%'";
                	 }
                	 else if(uniqueCommodity_brand.contains(var.toUpperCase()))
                	 {
                		 queryPart_brand=" and lower(pst.brand) = '"+var.toLowerCase()+"'";
                	 }
                	 else
                	 {
                		 if(!(var.equalsIgnoreCase("Army") || var.equalsIgnoreCase("Wine") || var.equalsIgnoreCase("Dark") || var.equalsIgnoreCase("Navy")))
                		 {
                			 arr.add(var);
                		 }
                		
                	 }
                 }
             }
             
             for (String desc : arr)
             {
            	 count_long_desc++;
        		 
        		 if(count_long_desc==arr.size())
        		 {
        			 queryPart_desc+="  lower(psk.description) like '%"+desc.toLowerCase()+"%'";
        			 queryPart_Longdesc+=" lower(psk.long_description) like '%"+desc.toLowerCase()+"%'";
        		 }
        		 else
        		 {
        			 queryPart_desc+=" lower(psk.description) like '%"+desc.toLowerCase()+"%' and ";
        			 queryPart_Longdesc+=" lower(psk.long_description) like '%"+desc.toLowerCase()+"%' and ";
        		 }
             }
            	
             
        	 if(arr.size()>0)
        	 {
        		 sql1 = "select  pc.commodity_name, pc.commodity,pc.class,pc.class_name,pc.family,pc.family_name,pp.list_price,"
                   		+ "pp.discount,pp.in_stock,psk.description,psk.item_number," + 
                   		"psk.sku_attribute_value1,psk.sku_attribute_value2,pst.brand " + 
                   		"from XXIBM_PRODUCT_CATALOGUE pc, XXIBM_PRODUCT_SKU psk," + 
                   		"XXIBM_PRODUCT_PRICING pp, XXIBM_PRODUCT_STYLE pst where pc.commodity=psk.catalogue_category and " + 
                   		"psk.item_number=pp.item_number and pc.commodity=pst.catalogue_category and psk.style_item=pst.item_number "
                   		+ queryPart_color + queryPart_size + queryPart_brand + " and ((" + queryPart_desc +" ) or ( "+queryPart_Longdesc+" ))";
        		 
        	 }
        	 else
        	 {
        		 sql1 = "select  pc.commodity_name, pc.commodity,pc.class,pc.class_name,pc.family,pc.family_name,pp.list_price,"
                   		+ "pp.discount,pp.in_stock,psk.description,psk.item_number," + 
                   		"psk.sku_attribute_value1,psk.sku_attribute_value2,pst.brand " + 
                   		"from XXIBM_PRODUCT_CATALOGUE pc, XXIBM_PRODUCT_SKU psk," + 
                   		"XXIBM_PRODUCT_PRICING pp, XXIBM_PRODUCT_STYLE pst where pc.commodity=psk.catalogue_category and " + 
                   		"psk.item_number=pp.item_number and pc.commodity=pst.catalogue_category and psk.style_item=pst.item_number "
                   		+ queryPart_color + queryPart_size + queryPart_brand;
        	 }
              
              System.out.println("Database connection obtained : " + con) ;
              prstmt=con.prepareStatement(sql1);
              
              rs= prstmt.executeQuery();
              while(rs.next()){
            	  count++;
            	  ProductCommodity productCommodity = new ProductCommodity() ;
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
                  productCommodity.setCommodityBrand(rs.getString("brand"));
                  productCommodity.setItemNumber(rs.getString("item_number"));
                  entities.add(productCommodity);
                  }
                      
              prstmt.close();
              int count_commodity=0;
              
            if(count==0)
              {
            	  
            	 queryPart_desc="";
            	 queryPart_family="";
            	 String queryPart_segment="";
            	 String queryPart_class="";
             	 String[] arrOfStr1 = filter.split(" "); 
             	  
                  for (String var : arrOfStr1)
                  {
                 	 if(null!=var)
                      {
                 		count_commodity++;
                     	if(count_commodity==arrOfStr1.length)
                     	{
                     		queryPart_desc+=" lower(commodity_name) like '%"+var.toLowerCase()+"%' ";
                     		queryPart_family+=" lower(family_name) like '%"+var.toLowerCase()+"%' ";
                     		queryPart_segment+=" lower(segment_name) like '%"+var.toLowerCase()+"%' ";
                     		queryPart_class+=" lower(class_name) like '%"+var.toLowerCase()+"%' ";
                     	}
                     	else
                     	{
                     		queryPart_desc+=" lower(commodity_name) like '%"+var.toLowerCase()+"%' and ";
                     		queryPart_family+=" lower(family_name) like '%"+var.toLowerCase()+"%' and ";
                     		queryPart_segment+=" lower(segment_name) like '%"+var.toLowerCase()+"%' and ";
                     		queryPart_class+=" lower(class_name) like '%"+var.toLowerCase()+"%' and ";
                     	}
                     	
                      }
                  }
                  
            	  
            	  
            	  
            	  String sql = "select distinct(commodity_name), commodity ,class, class_name, family, family_name from XXIBM_PRODUCT_CATALOGUE where ("
            	  		+ queryPart_desc + ") or ("+ queryPart_family+" ) or ("+ queryPart_segment+" ) or ("+ queryPart_class+" )";
            	  
                  System.out.println("Database connection obtained : " + con) ;
                  prstmt=con.prepareStatement(sql);
                  rs = prstmt.executeQuery();
                  while(rs.next()){
                      
                      ProductCommodity productCommodity = new ProductCommodity() ;
                      productCommodity.setCommodity(rs.getString("commodity"));
                      productCommodity.setCommodityName(rs.getString("commodity_name"));
                      productCommodity.setClassID(rs.getString("class"));
                      productCommodity.setClassName(rs.getString("class_name"));
                      productCommodity.setFamily(rs.getString("family"));
                      productCommodity.setFamilyName(rs.getString("family_name"));
                      productCommodity.setCommodityColour("NA");
                      productCommodity.setCommodityPrice("NA");
                      productCommodity.setCommoditySize("NA");
                      productCommodity.setCommodityDiscount("NA");
                      productCommodity.setCommodityDescription("NA");
                      productCommodity.setCommodityInStock("NA");
                      productCommodity.setItemNumber("NA");
                      productCommodity.setTeamName("Enceladus") ;
                      entities.add(productCommodity);

      			}
              }
              
        } catch (Exception e) {
            e.printStackTrace(); 

        } finally {

            try {
            	if(null!=prstmt)
                {
            	prstmt.close();
                }
            	if(null!=con)
                {
                con.close();
                }
                if(null!=rs)
                {
                	rs.close();
                }
               
            } catch (Exception e) {
                e.printStackTrace(); 

            }
           

        }

       
        return new ResponseEntity<Object>(entities, HttpStatus.OK);
    }
    
}
