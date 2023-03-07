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

import anam.pkg.duniyaar.model.RBoucleMouhoun;
import anam.pkg.duniyaar.services.RBoucleMouhounService;
import anam.pkg.duniyaar.services.FileStorageService;

@RestController
@RequestMapping("/rBoucleMouhoun")
public class RBoucleMouhounController {
	private final RBoucleMouhounService rBoucleMouhounService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public RBoucleMouhounController(RBoucleMouhounService rBoucleMouhounService) {
		this.rBoucleMouhounService = rBoucleMouhounService;	
	}
	
	/*@GetMapping("/oneactualiteMeteo")
	public ResponseEntity<List<ActualiteMeteoLimitOne>> getListActualiteMeteoLimitOne(){
		List<ActualiteMeteoLimitOne> actualiteMeteoLimitOne= actualiteMeteoService.getListActualiteMeteoLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<List<RBoucleMouhoun>> getAllRBoucleMouhouns(){
		List<RBoucleMouhoun> rBoucleMouhouns= rBoucleMouhounService.findAllRBoucleMouhouns();
		
		return new ResponseEntity<>(rBoucleMouhouns, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<RBoucleMouhoun> getRBoucleMouhounById(@PathVariable("id") Long id){
		RBoucleMouhoun actualiteMeteo= rBoucleMouhounService.findRBoucleMouhounById(id);
		
		return new ResponseEntity<>(actualiteMeteo, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<RBoucleMouhoun> addRBoucleMouhoun(@RequestParam("rBoucleMouhoun") String rBoucleMouhounReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RBoucleMouhoun rBoucleMouhoun = new ObjectMapper().readValue(rBoucleMouhounReq, RBoucleMouhoun.class);
		
		RBoucleMouhoun rBoucleMouhounBd = new RBoucleMouhoun(rBoucleMouhoun.getTitre(), rBoucleMouhoun.getDescription(),fileDownloadUri);
		
		RBoucleMouhoun updaterBoucleMouhounBd= rBoucleMouhounService.updateRBoucleMouhoun(rBoucleMouhounBd);
		
		return new ResponseEntity<>(updaterBoucleMouhounBd, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<RBoucleMouhoun> updateRBoucleMouhoun(@RequestParam("rBoucleMouhoun") String rBoucleMouhounReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RBoucleMouhoun rBoucleMouhoun = new ObjectMapper().readValue(rBoucleMouhounReq, RBoucleMouhoun.class);
		
		RBoucleMouhoun rBoucleMouhounBd = new RBoucleMouhoun(rBoucleMouhoun.getId() ,rBoucleMouhoun.getTitre(),rBoucleMouhoun.getDescription(),fileDownloadUri);
		
		RBoucleMouhoun updateautrrBoucleMouhounBd= rBoucleMouhounService.updateRBoucleMouhoun(rBoucleMouhounBd);
		
		return new ResponseEntity<>(updateautrrBoucleMouhounBd, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRBoucleMouhoun(@PathVariable("id") Long id){
		rBoucleMouhounService.deleteRBoucleMouhoun(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}
