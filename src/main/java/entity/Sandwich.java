package entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@XmlRootElement
@NamedQuery(name = "findAll", query = "SELECT s FROM Sandwich s")
@NamedQueries({
        @NamedQuery(name = "Sandwich.findAll", query = "SELECT s FROM Sandwich s")
})
public class Sandwich implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String nom;
    private ArrayList<Ingredient> ingredients;

    public Sandwich() {}

    public Sandwich(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
