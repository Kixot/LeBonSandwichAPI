package boundary;

import entity.Sandwich;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sandwich")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class SandwichRepresentation {

    @EJB
    SandwichResource sandwichResource;

    @GET
    @Path("/{sandwichId}")
    public Response getSandwich(@PathParam("sandwichId") String sandwichId) {
        Sandwich s = this.sandwichResource.findById(sandwichId);
        if (s != null)
            return Response.ok(s).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}