package boundary;

import entity.Ingredient;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class IngredientResource {

    @PersistenceContext
    EntityManager em;

    public Ingredient findById(String id){
        return this.em.find(Ingredient.class, id);
    }

    public List<Ingredient> findAll(String ingredientId){
        Query query = em.createQuery("SELECT i FROM Ingredient i");
        query.setParameter("id", ingredientId);
        List<Ingredient> liste = query.getResultList();
        return liste;
    }
}