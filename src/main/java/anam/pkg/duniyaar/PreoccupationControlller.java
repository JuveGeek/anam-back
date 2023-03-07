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

import anam.pkg.duniyaar.DTO.PreoccupationLimitThree;
import anam.pkg.duniyaar.model.Preoccupation;
import anam.pkg.duniyaar.services.PreoccupationService;

@RestController
@RequestMapping("/preoccupation")
public class PreoccupationControlller{
	private final PreoccupationService preoccupationService;
	
	public PreoccupationControlller(PreoccupationService preoccupationService) {
		this.preoccupationService = preoccupationService;	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Preoccupation>> getAllFAQs(){
		List<Preoccupation> abonneNewsletters= preoccupationService.findAlPreoccupations();
		
		return new ResponseEntity<>(abonneNewsletters, HttpStatus.OK);		
	}
	
	@GetMapping("/threePreoccupation")
	public ResponseEntity<List<PreoccupationLimitThree>> getPreoccupationLimitThree(){
		List<PreoccupationLimitThree> abonneNewsletters= preoccupationService.geListPreoccupationLimitThree();
		
		return new ResponseEntity<>(abonneNewsletters, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<Preoccupation> getFAQById(@PathVariable("id") Long id){
		Preoccupation abonneNewsletter= preoccupationService.findPreoccupationById(id);
		
		return new ResponseEntity<>(abonneNewsletter, HttpStatus.OK);	
	}
	
	@PostMapping("/add")
	public ResponseEntity<Preoccupation> addA(@RequestBody Preoccupation preoccupation){
		Preoccupation newPreoccupation= preoccupationService.Preoccupation(preoccupation);
		
		return new ResponseEntity<>(newPreoccupation, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Preoccupation> update(@RequestBody Preoccupation vacancePoste){
		Preoccupation updatevacancePoste= preoccupationService.updatPreoccupation(vacancePoste);
		
		return new ResponseEntity<>(updatevacancePoste, HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteFAQ(@PathVariable("id") Long id){
		preoccupationService.deletePreoccupationById(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
