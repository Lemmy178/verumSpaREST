/*=============================================================================
 |       Author:  Ricrdo
 |       Course:  Spa
 |     Due Date:  11/05/2019
 |  Description:  DAO Model
 |                
 | Deficiencies:  
 *===========================================================================*/
package com.verum.spa.dao;

import com.verum.spa.model.ConexionSpaMYSQL;
import com.verum.spa.model.Room;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAORoom {

    private ConexionSpaMYSQL conexion = new ConexionSpaMYSQL();
    private String sql = "";
    private PreparedStatement pst;

    public boolean addRoom(Room room) throws ClassNotFoundException, SQLException {
        sql = "INSERT INTO ROOM(roomName,roomDesc,photo,roomStatus,brancId) VALUES (?,?,?," + 1 + ")";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, room.getRoomName());
        pst.setString(2, room.getRoomDesc());
        pst.setString(3, room.getPhoto());
        pst.setInt(4, room.getBranchId());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean modifyRoom(Room room) throws ClassNotFoundException, SQLException {
        sql = "UPDATE ROOM SET roomName = ?, roomDesc=?,photo= ?, brancId= ? WHERE roomId = ?";
        pst = conexion.startConnection().prepareStatement(sql);

        pst.setString(1, room.getRoomName());
        pst.setString(3, room.getRoomDesc());
        pst.setString(2, room.getPhoto());
        pst.setInt(4, room.getBranchId());
        pst.setInt(5, room.getRoomId());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public boolean deleteRoom(Room room) throws ClassNotFoundException, SQLException {
        sql = "UPDATE ROOM SET roomStatus = ? WHERE roomId = ?";

        pst = conexion.startConnection().prepareStatement(sql);
        pst.setInt(1, room.getRoomStatus());
        pst.setInt(2, room.getRoomId());

        if (pst.executeUpdate() > 0) {
            conexion.closeConnection();
            return true;
        } else {
            conexion.closeConnection();
            return false;
        }
    }

    public ArrayList<Room> roomList(int preVis) throws SQLException, ClassNotFoundException {
        ResultSet rs;
        ArrayList<Room> roomData = new ArrayList<>();

        sql = "SELECT * FROM ROOM";
        Class.forName(conexion.getDRIVER());
        pst = conexion.startConnection().prepareStatement(sql);

        rs = pst.executeQuery();

        if (rs.first()) {
            rs.beforeFirst();
            while (rs.next()) {
                roomData.add(new Room(rs.getInt("roomId"), rs.getString("roomName"), rs.getString("roomDesc"),
                        rs.getString("photo"), rs.getInt("roomStatus"), rs.getInt("brancId")));
            }
            conexion.closeConnection();
            return roomData;
        } else {
            return null;
        }
    }

}
