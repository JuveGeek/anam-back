package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.RNordNotFindException;
import anam.pkg.duniyaar.model.RNord;
import anam.pkg.duniyaar.repo.RNordRepo;

@Service
public class RNordService {
	
    private final RNordRepo rNordRepo;
	
	@Autowired
	public RNordService(RNordRepo rNordRepo) {
		this.rNordRepo=rNordRepo;
	}
	
	/*public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}*/
	
	public RNord addNord(RNord rNord) {
		
		return rNordRepo.save(rNord);
	}
	
	public List<RNord> findAllRNords(){
		return rNordRepo.findAll();
	}
	
	public RNord updateRNord(RNord rNord) {
		return rNordRepo.save(rNord);
	}
	
	public RNord findRNordById(Long id){
		return rNordRepo.findById(id)
				.orElseThrow(()->new RNordNotFindException("RNord by id"+id+"was not found"));
	}
	
	public void deleteRNord(Long id) {
		
		rNordRepo.deleteById(id);
	}

	public RNordRepo getRNordRepo() {
		return rNordRepo;
	}

}