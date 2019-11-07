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
public class Consumer {

    private String conName;
    private String pass;
    private String role;

    public Consumer() {
    }

    public Consumer(String conName, String pass, String role) {
        this.conName = conName;
        this.pass = pass;
        this.role = role;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
