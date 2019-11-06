/*=============================================================================
 |       Author:  Ruben
 |       Course:  Spa
 |     Due Date:  10/18/2019
 |  Description:  Product Model
 |                
 | Deficiencies:  Falta de acentos en respuestas.

                http://localhost:8080/VerumRESTSpa/api/product
 *===========================================================================*/
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.core.JsonResponses;
import com.verum.spa.dao.DAOProduct;
import com.verum.spa.model.Product;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductRest {

    //Global variables
    DAOProduct daoPro = new DAOProduct();
    String value = "";
    boolean flag = false;

    @POST
    @Path("add")
    public Response addProduct(Product pro) throws ClassNotFoundException, SQLException {
        if (daoPro.addProduct(pro)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("modify")
    public Response modifyProduct(Product pro) throws ClassNotFoundException, SQLException {
        if (daoPro.modifyProduct(pro)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("logDelete")
    public Response deleteProduct(Product pro) throws ClassNotFoundException, SQLException {
        if (daoPro.deleteProduct(pro)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @GET
    @Path("productList")
    public Response productList(@DefaultValue("0") @QueryParam("prefVis") int prefVis) throws SQLException, ClassNotFoundException {
        /*Se agrega un parametro, para mostrar productos activos o inactivos. Por defecto, siempre se muestran
       solo activos (0).
        Si la aplicacion manda 1 quiere decir que se muestre tanto activos como inactivos
         */
//        value = new Gson().toJson(daoPro.productList(prefVis));
        ArrayList<Product> pro = new ArrayList();
        pro = daoPro.productList(prefVis);
//        value = daoPro.productList(prefVis);
        if (value != null) {
            return Response.ok(pro).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("No se encontraron productos para mostrar.")).build();
        }
    }
}
