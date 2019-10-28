/*=============================================================================
 |       Author:  Erick Ruben Ramos Vazquez
 |       Course:  Spa
 |     Due Date:  10/18/2019
 |  Description:  DAO Model
 |                
 | Deficiencies:  Sentencias de my sql sin cambiar...
                  solo funciona insert y list
 *===========================================================================*/
package com.verum.spa.dao;

import com.verum.spa.model.ConexionSpaMYSQL;
import com.verum.spa.model.Product;
import java.sql.*;
import java.util.ArrayList;

public class DAOProduct {

    private ConexionSpaMYSQL conexion = new ConexionSpaMYSQL();
    private String sql = "";
    private PreparedStatement pst;

    public boolean addProduct(String proName, String proBrand, double proPrice) throws ClassNotFoundException, SQLException {
        sql = "INSERT INTO PRODUCT(proName,proBrand,proPrice,proStatus) VALUES (?,?,?," + 1 + ")";

        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, proName);
        pst.setString(2, proBrand);
        pst.setDouble(3, proPrice);

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean addProduct2(String proName, String proBrand, double proPrice) throws ClassNotFoundException, SQLException {
        ConexionSpaMYSQL cone = new ConexionSpaMYSQL();
        Connection conn;
        PreparedStatement pst;
        sql = "INSERT INTO PRODUCT(proName,proBrand,proPrice,proStatus) VALUES (?,?,?," + 1 + ")";
        Class.forName(cone.getDRIVER());
        conn = DriverManager.getConnection(cone.getPATH(), cone.getUSER(), cone.getPASS());
        pst = conn.prepareStatement(sql);
        pst.setString(1, proName);
        pst.setString(2, proBrand);
        pst.setDouble(3, proPrice);
        pst.executeUpdate();
        conn.close();
        return true;
    }

    public boolean modifyProduct(String proName, String proBrand, double proPrice) throws ClassNotFoundException, SQLException {
        sql = "CALL MODIFYPRODUCT(?,?,?)";

        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, proName);
        pst.setString(2, proBrand);
        pst.setDouble(3, proPrice);

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean deleteProduct(int idPro) throws ClassNotFoundException, SQLException {

        sql = "CALL MODIFYPRODUCT(?)";

        pst = conexion.startConnection().prepareStatement(sql);
        pst.setBoolean(1, false);

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public ArrayList<Product> productList(int preVis) throws SQLException, ClassNotFoundException {
        ResultSet rs;
        ArrayList<Product> productData = new ArrayList<>();
        if (preVis == 0) {
            sql = "SELECT * FROM PRODUCT";
        } else {
            sql = "SELECT * FROM PRODUCT WHERE proStatus LIKE 0";
        }

        Class.forName(conexion.getDRIVER());
        pst = conexion.startConnection().prepareStatement(sql);
        rs = pst.executeQuery();

        if (rs.first()) {
            rs.beforeFirst();
            while (rs.next()) {
                productData.add(new Product(rs.getInt("idPro"), rs.getString("proName"), rs.getString("proBrand"),
                        rs.getDouble("proPrice"), rs.getBoolean("proStatus")));
            }
            conexion.closeConnection();
            return productData;
        } else {
            return null;
        }
    }

}
