package cookingRecipes.controllers;

import cookingRecipes.model.Recipe;
import cookingRecipes.service.RecipeService;
import cookingRecipes.service.UserService;
import cookingRecipes.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult addFriend(@RequestHeader("User") String user,@RequestHeader("Token") String token,
                         @Valid @RequestBody Recipe recipe) {
        try{
            if(userService.validateToken(token)==null){
                return new JsonResult(false, null, "Invalid Token");
            }
            String result = recipeService.addRecipe(recipe,user);
            if(result == null ){
                return new JsonResult(true, null, "Successful Create.");
            }else {
                return new JsonResult(false, null, result);
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody
    JsonResult updateRecipe(@RequestHeader("User") String user, @RequestHeader("Token") String token,
                          @Valid @RequestBody Recipe recipe) {
        try{
            if(userService.validateToken(token)==null){
                return new JsonResult(false, null, "Invalid token");
            }
            String result = recipeService.updateRecipe(recipe,user);
            if(result == null){
                return new JsonResult(true, null, "Successful update.");
            }else {
                return new JsonResult(false, null, result);
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(path = "/{page}/{size}", method = RequestMethod.GET)
    public @ResponseBody
    JsonResult getAllRecipes(@RequestHeader("Token") String token,@PathVariable int page,@PathVariable int size) {
        try{
            if(userService.validateToken(token)==null){
                return new JsonResult(false, null, "Invalid token");
            }
            List<Recipe> recipeList = recipeService.getAllRecipes(page,size);
            if(recipeList == null){
                return new JsonResult(false, null, "Error to get the recipes.");
            }else {
                return new JsonResult(true, recipeList, "");
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(path = "/{page}/{size}/{name}",method = RequestMethod.GET)
    public @ResponseBody
    JsonResult getRecipesByName(@RequestHeader("User") String user,@RequestHeader("Token") String token,
                                @PathVariable int page,@PathVariable int size,@PathVariable String name) {
        try{
            if(userService.validateToken(token)==null){
                return new JsonResult(false, null, "Invalid token");
            }
            List<Recipe> recipeList = recipeService.getRecipesByName(page,size,name);
            if(recipeList == null){
                return new JsonResult(false, null, "Error to get the recipes.");
            }else {
                return new JsonResult(true, recipeList, "");
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public @ResponseBody
    JsonResult disableFriend(@RequestHeader("User") String user, @RequestHeader("Token") String token,
                             @Valid @RequestBody Recipe recipe) {
        try{
            if(userService.validateToken(token)==null){
                return new JsonResult(false, null, "Invalid Token");
            }
            String result = recipeService.disableRecipe(recipe,user);
            if(result == null ){
                return new JsonResult(true, null, "Successful Delete.");
            }else {
                return new JsonResult(false, null, result);
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }
}
