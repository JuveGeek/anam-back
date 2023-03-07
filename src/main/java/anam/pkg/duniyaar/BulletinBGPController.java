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

import anam.pkg.duniyaar.DTO.BulletinBGPLimitOne;
import anam.pkg.duniyaar.model.BulletinBGP;

import anam.pkg.duniyaar.services.BulletinBGPService;
import anam.pkg.duniyaar.services.FileStorageService;

@RestController
@RequestMapping("/bulletinBGP")
public class BulletinBGPController {
	private final BulletinBGPService bulletinBGPService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@GetMapping("/oneBulletinBGP")
	public ResponseEntity<List<BulletinBGPLimitOne>> getListBulletinBGPLimitOne(){
		List<BulletinBGPLimitOne> bulletinLimitOne= bulletinBGPService.getListBulletinBGPLimitOne();
		
		return new ResponseEntity<>(bulletinLimitOne, HttpStatus.OK);		
	}
	
	public BulletinBGPController(BulletinBGPService bulletinBGPService) {
		this.bulletinBGPService = bulletinBGPService;	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<BulletinBGP>> getAllBulletinBGP(){
		List<BulletinBGP> bulletinBGPs= bulletinBGPService.findAllBulletinBGPs();
		
		return new ResponseEntity<>(bulletinBGPs, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<BulletinBGP> getBulletinBGPById(@PathVariable("id") Long id){
		BulletinBGP alertMeteo= bulletinBGPService.findBulletinBGPById(id);
		
		return new ResponseEntity<>(alertMeteo, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<BulletinBGP> addActuHanam(@RequestParam("bulletinBGP") String bulletinBGPe, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		BulletinBGP bulletinBGP = new ObjectMapper().readValue(bulletinBGPe, BulletinBGP.class);
		
		BulletinBGP autrActu = new BulletinBGP(bulletinBGP.getTitre(),fileDownloadUri);
		
		BulletinBGP updateautrActu= bulletinBGPService.updateBulletinBGP(autrActu);
		
		return new ResponseEntity<>(updateautrActu, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<BulletinBGP> updateBulletinBGP(@RequestParam("bulletinBGP") String bulletinBGPe, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		BulletinBGP actuHanam = new ObjectMapper().readValue(bulletinBGPe, BulletinBGP.class);
		
		BulletinBGP autrActu = new BulletinBGP(actuHanam.getId() ,actuHanam.getTitre(),fileDownloadUri);
		
		BulletinBGP updateautrActu= bulletinBGPService.updateBulletinBGP(autrActu);
		
		return new ResponseEntity<>(updateautrActu, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBulletinBGP(@PathVariable("id") Long id){
		bulletinBGPService.deleteBulletinBGP(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
