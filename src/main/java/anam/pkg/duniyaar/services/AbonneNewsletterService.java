package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.AbonneNewsletterNotFindException;
import anam.pkg.duniyaar.model.AbonneNewsletter;
import anam.pkg.duniyaar.repo.AbonneNewsletterRepo;


@Service
public class AbonneNewsletterService {
	private final AbonneNewsletterRepo abonneNewsletterRepo;
	
	@Autowired
	public AbonneNewsletterService(AbonneNewsletterRepo abonneNewsletterRepo) {
		this.abonneNewsletterRepo=abonneNewsletterRepo;
	}
	
	public AbonneNewsletter AbonneNewsletter(AbonneNewsletter faq) {
		
		return abonneNewsletterRepo.save(faq);
	}
	
	public  AbonneNewsletter updatAbonneNewsletter(AbonneNewsletter faq) {
		return abonneNewsletterRepo.save(faq);
	}
	
	public AbonneNewsletter findPById(Long id){
		return abonneNewsletterRepo.findById(id)
				.orElseThrow(()->new AbonneNewsletterNotFindException("abonneNewsletter by id"+id+"was not found"));
	}
	
	public void delete(Long id) {
		
		abonneNewsletterRepo.deleteById(id);
	}

	public AbonneNewsletterRepo getRepo() {
		return abonneNewsletterRepo;
	}

	public List<AbonneNewsletter> findAllAbonneNewletters() {
		return abonneNewsletterRepo.findAll();
	}
}