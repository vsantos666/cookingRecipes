package cookingRecipes.service;

import cookingRecipes.bean.Login;
import cookingRecipes.model.User;

import java.util.List;

/**
 * Created by vsantos on 28/03/2019.
 */
public interface UserService {

    public String saveUser(User userData);

    public String updateUser(User userData, String user);

    public List<User> getAllUsers(int page, int size);

    public String loginUser(Login login);

    public String validateToken(String token);
}
