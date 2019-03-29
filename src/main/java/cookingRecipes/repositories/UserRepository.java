package cookingRecipes.repositories;

import cookingRecipes.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by vsantos on 28/03/2019.
 */
public interface UserRepository extends JpaRepository<cookingRecipes.model.User, Long>,PagingAndSortingRepository<cookingRecipes.model.User, Long> {


    public List<User> findAllByDisabled(@Param("disabled") boolean disabled, Pageable pageable);

    public List<User> findAllByEmailAndPassword(@Param("email") String email,@Param("password") String password);



}
