package cookingRecipes.service.impl;

import cookingRecipes.bean.Login;
import cookingRecipes.model.User;
import cookingRecipes.model.UserToken;
import cookingRecipes.repositories.UserRepository;
import cookingRecipes.repositories.UserTokenRepository;
import cookingRecipes.service.MappingService;
import cookingRecipes.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cookingRecipes.utils.SecurePassword;

import java.util.*;


/**
 * Created by vsantos on 28/03/2019.
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private MappingService mappingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenRepository userTokenRepository;

    /**
     * Method to add a new user
     * @param user
     * @return una cadena con null si existe error
     */
    @Transactional
    @Override
    public String saveUser(User user) {
        try {
            user.setCreatedBy(user.getEmail());
            SecurePassword secure = new SecurePassword();
            String securePassword = secure.getSecurePassword(user.getPassword(), "123456789".getBytes());
            user.setPassword(securePassword);
            userRepository.saveAndFlush(user);
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Method to modify an user
     * @param userData
     * @param user
     * @return
     */
    @Transactional
    @Override
    public String updateUser(User userData, String user) {
        try {
            User user1 = userRepository.findOne(userData.getId());
            if(user1 !=null){
                user1.setLastName(userData.getLastName());
                user1.setName(userData.getName());
                user1.setBirthDate(userData.getBirthDate());
                user1.setUpdatedBy(user);
                userRepository.save(user1);
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
    public List<User> getAllUsers(int page,int size) {
        try {
            Pageable pageable = new PageRequest(page,size);
            List<User> userList = userRepository.findAllByDisabled(false,pageable);
            return userList;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    @Override
    public String loginUser(Login login) {
        try {
            SecurePassword secure = new SecurePassword();
            String securePassword = secure.getSecurePassword(login.getPassword(), "123456789".getBytes());
            List<User> userList = userRepository.findAllByEmailAndPassword(login.getUser(),securePassword);
            if(userList!= null && userList.size()>0){
                String token = UUID.randomUUID().toString();
             for(User user:userList){
                 UserToken userToken= new UserToken();
                 Calendar cal = Calendar.getInstance();
                 cal.setTime(new Date());
                 cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)+ 2);
                 userToken.setEndDate(cal.getTime());
                 userToken.setUserId(user);
                 userToken.setToken(token);
                 userTokenRepository.saveAndFlush(userToken);
             }
                return token;
            }else {
                return null;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    @Override
    public String validateToken(String token) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            UserToken userToken = userTokenRepository.findByTokenAndEndDateAfter(token,cal.getTime());
            if(userToken!= null){
                return "Conected";
            }else {
                return null;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
