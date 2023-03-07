package anam.pkg.duniyaar.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import anam.pkg.duniyaar.model.User;
 
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByEmail(String email); 
}