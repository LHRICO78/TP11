package lml.snir.gestionpersonne.physique.data;

/**
 *
 * @author fanou
 */
public class PhysiqueDataFactory {
    private PhysiqueDataFactory() {}
    
    private static PersonneDataService personneDataSrv = null;
    public static synchronized PersonneDataService getPersonneDataService() throws Exception {
        if (personneDataSrv == null) {
            personneDataSrv = new PersonneDataServiceJDBCImpl();
        }
        
        return personneDataSrv;
    }
    
    private static BadgeDataService badgeSrv = null;
    public static BadgeDataService getBadgeDataService() throws Exception {
        if (badgeSrv == null) {
            badgeSrv = new BadgeDataServiceJDBCImpl();
        }

        return badgeSrv;
    }
    
    private static AttributionDataService attributionSrv = null;
    public static AttributionDataService getAttributionDataService() throws Exception {
        if (attributionSrv == null) {
            attributionSrv = new AttributionDataServiceJDBCImpl();
        }

        return attributionSrv;
    }
}
