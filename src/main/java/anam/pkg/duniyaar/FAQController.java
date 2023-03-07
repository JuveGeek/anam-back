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

import anam.pkg.duniyaar.model.FAQ;
import anam.pkg.duniyaar.services.FAQService;

@RestController
@RequestMapping("/faq")
public class FAQController {
	private final FAQService vacancePosteService;
	
	public FAQController(FAQService vacancePosteService) {
		this.vacancePosteService = vacancePosteService;	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<FAQ>> getAllFAQs(){
		List<FAQ> vacancePostes= vacancePosteService.findAllFaqs();
		
		return new ResponseEntity<>(vacancePostes, HttpStatus.OK);		
	}
		
	@GetMapping("/find/{id}")
	public ResponseEntity<FAQ> getFAQById(@PathVariable("id") Long id){
		FAQ alertMeteo= vacancePosteService.findFAQPById(id);
		
		return new ResponseEntity<>(alertMeteo, HttpStatus.OK);	
	}
	
	@PostMapping("/add")
	public ResponseEntity<FAQ> addActualiteMeteo(@RequestBody FAQ vacancePoste){
		FAQ newAlertMeteo= vacancePosteService.addFAQ(vacancePoste);
		
		return new ResponseEntity<>(newAlertMeteo, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<FAQ> updateAlertMeteo(@RequestBody FAQ vacancePoste){
		FAQ updatevacancePoste= vacancePosteService.updateFaq(vacancePoste);
		
		return new ResponseEntity<>(updatevacancePoste, HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteFAQ(@PathVariable("id") Long id){
		vacancePosteService.deleteFAQ(id);
		
		return new ResponseEntity<>(HttpStatus.OK);

	}
}
