package anam.pkg.duniyaar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import anam.pkg.duniyaar.model.RCentreOuest;
import anam.pkg.duniyaar.services.FileStorageService;
import anam.pkg.duniyaar.services.RCentreOuestService;

@RestController
@RequestMapping("/rCentreOuest")
public class RCentreOuestController {
	private final RCentreOuestService rCentreOuestService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public RCentreOuestController(RCentreOuestService rCentreOuestService) {
		this.rCentreOuestService = rCentreOuestService;	
	}
	
	/*@GetMapping("/oneactualiteMeteo")
	public ResponseEntity<List<ActualiteMeteoLimitOne>> getListActualiteMeteoLimitOne(){
		List<ActualiteMeteoLimitOne> actualiteMeteoLimitOne= actualiteMeteoService.getListActualiteMeteoLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<List<RCentreOuest>> getAllRCentreOuests(){
		List<RCentreOuest> rCentreOuests= rCentreOuestService.findAllRCentreOuests();
		
		return new ResponseEntity<>(rCentreOuests, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<RCentreOuest> getRCentreOuestById(@PathVariable("id") Long id){
		RCentreOuest rCentreOuest= rCentreOuestService.findRCentreOuestById(id);
		
		return new ResponseEntity<>(rCentreOuest, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<RCentreOuest> addRCentreOuest(@RequestParam("rCentreOuest") String rCentreOuestReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RCentreOuest rCentreOuest = new ObjectMapper().readValue(rCentreOuestReq, RCentreOuest.class);
		
		RCentreOuest rCentreOuestBd = new RCentreOuest(rCentreOuest.getTitre(), rCentreOuest.getDescription(),fileDownloadUri);
		
		RCentreOuest updaterCentreOuestBd= rCentreOuestService.updateRCentreOuest(rCentreOuestBd);
		
		return new ResponseEntity<>(updaterCentreOuestBd, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<RCentreOuest> updateRCascades(@RequestParam("rCentreOuest") String rCentreOuestReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RCentreOuest rCentreOuest = new ObjectMapper().readValue(rCentreOuestReq, RCentreOuest.class);
		
		RCentreOuest rCentreOuestBd = new RCentreOuest(rCentreOuest.getId() ,rCentreOuest.getTitre(),rCentreOuest.getDescription(),fileDownloadUri);
		
		RCentreOuest updateautrrCentreOuestBd= rCentreOuestService.updateRCentreOuest(rCentreOuestBd);
		
		return new ResponseEntity<>(updateautrrCentreOuestBd, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRCentreOuest(@PathVariable("id") Long id){
		rCentreOuestService.deleteRCentreOuest(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}
