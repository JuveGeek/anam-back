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

import anam.pkg.duniyaar.DTO.BulletinBADLimitOne;
import anam.pkg.duniyaar.model.BulletinBAD;
import anam.pkg.duniyaar.services.BulletinBADService;
import anam.pkg.duniyaar.services.FileStorageService;

@RestController
@RequestMapping("/bulletinBAD")
public class BulletinBADController {
	private final BulletinBADService bulletinBADService;
	@Autowired
	private FileStorageService fileStorageService;
	
	@GetMapping("/oneBulletinBAD")
	public ResponseEntity<List<BulletinBADLimitOne>> getListBulletinBADLimitOne(){
		List<BulletinBADLimitOne> bulletinLimitOne= bulletinBADService.getListBulletinBADLimitOne();
		
		return new ResponseEntity<>(bulletinLimitOne, HttpStatus.OK);		
	}
	
	public BulletinBADController(BulletinBADService bulletinBADService) {
		this.bulletinBADService = bulletinBADService;	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<BulletinBAD>> getAllBulletinBADs(){
		List<BulletinBAD> actualiteMeteos= bulletinBADService.findAllBulletinBAD();
		
		return new ResponseEntity<>(actualiteMeteos, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<BulletinBAD> getBulletinBADById(@PathVariable("id") Long id){
		BulletinBAD alertMeteo= bulletinBADService.findBulletinBADById(id);
		
		return new ResponseEntity<>(alertMeteo, HttpStatus.OK);	
	}
	
	@PutMapping("/add")
	public ResponseEntity<BulletinBAD> addActuHanam(@RequestParam("bulletinBAD") String bulletinBADe, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		BulletinBAD bulletinBAD = new ObjectMapper().readValue(bulletinBADe, BulletinBAD.class);
		
		BulletinBAD autrActu = new BulletinBAD(bulletinBAD.getTitre(),fileDownloadUri);
		
		BulletinBAD updateautrActu= bulletinBADService.updateBulletinBAD(autrActu);
		
		return new ResponseEntity<>(updateautrActu, HttpStatus.OK);

	}
	
	@PutMapping("/update")
	public ResponseEntity<BulletinBAD> updateBulletinBAD(@RequestParam("bulletinBAD") String bulletinBADe, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		BulletinBAD actuHanam = new ObjectMapper().readValue(bulletinBADe, BulletinBAD.class);
		
		BulletinBAD autrActu = new BulletinBAD(actuHanam.getId() ,actuHanam.getTitre(),fileDownloadUri);
		
		BulletinBAD updateautrActu= bulletinBADService.updateBulletinBAD(autrActu);
		
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
		bulletinBADService.deleteBulletinBAD(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
