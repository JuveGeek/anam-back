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

import anam.pkg.duniyaar.model.RCentreSud;
import anam.pkg.duniyaar.services.FileStorageService;
import anam.pkg.duniyaar.services.RCentreSudService;

@RestController
@RequestMapping("/rCentreSud")
public class RCentreSudController {
	private final RCentreSudService rCentreSudService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public RCentreSudController(RCentreSudService rCentreSudService) {
		this.rCentreSudService = rCentreSudService;	
	}
	
	/*@GetMapping("/oneactualiteMeteo")
	public ResponseEntity<List<ActualiteMeteoLimitOne>> getListActualiteMeteoLimitOne(){
		List<ActualiteMeteoLimitOne> actualiteMeteoLimitOne= actualiteMeteoService.getListActualiteMeteoLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<List<RCentreSud>> getAllRCentreSuds(){
		List<RCentreSud> rCentreSuds= rCentreSudService.findAllRCentreSuds();
		
		return new ResponseEntity<>(rCentreSuds, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<RCentreSud> getRCentreSudById(@PathVariable("id") Long id){
		RCentreSud rCentreSud= rCentreSudService.findRCentreSudById(id);
		
		return new ResponseEntity<>(rCentreSud, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<RCentreSud> addRCentreSud(@RequestParam("rCentreSud") String rCentreSudReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RCentreSud rCentreSud = new ObjectMapper().readValue(rCentreSudReq, RCentreSud.class);
		
		RCentreSud rCentreSudBd = new RCentreSud(rCentreSud.getTitre(), rCentreSud.getDescription(),fileDownloadUri);
		
		RCentreSud updaterCentreSudBd= rCentreSudService.updateRCentreSud(rCentreSudBd);
		
		return new ResponseEntity<>(updaterCentreSudBd, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<RCentreSud> updateRSud(@RequestParam("rCentreSud") String rCentreSudReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RCentreSud rCentreSud = new ObjectMapper().readValue(rCentreSudReq, RCentreSud.class);
		
		RCentreSud rCentreSudBd = new RCentreSud(rCentreSud.getId() ,rCentreSud.getTitre(),rCentreSud.getDescription(),fileDownloadUri);
		
		RCentreSud updateautrrCentreSudBd= rCentreSudService.updateRCentreSud(rCentreSudBd);
		
		return new ResponseEntity<>(updateautrrCentreSudBd, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRCentreSud(@PathVariable("id") Long id){
		rCentreSudService.deleteRCentreSud(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}
