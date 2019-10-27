/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.core.JsonResponses;
import com.verum.spa.dao.DAOProduct;
import java.sql.SQLException;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ruben
 */
@Path("product")
public class ProductRest {

    //Global variables
    DAOProduct daoPro = new DAOProduct();
    String value = "";
    boolean flag = false;

    @POST
    @Path("add")
    public Response addProduct(Request request) throws ClassNotFoundException, SQLException {
        String proName = request.queryParams("username");
        String proBrand;
        String proPrice;
        if (daoPro.addProduct(proName, proBrand, proPrice)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    public Response modifyProduct(@QueryParam("proName") String proName,
            @QueryParam("proBrand") String proBrand,
            @QueryParam("proPrice") Double proPrice) throws ClassNotFoundException, SQLException {
        if (daoPro.modifyProduct(proName, proBrand, proPrice)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @DELETE
    public Response deleteProduct(@QueryParam("idPro") int idPro) throws ClassNotFoundException, SQLException {
        if (daoPro.deleteProduct(idPro)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @GET
    @Path("productList")
    public Response productList(@DefaultValue("1") @QueryParam("prefVis") boolean prefVis) throws SQLException, ClassNotFoundException {
        value = new Gson().toJson(daoPro.productList(prefVis));
        if (value != null) {
            return Response.ok(value).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("No se encontraron productos para mostrar.")).build();
        }

    }
}
