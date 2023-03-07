package anam.pkg.duniyaar;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import anam.pkg.duniyaar.DTO.AlertMeteoLimitOne;
import anam.pkg.duniyaar.model.AlertMeteo;
import anam.pkg.duniyaar.services.AlertMeteoService;

@RestController
@RequestMapping("/alertMeteo")
public class AlertMeteoController {
	private final AlertMeteoService alertMeteoService;
	
	public AlertMeteoController(AlertMeteoService alertMeteoService) {
		this.alertMeteoService = alertMeteoService;	
	}
	
	@GetMapping("/oneAlertMeteo")
	public ResponseEntity<List<AlertMeteoLimitOne>> getListAlertMeteoLimitOne(){
		List<AlertMeteoLimitOne> alertMeteoLimitOne= alertMeteoService.getListAlertMeteoLimitOne();
		
		return new ResponseEntity<>(alertMeteoLimitOne, HttpStatus.OK);		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AlertMeteo>> getAllAlertMeteos(){
		List<AlertMeteo> actualiteMeteos= alertMeteoService.findAllAlertMeteos();
		
		return new ResponseEntity<>(actualiteMeteos, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<AlertMeteo> getAlertMeteoById(@PathVariable("id") Long id){
		AlertMeteo alertMeteo= alertMeteoService.findAlertMeteoById(id);
		
		return new ResponseEntity<>(alertMeteo, HttpStatus.OK);	
	}
	
	@PostMapping("/add")
	public ResponseEntity<AlertMeteo> addActualiteMeteo(@RequestBody AlertMeteo alertMeteo){
		AlertMeteo newAlertMeteo= alertMeteoService.addAlertMeteo(alertMeteo);
		
		return new ResponseEntity<>(newAlertMeteo, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<AlertMeteo> updateAlertMeteo(@RequestBody AlertMeteo alertMeteo){
		AlertMeteo updatealertMeteo= alertMeteoService.updateAlertMeteo(alertMeteo);
		
		return new ResponseEntity<>(updatealertMeteo, HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAlertMeteo(@PathVariable("id") Long id){
		alertMeteoService.deleteAlertMeteo(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	} 
}
