package anam.pkg.duniyaar.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import anam.pkg.duniyaar.model.Administrateurs;
 
@Repository
public interface AdministrateursRepository extends CrudRepository<Administrateurs, Integer> {
    public Administrateurs findByEmail(String email);

	public boolean existsByEmail(String email); 
}