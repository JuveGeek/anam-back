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

import anam.pkg.duniyaar.model.RCascades;
import anam.pkg.duniyaar.services.FileStorageService;
import anam.pkg.duniyaar.services.RCascadesService;

@RestController
@RequestMapping("/rCascades")
public class RCascadesController {
	private final RCascadesService rCascadesService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public RCascadesController(RCascadesService rCascadesService) {
		this.rCascadesService = rCascadesService;	
	}
	
	/*@GetMapping("/oneactualiteMeteo")
	public ResponseEntity<List<ActualiteMeteoLimitOne>> getListActualiteMeteoLimitOne(){
		List<ActualiteMeteoLimitOne> actualiteMeteoLimitOne= actualiteMeteoService.getListActualiteMeteoLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}*/
	
	@GetMapping("/all")
	public ResponseEntity<List<RCascades>> getAllRCascadess(){
		List<RCascades> rCascadess= rCascadesService.findAllRCascadess();
		
		return new ResponseEntity<>(rCascadess, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<RCascades> getRCascadesById(@PathVariable("id") Long id){
		RCascades rCascades= rCascadesService.findRCascadesById(id);
		
		return new ResponseEntity<>(rCascades, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<RCascades> addRCascades(@RequestParam("rCascades") String rCascadesReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RCascades rCascades = new ObjectMapper().readValue(rCascadesReq, RCascades.class);
		
		RCascades rCascadesBd = new RCascades(rCascades.getTitre(), rCascades.getDescription(),fileDownloadUri);
		
		RCascades updaterCascadesBd= rCascadesService.updateRCascades(rCascadesBd);
		
		return new ResponseEntity<>(updaterCascadesBd, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<RCascades> updateRCascades(@RequestParam("rCascades") String rCascadesReq, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		RCascades rCascades = new ObjectMapper().readValue(rCascadesReq, RCascades.class);
		
		RCascades rCascadesBd = new RCascades(rCascades.getId() ,rCascades.getTitre(),rCascades.getDescription(),fileDownloadUri);
		
		RCascades updateautrrCascadesBd= rCascadesService.updateRCascades(rCascadesBd);
		
		return new ResponseEntity<>(updateautrrCascadesBd, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRCascades(@PathVariable("id") Long id){
		rCascadesService.deleteRCascades(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}
