/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verum.spa.model;

/**
 *
 * @author Edson
 */
public class Customer extends Person {

    private String uniqueNumber;
    private String email;
    private int cusStatus;
    private Consumer consumer;

    public Customer() {
        this.consumer = new Consumer();
    }

    public Customer(String uniqueNumber, String email, int cusStatus, String conName, String pass, String role, String firstName, String lastName1, String lastName2, String gender, String perAddress, String telephone, String rfc) {
        super(firstName, lastName1, lastName2, gender, perAddress, telephone, rfc);
        this.uniqueNumber = uniqueNumber;
        this.email = email;
        this.cusStatus = cusStatus;
        this.consumer = new Consumer(conName, pass, role);//conName, String pass, String role
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCusStatus() {
        return cusStatus;
    }

    public void setCusStatus(int cusStatus) {
        this.cusStatus = cusStatus;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

}
