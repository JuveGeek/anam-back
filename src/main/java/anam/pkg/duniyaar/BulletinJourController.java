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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import anam.pkg.duniyaar.DTO.BulletinJourLimitOne;

import anam.pkg.duniyaar.model.BulletinJour;
import anam.pkg.duniyaar.services.BulletinJourService;
import anam.pkg.duniyaar.services.FileStorageService;

@RestController
@RequestMapping("/bulletinJour")
public class BulletinJourController {
	private final BulletinJourService bulletinJourService;
	
	@GetMapping("/oneBulletinJour")
	public ResponseEntity<List<BulletinJourLimitOne>> getListBulletinJourLimitOne(){
		List<BulletinJourLimitOne> bulletinLimitOne= bulletinJourService.getListBulletinJourLimitOne();
		
		return new ResponseEntity<>(bulletinLimitOne, HttpStatus.OK);		
	}
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@PutMapping("/add")
	public ResponseEntity<BulletinJour> uploadFile(@RequestParam("bulletinJour") String bullJour,@RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		BulletinJour bullJourr = new ObjectMapper().readValue(bullJour, BulletinJour.class);
		
		BulletinJour autrbullJourr = new BulletinJour(bullJourr.getTitre(),bullJourr.getDescription(),fileDownloadUri);
		
		BulletinJour updateautrActu= bulletinJourService.updateBulletinJour(autrbullJourr);
		
		return new ResponseEntity<>(updateautrActu, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<BulletinJour> updateBulletin(@RequestParam("bulletinJour") String bulletinJourr, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		BulletinJour bulletinJour = new ObjectMapper().readValue(bulletinJourr, BulletinJour.class);
		
		BulletinJour autrbulletinJour = new BulletinJour(bulletinJour.getId() ,bulletinJour.getTitre(),bulletinJour.getDescription(),fileDownloadUri);
		
		BulletinJour updateautrbulletinJour= bulletinJourService.updateBulletinJour(autrbulletinJour);
		
		return new ResponseEntity<>(updateautrbulletinJour, HttpStatus.OK);
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
	
	
	public BulletinJourController(BulletinJourService bulletinJourService) {
		this.bulletinJourService = bulletinJourService;	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<BulletinJour>> getAllBulletinJours(){
		List<BulletinJour> actualiteMeteos= bulletinJourService.findAllAlertMeteos();
		
		return new ResponseEntity<>(actualiteMeteos, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<BulletinJour> getBulletinJourById(@PathVariable("id") Long id){
		BulletinJour alertMeteo= bulletinJourService.findBulletinJourPById(id);
		
		return new ResponseEntity<>(alertMeteo, HttpStatus.OK);	
	}
	
	@PostMapping("/add")
	public ResponseEntity<BulletinJour> addBulletinJour(@RequestBody BulletinJour alertMeteo){
		BulletinJour newAlertMeteo= bulletinJourService.addBulletinJour(alertMeteo);
		
		return new ResponseEntity<>(newAlertMeteo, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBulletinJour(@PathVariable("id") Long id){
		bulletinJourService.deleteBulletinJour(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
