package boundary;

import entity.Taille;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TailleResource {

    @PersistenceContext
    EntityManager em;


    public Taille findById(String id){
        return this.em.find(Taille.class,id);
    }

    public List<Taille> findAll() {
        Query q = this.em.createNamedQuery("Taille.FindAll", Taille.class);
        return q.getResultList();
    }

    public Taille ajouterTaille(Taille taille){
        taille.setId(UUID.randomUUID().toString());
        this.em.persist(taille);
        return taille;
    }

    public Taille save(Taille sdw) {
        sdw.setId(UUID.randomUUID().toString());
        return this.em.merge(sdw);
    }

    public void delete(String id) {
        try {
            Taille ref = this.em.getReference(Taille.class, id);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {}
    }
}