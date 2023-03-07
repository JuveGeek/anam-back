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

import anam.pkg.duniyaar.model.RCentreEst;
import anam.pkg.duniyaar.services.FileStorageService;
import anam.pkg.duniyaar.services.RCentreEstService;

@RestController
@RequestMapping("/rCentreEst")
public class RCentreEstController {
	private final RCentreEstService rCentreEstService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public RCentreEstController(RCentreEstService rCentreEstService) {
		this.rCentreEstService = rCentreEstService;	
	}
	
	/*@GetMapping("/oneactualiteMeteo")
	public ResponseEntity<List<ActualiteMeteoLimitOne>> getListActualiteMeteoLimitOne(){
		List<ActualiteMeteoLimitOne> actualiteMeteoLimitOne= actualiteMeteoService.getListActualiteMeteoLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<List<RCentreEst>> getAllRCentreEsts(){
		List<RCentreEst> rCentreEsts= rCentreEstService.findAllRCentreEsts();
		
		return new ResponseEntity<>(rCentreEsts, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<RCentreEst> getRCentreEstById(@PathVariable("id") Long id){
		RCentreEst rCentreEst= rCentreEstService.findRCentreEstById(id);
		
		return new ResponseEntity<>(rCentreEst, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<RCentreEst> addRCentreEst(@RequestParam("rCentreEst") String rCentreEstReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RCentreEst rCentreEst = new ObjectMapper().readValue(rCentreEstReq, RCentreEst.class);
		
		RCentreEst rCentreEstBd = new RCentreEst(rCentreEst.getTitre(), rCentreEst.getDescription(),fileDownloadUri);
		
		RCentreEst updaterCentreEstBd= rCentreEstService.updateRCentreEst(rCentreEstBd);
		
		return new ResponseEntity<>(updaterCentreEstBd, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<RCentreEst> updateRCascades(@RequestParam("rCentreEst") String rCentreEstReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RCentreEst rCentreEst = new ObjectMapper().readValue(rCentreEstReq, RCentreEst.class);
		
		RCentreEst rCentreEstBd = new RCentreEst(rCentreEst.getId() ,rCentreEst.getTitre(),rCentreEst.getDescription(),fileDownloadUri);
		
		RCentreEst updateautrrCentreEstBd= rCentreEstService.updateRCentreEst(rCentreEstBd);
		
		return new ResponseEntity<>(updateautrrCentreEstBd, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRCentreEst(@PathVariable("id") Long id){
		rCentreEstService.deleteRCentreEst(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}
