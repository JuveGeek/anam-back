package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.AvisAppelOffreNotFindException;
import anam.pkg.duniyaar.model.AvisAppelOffre;
import anam.pkg.duniyaar.repo.AvisAppelOffreRepo;

@Service
public class AvisAppelOffreService {
	private final anam.pkg.duniyaar.repo.AvisAppelOffreRepo avisAppelOffreRepo;
	
	@Autowired
	public AvisAppelOffreService(AvisAppelOffreRepo avisAppelOffreRepo) {
		this.avisAppelOffreRepo=avisAppelOffreRepo;
	}
	
	public AvisAppelOffre addAvisAppelOffre(AvisAppelOffre avisAppelOffre) {
		
		return avisAppelOffreRepo.save(avisAppelOffre);
	}
	
	public AvisAppelOffre updateAvisAppelOffre(AvisAppelOffre avisAppelOffre) {
		return avisAppelOffreRepo.save(avisAppelOffre);
	}
	
	public AvisAppelOffre findAutreAvisAppelOffreById(Long id){
		return avisAppelOffreRepo.findById(id)
				.orElseThrow(()->new AvisAppelOffreNotFindException("AutreActualite by id"+id+"was not found"));
	}
	
	public void deleteAvisAppelOffre(Long id) {
		
		avisAppelOffreRepo.deleteById(id);
	}

	public AvisAppelOffreRepo getAvisAppelOffreRepo() {
		return avisAppelOffreRepo;
	}

	public List<AvisAppelOffre> findAllAvisAppelOffres() {
		return avisAppelOffreRepo.findAll();
	}
}
