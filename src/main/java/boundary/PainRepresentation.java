package boundary;

import entity.Pain;
import java.net.URI;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

@Path("/pain")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless

public class PainRepresentation {
    @EJB
    PainResource painResource;

    @GET
    public Response getAllPain(@Context UriInfo uriInfo){
        List<Pain> list_Pain = this.painResource.findAll();
        GenericEntity<List<Pain>> list = new GenericEntity<List<Pain>>(list_Pain) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{PainId}")
    public Response getPain(@PathParam("PainId") String painId, @Context UriInfo uriInfo) {
        Pain pain = this.painResource.findById(painId);
        if (pain != null) {
            return Response.ok(pain).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response addPain(Pain pain, @Context UriInfo uriInfo) {
        Pain newPain = this.painResource.save(pain);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newPain.getId()).build();
        return Response.created(uri)
                .entity(newPain)
                .build();
    }

    @DELETE
    @Path("/{PainId}")
    public void deletePain(@PathParam("PainId") String id) {
        this.painResource.delete(id);
    }
}