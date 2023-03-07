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

import anam.pkg.duniyaar.model.RCentreNord;
import anam.pkg.duniyaar.services.FileStorageService;
import anam.pkg.duniyaar.services.RCentreNordService;

@RestController
@RequestMapping("/rCentreNord")
public class RCentreNordController {
	private final RCentreNordService rCentreNordService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public RCentreNordController(RCentreNordService rCentreNordService) {
		this.rCentreNordService = rCentreNordService;	
	}
	
	/*@GetMapping("/oneactualiteMeteo")
	public ResponseEntity<List<ActualiteMeteoLimitOne>> getListActualiteMeteoLimitOne(){
		List<ActualiteMeteoLimitOne> actualiteMeteoLimitOne= actualiteMeteoService.getListActualiteMeteoLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<List<RCentreNord>> getAllRCentreNords(){
		List<RCentreNord> rCentreNords= rCentreNordService.findAllRCentreNords();
		
		return new ResponseEntity<>(rCentreNords, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<RCentreNord> getRCentreNordById(@PathVariable("id") Long id){
		RCentreNord rCentreNord= rCentreNordService.findRCentreNordById(id);
		
		return new ResponseEntity<>(rCentreNord, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<RCentreNord> addRCentreNord(@RequestParam("rCentreNord") String rCentreNordReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RCentreNord rCentreNord = new ObjectMapper().readValue(rCentreNordReq, RCentreNord.class);
		
		RCentreNord rCentreEstBd = new RCentreNord(rCentreNord.getTitre(), rCentreNord.getDescription(),fileDownloadUri);
		
		RCentreNord updaterCentreNordBd= rCentreNordService.updateRCentreNord(rCentreEstBd);
		
		return new ResponseEntity<>(updaterCentreNordBd, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<RCentreNord> updateRNord(@RequestParam("rCentreNord") String rCentreNordReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RCentreNord rCentreNord = new ObjectMapper().readValue(rCentreNordReq, RCentreNord.class);
		
		RCentreNord rCentreNordBd = new RCentreNord(rCentreNord.getId() ,rCentreNord.getTitre(),rCentreNord.getDescription(),fileDownloadUri);
		
		RCentreNord updateautrrCentreNordBd= rCentreNordService.updateRCentreNord(rCentreNordBd);
		
		return new ResponseEntity<>(updateautrrCentreNordBd, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRCentreNord(@PathVariable("id") Long id){
		rCentreNordService.deleteRCentreNord(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}
