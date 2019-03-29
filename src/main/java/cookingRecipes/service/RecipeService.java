package cookingRecipes.service;

import cookingRecipes.model.Recipe;

import java.util.List;

public interface RecipeService {

    public String addRecipe(Recipe recipe, String user);

    public String updateRecipe(Recipe recipe, String user);

    public List<Recipe> getAllRecipes(int page, int size);

    public List<Recipe> getRecipesByName(int page, int size,String name);

    public String disableRecipe(Recipe recipe, String user);
}
