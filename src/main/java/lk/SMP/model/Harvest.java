package lk.SMP.model;

import java.sql.Date;

public class Harvest {
    private String harvestId;
    private String cropType;
    private double quantity;
    private Date date;
    private String fieldId;
    private double unitPrice;
    private double waste;

    public Harvest(){

    }
    public Harvest(String harvestId, String cropType, double quantity, Date date, String fieldId, double unitPrice, double waste) {
        this.harvestId = harvestId;
        this.cropType = cropType;
        this.quantity = quantity;
        this.date = date;
        this.fieldId = fieldId;
        this.unitPrice = unitPrice;
        this.waste = waste;
    }

    public String getHarvestId() {
        return harvestId;
    }

    public void setHarvestId(String harvestId) {
        this.harvestId = harvestId;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getWaste() {
        return waste;
    }

    public void setWaste(double waste) {
        this.waste = waste;
    }

    @Override
    public String toString() {
        return "Harvest{" +
                "harvestId='" + harvestId + '\'' +
                ", cropType='" + cropType + '\'' +
                ", quantity=" + quantity +
                ", date=" + date +
                ", fieldId='" + fieldId + '\'' +
                ", unitPrice=" + unitPrice +
                ", waste=" + waste +
                '}';
    }
}
