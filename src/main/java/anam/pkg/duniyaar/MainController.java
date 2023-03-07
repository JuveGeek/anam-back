package anam.pkg.duniyaar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@Autowired
	@Qualifier(value = "user")
	DaoAuthenticationProvider authenticationManagerUser;
	
	@Autowired
	@Qualifier(value = "admin")
	DaoAuthenticationProvider authenticationManagerAdmin;
	
    @GetMapping("/admin/login")
    public String viewAdminLoginPage() {
        return "admin/admin_login";
    }
     
    @GetMapping("/admin/home")
    public String viewAdminHomePage() {
        return "admin/admin_home";
    }
    
    @GetMapping("/user/home")
    public String viewUserHomePage() {
        return "user/user_home";
    }
     
    @GetMapping("/user/login")
    public String viewUserLoginPage() {
        return "user/user_login";
    }
     
   
}