package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.VacancePostNotFindException;
import anam.pkg.duniyaar.model.VacancePoste;
import anam.pkg.duniyaar.repo.VacancePosteRepo;

@Service
public class VacancePosteService {
private final VacancePosteRepo vacancePosteRepo;
	
	@Autowired
	public VacancePosteService(VacancePosteRepo vacancePosteRepo) {
		this.vacancePosteRepo=vacancePosteRepo;
	}
	
	public VacancePoste addVacancePoste(VacancePoste vacancePoste) {
		
		return vacancePosteRepo.save(vacancePoste);
	}
	
	public VacancePoste updateVacancePoste(VacancePoste flashInfo) {
		return vacancePosteRepo.save(flashInfo);
	}
	
	public VacancePoste findVacancePosteById(Long id){
		return vacancePosteRepo.findById(id)
				.orElseThrow(()->new VacancePostNotFindException("flashInfo by id"+id+"was not found"));
	}
	
	public void deleteVacancePoste(Long id) {
		
		vacancePosteRepo.deleteById(id);
	}

	public VacancePosteRepo getMeteoMediaRepo() {
		return vacancePosteRepo;
	}

	public List<VacancePoste> findAllVacancePostes() {
		
		return vacancePosteRepo.findAll();
	}
}
