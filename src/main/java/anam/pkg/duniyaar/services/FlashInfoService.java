package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.FlashInfoNotFindException;
import anam.pkg.duniyaar.model.FlashInfo;
import anam.pkg.duniyaar.repo.FlashInfoRepo;

@Service
public class FlashInfoService {
	private final FlashInfoRepo flashInfoRepo;
	
	@Autowired
	public FlashInfoService(FlashInfoRepo flashInfoRepo) {
		this.flashInfoRepo=flashInfoRepo;
	}
	
	public FlashInfo addFlashInfo(FlashInfo flashInfo) {
		
		return flashInfoRepo.save(flashInfo);
	}
	
	public FlashInfo updateFlashInfo(FlashInfo flashInfo) {
		return flashInfoRepo.save(flashInfo);
	}
	
	public FlashInfo findFlashInfoById(Long id){
		return flashInfoRepo.findById(id)
				.orElseThrow(()->new FlashInfoNotFindException("flashInfo by id"+id+"was not found"));
	}
	
	public void deleteFlashInfo(Long id) {
		
		flashInfoRepo.deleteById(id);
	}

	public FlashInfoRepo getFlashInfoRepo() {
		return flashInfoRepo;
	}

	public List<FlashInfo> findAllFlashInfos() {
		return flashInfoRepo.findAll();
	}
}
