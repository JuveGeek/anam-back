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

import anam.pkg.duniyaar.model.AvisAppelOffre;
import anam.pkg.duniyaar.services.AvisAppelOffreService;

@RestController
@RequestMapping("/avisAppelOffre")
public class AvisAppelOffreController {
	private final AvisAppelOffreService avisAppelOffreService;
	
	public AvisAppelOffreController(AvisAppelOffreService avisAppelOffreService) {
		this.avisAppelOffreService = avisAppelOffreService;	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AvisAppelOffre>> getAllAvisAppelOffres(){
		List<AvisAppelOffre> actualiteMeteos= avisAppelOffreService.findAllAvisAppelOffres();
		
		return new ResponseEntity<>(actualiteMeteos, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<AvisAppelOffre> getAlertMeteoById(@PathVariable("id") Long id){
		AvisAppelOffre alertMeteo= avisAppelOffreService.findAutreAvisAppelOffreById(id);
		
		return new ResponseEntity<>(alertMeteo, HttpStatus.OK);	
	}
	
	@PostMapping("/add")
	public ResponseEntity<AvisAppelOffre> addActualiteMeteo(@RequestBody AvisAppelOffre alertMeteo){
		AvisAppelOffre newAlertMeteo= avisAppelOffreService.addAvisAppelOffre(alertMeteo);
		
		return new ResponseEntity<>(newAlertMeteo, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<AvisAppelOffre> updateAlertMeteo(@RequestBody AvisAppelOffre alertMeteo){
		AvisAppelOffre updateavisAppelOffre= avisAppelOffreService.updateAvisAppelOffre(alertMeteo);
		
		return new ResponseEntity<>(updateavisAppelOffre, HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAlertMeteo(@PathVariable("id") Long id){
		avisAppelOffreService.deleteAvisAppelOffre(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
