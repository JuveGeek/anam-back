package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.DTO.ActualiteMeteoLimitOne;
import anam.pkg.duniyaar.exception.ActualiteMeteoNotFoundException;
import anam.pkg.duniyaar.model.ActualiteMeteo;
import anam.pkg.duniyaar.repo.ActualiteMeteoRepo;

@Service
public class ActualiteMeteoService {
	
    private final ActualiteMeteoRepo actualiteMeteoRepo;
	
	@Autowired
	public ActualiteMeteoService(ActualiteMeteoRepo actualiteMeteoRepo) {
		this.actualiteMeteoRepo=actualiteMeteoRepo;
	}
	
	public List<ActualiteMeteoLimitOne> getListActualiteMeteoLimitOne(){
		
		return actualiteMeteoRepo.ListActualiteMeteoLimitOne((PageRequest.of(0, 1)));	
	}
	
	public ActualiteMeteo addCategorie(ActualiteMeteo actualiteMeteo) {
		
		return actualiteMeteoRepo.save(actualiteMeteo);
	}
	
	public List<ActualiteMeteo> findAllActualiteMeteos(){
		return actualiteMeteoRepo.findAll();
	}
	
	public ActualiteMeteo updateCategorie(ActualiteMeteo actualiteMeteo) {
		return actualiteMeteoRepo.save(actualiteMeteo);
	}
	
	public ActualiteMeteo findActualiteMeteoById(Long id){
		return actualiteMeteoRepo.findById(id)
				.orElseThrow(()->new ActualiteMeteoNotFoundException("actualiteMeteo by id"+id+"was not found"));
	}
	
	public void deleteActualiteMeteo(Long id) {
		
		actualiteMeteoRepo.deleteById(id);
	}

	public ActualiteMeteoRepo getActualiteMeteoRepo() {
		return actualiteMeteoRepo;
	}

	
	
}
