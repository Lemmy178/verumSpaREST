/*=============================================================================
 |       Author:  Edson Mesraim Santos Perez
 |       Course:  Spa
 |     Due Date:  11/06/2019
 |  Description:  Employee Model
 |                
 | Deficiencies:  No funcionó ningun metodo de modificar.
 *===========================================================================*/
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.dao.DAOCustomer;
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
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.verum.spa.model.Customer;
import com.verum.spa.core.JsonResponses;

@Path("Customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerREST extends Application {

    DAOCustomer daoCustomer = new DAOCustomer();
    String value = "";
    boolean flag = false;

    @Path("add")
    @POST
    public Response addEmployee(Customer customer) throws ClassNotFoundException, SQLException {
        if (daoCustomer.addCustomer(customer)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @Path("modify")
    @PUT
    public Response modifyCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        if (daoCustomer.modifyCustomer(customer)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @Path("logDelete")
    @PUT
    public Response deleteCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        if (daoCustomer.deleteCustomer(customer)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @Path("list")
    @GET
    public Response customerListAll(@DefaultValue("0") @QueryParam("prefVis") int prefVis) throws SQLException, ClassNotFoundException {
//      value = new Gson().toJson(daoEmp.employeeList(prefVis));
        ArrayList<Customer> customers = new ArrayList();
        customers = daoCustomer.customerList();
        if (value != null) {
            return Response.ok(customers).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("No se encontraron empleados para mostrar!")).build();
        }
    }

}
