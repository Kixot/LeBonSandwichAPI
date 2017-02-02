package boundary;

import entity.Taille;
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

@Path("/taille")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless

public class TailleRepresentation {
    @EJB
    TailleResource tailleResource;

    @GET
    public Response getAllTaille(@Context UriInfo uriInfo){
        List<Taille> list_Taille = this.tailleResource.findAll();
        GenericEntity<List<Taille>> list = new GenericEntity<List<Taille>>(list_Taille) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{TailleId}")
    public Response getTaille(@PathParam("TailleId") String tailleId, @Context UriInfo uriInfo) {
        Taille taille = this.tailleResource.findById(tailleId);
        if (taille != null) {
            return Response.ok(taille).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response addTaille(Taille taille, @Context UriInfo uriInfo) {
        Taille newTaille = this.tailleResource.save(taille);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newTaille.getId()).build();
        return Response.created(uri)
                .entity(newTaille)
                .build();
    }

    @DELETE
    @Path("/{TailleId}")
    public void deleteTaille(@PathParam("TailleId") String id) {
        this.tailleResource.delete(id);
    }
}