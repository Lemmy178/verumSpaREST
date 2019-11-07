/*=============================================================================
 |       Author:  Moises Morua Lopez
 |       Course:  Spa
 |     Due Date:  11/03/2019
 |  Description:  Branch Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/
package com.verum.spa.restServices;

import com.google.gson.Gson;
import com.verum.spa.core.JsonResponses;
import com.verum.spa.dao.DAOBranch;
import com.verum.spa.model.Branch;
import java.sql.SQLException;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author moi_3
 */
@Path("branch")
public class BranchRest {
    
    DAOBranch daoBranch = new DAOBranch();   
    
    
    @POST
    @Path("add")
    public Response addProduct(Branch branch) throws ClassNotFoundException, SQLException {
        if (daoBranch.addBranch(branch.getBranchName(), branch.getBranchAddress(), branch.getLatitude(),branch.getLongitude(), true)) {            
            return Response.ok(JsonResponses.jsonResponse(true)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(false)).build();
        }
    }
       
    @PUT
    public Response modifyBranch(@QueryParam("branchId") String branchId,@QueryParam("branchName") String branchName,@QueryParam("branchAddress") String branchAddress,@QueryParam("latitude") String latitude,@QueryParam("longitude") String longitude,@QueryParam("branchStatus") String branchStatus) throws ClassNotFoundException, SQLException {    
        if(daoBranch.modifyBranch(Integer.parseInt(branchId),branchName,branchAddress,Double.parseDouble(latitude),Double.parseDouble(longitude), branchStatus.equals("1"))) {        
            return Response.ok(JsonResponses.jsonResponse(true)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(false)).build();
        }
    }
    
    @DELETE
    public Response deleteProduct(@QueryParam("idPro") int idPro) throws ClassNotFoundException, SQLException {
        if (daoBranch.changeStatusBranch(idPro,false)) {            
            return Response.ok(JsonResponses.jsonResponse(true)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(JsonResponses.jsonResponse(false)).build();
        }
    }

    @GET
    @Path("branchList")
    public Response productList(@DefaultValue("1") @QueryParam("prefVis") boolean prefVis) throws SQLException, ClassNotFoundException {        
        String value = new Gson().toJson((daoBranch.branchList(prefVis)));                
        if (value != null) {
            return Response.ok(value).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new Gson().toJson(null)).build();
        }
    }
        
}