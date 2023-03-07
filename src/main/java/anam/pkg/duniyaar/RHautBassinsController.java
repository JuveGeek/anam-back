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

import anam.pkg.duniyaar.model.RHautBassins;
import anam.pkg.duniyaar.services.FileStorageService;
import anam.pkg.duniyaar.services.RHautBassinsService;

@RestController
@RequestMapping("/rHautBassins")
public class RHautBassinsController {
	private final RHautBassinsService rHautBassinsService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public RHautBassinsController(RHautBassinsService rHautBassinsService) {
		this.rHautBassinsService = rHautBassinsService;	
	}
	
	/*@GetMapping("/oneactualiteMeteo")
	public ResponseEntity<List<ActualiteMeteoLimitOne>> getListActualiteMeteoLimitOne(){
		List<ActualiteMeteoLimitOne> actualiteMeteoLimitOne= actualiteMeteoService.getListActualiteMeteoLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<List<RHautBassins>> getAllRHautBassinss(){
		List<RHautBassins> rHautBassinss= rHautBassinsService.findAllRHautBassinss();
		
		return new ResponseEntity<>(rHautBassinss, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<RHautBassins> getRHautBassinsById(@PathVariable("id") Long id){
		RHautBassins rHautBassins= rHautBassinsService.findRHautBassinsById(id);
		
		return new ResponseEntity<>(rHautBassins, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<RHautBassins> addRHautBassins(@RequestParam("rHautBassins") String rHautBassinsReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RHautBassins rHautBassins = new ObjectMapper().readValue(rHautBassinsReq, RHautBassins.class);
		
		RHautBassins rHautBassinsBd = new RHautBassins(rHautBassins.getTitre(), rHautBassins.getDescription(),fileDownloadUri);
		
		RHautBassins updaterHautBassinsBd= rHautBassinsService.updateRHautBassins(rHautBassinsBd);
		
		return new ResponseEntity<>(updaterHautBassinsBd, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<RHautBassins> uHautBassins(@RequestParam("rHautBassins") String rHautBassinsReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RHautBassins rHautBassins = new ObjectMapper().readValue(rHautBassinsReq, RHautBassins.class);
		
		RHautBassins rHautBassinsBd = new RHautBassins(rHautBassins.getId() ,rHautBassins.getTitre(),rHautBassins.getDescription(),fileDownloadUri);
		
		RHautBassins updateautrrHautBassinsBd= rHautBassinsService.updateRHautBassins(rHautBassinsBd);
		
		return new ResponseEntity<>(updateautrrHautBassinsBd, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRHautBassins(@PathVariable("id") Long id){
		rHautBassinsService.deleteRHautBassins(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}
