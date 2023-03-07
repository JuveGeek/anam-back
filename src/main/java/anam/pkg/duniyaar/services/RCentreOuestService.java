package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.RCentreOuestNotFoundException;
import anam.pkg.duniyaar.model.RCentreOuest;
import anam.pkg.duniyaar.repo.RCentreOuestRepo;

@Service
public class RCentreOuestService {
	
    private final RCentreOuestRepo rCentreOuestRepo;
	
	@Autowired
	public RCentreOuestService(RCentreOuestRepo rCentreOuestRepo) {
		this.rCentreOuestRepo=rCentreOuestRepo;
	}
	
	/*public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}*/
	
	public RCentreOuest addCentreOuest(RCentreOuest rCentreOuest) {
		
		return rCentreOuestRepo.save(rCentreOuest);
	}
	
	public List<RCentreOuest> findAllRCentreOuests(){
		return rCentreOuestRepo.findAll();
	}
	
	public RCentreOuest updateRCentreOuest(RCentreOuest rCentreOuest) {
		return rCentreOuestRepo.save(rCentreOuest);
	}
	
	public RCentreOuest findRCentreOuestById(Long id){
		return rCentreOuestRepo.findById(id)
				.orElseThrow(()->new RCentreOuestNotFoundException("RCentreOuest by id"+id+"was not found"));
	}
	
	public void deleteRCentreOuest(Long id) {
		
		rCentreOuestRepo.deleteById(id);
	}

	public RCentreOuestRepo getRCentreOuestRepo() {
		return rCentreOuestRepo;
	}

}