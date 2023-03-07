package anam.pkg.duniyaar;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import anam.pkg.duniyaar.model.FlashInfo;
import anam.pkg.duniyaar.services.FlashInfoService;

@RestController
@RequestMapping("/flashInfo")
public class FlashInfoController {
	private final FlashInfoService flashInfoService;
	
	public FlashInfoController(FlashInfoService flashInfoService) {
		this.flashInfoService = flashInfoService;	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<FlashInfo>> getAlllertMeteos(){
		List<FlashInfo> actualiteMeteos= flashInfoService.findAllFlashInfos();
		
		return new ResponseEntity<>(actualiteMeteos, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<FlashInfo> getAlertMeteoById(@PathVariable("id") Long id){
		FlashInfo alertMeteo= flashInfoService.findFlashInfoById(id);
		
		return new ResponseEntity<>(alertMeteo, HttpStatus.OK);	
	}
	
	@PostMapping("/add")
	public ResponseEntity<FlashInfo> addActualiteMeteo(@RequestBody FlashInfo alertMeteo){
		FlashInfo newAlertMeteo= flashInfoService.addFlashInfo(alertMeteo);
		
		return new ResponseEntity<>(newAlertMeteo, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<FlashInfo> updateAlertMeteo(@RequestBody FlashInfo flashInfo){
		FlashInfo updateflashInfo= flashInfoService.updateFlashInfo(flashInfo);
		
		return new ResponseEntity<>(updateflashInfo, HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteFlashInfo(@PathVariable("id") Long id){
		flashInfoService.deleteFlashInfo(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
