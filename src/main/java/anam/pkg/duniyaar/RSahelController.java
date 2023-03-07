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

import anam.pkg.duniyaar.model.RSahel;
import anam.pkg.duniyaar.services.FileStorageService;
import anam.pkg.duniyaar.services.RSahelService;

@RestController
@RequestMapping("/rSahel")
public class RSahelController {
	private final RSahelService rSahelService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public RSahelController(RSahelService rSahelService) {
		this.rSahelService = rSahelService;	
	}
	
	/*@GetMapping("/oneactualiteMeteo")
	public ResponseEntity<List<ActualiteMeteoLimitOne>> getListActualiteMeteoLimitOne(){
		List<ActualiteMeteoLimitOne> actualiteMeteoLimitOne= actualiteMeteoService.getListActualiteMeteoLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<List<RSahel>> getAllRSahels(){
		List<RSahel> rSahels= rSahelService.findAllRSahels();
		
		return new ResponseEntity<>(rSahels, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<RSahel> getRSahelById(@PathVariable("id") Long id){
		RSahel rSahel= rSahelService.findRSahelById(id);
		
		return new ResponseEntity<>(rSahel, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<RSahel> addRSahel(@RequestParam("rSahel") String rSahelReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RSahel rSahel = new ObjectMapper().readValue(rSahelReq, RSahel.class);
		
		RSahel rSahelBd = new RSahel(rSahel.getTitre(), rSahel.getDescription(),fileDownloadUri);
		
		RSahel updaterSahelBd= rSahelService.updateRSahel(rSahelBd);
		
		return new ResponseEntity<>(updaterSahelBd, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<RSahel> updateRSahel(@RequestParam("rSahel") String rSahelReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RSahel rSahel = new ObjectMapper().readValue(rSahelReq, RSahel.class);
		
		RSahel rSahelBd = new RSahel(rSahel.getId() ,rSahel.getTitre(),rSahel.getDescription(),fileDownloadUri);
		
		RSahel updateautrrSahelBd= rSahelService.updateRSahel(rSahelBd);
		
		return new ResponseEntity<>(updateautrrSahelBd, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRSudSahel(@PathVariable("id") Long id){
		rSahelService.deleteRSahel(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}

