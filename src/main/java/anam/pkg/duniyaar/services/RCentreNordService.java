package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.RCentreNordNotFoundException;
import anam.pkg.duniyaar.model.RCentreNord;
import anam.pkg.duniyaar.repo.RCentreNordRepo;

@Service
public class RCentreNordService {
	
    private final RCentreNordRepo rCentreNordRepo;
	
	@Autowired
	public RCentreNordService(RCentreNordRepo rCentreNordRepo) {
		this.rCentreNordRepo=rCentreNordRepo;
	}
	
	/*public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}*/
	
	public RCentreNord addCentreNord(RCentreNord rCentreNord) {
		
		return rCentreNordRepo.save(rCentreNord);
	}
	
	public List<RCentreNord> findAllRCentreNords(){
		return rCentreNordRepo.findAll();
	}
	
	public RCentreNord updateRCentreNord(RCentreNord rCentreNord) {
		return rCentreNordRepo.save(rCentreNord);
	}
	
	public RCentreNord findRCentreNordById(Long id){
		return rCentreNordRepo.findById(id)
				.orElseThrow(()->new RCentreNordNotFoundException("RCentreNord by id"+id+"was not found"));
	}
	
	public void deleteRCentreNord(Long id) {
		
		rCentreNordRepo.deleteById(id);
	}

	public RCentreNordRepo getRCentreNordRepo() {
		return rCentreNordRepo;
	}

}