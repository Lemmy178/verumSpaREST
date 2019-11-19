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
import com.verum.spa.dao.DAOEmployee;
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
import com.verum.spa.model.Employee;
import com.verum.spa.core.JsonResponses;

@Path("Employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeREST extends Application {

    DAOEmployee daoEmployee = new DAOEmployee();
    String value = "";
    boolean flag = false;

    @Path("add")
    @POST
    public Response addEmployee(Employee employee) throws ClassNotFoundException, SQLException {
        if (daoEmployee.addEmployee(employee)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @Path("modify")
    @PUT
    public Response modifyCustomer(Employee employee) throws ClassNotFoundException, SQLException {
        if (daoEmployee.modifyEmployee(employee)) {
            flag = true;
            return Response.ok(JsonResponses.jsonResponse(flag)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(flag)).build();
        }
    }

    @Path("logDelete")
    @PUT
    public Response deleteCustomer(Employee employee) throws ClassNotFoundException, SQLException {
        if (daoEmployee.deleteEmployee(employee)) {
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
        ArrayList<Employee> employees = new ArrayList();
        employees = daoEmployee.employeeList();
        if (value != null) {
            return Response.ok(employees).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson("No se encontraron empleados para mostrar!")).build();
        }
    }
}
