package anam.pkg.duniyaar.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import anam.pkg.duniyaar.model.User;
import anam.pkg.duniyaar.repo.UserRepository;
 
public class CUserDetailsService implements UserDetailsService {
    @Autowired private UserRepository repo;
 
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with the given email");
        }
         
        return new CUserDetails(user);
    }
 
}