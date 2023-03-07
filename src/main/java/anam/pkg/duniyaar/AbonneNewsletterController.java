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

import anam.pkg.duniyaar.model.AbonneNewsletter;
import anam.pkg.duniyaar.services.AbonneNewsletterService;

@RestController
@RequestMapping("/abonneNewsletter")
public class AbonneNewsletterController {
	private final AbonneNewsletterService abonneNewsletterService;
	
	public AbonneNewsletterController(AbonneNewsletterService abonneNewsletterService) {
		this.abonneNewsletterService = abonneNewsletterService;	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AbonneNewsletter>> getAllFAQs(){
		List<AbonneNewsletter> abonneNewsletters= abonneNewsletterService.findAllAbonneNewletters();
		
		return new ResponseEntity<>(abonneNewsletters, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<AbonneNewsletter> getFAQById(@PathVariable("id") Long id){
		AbonneNewsletter abonneNewsletter= abonneNewsletterService.findPById(id);
		
		return new ResponseEntity<>(abonneNewsletter, HttpStatus.OK);	
	}
	
	@PostMapping("/add")
	public ResponseEntity<AbonneNewsletter> addAabonneNewsletter(@RequestBody AbonneNewsletter abonneNewsletter){
		AbonneNewsletter newAlertMeteo= abonneNewsletterService.AbonneNewsletter(abonneNewsletter);
		
		return new ResponseEntity<>(newAlertMeteo, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<AbonneNewsletter> updateAbonneNewsletter(@RequestBody AbonneNewsletter vacancePoste){
		AbonneNewsletter updatevacancePoste= abonneNewsletterService.updatAbonneNewsletter(vacancePoste);
		
		return new ResponseEntity<>(updatevacancePoste, HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteFAQ(@PathVariable("id") Long id){
		abonneNewsletterService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
