package lk.SMP.model;

import java.util.Date;

public class Stock {
    private String productId;
    private double quantity;
    private double unitPrice;
    private String expireDate;
    private String name;

    /*public Stock(String productId, String name, String quantity, String expireDate, String unitPrice){

    }*/
    public Stock(String productId, String name, double quantity, String expireDate, double unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.expireDate =expireDate ;
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDate() {
        return expireDate;
    }

    public void setDate(Date date) {
        this.expireDate = expireDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "productId='" + productId + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", date=" + expireDate +
                ", name='" + name + '\'' +
                '}';
    }
}
