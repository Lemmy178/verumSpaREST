/*=============================================================================
 |       Author:  Richard
 |       Course:  Spa
 |     Due Date:  11/05/2019
 |  Description:  Product Model
 |                
 | Deficiencies:   

                http://localhost:8080/VerumRESTSpa/api/room/
 *===========================================================================*/
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.core.JsonResponses;
import com.verum.spa.dao.DAORoom;
import com.verum.spa.model.Room;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("room")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomRest {
    
    DAORoom daoRoom = new DAORoom();
    String value = "";
    boolean flag = false;

    @POST
    @Path("add")
    public Response addRoom(Room room) throws ClassNotFoundException, SQLException {
        if (daoRoom.addRoom(room)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("modify")
    public Response modifyRoom(Room room) throws ClassNotFoundException, SQLException {
        if (daoRoom.modifyRoom(room)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("logDelete")
    public Response deleteRoom(Room room) throws ClassNotFoundException, SQLException {
        if (daoRoom.deleteRoom(room)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @GET
    @Path("roomList")
    public Response RoomList(@DefaultValue("0") @QueryParam("prefVis") int prefVis) throws SQLException, ClassNotFoundException {
        ArrayList<Room> room;
        room = daoRoom.roomList(prefVis);
        if (room != null) {
            return Response.ok(room).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("No se encontraron salas para mostrar.")).build();
        }
    }
}
