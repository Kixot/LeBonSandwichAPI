package boundary;

import entity.Categorie;
import entity.Ingredient;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/ingredient")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class IngredientRepresentation {

    @EJB
    IngredientResource ingredientResource;

    @GET
    @Path("/{ingredientId}")
    public Response getIngredient(@PathParam("ingredientId") String ingredientId){
        Ingredient i = this.ingredientResource.findById(ingredientId);
        if(i != null)
            return Response.ok(i).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    @Path("/{ingredientId}/categorie")
    public Response getCategorie(@PathParam("ingredientId") String ingredientId){
        Ingredient i = this.ingredientResource.findById(ingredientId);
        Categorie c = i.getCategorie();
        if(c != null)
            return Response.ok(c).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{ingredientId}")
    public void deleteIngredient(@PathParam("ingredientId") String id) {
        this.ingredientResource.delete(id);
    }

}
