package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.core.JsonResponses;
import com.verum.spa.dao.DAORoom;
import com.verum.spa.model.Room;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("roomR")
public class pruebas {

    @POST
    @Path("addRoom")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(Room room) {
        String value = "";
        boolean flag = false;
        DAORoom daoRoom = new DAORoom();
        try {
            if (daoRoom.addRoom(room)) {
                flag = true;
                return Response.ok(JsonResponses.jsonResponse(flag)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @PUT
    @Path("modifyRoom")
    @Consumes(MediaType.APPLICATION_JSON)
    public void modifyRoomR(Room room) {
        DAORoom daoRoom = new DAORoom();
        try {
            daoRoom.modifyRoom(room);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PUT
    @Path("deleteRoom")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteRoom(Room room) {
        DAORoom daoRoom = new DAORoom();
        try {
            daoRoom.deleteRoom(room);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("roomList")
    public Response RoomList(@DefaultValue("0") @QueryParam("prefVis") int prefVis) throws SQLException, ClassNotFoundException {
        DAORoom daoRoom = new DAORoom();
        ArrayList<Room> room;
        room = daoRoom.roomList(prefVis);
        if (room != null) {
            return Response.ok(room).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("No se encontraron salas para mostrar.")).build();
        }
    }
}
