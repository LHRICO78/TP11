package lml.snir.gestionpersonne.metier.transactionel;

import lml.snir.gestionpersonne.metier.entity.Personne;
import java.util.List;
import lml.snir.persistence.CrudService;

/**
 *
 * @author fanou
 */
public interface PersonneService extends CrudService<Personne> {
    List<Personne> getAllByType(Class type) throws Exception;
    List<Personne> getByNom(String nom) throws Exception;
    List<Personne> getByGenre(boolean masculin) throws Exception;
    
    public List<Personne> getAllSorted() throws Exception;
    public List<Personne> getAllSorted(int begin, int count) throws Exception;
}
