package boundary;

import entity.Commande;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CommandeResource {

    @PersistenceContext
    private EntityManager em;


    public Commande findById(String id){
        return this.em.find(Commande.class,id);
    }

    public List<Commande> findAll() {
        Query q = this.em.createNamedQuery("Commande.findAll", Commande.class);
        return q.getResultList();
    }

    public Commande save(Commande c) {
        c.setId(UUID.randomUUID().toString());
        return this.em.merge(c);
    }

    public void delete(String id) {
        try {
            Commande ref = this.em.getReference(Commande.class, id);
            this.em.remove(ref);
        } catch (EntityNotFoundException e) {}
    }
}