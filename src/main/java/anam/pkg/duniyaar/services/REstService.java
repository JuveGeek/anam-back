package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.REstNotFindException;
import anam.pkg.duniyaar.model.REst;
import anam.pkg.duniyaar.repo.REstRepo;

@Service
public class REstService {
	
    private final REstRepo rEstRepo;
	
	@Autowired
	public REstService(REstRepo rEstRepo) {
		this.rEstRepo=rEstRepo;
	}
	
	/*public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}*/
	
	public REst addCentreEst(REst rEst) {
		
		return rEstRepo.save(rEst);
	}
	
	public List<REst> findAllREsts(){
		return rEstRepo.findAll();
	}
	
	public REst updateREst(REst rEst) {
		return rEstRepo.save(rEst);
	}
	
	public REst findREstById(Long id){
		return rEstRepo.findById(id)
				.orElseThrow(()->new REstNotFindException("REst by id"+id+"was not found"));
	}
	
	public void deleteREst(Long id) {
		
		rEstRepo.deleteById(id);
	}

	public REstRepo getREstRepo() {
		return rEstRepo;
	}

}