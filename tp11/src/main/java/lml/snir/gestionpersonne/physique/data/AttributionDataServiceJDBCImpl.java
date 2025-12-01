package lml.snir.gestionpersonne.physique.data;

import java.util.Map;
import lml.snir.persistence.jdbc.AbstractCrudServiceJDBC;
import lml.snir.gestionpersonne.metier.entity.Attribution;
import lml.snir.gestionpersonne.metier.entity.Badge;
import lml.snir.gestionpersonne.metier.entity.Personne;

final class AttributionDataServiceJDBCImpl<T> extends AbstractCrudServiceJDBC<Attribution> implements AttributionDataService {

    AttributionDataServiceJDBCImpl() throws Exception {
        String query = null;
        try {
            switch (super.getDBType()) {
                case MYSQL:
                    query = "CREATE TABLE IF NOT EXISTS `" + super.getEntityName() + "` (\n"
                            + "  `id` int(11) NOT NULL AUTO_INCREMENT,\n"
                            + "  `idBadge` int(11) NOT NULL,\n"
                            + "  `idPersonne` int(11) NOT NULL,\n"
                            + "  PRIMARY KEY (`id`),\n"
                            + "  UNIQUE KEY `idBadge` (`idBadge`),\n"
                            + "  UNIQUE KEY `idPersonne` (`idPersonne`)\n"
                            + ") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;";
                    break;
                case SQLITE:
                    query = "CREATE TABLE IF NOT EXISTS `" + super.getEntityName() + "` (\n"
                            + "  `id` INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                            + "  `idBadge` INTEGER NOT NULL UNIQUE,\n"
                            + "  `idPersonne` INTEGER NOT NULL UNIQUE\n"
                            + ");";
                    break;
            }
            super.executeQuery(query);

        } catch (Exception ex) {
            System.out.println(this.getClass().getSimpleName() + "\n"  + super.getDBType() + "\n" + ex);
        }
    }

    @Override
    protected Attribution createEntity(Map map) throws Exception {
        Attribution attribution;

        long id = (int) map.get("id");
        long idBadge = (int) map.get("idBadge");
        long idPersonne = (int) map.get("idPersonne");

        attribution = new Attribution();
        attribution.setId(id);

        Badge badge = PhysiqueDataFactory.getBadgeDataService().getById(idBadge);
        Personne personne = PhysiqueDataFactory.getPersonneDataService().getById(idPersonne);
        attribution.setBadge(badge);
        attribution.setPersonne(personne);

        return attribution;
    }

    @Override
    public Attribution add(Attribution attribution) throws Exception {
        String query = "INSERT INTO " + super.getEntityName() + " (idBadge, idPersonne) VALUES ('"
                + attribution.getBadge().getId() + "','"
                + attribution.getPersonne().getId() + "')";

        attribution.setId(super.executeAdd(query));

        return attribution;
    }

    @Override
    public void remove(Attribution attribution) throws Exception {
        String query = "DELETE FROM " + super.getEntityName() + " WHERE id = '" + attribution.getId() + "'";
        super.executeQuery(query);
    }

    @Override
    public void update(Attribution attribution) throws Exception {
        String query = "UPDATE " + super.getEntityName() + " SET id = '"
                + attribution.getId() + "', idBadge = '"
                + attribution.getBadge().getId() + "',  idPersonne = '"
                + attribution.getPersonne().getId() + "' WHERE id = '" + attribution.getId() + "'";

        super.executeQuery(query);
    }

    @Override
    public Attribution getByBadge(Badge badge) throws Exception {
        String query = "SELECT * FROM " + super.getEntityName() + " WHERE idBadge = '" + badge.getId() + "'";
        return super.getSingleResult(query);
    }

    @Override
    public Attribution getByPersonne(Personne personne) throws Exception {
        String query = "SELECT * FROM " + super.getEntityName() + " WHERE idPersonne = '" + personne.getId() + "'";
        return super.getSingleResult(query);
    }

    @Override
    public Boolean isBadgeAttribue(Personne personne) throws Exception {        
        return (this.getByPersonne(personne) != null);
    }

    @Override
    public Boolean isBadgeAttribue(Badge badge) throws Exception {
        return (this.getByBadge(badge) != null);
    }
}
