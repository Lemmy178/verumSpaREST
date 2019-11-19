/*=============================================================================
 |       Author:  Edson Mesraim Santos Perez
 |       Course:  Spa
 |     Due Date:  11/06/2019
 |  Description:  Employee Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/
package com.verum.spa.dao;

import com.verum.spa.model.ConexionSpaMYSQL;
import com.verum.spa.model.ConexionSpaMYSQL;
import com.verum.spa.model.Customer;
import com.verum.spa.model.Customer;
import java.sql.*;
import java.util.ArrayList;

public class DAOCustomer {

    private ConexionSpaMYSQL conexion = new ConexionSpaMYSQL();
    private String sql = "";
    private PreparedStatement pst;

    public boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        sql = "CALL ADD_CUSTOMER(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, customer.getFirstName());
        pst.setString(2, customer.getLastName1());
        pst.setString(3, customer.getLastName2());
        pst.setString(4, customer.getGender());
        pst.setString(5, customer.getPerAddress());
        pst.setString(6, customer.getTelephone());
        pst.setString(7, customer.getRfc());
        pst.setString(8, customer.getUniqueNumber());
        pst.setString(9, customer.getEmail());
        pst.setInt(10, customer.getCusStatus());
        pst.setString(11, customer.getConsumer().getConName());
        pst.setString(12, customer.getConsumer().getPass());
        pst.setString(13, customer.getConsumer().getRole());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean modifyCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        sql = "call modifyCustomer (?,?,?,?,?,?,?,?,?,?,?,?) ";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, customer.getFirstName());
        pst.setString(2, customer.getLastName1());
        pst.setString(3, customer.getLastName2());
        pst.setString(4, customer.getGender());
        pst.setString(5, customer.getPerAddress());
        pst.setString(6, customer.getTelephone());
        pst.setString(7, customer.getConsumer().getPass());
        pst.setString(8, customer.getEmail());
        pst.setInt(9, customer.getCusStatus());
        pst.setInt(10, customer.getCusId());
        pst.setInt(11, customer.getConsumer().getConId());
        pst.setInt(12, customer.getPerId());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean deleteCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        sql = "UPDATE CUSTOMER SET cusStatus = 2 WHERE cusId=?";

        pst = conexion.startConnection().prepareStatement(sql);
        pst.setInt(1, customer.getCusStatus());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public ArrayList<Customer> customerList() throws ClassNotFoundException, SQLException {
        ResultSet rs;
        ArrayList<Customer> customerData = new ArrayList<>();
        sql = "SELECT * FROM LIST_CUSTOMER";

        Class.forName(conexion.getDRIVER());
        pst = conexion.startConnection().prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.first()) {
            rs.beforeFirst();
            while (rs.next()) {
//              String uniqueNumber, String email, int cusStatus, String conName, String pass, String role, String firstName, 
//              String lastName1, String lastName2, String gender, String perAddress, String telepnohe, String rfc
                customerData.add(new Customer(rs.getInt("cusId"), rs.getString("uniqueNumber"), rs.getString("email"),
                        rs.getInt("cusStatus"), rs.getInt("conId"), rs.getString("conName"), rs.getString("pass"), rs.getString("role"),
                        rs.getString("firstName"), rs.getString("lastName1"), rs.getString("lastName2"),
                        rs.getString("gender"), rs.getString("perAddress"), rs.getString("telephone"), rs.getString("rfc")));
            }
            conexion.closeConnection();
            return customerData;
        } else {
            return null;
        }
    }

    public Customer customerSearch(Customer customer) throws SQLException, ClassNotFoundException {
        Customer customer1 = null;
        PreparedStatement pst;
        ResultSet rs;
        sql = "SELECT * FROM LIST_CUSTOMER WHERE cusid = " + customer.getCusId();

        Class.forName(conexion.getDRIVER());
        pst = conexion.startConnection().prepareStatement(sql);
        rs = pst.executeQuery();
        if (rs.first()) {
            rs.beforeFirst();
            while (rs.next()) {
//              String uniqueNumber, String email, int cusStatus, String conName, String pass, String role, String firstName, 
//              String lastName1, String lastName2, String gender, String perAddress, String telepnohe, String rfc
                customer1 = new Customer(rs.getInt("cusStatus"), rs.getString("uniqueNumber"), rs.getString("email"),
                        rs.getInt("cusStatus"), rs.getInt("conId"), rs.getString("conName"), rs.getString("pass"), rs.getString("role"),
                        rs.getString("firstName"), rs.getString("lastName1"), rs.getString("lastName2"),
                        rs.getString("gender"), rs.getString("perAddress"), rs.getString("telephone"), rs.getString("rfc"));
            }
            conexion.closeConnection();
            return customer1;
        } else {
            conexion.closeConnection();
            return null;
        }
    }
}
