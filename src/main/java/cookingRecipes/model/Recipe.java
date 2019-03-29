package cookingRecipes.model;

import cookingRecipes.model.base.BaseEntityAudit;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="RECIPES",schema = "recipes")
@XmlRootElement
public class Recipe extends BaseEntityAudit {


    @Column(name="NAME", length = 100, nullable = false)
    private String name;

    @Column(name="INGREDIENTS", nullable = false)
    private String ingredients;

    @Column(name="PREPARATION", nullable = false)
    private String preparation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;


    public Recipe() {
    }

    public Recipe(String name, String ingredients, String preparation, User user) {
        this.name = name;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
