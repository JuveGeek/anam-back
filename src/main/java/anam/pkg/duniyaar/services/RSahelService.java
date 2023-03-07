package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.RSahelNotFoundException;
import anam.pkg.duniyaar.model.RSahel;
import anam.pkg.duniyaar.repo.RSahelRepo;

@Service
public class RSahelService {
	
    private final RSahelRepo rSahelRepo;
	
	@Autowired
	public RSahelService(RSahelRepo rSahelRepo) {
		this.rSahelRepo=rSahelRepo;
	}
	
	/*public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}*/
	
	public RSahel addSahel(RSahel rSahel) {
		
		return rSahelRepo.save(rSahel);
	}
	
	public List<RSahel> findAllRSahels(){
		return rSahelRepo.findAll();
	}
	
	public RSahel updateRSahel(RSahel rSahel) {
		return rSahelRepo.save(rSahel);
	}
	
	public RSahel findRSahelById(Long id){
		return rSahelRepo.findById(id)
				.orElseThrow(()->new RSahelNotFoundException("RPlateauCentral by id"+id+"was not found"));
	}
	
	public void deleteRSahel(Long id) {
		
		rSahelRepo.deleteById(id);
	}

	public RSahelRepo getRSahelRepo() {
		return rSahelRepo;
	}

}