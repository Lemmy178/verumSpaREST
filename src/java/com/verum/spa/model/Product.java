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

    @SerializedName("idPro")
    private int idPro;
    @SerializedName("proName")
    private String proName;
    @SerializedName("proBrand")
    private String proBrand;
    @SerializedName("proPrice")
    private double proPrice;
    @SerializedName("proStatus")
    private boolean proStatus;

    public Product() {
    }

    public Product(int idPro, String proName, String proBrand, double proPrice, boolean proStatus) {
        this.idPro = idPro;
        this.proName = proName;
        this.proBrand = proBrand;
        this.proPrice = proPrice;
        this.proStatus = proStatus;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProBrand() {
        return proBrand;
    }

    public void setProBrand(String proBrand) {
        this.proBrand = proBrand;
    }

    public double getProPrice() {
        return proPrice;
    }

    public void setProPrice(double proPrice) {
        this.proPrice = proPrice;
    }

    public boolean isProStatus() {
        return proStatus;
    }

    public void setProStatus(boolean proStatus) {
        this.proStatus = proStatus;
    }

}
