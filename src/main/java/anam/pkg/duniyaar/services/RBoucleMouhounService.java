package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.RBoucleMouhounNotFoundException;
import anam.pkg.duniyaar.model.RBoucleMouhoun;
import anam.pkg.duniyaar.repo.RBoucleMouhounRepo;

@Service
public class RBoucleMouhounService {
	
    private final RBoucleMouhounRepo rBoucleMouhounRepo;
	
	@Autowired
	public RBoucleMouhounService(RBoucleMouhounRepo rBoucleMouhounRepo) {
		this.rBoucleMouhounRepo=rBoucleMouhounRepo;
	}
	
	/*public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}*/
	
	public RBoucleMouhoun addBoucleMouhoun(RBoucleMouhoun rBoucleMouhoun) {
		
		return rBoucleMouhounRepo.save(rBoucleMouhoun);
	}
	
	public List<RBoucleMouhoun> findAllRBoucleMouhouns(){
		return rBoucleMouhounRepo.findAll();
	}
	
	public RBoucleMouhoun updateRBoucleMouhoun(RBoucleMouhoun rBoucleMouhoun) {
		return rBoucleMouhounRepo.save(rBoucleMouhoun);
	}
	
	public RBoucleMouhoun findRBoucleMouhounById(Long id){
		return rBoucleMouhounRepo.findById(id)
				.orElseThrow(()->new RBoucleMouhounNotFoundException("RBoucleMouhoun by id"+id+"was not found"));
	}
	
	public void deleteRBoucleMouhoun(Long id) {
		
		rBoucleMouhounRepo.deleteById(id);
	}

	public RBoucleMouhounRepo getRBoucleMouhounRepo() {
		return rBoucleMouhounRepo;
	}

	
	
}