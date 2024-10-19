package api.product;

public class Product {

    private int productNo;
    private String productType;
    private String areaName;
    private int policyNumber;

    public Product(int productNo, String productType, String areaName, int policyNumber) {
        this.productNo = productNo;
        this.productType = productType;
        this.areaName = areaName;
        this.policyNumber = policyNumber;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(int policyNumber) {
        this.policyNumber = policyNumber;
    }

    @Override
    public String toString() {
        return "Product [productNo=" + productNo + ", productType=" + productType + ", areaName=" + areaName
                + ", policyNumber=" + policyNumber + "]";
    }

    

    
    
}
