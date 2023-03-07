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

import anam.pkg.duniyaar.DTO.CrConseilMinistreLimitOne;
import anam.pkg.duniyaar.model.CRConseilMinistre;
import anam.pkg.duniyaar.services.CRConseilMinistreService;
import anam.pkg.duniyaar.services.FileStorageService;

@RestController
@RequestMapping("/cRConseilMinisre")
public class CRConseilMinisreController {
	private final CRConseilMinistreService cRConseilMinisreService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@GetMapping("/oneCrConseilMinistre")
	public ResponseEntity<List<CrConseilMinistreLimitOne>> getListCrConseilMinistreLimitOneLimitOne(){
		List<CrConseilMinistreLimitOne> actualiteMeteoLimitOne= cRConseilMinisreService.getListCRConseilMinistreLimitOne();
		
		return new ResponseEntity<>(actualiteMeteoLimitOne, HttpStatus.OK);		
	}
	
	public CRConseilMinisreController(CRConseilMinistreService cRConseilMinisreService) {
		this.cRConseilMinisreService = cRConseilMinisreService;	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CRConseilMinistre>> getAllCRConseilMinistres(){
		List<CRConseilMinistre> actualiteMeteos= cRConseilMinisreService.findAllCRConseilMinistres();
		
		return new ResponseEntity<>(actualiteMeteos, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<CRConseilMinistre> getCRConseilMinistreById(@PathVariable("id") Long id){
		CRConseilMinistre alertMeteo= cRConseilMinisreService.findBulletinJourPById(id);
		
		return new ResponseEntity<>(alertMeteo, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<CRConseilMinistre> addActuHanam(@RequestParam("cRConseilMinistre") String actuMeteoo, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		CRConseilMinistre actuMeteo = new ObjectMapper().readValue(actuMeteoo, CRConseilMinistre.class);
		
		CRConseilMinistre autrActu = new CRConseilMinistre(actuMeteo.getTitre(),actuMeteo.getDescription(),fileDownloadUri);
		
		CRConseilMinistre updateautrActu= cRConseilMinisreService.updateCRConseilMinistre(autrActu);
		
		return new ResponseEntity<>(updateautrActu, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<CRConseilMinistre> updateActuHanam(@RequestParam("cRConseilMinistre") String actuMeteoo, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		CRConseilMinistre actuHanam = new ObjectMapper().readValue(actuMeteoo, CRConseilMinistre.class);
		
		CRConseilMinistre autrActu = new CRConseilMinistre(actuHanam.getId() ,actuHanam.getTitre(),actuHanam.getDescription(),fileDownloadUri);
		
		CRConseilMinistre updateautrActu= cRConseilMinisreService.updateCRConseilMinistre(autrActu);
		
		return new ResponseEntity<>(updateautrActu, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCRConseilMinistre(@PathVariable("id") Long id){
		cRConseilMinisreService.deleteCRConseilMinistre(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
