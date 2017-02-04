package entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
@NamedQuery(name = "Categorie.FindAll",query = "SELECT c FROM Categorie c")
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String nom;
    @OneToMany
    @JsonManagedReference
    private List<Ingredient> ingredients;

    public Categorie(){
        this.ingredients = new ArrayList<>();
    }

    public Categorie(String n){
        this.nom = n;
        this.ingredients = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public String getId(){return this.id;}

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(String id){this.id = id;}

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}