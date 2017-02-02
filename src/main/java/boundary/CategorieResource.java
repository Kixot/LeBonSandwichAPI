package boundary;

import entity.Categorie;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CategorieResource {

    @PersistenceContext
    EntityManager em;


    public Categorie findById(String id){
        return this.em.find(Categorie.class,id);
    }

    public List<Categorie> findAll() {
        Query q = this.em.createNamedQuery("Categorie.FindAll", Categorie.class);
        return q.getResultList();
    }

    public Categorie save(Categorie sdw) {
        sdw.setId(UUID.randomUUID().toString());
        return this.em.merge(sdw);
    }

    public void delete(String id) {
        try {
            Categorie ref = this.em.getReference(Categorie.class, id);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {}
    }
}