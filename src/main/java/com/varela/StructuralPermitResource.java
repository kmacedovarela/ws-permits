package com.varela;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.kvarela.Status;
import com.kvarela.StructuralPermit;

/**
 * @author Karina Varela
 */
@Path("/structuralpermit")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
@Transactional
public class StructuralPermitResource {

    @Inject
    PersistenceHelper helper;

    @POST
    public Long post(StructuralPermit sp) {
    	sp.setStatus(Status.INPROGRESS.toString());
    	helper.getEntityManager().persist(sp);
    	System.out.println("Inserted struct. permit with id: "+sp.getId());
    	return sp.getId();
    }
    
	@GET
	@Path("{id}")
	public String getStatus(@PathParam("id") Long id)  {
		StructuralPermit result = helper.getEntityManager().find(StructuralPermit.class, id);
		System.out.println("Retrieving struct. permit with id: "+result.getId()+" status: "+ result.getStatus());
		if (result.getStatus().equalsIgnoreCase(Status.DENIED.toString())){
			System.out.println("Permit DENIED! Throwing exception");
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return result.getStatus();
	}


	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		updateStatus(id, "DELETED");
		System.out.println("Deleted elect. permit with id: "+id);
	}

	@PUT
	@Path("{id}/{status}")
	public void update(@PathParam("id") Long id, @PathParam("status") String status) {
		updateStatus(id, status);
		System.out.println("Updated struc. permit with id: "+id+" status: "+status);
	}
	
	private void updateStatus(Long id, String status){
		StructuralPermit sp = helper.getEntityManager().find(StructuralPermit.class, id);
		sp.setStatus(status);

		helper.getEntityManager().merge(sp);
	}

}
