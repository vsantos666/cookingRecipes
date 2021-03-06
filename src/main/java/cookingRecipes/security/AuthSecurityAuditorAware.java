package cookingRecipes.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
/**
 * Created by vsantos on 28/03/2019.
 */
@Component
public class AuthSecurityAuditorAware implements AuditorAware<String> {

    public static final String SYSTEM_ACCOUNT = "system";

    @Override
    public String getCurrentAuditor() {
//        String userName = SecurityUtils.getCurrentLogin();
        String userName = null;
        return (userName != null ? userName : SYSTEM_ACCOUNT);
    }
}