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

import anam.pkg.duniyaar.model.RNord;
import anam.pkg.duniyaar.services.FileStorageService;
import anam.pkg.duniyaar.services.RNordService;

@RestController
@RequestMapping("/rNord")
public class RNordController {
	private final RNordService rNordService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public RNordController(RNordService rNordService) {
		this.rNordService = rNordService;	
	}
	
	/*@GetMapping("/oneactualiteMeteo")
	public ResponseEntity<List<ActualiteMeteoLimitOne>> getListActualiteMeteoLimitOne(){
		List<ActualiteMeteoLimitOne> actualiteMeteoLimitOne= actualiteMeteoService.getListActualiteMeteoLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<List<RNord>> getAllRNords(){
		List<RNord> rNords= rNordService.findAllRNords();
		
		return new ResponseEntity<>(rNords, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<RNord> getRNordById(@PathVariable("id") Long id){
		RNord rNord= rNordService.findRNordById(id);
		
		return new ResponseEntity<>(rNord, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<RNord> addRNord(@RequestParam("rNord") String rNordReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RNord rNord = new ObjectMapper().readValue(rNordReq, RNord.class);
		
		RNord rNordBd = new RNord(rNord.getTitre(), rNord.getDescription(),fileDownloadUri);
		
		RNord updaterNordBd= rNordService.updateRNord(rNordBd);
		
		return new ResponseEntity<>(updaterNordBd, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<RNord> updateNords(@RequestParam("rNord") String rNordReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RNord rNord = new ObjectMapper().readValue(rNordReq, RNord.class);
		
		RNord rNordBd = new RNord(rNord.getId() ,rNord.getTitre(),rNord.getDescription(),fileDownloadUri);
		
		RNord updateautrrNordBd= rNordService.updateRNord(rNordBd);
		
		return new ResponseEntity<>(updateautrrNordBd, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRNord(@PathVariable("id") Long id){
		rNordService.deleteRNord(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}
