package net.containercrush.enceladus.productsearchapp.model ;

public class Product {

    private String name ;
    private String productType ;
    private String productMaterial ;
    private String productColour ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductMaterial() {
		return productMaterial;
	}

	public void setProductMaterial(String productMaterial) {
		this.productMaterial = productMaterial;
	}

	public String getProductColour() {
		return productColour;
	}

	public void setProductColour(String productColour) {
		this.productColour = productColour;
	}


}