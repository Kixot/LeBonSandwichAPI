package boundary;

import entity.Categorie;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/categorie")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class CategorieRepresentation {

    @EJB
    CategorieResource CategorieResource;

    @GET
    public Response getAllCategorie(@Context UriInfo uriInfo){
        List<Categorie> list_Categorie = this.CategorieResource.findAll();
        GenericEntity<List<Categorie>> list = new GenericEntity<List<Categorie>>(list_Categorie) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{CategorieId}")
    public Response getCategorie(@PathParam("CategorieId") String CategorieId){
        Categorie c = this.CategorieResource.findById(CategorieId);
        if(c != null)
            return Response.ok(c).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addCategorie(Categorie c, @Context UriInfo u){
        Categorie Categorie = this.CategorieResource.save(c);
        URI uri = u.getAbsolutePathBuilder().path(Categorie.getId()).build();
        return Response.created(uri).entity(Categorie).build();
    }

    @DELETE
    @Path("/{CategorieId}")
    public void deleteCategorie(@PathParam("CategorieId") String id) {
        this.CategorieResource.delete(id);
    }

}
