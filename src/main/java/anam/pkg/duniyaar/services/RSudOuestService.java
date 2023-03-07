package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.RSudOuestNotFoundException;
import anam.pkg.duniyaar.model.RSudOuest;
import anam.pkg.duniyaar.repo.RSudOuestRepo;

@Service
public class RSudOuestService {
	
    private final RSudOuestRepo rSudOuestRepo;
	
	@Autowired
	public RSudOuestService(RSudOuestRepo rSudOuestRepo) {
		this.rSudOuestRepo=rSudOuestRepo;
	}
	
	/*public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}*/
	
	public RSudOuest addSudOuest(RSudOuest rSudOuest) {
		
		return rSudOuestRepo.save(rSudOuest);
	}
	
	public List<RSudOuest> findAllRSudOuests(){
		return rSudOuestRepo.findAll();
	}
	
	public RSudOuest updateRSudOuest(RSudOuest rSudOuest) {
		return rSudOuestRepo.save(rSudOuest);
	}
	
	public RSudOuest findRSudOuestById(Long id){
		return rSudOuestRepo.findById(id)
				.orElseThrow(()->new RSudOuestNotFoundException("RSudOuest by id"+id+"was not found"));
	}
	
	public void deleteRSudOuest(Long id) {
		
		rSudOuestRepo.deleteById(id);
	}

	public RSudOuestRepo getRSudOuestRepo() {
		return rSudOuestRepo;
	}

}