package ma.emsi.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(path="comptes",excerptProjection=CompteSummary.class)
public interface CompteRepository extends JpaRepository<Compte,String> {}
