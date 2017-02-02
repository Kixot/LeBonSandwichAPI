package boundary;

import entity.Categorie;
import entity.Ingredient;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Stateless
public class IngredientResource {

    @PersistenceContext
    private EntityManager em;

    public Ingredient findById(String id){
        return this.em.find(Ingredient.class, id);
    }

    public List<Ingredient> findAll(String categorieId){
        Query query = this.em.createQuery("SELECT i FROM Ingredient i where i.categorie.id= :id ");
        query.setParameter("id", categorieId);
        return query.getResultList();
    }

    public Ingredient save(Ingredient i){
        i.setId(UUID.randomUUID().toString());
        return this.em.merge(i);
    }

    public void delete(String id) {
        try {
            Ingredient ref = this.em.getReference(Ingredient.class, id);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {}
    }

    public Ingredient ajouteIngredient(String categorieId, Ingredient ingredient) {
        Ingredient i = new Ingredient(ingredient.getNom(), ingredient.getCategorie());
        i.setId(UUID.randomUUID().toString());
        i.setCategorie(this.em.find(Categorie.class, categorieId));
        this.em.persist(i);
        return i;
    }
}
