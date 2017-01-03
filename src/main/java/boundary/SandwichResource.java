package boundary;

import entity.Sandwich;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class SandwichResource {

    @PersistenceContext
    EntityManager em;

    public Sandwich findById(String id){
        return this.em.find(Sandwich.class, id);
    }

    public List<Sandwich> findAll(String sandwichId) {
        Query query = em.createQuery("SELECT s FROM Sandwich s where s.sandwich.id= :id ");
        query.setParameter("id", sandwichId);
        List<Sandwich> liste = query.getResultList();
        return liste;
    }
}
