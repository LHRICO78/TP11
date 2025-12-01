package lml.snir.gestionpersonne.metier;

import lml.snir.gestionpersonne.metier.transactionel.AttributionService;
import lml.snir.gestionpersonne.metier.transactionel.AttributionServiceImpl;
import lml.snir.gestionpersonne.metier.transactionel.BadgeService;
import lml.snir.gestionpersonne.metier.transactionel.BadgeServiceImpl;
import lml.snir.gestionpersonne.metier.transactionel.PersonneService;
import lml.snir.gestionpersonne.metier.transactionel.PersonneServiceImpl;

/**
 *
 * @author fanou
 */
public class MetierFactory {
    private MetierFactory() {}
    
    private static PersonneService personneSrv = null;
    public static synchronized PersonneService getPersonneService() throws Exception {
        if (personneSrv == null) {
            personneSrv = new PersonneServiceImpl();
        }
        
        return personneSrv;
    }
    
    private static BadgeService badgeSrv = null;
    public static BadgeService getBadgeService() throws Exception {
        if (badgeSrv == null) {
            badgeSrv = new BadgeServiceImpl();
        }

        return badgeSrv;
    }

    private static AttributionService attributionSrv = null;
    public static AttributionService getAttributionService() throws Exception {
        if (attributionSrv == null) {
            attributionSrv = new AttributionServiceImpl();
        }

        return attributionSrv;
    }
}
