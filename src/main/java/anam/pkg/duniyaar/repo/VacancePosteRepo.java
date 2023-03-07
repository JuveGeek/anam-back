package anam.pkg.duniyaar.repo;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import anam.pkg.duniyaar.model.VacancePoste;

@Repository
public interface VacancePosteRepo extends JpaRepositoryImplementation<VacancePoste, Long> {

}
