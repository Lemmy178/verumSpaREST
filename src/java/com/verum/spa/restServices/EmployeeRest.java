/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.core.JsonResponses;
import com.verum.spa.dao.DAOEmployee;
import com.verum.spa.model.Employee;
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

@Path("employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeRest {

    DAOEmployee daoEmp = new DAOEmployee();
    String value = "";
    boolean flag = false;

    @POST
    @Path("add")
    public Response addEmployee(Employee emp) throws ClassNotFoundException, SQLException {
        if (daoEmp.addEmployee(emp)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("modify")
    public Response modifyEmployee(Employee emp) throws ClassNotFoundException, SQLException {
        if (daoEmp.modifyEmployee(emp)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @PUT
    @Path("logDelete")
    public Response deleteEmployee(Employee emp) throws ClassNotFoundException, SQLException {
        if (daoEmp.deleteEmployee(emp)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @GET
    @Path("employeeListAll")
    public Response employeeListAll(@DefaultValue("0") @QueryParam("prefVis") int prefVis) throws SQLException, ClassNotFoundException {
//      value = new Gson().toJson(daoEmp.employeeList(prefVis));
        ArrayList<Employee> emps = new ArrayList();
        emps = daoEmp.employeeList();
        if (value != null) {
            return Response.ok(emps).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("No se encontraron empleados para mostrar!")).build();
        }
    }

    @GET
    @Path("employeeListUnique")
    public Response employeeListUnique(Employee emp) throws ClassNotFoundException, SQLException {
        Employee empR = new Employee();
        empR = daoEmp.employeeSearch(emp);
        if (empR != null) {
            return Response.ok(empR).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("Error al cargar el empleado seleccionado!")).build();
        }
    }
}