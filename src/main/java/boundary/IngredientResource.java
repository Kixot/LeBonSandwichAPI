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
    private EntityManager em;

    public Ingredient findById(String id){
        return this.em.find(Ingredient.class, id);
    }

    public List<Ingredient> findAll(){
        Query query = this.em.createNamedQuery("Ingredient.FindAll", Ingredient.class);
        return query.getResultList();
    }
}
