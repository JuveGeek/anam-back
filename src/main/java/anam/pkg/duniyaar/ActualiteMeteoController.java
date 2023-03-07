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

import anam.pkg.duniyaar.DTO.ActualiteMeteoLimitOne;
import anam.pkg.duniyaar.model.ActualiteMeteo;
import anam.pkg.duniyaar.services.ActualiteMeteoService;
import anam.pkg.duniyaar.services.FileStorageService;

@RestController
@RequestMapping("/actualiteMeteo")
public class ActualiteMeteoController {
	private final ActualiteMeteoService actualiteMeteoService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public ActualiteMeteoController(ActualiteMeteoService actualiteMeteoService) {
		this.actualiteMeteoService = actualiteMeteoService;	
	}
	
	@GetMapping("/oneActualiteMeteo")
	public ResponseEntity<List<ActualiteMeteoLimitOne>> getListActualiteMeteoLimitOne(){
		List<ActualiteMeteoLimitOne> actualiteMeteoLimitOne= actualiteMeteoService.getListActualiteMeteoLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ActualiteMeteo>> getAllActualiteMeteos(){
		List<ActualiteMeteo> actualiteMeteos= actualiteMeteoService.findAllActualiteMeteos();
		
		return new ResponseEntity<>(actualiteMeteos, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<ActualiteMeteo> getActualiteMeteoById(@PathVariable("id") Long id){
		ActualiteMeteo actualiteMeteo= actualiteMeteoService.findActualiteMeteoById(id);
		
		return new ResponseEntity<>(actualiteMeteo, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<ActualiteMeteo> addActuHanam(@RequestParam("actualiteMeteo") String actuMeteoo, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		ActualiteMeteo actuMeteo = new ObjectMapper().readValue(actuMeteoo, ActualiteMeteo.class);
		
		ActualiteMeteo autrActu = new ActualiteMeteo(actuMeteo.getTitre(),actuMeteo.getDescription(),fileDownloadUri);
		
		ActualiteMeteo updateautrActu= actualiteMeteoService.updateCategorie(autrActu);
		
		return new ResponseEntity<>(updateautrActu, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<ActualiteMeteo> updateActuHanam(@RequestParam("actualiteMeteo") String actuMeteoo, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		ActualiteMeteo actuHanam = new ObjectMapper().readValue(actuMeteoo, ActualiteMeteo.class);
		
		ActualiteMeteo autrActu = new ActualiteMeteo(actuHanam.getId() ,actuHanam.getTitre(),actuHanam.getDescription(),fileDownloadUri);
		
		ActualiteMeteo updateautrActu= actualiteMeteoService.updateCategorie(autrActu);
		
		return new ResponseEntity<>(updateautrActu, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteActualiteMeteo(@PathVariable("id") Long id){
		actualiteMeteoService.deleteActualiteMeteo(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}
