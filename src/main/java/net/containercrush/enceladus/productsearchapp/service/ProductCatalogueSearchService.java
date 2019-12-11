package net.containercrush.enceladus.productsearchapp.service;

import java.sql.SQLException;
import java.util.List;

import net.containercrush.enceladus.productsearchapp.model.ProductCatalogue;

public interface ProductCatalogueSearchService {

    public ProductCatalogue getProdCatalogueDetByCommName(String commodityName) throws SQLException;
    public List<ProductCatalogue> getProdCatalogueDetByFamilyName(String familyName) throws SQLException;
}