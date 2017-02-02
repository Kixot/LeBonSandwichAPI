package entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@XmlRootElement
@NamedQuery(name = "Commande.findAll",query = "SELECT c FROM Commande c")
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private Date dateRetrait;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")
    @JsonManagedReference
    private List<Sandwich> sandwichs;

    public Commande(){
        this.sandwichs = new ArrayList<>();
    }

    public Commande(ArrayList<Sandwich> s){
        this.sandwichs = s;
    }

    public String getId(){return this.id;}

    public void setId(String id){this.id = id;}

    public List<Sandwich> getSandwichs() {
        return sandwichs;
    }

    public void setSandwichs(List<Sandwich> sandwichs) {
        this.sandwichs = sandwichs;
    }

    public Date getDateRetrait() {
        return dateRetrait;
    }

    public void setDateRetrait(Date dateRetrait) {
        this.dateRetrait = dateRetrait;
    }
}