package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.RCascadesNotFoundException;
import anam.pkg.duniyaar.model.RCascades;
import anam.pkg.duniyaar.repo.RCascadesRepo;

@Service
public class RCascadesService {
	
    private final RCascadesRepo rCascadesRepo;
	
	@Autowired
	public RCascadesService(RCascadesRepo rCascadesRepo) {
		this.rCascadesRepo=rCascadesRepo;
	}
	
	/*public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}*/
	
	public RCascades addCascades(RCascades rCascades) {
		
		return rCascadesRepo.save(rCascades);
	}
	
	public List<RCascades> findAllRCascadess(){
		return rCascadesRepo.findAll();
	}
	
	public RCascades updateRCascades(RCascades rCascades) {
		return rCascadesRepo.save(rCascades);
	}
	
	public RCascades findRCascadesById(Long id){
		return rCascadesRepo.findById(id)
				.orElseThrow(()->new RCascadesNotFoundException("RCascades by id"+id+"was not found"));
	}
	
	public void deleteRCascades(Long id) {
		
		rCascadesRepo.deleteById(id);
	}

	public RCascadesRepo getRCascadesRepo() {
		return rCascadesRepo;
	}

	
	
}