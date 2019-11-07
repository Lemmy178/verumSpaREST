/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verum.spa.dao;

import com.verum.spa.model.ConexionSpaMYSQL;
import com.verum.spa.model.Customer;
import com.verum.spa.model.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Edson
 */
public class DAOEmployee {

    private ConexionSpaMYSQL conexion = new ConexionSpaMYSQL();
    private String sql = "";
    private PreparedStatement pst;

    public boolean addEmployee(Employee emp) throws ClassNotFoundException, SQLException {
        sql = "CALL ADD_CUSTOMER(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, emp.getFirstName());
        pst.setString(2, emp.getLastName1());
        pst.setString(2, emp.getLastName2());
        pst.setString(4, emp.getGender());
        pst.setString(5, emp.getPerAddress());
        pst.setString(6, emp.getTelephone());
        pst.setString(7, emp.getRfc());
        pst.setString(8, emp.getEmpNumber());
        pst.setString(9, emp.getEmpPosition());
        pst.setInt(10, emp.getEmpStatus());
        pst.setString(11, emp.getPhoto());
        pst.setString(12, emp.getConsumer().getConName());
        pst.setString(13, emp.getConsumer().getPass());
        pst.setString(14, emp.getConsumer().getRole());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean modifyEmployee(Employee emp) throws ClassNotFoundException, SQLException {
        sql = "UPDATE CUSTOMER SET "
                + "firstName = ?, lastName1 = ?, lastName2 = ?, gender = ?, perAddress = ?,"
                + "telephone = ?, pass = ?,  empPosition = ?, empStatus = ?, photo = ?";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, emp.getFirstName());
        pst.setString(2, emp.getLastName1());
        pst.setString(3, emp.getLastName2());
        pst.setString(4, emp.getGender());
        pst.setString(5, emp.getPerAddress());
        pst.setString(6, emp.getTelephone());
        pst.setString(7, emp.getConsumer().getPass());
        pst.setString(8, emp.getEmpPosition());
        pst.setInt(9, emp.getEmpStatus());
        pst.setString(10, emp.getPhoto());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean deleteEmployee(Employee emp) throws ClassNotFoundException, SQLException {
        sql = "UPDATE EMPLOYEE SET empStatus = 2 WHERE uniqueNumber LIKE '?' ";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, emp.getEmpNumber());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public ArrayList<Employee> employeeList() throws ClassNotFoundException, SQLException {
        ResultSet rs;
        ArrayList<Employee> employeeData = new ArrayList<>();
        sql = "SELECT * FROM LIST_EMPLOYEE";

        Class.forName(conexion.getDRIVER());
        pst = conexion.startConnection().prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.first()) {
            rs.beforeFirst();
            while (rs.next()) {
//            String empNumber, String empPosition, int empStatus, String photo,
//            String conName , String pass , String role , String firstName , String lastName1 , String lastName2 ,
//            String gender , String perAddress , String telephone , String rfc
                employeeData.add(new Employee(rs.getString("empNumber"), rs.getString("empPosition"),
                        rs.getInt("empStatus"), rs.getString("photo"), rs.getString("conName"), rs.getString("pass"),
                        rs.getString("role"), rs.getString("firstName"), rs.getString("lastName1"), rs.getString("lastName2"),
                        rs.getString("gender"), rs.getString("perAddress"), rs.getString("telephone"), rs.getString("rfc")));
            }
            conexion.closeConnection();
            return employeeData;
        } else {
            return null;
        }
    }

    public Employee employeeSearch(Employee emp) throws SQLException, ClassNotFoundException {
        Employee emp1 = null;
        PreparedStatement pst;
        ResultSet rs;
        sql = "SELECT * FROM LIST_EMPLOYEE WHERE empNumber LIKE '" + emp.getEmpNumber() + "' ";

        Class.forName(conexion.getDRIVER());
        pst = conexion.startConnection().prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.first()) {
            rs.beforeFirst();
            while (rs.next()) {
//              String uniqueNumber, String email, int cusStatus, String conName, String pass, String role, String firstName, 
//              String lastName1, String lastName2, String gender, String perAddress, String telepnohe, String rfc
                emp1 = new Employee(rs.getString("empNumber"), rs.getString("empPosition"),
                        rs.getInt("empStatus"), rs.getString("photo"), rs.getString("conName"), rs.getString("pass"),
                        rs.getString("role"), rs.getString("firstName"), rs.getString("lastName1"), rs.getString("lastName2"),
                        rs.getString("gender"), rs.getString("perAddress"), rs.getString("telephone"), rs.getString("rfc"));
            }
            conexion.closeConnection();
            return emp1;
        } else {
            conexion.closeConnection();
            return null;
        }
    }

}
