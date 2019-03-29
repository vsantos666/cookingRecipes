package cookingRecipes.service.impl;

import cookingRecipes.model.Recipe;
import cookingRecipes.model.User;
import cookingRecipes.repositories.RecipeRepository;
import cookingRecipes.repositories.UserRepository;
import cookingRecipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RecipeRepository recipeRepository;

    /**
     * Method to add a recipe
     * @param recipe
     * @param user
     * @return
     */

    @Transactional
    @Override
    public String addRecipe(Recipe recipe, String user) {
        try {
           /* Recipe recipe1 = recipeRepository.findUserByUserIdAndFriendId(recipe.getUserId().getId(), recipe.getFriendId().getId());
            if(recipe1 !=null){
                return "The recipe is already aggregated!!";
            }*/
            recipe.setCreatedBy(user);
            User user1=userRepository.findOne(recipe.getUser().getId());
            recipe.setUser(user1);
            recipeRepository.saveAndFlush(recipe);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Method to modify a recipe
     * @param recipe
     * @param user
     * @return
     */
    @Transactional
    @Override
    public String updateRecipe(Recipe recipe, String user) {
        try {
            Recipe recipe1 = recipeRepository.findOne(recipe.getId());
            if(recipe1 !=null){
                recipe1.setIngredients(recipe.getIngredients());
                recipe1.setName(recipe.getName());
                recipe1.setPreparation(recipe.getPreparation());
                recipe1.setUpdatedBy(user);
                recipeRepository.saveAndFlush(recipe1);
                return null;
            }else{
                return "There is no data.";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    /**
     * Method to get all users
     * @return List<User> lista de usuarios
     */
    @Override
    public List<Recipe> getAllRecipes(int page, int size) {
        try {
            Pageable pageable = new  PageRequest(page,size);
            List<Recipe> recipeList = recipeRepository.findAllByDisabled(false,pageable);
            return recipeList;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method to get all users
     * @return List<User> lista de usuarios
     */
    @Override
    public List<Recipe> getRecipesByName(int page, int size,String name) {
        try {
            Pageable pageable = new  PageRequest(page,size);
            List<Recipe> recipeList =  recipeRepository.findAllByDisabledAndNameContains(false,name,pageable);
            return recipeList;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method to delete a recipe
     * @param recipe
     * @param user
     * @return
     */
    @Transactional
    @Override
    public String disableRecipe(Recipe recipe, String user) {
        try {
            Recipe recipe1 = recipeRepository.findOne(recipe.getId());
            recipe1.setDisabled(true);
            recipe1.setUpdatedBy(user);
            recipeRepository.saveAndFlush(recipe);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
