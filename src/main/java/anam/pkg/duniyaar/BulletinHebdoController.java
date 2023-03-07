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

import anam.pkg.duniyaar.DTO.BulletinHebdoLimitOne;
import anam.pkg.duniyaar.model.BulletinHebdo;
import anam.pkg.duniyaar.services.BulletinHebdoService;
import anam.pkg.duniyaar.services.FileStorageService;

@RestController
@RequestMapping("/bulletinHebdo")
public class BulletinHebdoController {
	private final BulletinHebdoService bulletinHebdoService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	public BulletinHebdoController(BulletinHebdoService bulletinHebdoService) {
		this.bulletinHebdoService = bulletinHebdoService;	
	}
	
	@GetMapping("/oneBulletinHebdo")
	public ResponseEntity<List<BulletinHebdoLimitOne>> getListBulletinHebdoLimitOne(){
		List<BulletinHebdoLimitOne> bulletinLimitOne= bulletinHebdoService.getListBulletinHebdoLimitOne();
		
		return new ResponseEntity<>(bulletinLimitOne, HttpStatus.OK);		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<BulletinHebdo>> getAlllertMeteos(){
		List<BulletinHebdo> actualiteMeteos= bulletinHebdoService.findAllBulletinHebdos();
		
		return new ResponseEntity<>(actualiteMeteos, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<BulletinHebdo> getAlertMeteoById(@PathVariable("id") Long id){
		BulletinHebdo alertMeteo= bulletinHebdoService.findBulletinHebdoById(id);
		
		return new ResponseEntity<>(alertMeteo, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<BulletinHebdo> addActuHanam(@RequestParam("bulletinHebdo") String bulletinHebdoe, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		BulletinHebdo bulletinHebdo = new ObjectMapper().readValue(bulletinHebdoe, BulletinHebdo.class);
		
		BulletinHebdo autrActu = new BulletinHebdo(bulletinHebdo.getTitre(),fileDownloadUri);
		
		BulletinHebdo updateautrActu= bulletinHebdoService.updateBulletinHebdo(autrActu);
		
		return new ResponseEntity<>(updateautrActu, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<BulletinHebdo> updateBulletinHebdo(@RequestParam("bulletinHebdo") String bulletinHebdoe, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		BulletinHebdo actuHanam = new ObjectMapper().readValue(bulletinHebdoe, BulletinHebdo.class);
		
		BulletinHebdo autrActu = new BulletinHebdo(actuHanam.getId() ,actuHanam.getTitre(),fileDownloadUri);
		
		BulletinHebdo updateautrActu= bulletinHebdoService.updateBulletinHebdo(autrActu);
		
		return new ResponseEntity<>(updateautrActu, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAlertMeteo(@PathVariable("id") Long id){
		bulletinHebdoService.deleteBulletinHebdo(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
