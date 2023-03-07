package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.DTO.PreoccupationLimitThree;
import anam.pkg.duniyaar.exception.PreoccupationNotFindException;
import anam.pkg.duniyaar.model.Preoccupation;
import anam.pkg.duniyaar.repo.PreoccupationRepo;

@Service
public class PreoccupationService {
	private final PreoccupationRepo preoccupationRepo;
	
	@Autowired
	public PreoccupationService(PreoccupationRepo preoccupationRepo) {
		this.preoccupationRepo=preoccupationRepo;
	}
	
	public Preoccupation Preoccupation(Preoccupation faq) {
		
		return preoccupationRepo.save(faq);
	}
	
	public  Preoccupation updatPreoccupation(Preoccupation faq) {
		return preoccupationRepo.save(faq);
	}
	
	public Preoccupation findPreoccupationById(Long id){
		return preoccupationRepo.findById(id)
				.orElseThrow(()->new PreoccupationNotFindException("Preoccupation by id"+id+"was not found"));
	}
	
	public void deletePreoccupationById(Long id) {
		
		preoccupationRepo.deleteById(id);
	}
	
	public List<PreoccupationLimitThree> geListPreoccupationLimitThree(){
		
		return preoccupationRepo.ListPreoccupationLimitThree(PageRequest.of(0, 3)) ;	
	}

	public PreoccupationRepo getRepo() {
		return preoccupationRepo;
	}

	public List<Preoccupation> findAlPreoccupations() {
		return preoccupationRepo.findAll();
	}
}