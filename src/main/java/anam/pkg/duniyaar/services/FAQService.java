package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.exception.faqNotFindException;
import anam.pkg.duniyaar.model.FAQ;
import anam.pkg.duniyaar.repo.FAQRepo;

@Service
public class FAQService {
	private final FAQRepo faqRepo;
	
	@Autowired
	public FAQService(FAQRepo faqRepo) {
		this.faqRepo=faqRepo;
	}
	
	public FAQ addFAQ(FAQ faq) {
		
		return faqRepo.save(faq);
	}
	
	public FAQ updateFaq(FAQ faq) {
		return faqRepo.save(faq);
	}
	
	public FAQ findFAQPById(Long id){
		return faqRepo.findById(id)
				.orElseThrow(()->new faqNotFindException("FAQ by id"+id+"was not found"));
	}
	
	public void deleteFAQ(Long id) {
		
	 faqRepo.deleteById(id);
	}

	public FAQRepo getFAQRepo() {
		return faqRepo;
	}

	public List<FAQ> findAllFaqs() {
		return faqRepo.findAll();
	}
}