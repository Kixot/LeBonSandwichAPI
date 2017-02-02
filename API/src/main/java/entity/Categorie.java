package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
@NamedQuery(name = "Categorie.FindAll",query = "SELECT c FROM Categorie c")
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String nom;

    public Categorie(){

    }

    public Categorie(String n){
        this.nom = n;
    }

    public String getNom() {
        return nom;
    }

    public String getId(){return this.id;}

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(String id){this.id = id;}
}