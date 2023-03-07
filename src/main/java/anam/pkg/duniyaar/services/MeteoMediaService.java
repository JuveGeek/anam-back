package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.DTO.MeteoMediaLimitOne;
import anam.pkg.duniyaar.exception.MeteoMediaNotFindException;
import anam.pkg.duniyaar.model.MeteoMedia;
import anam.pkg.duniyaar.repo.MeteoMediaRepo;

@Service
public class MeteoMediaService {
	private final MeteoMediaRepo meteoMediaRepo;
	
	@Autowired
	public MeteoMediaService(MeteoMediaRepo meteoMediaRepo) {
		this.meteoMediaRepo=meteoMediaRepo;
	}
	
	public List<MeteoMediaLimitOne> getListMeteoMediaLimitOne(){
		
		return meteoMediaRepo.ListMeteoMediaLimitOne((PageRequest.of(0, 1)));	
	}
	
	public MeteoMedia addMeteoMedia(MeteoMedia flashInfo) {
		
		return meteoMediaRepo.save(flashInfo);
	}
	
	public MeteoMedia updateMeteoMedia(MeteoMedia flashInfo) {
		return meteoMediaRepo.save(flashInfo);
	}
	
	public MeteoMedia findMeteoMediaById(Long id){
		return meteoMediaRepo.findById(id)
				.orElseThrow(()->new MeteoMediaNotFindException("flashInfo by id"+id+"was not found"));
	}
	
	public void deleteMeteoMedia(Long id) {
		
		meteoMediaRepo.deleteById(id);
	}

	public MeteoMediaRepo getMeteoMediaRepo() {
		return meteoMediaRepo;
	}

	public List<MeteoMedia> findAllMeteoMedias() {
		
		return meteoMediaRepo.findAll();
	}
}
