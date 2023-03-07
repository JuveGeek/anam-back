package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.RCentreEstNotFoundException;
import anam.pkg.duniyaar.model.RCentreEst;
import anam.pkg.duniyaar.repo.RCentreEstRepo;

@Service
public class RCentreEstService {
	
    private final RCentreEstRepo rCentreEstRepo;
	
	@Autowired
	public RCentreEstService(RCentreEstRepo rCentreEstRepo) {
		this.rCentreEstRepo=rCentreEstRepo;
	}
	
	/*public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}*/
	
	public RCentreEst addCentreEst(RCentreEst rCentreEst) {
		
		return rCentreEstRepo.save(rCentreEst);
	}
	
	public List<RCentreEst> findAllRCentreEsts(){
		return rCentreEstRepo.findAll();
	}
	
	public RCentreEst updateRCentreEst(RCentreEst rCentreEst) {
		return rCentreEstRepo.save(rCentreEst);
	}
	
	public RCentreEst findRCentreEstById(Long id){
		return rCentreEstRepo.findById(id)
				.orElseThrow(()->new RCentreEstNotFoundException("RCentreEst by id"+id+"was not found"));
	}
	
	public void deleteRCentreEst(Long id) {
		
		rCentreEstRepo.deleteById(id);
	}

	public RCentreEstRepo getRCentreEstRepo() {
		return rCentreEstRepo;
	}

}