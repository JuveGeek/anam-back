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

import anam.pkg.duniyaar.model.RSudOuest;
import anam.pkg.duniyaar.services.FileStorageService;
import anam.pkg.duniyaar.services.RSudOuestService;

@RestController
@RequestMapping("/rSudOuest")
public class RSudOuestController {
	private final RSudOuestService rSudOuestService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public RSudOuestController(RSudOuestService rSudOuestService) {
		this.rSudOuestService = rSudOuestService;	
	}
	
	/*@GetMapping("/oneactualiteMeteo")
	public ResponseEntity<List<ActualiteMeteoLimitOne>> getListActualiteMeteoLimitOne(){
		List<ActualiteMeteoLimitOne> actualiteMeteoLimitOne= actualiteMeteoService.getListActualiteMeteoLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<List<RSudOuest>> getAllRSudOuests(){
		List<RSudOuest> rSudOuests= rSudOuestService.findAllRSudOuests();
		
		return new ResponseEntity<>(rSudOuests, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<RSudOuest> getRSudOuestById(@PathVariable("id") Long id){
		RSudOuest rSudOuest= rSudOuestService.findRSudOuestById(id);
		
		return new ResponseEntity<>(rSudOuest, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<RSudOuest> addRSudOuest(@RequestParam("rSudOuest") String rSudOuestReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RSudOuest rSudOuest = new ObjectMapper().readValue(rSudOuestReq, RSudOuest.class);
		
		RSudOuest rSudOuestBd = new RSudOuest(rSudOuest.getTitre(), rSudOuest.getDescription(),fileDownloadUri);
		
		RSudOuest updaterSudOuestBd= rSudOuestService.updateRSudOuest(rSudOuestBd);
		
		return new ResponseEntity<>(updaterSudOuestBd, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<RSudOuest> updateRCascades(@RequestParam("rSudOuest") String rSudOuestReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RSudOuest rSudOuest = new ObjectMapper().readValue(rSudOuestReq, RSudOuest.class);
		
		RSudOuest rSudOuestBd = new RSudOuest(rSudOuest.getId() ,rSudOuest.getTitre(),rSudOuest.getDescription(),fileDownloadUri);
		
		RSudOuest updateautrrSudOuestBd= rSudOuestService.updateRSudOuest(rSudOuestBd);
		
		return new ResponseEntity<>(updateautrrSudOuestBd, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRSudSudOuest(@PathVariable("id") Long id){
		rSudOuestService.deleteRSudOuest(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}
