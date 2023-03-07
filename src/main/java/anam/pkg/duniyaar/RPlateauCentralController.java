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

import anam.pkg.duniyaar.model.RPlateauCentral;
import anam.pkg.duniyaar.services.FileStorageService;
import anam.pkg.duniyaar.services.RPlateauCentralService;

@RestController
@RequestMapping("/rPlateauCentral")
public class RPlateauCentralController {
	private final RPlateauCentralService rPlateauCentralService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public RPlateauCentralController(RPlateauCentralService rPlateauCentralService) {
		this.rPlateauCentralService = rPlateauCentralService;	
	}
	
	/*@GetMapping("/oneactualiteMeteo")
	public ResponseEntity<List<ActualiteMeteoLimitOne>> getListActualiteMeteoLimitOne(){
		List<ActualiteMeteoLimitOne> actualiteMeteoLimitOne= actualiteMeteoService.getListActualiteMeteoLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<List<RPlateauCentral>> getAllRPlateauCentrals(){
		List<RPlateauCentral> rPlateauCentrals= rPlateauCentralService.findAllRPlateauCentrals();
		
		return new ResponseEntity<>(rPlateauCentrals, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<RPlateauCentral> getRPlateauCentralById(@PathVariable("id") Long id){
		RPlateauCentral rPlateauCentral= rPlateauCentralService.findRPlateauCentralById(id);
		
		return new ResponseEntity<>(rPlateauCentral, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<RPlateauCentral> addRPlateauCentral(@RequestParam("rPlateauCentral") String rPlateauCentralReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RPlateauCentral rPlateauCentral = new ObjectMapper().readValue(rPlateauCentralReq, RPlateauCentral.class);
		
		RPlateauCentral rPlateauCentralBd = new RPlateauCentral(rPlateauCentral.getTitre(), rPlateauCentral.getDescription(),fileDownloadUri);
		
		RPlateauCentral updaterPlateauCentralBd= rPlateauCentralService.updateRPlateauCentral(rPlateauCentralBd);
		
		return new ResponseEntity<>(updaterPlateauCentralBd, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<RPlateauCentral> updateRCascades(@RequestParam("rPlateauCentral") String rPlateauCentralReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RPlateauCentral rPlateauCentral = new ObjectMapper().readValue(rPlateauCentralReq, RPlateauCentral.class);
		
		RPlateauCentral rPlateauCentralBd = new RPlateauCentral(rPlateauCentral.getId() ,rPlateauCentral.getTitre(),rPlateauCentral.getDescription(),fileDownloadUri);
		
		RPlateauCentral updateautrrPlateauCentralBd= rPlateauCentralService.updateRPlateauCentral(rPlateauCentralBd);
		
		return new ResponseEntity<>(updateautrrPlateauCentralBd, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRPlateauCentral(@PathVariable("id") Long id){
		rPlateauCentralService.deleteRPlateauCentral(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}
