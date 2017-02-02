package boundary;

import entity.Ingredient;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/ingredient")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class IngredientRepresentation {

    @EJB
    IngredientResource ingredientResource;

    @GET
    public Response getAllIngredient(@Context UriInfo uriInfo){
        List<Ingredient> list_ingredient = this.ingredientResource.findAll();
        GenericEntity<List<Ingredient>> list = new GenericEntity<List<Ingredient>>(list_ingredient) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{ingredientId}")
    public Response getIngredient(@PathParam("ingredientId") String ingredientId){
        Ingredient i = this.ingredientResource.findById(ingredientId);
        if(i != null)
            return Response.ok(i).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addIngredient(Ingredient i, @Context UriInfo u){
        Ingredient ingredient = this.ingredientResource.save(i);
        URI uri = u.getAbsolutePathBuilder().path(ingredient.getId()).build();
        return Response.created(uri).entity(ingredient).build();
    }

    @DELETE
    @Path("/{ingredientId}")
    public void deleteIngredient(@PathParam("ingredientId") String id) {
        this.ingredientResource.delete(id);
    }

}
