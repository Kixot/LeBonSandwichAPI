package boundary;

import entity.Pain;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PainResource {

    @PersistenceContext
    EntityManager em;


    public Pain findById(String id){
        return this.em.find(Pain.class,id);
    }

    public List<Pain> findAll() {
        Query q = this.em.createNamedQuery("Pain.FindAll", Pain.class);
        return q.getResultList();
    }

    public Pain save(Pain sdw) {
        sdw.setId(UUID.randomUUID().toString());
        return this.em.merge(sdw);
    }

    public void delete(String id) {
        try {
            Pain ref = this.em.getReference(Pain.class, id);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {}
    }
}