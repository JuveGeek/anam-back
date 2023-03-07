package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.DTO.AlertMeteoLimitOne;
import anam.pkg.duniyaar.exception.AlertMeteoRepoNotFoundException;
import anam.pkg.duniyaar.model.AlertMeteo;
import anam.pkg.duniyaar.repo.AlertMeteoRepo;

@Service
public class AlertMeteoService {
	private final AlertMeteoRepo alertMeteoRepo;
	
	@Autowired
	public AlertMeteoService(AlertMeteoRepo alertMeteoRepo) {
		this.alertMeteoRepo=alertMeteoRepo;
	}
	
	public List<AlertMeteoLimitOne> getListAlertMeteoLimitOne(){
		
		return alertMeteoRepo.ListAlertMeteoLimitOne((PageRequest.of(0, 1)));	
	}
	
	public List<AlertMeteo> findAllAlertMeteos(){
		return alertMeteoRepo.findAll();
	}
	
	public AlertMeteo addAlertMeteo(AlertMeteo alertMeteo) {
		
		return alertMeteoRepo.save(alertMeteo);
	}
	
	public AlertMeteo updateAlertMeteo(AlertMeteo alertMeteo) {
		return alertMeteoRepo.save(alertMeteo);
	}
	
	public AlertMeteo findAlertMeteoById(Long id){
		return alertMeteoRepo.findById(id)
				.orElseThrow(()->new AlertMeteoRepoNotFoundException("alertMeteo by id"+id+"was not found"));
	}
	
	public void deleteAlertMeteo(Long id) {
		
		alertMeteoRepo.deleteById(id);
	}

	public AlertMeteoRepo getAlertMeteoRepo() {
		return alertMeteoRepo;
	}
}
