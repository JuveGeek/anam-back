package anam.pkg.duniyaar;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import anam.pkg.duniyaar.DTO.AutreActualiteLimitThree;
import anam.pkg.duniyaar.model.AutreActualite;
import anam.pkg.duniyaar.services.AutreActualiteService;
import anam.pkg.duniyaar.services.FileStorageService;

@RestController
@RequestMapping("/autreActualite")
public class AutreActualiteController {
	private final AutreActualiteService autreActualiteService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public AutreActualiteController(AutreActualiteService autreActualiteService) {
		this.autreActualiteService = autreActualiteService;	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AutreActualite>> getAllautreActualites(){
		List<AutreActualite> actualiteMeteos= autreActualiteService.findAllAutreActualites();
		
		return new ResponseEntity<>(actualiteMeteos, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<AutreActualite> getAlertMeteoById(@PathVariable("id") Long id){
		AutreActualite alertMeteo= autreActualiteService.findAutreActualiteById(id);
		
		return new ResponseEntity<>(alertMeteo, HttpStatus.OK);	
	}
	
	/*@PostMapping("/add")
	public ResponseEntity<AutreActualite> addAutreActualite(@RequestBody AutreActualite alertMeteo){
		AutreActualite newAlertMeteo= autreActualiteService.addAutreActualite(alertMeteo);
		
		return new ResponseEntity<>(newAlertMeteo, HttpStatus.CREATED);
	}*/
	
	@PutMapping("/add")
	public ResponseEntity<AutreActualite> addActuHanam(@RequestParam("actuHanam") String actuHana, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		AutreActualite actuHanam = new ObjectMapper().readValue(actuHana, AutreActualite.class);
		
		AutreActualite autrActu = new AutreActualite(actuHanam.getTitre(),actuHanam.getDescription(),fileDownloadUri);
		
		AutreActualite updateautrActu= autreActualiteService.updateAutreActualite(autrActu);
		
		return new ResponseEntity<>(updateautrActu, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<AutreActualite> updateActuHanam(@RequestParam("actuHanam") String actuHana, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		AutreActualite actuHanam = new ObjectMapper().readValue(actuHana, AutreActualite.class);
		
		AutreActualite autrActu = new AutreActualite(actuHanam.getId() ,actuHanam.getTitre(),actuHanam.getDescription(),fileDownloadUri);
		
		AutreActualite updateautrActu= autreActualiteService.updateAutreActualite(autrActu);
		
		return new ResponseEntity<>(updateautrActu, HttpStatus.OK);
	}
	
	@GetMapping("/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,HttpServletRequest request){
		
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		
		String contentType = null;
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		}catch(IOException ex) {
			System.out.println("Could not determine fileType");
		}
		
		if(contentType==null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.body(resource);
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAlertMeteo(@PathVariable("id") Long id){
		autreActualiteService.deleteAautreActualite(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@GetMapping("/autreActualiteLimitThree")
	public ResponseEntity<List<AutreActualiteLimitThree>> getListAutreActualiteLimitThree(){
		List<AutreActualiteLimitThree> actualiteMeteos= autreActualiteService.getListAutreActualiteLimitThree();
		
		return new ResponseEntity<>(actualiteMeteos, HttpStatus.OK);		
	}
	
}


