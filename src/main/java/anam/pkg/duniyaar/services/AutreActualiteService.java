package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.DTO.AutreActualiteLimitThree;
import anam.pkg.duniyaar.exception.AutreActualiteNotFoundException;
import anam.pkg.duniyaar.model.AutreActualite;
import anam.pkg.duniyaar.repo.AutreActualiteRepo;

@Service
public class AutreActualiteService {
	private final AutreActualiteRepo autreActualiteRepo;
	
	@Autowired
	public AutreActualiteService(AutreActualiteRepo autreActualiteRepo) {
		this.autreActualiteRepo=autreActualiteRepo;
	}
	
	public AutreActualite addAutreActualite(AutreActualite autreActualite) {
		
		return autreActualiteRepo.save(autreActualite);
	}
	
	public AutreActualite updateAutreActualite(AutreActualite autreActualite) {
		return autreActualiteRepo.save(autreActualite);
	}
	
	public AutreActualite findAutreActualiteById(Long id){
		return autreActualiteRepo.findById(id)
				.orElseThrow(()->new AutreActualiteNotFoundException("AutreActualite by id"+id+"was not found"));
	}
	
	public void deleteAautreActualite(Long id) {
		
		autreActualiteRepo.deleteById(id);
	}

	public AutreActualiteRepo getAutreActualiteRepo() {
		return autreActualiteRepo;
	}

	public List<AutreActualite> findAllAutreActualites() {
		
		return autreActualiteRepo.findAll();
	}
	
public List<AutreActualiteLimitThree> getListAutreActualiteLimitThree(){
		
		return autreActualiteRepo.ListAutreActualiteLimitThree(PageRequest.of(0, 3)) ;	
	}
	
}
