/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.core.JsonResponses;
import com.verum.spa.dao.DAOCustomer;
import com.verum.spa.model.Customer;
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

@Path("customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerRest {
    
    DAOCustomer daoCustomer = new DAOCustomer();
    String value = "";
    boolean flag = false;
    
    @POST
    @Path("add")
    public Response addEmployee(Customer customer) throws ClassNotFoundException, SQLException {
        if (daoCustomer.addCustomer(customer)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("modify")
    public Response modifyCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        if (daoCustomer.modifyCustomer(customer)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("logDelete")
    public Response deleteCustomer(Customer customer) throws ClassNotFoundException, SQLException {
        if (daoCustomer.deleteCustomer(customer)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @GET
    @Path("customerListAll")
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

    @GET
    @Path("customerListUnique")
    public Response employeeListUnique(Customer customer) throws ClassNotFoundException, SQLException {
        Customer customerR = new Customer();
        customerR = daoCustomer.customerSearch(customer);
        if (customerR != null) {
            return Response.ok(customerR).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("Error al cargar el empleado seleccionado!")).build();
        }
    }
}
