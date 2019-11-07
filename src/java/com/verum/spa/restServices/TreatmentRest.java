/*=============================================================================
 |       Author:  Richard
 |       Course:  Spa
 |     Due Date:  11/05/2019
 |  Description:  Product Model
 |                
 | Deficiencies:   

                http://localhost:8080/VerumRESTSpa/api/treatment/
 *===========================================================================*/
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.core.JsonResponses;
import com.verum.spa.dao.DAOTreatment;
import com.verum.spa.model.Treatment;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("treatment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TreatmentRest {

    DAOTreatment daoTreat = new DAOTreatment();
    String value = "";
    boolean flag = false;

    @POST
    @Path("add")
    public Response addTreatment(Treatment treat) throws ClassNotFoundException, SQLException {
        if(daoTreat.addTreatment(treat)){
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("modify")
    public Response modifyTreatment(Treatment treat) throws ClassNotFoundException, SQLException {
        if(daoTreat.modifyTreatment(treat)){
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("logDelete")
    public Response deleteTreatment(Treatment treat) throws ClassNotFoundException, SQLException {
        if(daoTreat.addTreatment(treat)){
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @GET
    @Path("TreatmentList")
    public Response TreatmentList(@DefaultValue("0") @QueryParam("prefVis") int prefVis) throws SQLException, ClassNotFoundException {
        ArrayList<Treatment> treat = daoTreat.roomList(prefVis);
        if(treat != null){
            return Response.ok(treat).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("No se encontraron salas para mostrar.")).build();
        }
    }
}
