package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.RCentreSudNotFoundException;
import anam.pkg.duniyaar.model.RCentreSud;
import anam.pkg.duniyaar.repo.RCentreSudRepo;

@Service
public class RCentreSudService {
	
    private final RCentreSudRepo rCentreSudRepo;
	
	@Autowired
	public RCentreSudService(RCentreSudRepo rCentreSudRepo) {
		this.rCentreSudRepo=rCentreSudRepo;
	}
	
	/*public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}*/
	
	public RCentreSud addCentreSud(RCentreSud rCentreSud) {
		
		return rCentreSudRepo.save(rCentreSud);
	}
	
	public List<RCentreSud> findAllRCentreSuds(){
		return rCentreSudRepo.findAll();
	}
	
	public RCentreSud updateRCentreSud(RCentreSud rCentreSud) {
		return rCentreSudRepo.save(rCentreSud);
	}
	
	public RCentreSud findRCentreSudById(Long id){
		return rCentreSudRepo.findById(id)
				.orElseThrow(()->new RCentreSudNotFoundException("RCentreSud by id"+id+"was not found"));
	}
	
	public void deleteRCentreSud(Long id) {
		
		rCentreSudRepo.deleteById(id);
	}

	public RCentreSudRepo getRCentreSudRepo() {
		return rCentreSudRepo;
	}

}

