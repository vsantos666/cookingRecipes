package cookingRecipes.repositories;

import cookingRecipes.model.Recipe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long>, PagingAndSortingRepository<Recipe, Long>{

    public List<Recipe> findAllByDisabled(@Param("disabled") boolean disabled, Pageable pageable);

    public List<Recipe> findAllByDisabledAndNameContains(@Param("disabled") boolean disabled, @Param("name") String name, Pageable pageable);

}
