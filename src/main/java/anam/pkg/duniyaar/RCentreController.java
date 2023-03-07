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

import anam.pkg.duniyaar.model.RCentre;
import anam.pkg.duniyaar.services.FileStorageService;
import anam.pkg.duniyaar.services.RCentreService;

@RestController
@RequestMapping("/rCentre")
public class RCentreController {
	private final RCentreService rCentreService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public RCentreController(RCentreService rCentreService) {
		this.rCentreService = rCentreService;	
	}
	
	/*@GetMapping("/oneactualiteMeteo")
	public ResponseEntity<List<ActualiteMeteoLimitOne>> getListActualiteMeteoLimitOne(){
		List<ActualiteMeteoLimitOne> actualiteMeteoLimitOne= actualiteMeteoService.getListActualiteMeteoLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<List<RCentre>> getAllRCentres(){
		List<RCentre> rCentres= rCentreService.findAllRCentres();
		
		return new ResponseEntity<>(rCentres, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<RCentre> getRCentreById(@PathVariable("id") Long id){
		RCentre rCentre= rCentreService.findRCentreById(id);
		
		return new ResponseEntity<>(rCentre, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<RCentre> addRCentre(@RequestParam("rCentre") String rCentreReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RCentre rCentre = new ObjectMapper().readValue(rCentreReq, RCentre.class);
		
		RCentre rCentreBd = new RCentre(rCentre.getTitre(), rCentre.getDescription(),fileDownloadUri);
		
		RCentre updaterCentreBd= rCentreService.updateRCentre(rCentreBd);
		
		return new ResponseEntity<>(updaterCentreBd, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<RCentre> updateRCascades(@RequestParam("rCentre") String rCentreReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RCentre rCentre = new ObjectMapper().readValue(rCentreReq, RCentre.class);
		
		RCentre rCentreBd = new RCentre(rCentre.getId() ,rCentre.getTitre(),rCentre.getDescription(),fileDownloadUri);
		
		RCentre updateautrrCentreBd= rCentreService.updateRCentre(rCentreBd);
		
		return new ResponseEntity<>(updateautrrCentreBd, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRCentre(@PathVariable("id") Long id){
		rCentreService.deleteRCentre(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}
