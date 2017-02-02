package boundary;

import entity.Categorie;
import entity.Ingredient;

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
    @EJB
    IngredientResource ingredientResource;

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

    @GET
    @Path("{CategorieId}/ingredients")
    public Response getAllIngredients(@PathParam("CategorieId") String categorieId, @Context UriInfo uriInfo) {
        List<Ingredient> lc = this.ingredientResource.findAll(categorieId);
        GenericEntity<List<Ingredient>> list = new GenericEntity<List<Ingredient>>(lc) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("{categorieId}/ingredients/{ingredientId}")
    public Response getOneIngredient(@PathParam("categorieId") String categorieId,
                                      @Context UriInfo uriInfo,
                                      @PathParam("ingredientId") String ingredientId) {
        Ingredient i = this.ingredientResource.findById(ingredientId);
        return Response.ok(i, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/{categorieId}/ingredients")
    public Response addIngredient(@PathParam("categorieId") String categorieId,
                                   Ingredient ingredient,
                                   @Context UriInfo uriInfo) {
        Ingredient i = this.ingredientResource.ajouteIngredient(categorieId, ingredient);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .path("/")
                .path(i.getId())
                .build();
        return Response.created(uri).entity(i).build();
    }

}
