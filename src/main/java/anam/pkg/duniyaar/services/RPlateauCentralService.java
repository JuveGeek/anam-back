package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.RPlateauCentralNotFoundException;
import anam.pkg.duniyaar.model.RPlateauCentral;
import anam.pkg.duniyaar.repo.RPlateauCentralRepo;

@Service
public class RPlateauCentralService {
	
    private final RPlateauCentralRepo rPlateauCentralRepo;
	
	@Autowired
	public RPlateauCentralService(RPlateauCentralRepo rPlateauCentralRepo) {
		this.rPlateauCentralRepo=rPlateauCentralRepo;
	}
	
	/*public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}*/
	
	public RPlateauCentral addPlateauCentral(RPlateauCentral rPlateauCentral) {
		
		return rPlateauCentralRepo.save(rPlateauCentral);
	}
	
	public List<RPlateauCentral> findAllRPlateauCentrals(){
		return rPlateauCentralRepo.findAll();
	}
	
	public RPlateauCentral updateRPlateauCentral(RPlateauCentral rPlateauCentral) {
		return rPlateauCentralRepo.save(rPlateauCentral);
	}
	
	public RPlateauCentral findRPlateauCentralById(Long id){
		return rPlateauCentralRepo.findById(id)
				.orElseThrow(()->new RPlateauCentralNotFoundException("RPlateauCentral by id"+id+"was not found"));
	}
	
	public void deleteRPlateauCentral(Long id) {
		
		rPlateauCentralRepo.deleteById(id);
	}

	public RPlateauCentralRepo getRPlateauCentralRepo() {
		return rPlateauCentralRepo;
	}

}