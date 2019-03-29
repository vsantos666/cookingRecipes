package cookingRecipes.controllers;

import cookingRecipes.bean.Login;
import cookingRecipes.model.User;
import cookingRecipes.service.UserService;
import cookingRecipes.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by vsantos on 28/03/2019.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    JsonResult addUser(@Valid @RequestBody User user) {
        try {
            String result = userService.saveUser(user);
            if (result == null) {
                return new JsonResult(true, null, "Successful Create.");
            } else {
                return new JsonResult(false, null, result);
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody
    JsonResult updateUser(@RequestHeader("User") String user, @RequestHeader("Token") String token,
                          @Valid @RequestBody User userData) {
         try{
             if(userService.validateToken(token)==null){
                 return new JsonResult(false, null, "Invalid token");
             }
             String result = userService.updateUser(userData,user);
            if(result == null){
                return new JsonResult(true, null, "Successful update.");
            }else {
                return new JsonResult(false, null, result);
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(path = "/{page}/{size}",method = RequestMethod.GET)
    public @ResponseBody
    JsonResult getAllUsers(@RequestHeader("Token") String token,@PathVariable int page,@PathVariable int size) {
        try{
            if(userService.validateToken(token)==null){
                return new JsonResult(false, null, "Invalid token");
            }
            List<User> userList = userService.getAllUsers(page,size);
            if(userList == null){
                return new JsonResult(false, null, "Error to get the users.");
            }else {
                return new JsonResult(true, userList, "");
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public @ResponseBody
    JsonResult userLogin(@Valid @RequestBody Login login) {
        try{
            String respuesta = userService.loginUser(login);
            if(respuesta != null ){
                return new JsonResult(true, respuesta, "Success");
            }else {
                return new JsonResult(false, null, "Incorrect User or Password");
            }
        }catch (Exception ex){
            return new JsonResult(false, null, ex.getMessage());
        }
    }


}
