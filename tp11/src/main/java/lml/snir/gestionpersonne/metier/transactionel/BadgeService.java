package lml.snir.gestionpersonne.metier.transactionel;

import java.util.List;
import lml.snir.persistence.CrudService;
import lml.snir.gestionpersonne.metier.entity.Badge;

/**
 *
 * @author fanou
 */
public interface BadgeService extends CrudService<Badge> {
    public Badge getByContenu(String contenu) throws Exception;
    public List<Badge> getByAttribution(boolean attribue) throws Exception;
}
