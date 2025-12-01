package lml.snir.gestionpersonne.physique.data;

import java.util.List;
import java.util.Map;
import lml.snir.persistence.jdbc.AbstractCrudServiceJDBC;
import lml.snir.gestionpersonne.metier.entity.Badge;

/**
 *
 * @author fanou
 */
final class BadgeDataServiceJDBCImpl<T> extends AbstractCrudServiceJDBC<Badge> implements BadgeDataService {

    BadgeDataServiceJDBCImpl() throws Exception {
        try {
            String query = null;

            switch (super.getDBType()) {
                case MYSQL:
                    query = "CREATE TABLE IF NOT EXISTS `" + super.getEntityName() + "` (\n"
                            + "  `id` int(11) NOT NULL AUTO_INCREMENT,\n"
                            + "  `contenu` varchar(32) NOT NULL,\n"
                            + "  PRIMARY KEY (`id`),\n"
                            + "  UNIQUE KEY `contenu` (`contenu`),\n"
                            + "  UNIQUE KEY `id` (`id`)\n"
                            + ") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;";
                    break;
                case SQLITE:
                    query = "CREATE TABLE IF NOT EXISTS `" + super.getEntityName() + "` (\n"
                            + "  `id` INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                            + "  `contenu` varchar(32) NOT NULL UNIQUE\n"
                            + ");";
                    break;
            }
            super.executeQuery(query);

        } catch (Exception ex) {
            System.out.println(this.getClass().getSimpleName() + "\n" + super.getDBType() + "\n" + ex);
        }
    }

    @Override
    protected Badge createEntity(Map map) throws Exception {
        Badge badge;

        long id = super.getFieldAsLong("id", map);
        String contenu = (String) map.get("contenu");

        badge = new Badge();
        badge.setId(id);
        badge.setContenu(contenu);
        
        return badge;
    }

    @Override
    public Badge add(Badge badge) throws Exception {
        String query = "INSERT INTO " + super.getEntityName() + " (contenu) VALUES ('"
                + badge.getContenu() + "')";
        try {
            badge.setId(super.executeAdd(query));
        } catch (Exception ex) {
            if (ex.toString().contains("Erreur SQL :")) {
                int beginIndex = ex.toString().lastIndexOf(":") + 1;
                String codeStr = ex.toString().substring(beginIndex).trim();
                int code = Integer.parseInt(codeStr);
                if (code == 1062) {
                    throw new Exception("Badge déjà en base");
                }
            }
            System.out.println(ex);
            throw ex;
        }
        return badge;
    }

    @Override
    public void remove(Badge badge) throws Exception {
        String query = "DELETE FROM " + super.getEntityName() + " WHERE id = '" + badge.getId() + "'";
        super.executeQuery(query);
    }

    @Override
    public void update(Badge badge) throws Exception {
        String query = "UPDATE " + super.getEntityName() + " SET id = '"
                + badge.getId() + "', contenu = '"
                + badge.getContenu() + "' WHERE id = '" + badge.getId() + "'";
        super.executeQuery(query);
    }

    @Override
    public Badge getByContenu(String contenu) throws Exception {
        String query = "SELECT * FROM " + super.getEntityName() + " WHERE contenu= '" + contenu + "'";
        return super.getSingleResult(query);
    }
    
    @Override
    public List<Badge> getByAttribution(boolean attribue) throws Exception {
        String query = "SELECT * FROM " + super.getEntityName() + " WHERE id IN (SELECT idBadge FROM ATTRIBUTION)";
        if (!attribue) {
            query = "SELECT * FROM " + super.getEntityName() + " WHERE id NOT IN (SELECT idBadge FROM ATTRIBUTION)";
        }
        return super.getResults(query);
    }
}
