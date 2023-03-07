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

import anam.pkg.duniyaar.DTO.MeteoMediaLimitOne;
import anam.pkg.duniyaar.Response.FileResponse;
import anam.pkg.duniyaar.model.MeteoMedia;
import anam.pkg.duniyaar.repo.MeteoMediaRepo;
import anam.pkg.duniyaar.services.FileStorageService;
import anam.pkg.duniyaar.services.MeteoMediaService;

@RestController
@RequestMapping("/meteoMedia")
public class MeteoMediaController {
	private final MeteoMediaService meteoMediaService;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private MeteoMediaRepo meteoMediaRepo;
	
	@GetMapping("/oneMeteoMedia")
	public ResponseEntity<List<MeteoMediaLimitOne>> getListMeteoMediaLimitOne(){
		List<MeteoMediaLimitOne> meteoMediaLimitOne= meteoMediaService.getListMeteoMediaLimitOne();
		
		return new ResponseEntity<>(meteoMediaLimitOne, HttpStatus.OK);		
	}
	
	
	public MeteoMediaController(MeteoMediaService meteoMediaService) {
		this.meteoMediaService = meteoMediaService;	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<MeteoMedia>> getAlllertMeteos(){
		List<MeteoMedia> meteoMedias= meteoMediaService.findAllMeteoMedias();
		
		return new ResponseEntity<>(meteoMedias, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<MeteoMedia> getAlertMeteoById(@PathVariable("id") Long id){
		MeteoMedia meteoMedia= meteoMediaService.findMeteoMediaById(id);
		
		return new ResponseEntity<>(meteoMedia, HttpStatus.OK);	
	}
	
	@PutMapping("/files")
	public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file){
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		MeteoMedia bullJour = new MeteoMedia(fileDownloadUri);
		
		meteoMediaRepo.save(bullJour);
		
		FileResponse fileResponse = new FileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
		return new ResponseEntity<FileResponse>(fileResponse,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<MeteoMedia> updateMeteoMedia(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file){
		
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		MeteoMedia bulJou = new MeteoMedia(id,fileDownloadUri);
		
		
		MeteoMedia updateMeteoMedia= meteoMediaService.updateMeteoMedia(bulJou);
		
		return new ResponseEntity<>(updateMeteoMedia, HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMeteoMedia(@PathVariable("id") Long id){
		meteoMediaService.deleteMeteoMedia(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
