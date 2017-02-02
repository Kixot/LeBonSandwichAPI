package boundary;

import entity.Categorie;
import entity.Commande;
import entity.Ingredient;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/commandes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class CommandeRepresentation {

    @EJB
    CommandeResource commandeResource;
    @EJB
    SandwichResource sandwichResource;

    @GET
    public Response getAllCommandes(@Context UriInfo uriInfo){
        List<Commande> list_Commande = this.commandeResource.findAll();
        GenericEntity<List<Commande>> list = new GenericEntity<List<Commande>>(list_Commande) {
        };
        return Response.ok(list, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{commandeId}")
    public Response getCommande(@PathParam("commandeId") String commandeId){
        Commande c = this.commandeResource.findById(commandeId);
        if(c != null)
            return Response.ok(c).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addCommande(Commande c, @Context UriInfo u){
        Commande commande = this.commandeResource.save(c);
        URI uri = u.getAbsolutePathBuilder().path(commande.getId()).build();
        // TODO return token
        return Response.created(uri).entity(commande).build();
    }

}
