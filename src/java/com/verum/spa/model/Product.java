/*=============================================================================
 |       Author:  Erick Ruben Ramos Vazquez
 |       Course:  Spa
 |     Due Date:  10/18/2019
 |  Description:  Product Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/
package com.verum.spa.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("prodId")
    private int prodId;
    @SerializedName("prodName")
    private String prodName;
    @SerializedName("brand")
    private String brand;
    @SerializedName("prodStatus")
    private int prodStatus;
    @SerializedName("useCost")
    private double useCost;

    public Product() {
    }

    public Product(int prodId, String prodName, String brand, int prodStatus, double useCost) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.brand = brand;
        this.prodStatus = prodStatus;
        this.useCost = useCost;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(int prodStatus) {
        this.prodStatus = prodStatus;
    }

    public double getUseCost() {
        return useCost;
    }

    public void setUseCost(double useCost) {
        this.useCost = useCost;
    }

}
