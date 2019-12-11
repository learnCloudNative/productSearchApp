package net.containercrush.enceladus.productsearchapp.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.containercrush.enceladus.productsearchapp.model.ProductCatalogue;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)

public class ProductCatalogueSearchServiceImpl implements ProductCatalogueSearchService{

	private static final String DB_DRIVER = "org.h2.Driver";
	// private static final String DB_CONNECTION = "jdbc:h2:~/test";
	private static final String DB_CONNECTION = "jdbc:h2:tcp://localhost/~/test";
	private static final String DB_USER = "security";
	private static final String DB_PASSWORD = "security123";

  
    

	@Override
	public ProductCatalogue getProdCatalogueDetByCommName(String commodityName) throws SQLException {

		
		ProductCatalogue prodcat=null;
		Connection con = getDBConnection();
		ResultSet rs = null;
		PreparedStatement prstmt = null;
		prstmt = con.prepareStatement("SELECT * FROM XXIBM_PRODUCT_CATALOGUE WHERE commodity_name = ?");
		try {
			prstmt.setString(1, commodityName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			rs = prstmt.executeQuery();
			while (rs.next()) {
				prodcat=new ProductCatalogue();
				prodcat.setClassCategory(rs.getString("class"));
				prodcat.setClassName(rs.getString("class_name"));
				prodcat.setFamily(rs.getString("family"));
				prodcat.setFamilyName(rs.getString("family_name"));
				prodcat.setCommodity(rs.getString("commodity"));
				prodcat.setCommodityName(rs.getString("commodity_name"));
				prodcat.setSegment(rs.getString("segment"));
				prodcat.setSegmentName(rs.getString("segment_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prodcat;

	}
	
	
	@Override
	public List<ProductCatalogue> getProdCatalogueDetByFamilyName(String familyName) throws SQLException {

		List<ProductCatalogue> prodCatList=new ArrayList<ProductCatalogue>();
		ProductCatalogue prodcat=null;
		Connection con = getDBConnection();
		ResultSet rs = null;
		PreparedStatement prstmt = null;
		prstmt = con.prepareStatement("SELECT * FROM XXIBM_PRODUCT_CATALOGUE WHERE family_name = ?");
		try {
			prstmt.setString(1, familyName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			rs = prstmt.executeQuery();
			while (rs.next()) {
				prodcat=new ProductCatalogue();
				prodcat.setClassCategory(rs.getString("class"));
				prodcat.setClassName(rs.getString("class_name"));
				prodcat.setFamily(rs.getString("family"));
				prodcat.setFamilyName(rs.getString("family_name"));
				prodcat.setCommodity(rs.getString("commodity"));
				prodcat.setCommodityName(rs.getString("commodity_name"));
				prodcat.setSegment(rs.getString("segment"));
				prodcat.setSegmentName(rs.getString("segment_name"));
				prodCatList.add(prodcat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prodCatList;

	}

    
    private Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

}