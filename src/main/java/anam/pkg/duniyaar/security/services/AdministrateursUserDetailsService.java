package anam.pkg.duniyaar.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

import anam.pkg.duniyaar.model.Administrateurs;
import anam.pkg.duniyaar.repo.AdministrateursRepository;
 
public class AdministrateursUserDetailsService implements UserDetailsService {
 
    @Autowired private AdministrateursRepository repo;
     
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	Administrateurs customer = repo.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("No customer found with the given email.");
        }
         
        return new AdministrateursUserDetails(customer);
    }
    
 
}