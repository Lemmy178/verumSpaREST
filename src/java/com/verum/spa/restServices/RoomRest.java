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

import com.verum.spa.model.Room;
import java.sql.SQLException;
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

    String value = "";
    boolean flag = false;
//
//    @POST
//    @Path("add")
//    public Response addRoom(Room pro) throws ClassNotFoundException, SQLException {
//
//    }
//
//    @PUT
//    @Path("modify")
//    public Response modifyRoom(Room pro) throws ClassNotFoundException, SQLException {
//
//    }
//
//    @PUT
//    @Path("logDelete")
//    public Response deleteRoom(Room pro) throws ClassNotFoundException, SQLException {
//
//    }
//
//    @GET
//    @Path("roomList")
//    public Response RoomList(@DefaultValue("0") @QueryParam("prefVis") int prefVis) throws SQLException, ClassNotFoundException {
//
//    }
}
