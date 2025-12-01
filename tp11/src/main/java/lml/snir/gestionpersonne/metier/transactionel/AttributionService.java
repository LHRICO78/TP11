package lml.snir.gestionpersonne.metier.transactionel;

import lml.snir.persistence.CrudService;
import lml.snir.gestionpersonne.metier.entity.Attribution;
import lml.snir.gestionpersonne.metier.entity.Badge;
import lml.snir.gestionpersonne.metier.entity.Personne;

/**
 *
 * @author fanou
 */
public interface AttributionService extends CrudService<Attribution> {
    public Attribution getByBadge(Badge badge) throws Exception;
    public Attribution getByPersonne(Personne personne) throws Exception;
    public Boolean isBadgeAttribue(Personne personne) throws Exception;
    public Boolean isBadgeAttribue(Badge badge) throws Exception;
}
