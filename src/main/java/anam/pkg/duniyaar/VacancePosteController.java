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

import anam.pkg.duniyaar.model.VacancePoste;
import anam.pkg.duniyaar.services.VacancePosteService;

@RestController
@RequestMapping("/vacancePoste")
public class VacancePosteController {
	private final VacancePosteService vacancePosteService;
	
	public VacancePosteController(VacancePosteService vacancePosteService) {
		this.vacancePosteService = vacancePosteService;	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<VacancePoste>> getAllVacancePostes(){
		List<VacancePoste> vacancePostes= vacancePosteService.findAllVacancePostes();
		
		return new ResponseEntity<>(vacancePostes, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<VacancePoste> getVacancePosteById(@PathVariable("id") Long id){
		VacancePoste alertMeteo= vacancePosteService.findVacancePosteById(id);
		
		return new ResponseEntity<>(alertMeteo, HttpStatus.OK);	
	}
	
	@PostMapping("/add")
	public ResponseEntity<VacancePoste> addActualiteMeteo(@RequestBody VacancePoste vacancePoste){
		VacancePoste newAlertMeteo= vacancePosteService.addVacancePoste(vacancePoste);
		
		return new ResponseEntity<>(newAlertMeteo, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<VacancePoste> updateAlertMeteo(@RequestBody VacancePoste vacancePoste){
		VacancePoste updatevacancePoste= vacancePosteService.updateVacancePoste(vacancePoste);
		
		return new ResponseEntity<>(updatevacancePoste, HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteVacancePoste(@PathVariable("id") Long id){
		vacancePosteService.deleteVacancePoste(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
