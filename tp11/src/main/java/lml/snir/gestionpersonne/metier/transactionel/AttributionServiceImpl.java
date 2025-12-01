package lml.snir.gestionpersonne.metier.transactionel;

import java.util.List;
import lml.snir.gestionpersonne.metier.entity.Attribution;
import lml.snir.gestionpersonne.metier.entity.Badge;
import lml.snir.gestionpersonne.metier.entity.Personne;
import lml.snir.gestionpersonne.physique.data.AttributionDataService;
import lml.snir.gestionpersonne.physique.data.PhysiqueDataFactory;

/**
 *
 * @author fanou
 */
public class AttributionServiceImpl implements AttributionService {
    private final AttributionDataService attributionDataSrv;
    
    public AttributionServiceImpl() throws Exception {
        this.attributionDataSrv = PhysiqueDataFactory.getAttributionDataService();
    }

    @Override
    public Attribution add(Attribution t) throws Exception {
        // TO DO ADD business rules here
        return this.attributionDataSrv.add(t);
    }

    @Override
    public void remove(Attribution t) throws Exception {
        // TO DO ADD business rules here
        this.attributionDataSrv.remove(t);
    }

    @Override
    public void update(Attribution t) throws Exception {
        // TO DO ADD business rules here
        this.attributionDataSrv.update(t);
    }

    @Override
    public Attribution getById(Long id) throws Exception {
        return this.attributionDataSrv.getById(id);
    }

    @Override
    public long getCount() throws Exception {
        return this.attributionDataSrv.getCount();
    }

    @Override
    public List<Attribution> getAll() throws Exception {
        return this.attributionDataSrv.getAll();
    }

    @Override
    public List<Attribution> getAll(int begin, int count) throws Exception {
        return this.attributionDataSrv.getAll(begin, count);
    }

    @Override
    public Attribution getByBadge(Badge badge) throws Exception {
        return this.attributionDataSrv.getByBadge(badge);
    }

    @Override
    public Attribution getByPersonne(Personne user) throws Exception {
        return this.attributionDataSrv.getByPersonne(user);
    }

    @Override
    public Boolean isBadgeAttribue(Personne personne) throws Exception {
        return this.attributionDataSrv.isBadgeAttribue(personne);
    }

    @Override
    public Boolean isBadgeAttribue(Badge badge) throws Exception {
        return this.attributionDataSrv.isBadgeAttribue(badge);
    }

}
