package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.RCentreNotFoundException;
import anam.pkg.duniyaar.model.RCentre;
import anam.pkg.duniyaar.repo.RCentreRepo;

@Service
public class RCentreService {
	
    private final RCentreRepo rCentreRepo;
	
	@Autowired
	public RCentreService(RCentreRepo rCentreRepo) {
		this.rCentreRepo=rCentreRepo;
	}
	
	/*public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}*/
	
	public RCentre addCentre(RCentre rCentre) {
		
		return rCentreRepo.save(rCentre);
	}
	
	public List<RCentre> findAllRCentres(){
		return rCentreRepo.findAll();
	}
	
	public RCentre updateRCentre(RCentre rCentre) {
		return rCentreRepo.save(rCentre);
	}
	
	public RCentre findRCentreById(Long id){
		return rCentreRepo.findById(id)
				.orElseThrow(()->new RCentreNotFoundException("RCentre by id"+id+"was not found"));
	}
	
	public void deleteRCentre(Long id) {
		
		rCentreRepo.deleteById(id);
	}

	public RCentreRepo getRCentreRepo() {
		return rCentreRepo;
	}

	
	
}