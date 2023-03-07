package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.RHautBassinsNotFoundException;
import anam.pkg.duniyaar.model.RHautBassins;
import anam.pkg.duniyaar.repo.RHautBassinsRepo;

@Service
public class RHautBassinsService {
	
    private final RHautBassinsRepo rHautBassinsRepo;
	
	@Autowired
	public RHautBassinsService(RHautBassinsRepo rHautBassinsRepo) {
		this.rHautBassinsRepo=rHautBassinsRepo;
	}
	
	/*public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}*/
	
	public RHautBassins addHautBassins(RHautBassins rHautBassins) {
		
		return rHautBassinsRepo.save(rHautBassins);
	}
	
	public List<RHautBassins> findAllRHautBassinss(){
		return rHautBassinsRepo.findAll();
	}
	
	public RHautBassins updateRHautBassins(RHautBassins rHautBassins) {
		return rHautBassinsRepo.save(rHautBassins);
	}
	
	public RHautBassins findRHautBassinsById(Long id){
		return rHautBassinsRepo.findById(id)
				.orElseThrow(()->new RHautBassinsNotFoundException("RHautBassins by id"+id+"was not found"));
	}
	
	public void deleteRHautBassins(Long id) {
		
		rHautBassinsRepo.deleteById(id);
	}

	public RHautBassinsRepo getRHautBassinsRepo() {
		return rHautBassinsRepo;
	}

}

