package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement
@NamedQuery(name = "Ingredient.FindAll",query = "SELECT i FROM Ingredient i")
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String nom;
    @ManyToOne
    private Categorie categorie;
    @ManyToOne
    @JsonBackReference
    private Sandwich sandwich;

    public Ingredient(){}

    public Ingredient(String n, Categorie cat){
        this.nom = n;
        this.categorie = cat;
    }

    public String getNom() {
        return nom;
    }

    public String getId(){return id;}

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(String id){this.id = id;}

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}