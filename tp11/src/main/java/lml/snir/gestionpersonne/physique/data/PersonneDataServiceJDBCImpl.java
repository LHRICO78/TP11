package lml.snir.gestionpersonne.physique.data;

import java.util.List;
import java.util.Map;
import lml.snir.persistence.jdbc.AbstractCrudServiceJDBC;
import lml.snir.gestionpersonne.metier.entity.Administrateur;
import lml.snir.gestionpersonne.metier.entity.Agent;
import lml.snir.gestionpersonne.metier.entity.Personne;

/**
 *
 * @author fanou
 */
public final class PersonneDataServiceJDBCImpl extends AbstractCrudServiceJDBC<Personne> implements PersonneDataService {

    PersonneDataServiceJDBCImpl() throws Exception {
        super();
        String query = null;
        try {
            switch (super.getDBType()) {
                case MYSQL:
                    query = "CREATE TABLE IF NOT EXISTS `" + super.getEntityName() + "` (\n"
                            + "`ID` int(11) NOT NULL AUTO_INCREMENT,\n"
                            + "`TYPE` varchar(31) DEFAULT NULL,\n"
                            + "`AGE` int DEFAULT NULL,\n"
                            + "`FIRSTNAME` varchar(255) DEFAULT NULL,\n"
                            + "`ISMASCULIN` tinyint(1) DEFAULT '0',\n"
                            + "`NAME` varchar(255) DEFAULT NULL,\n"
                            + " PRIMARY KEY (`id`)\n"
                            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci AUTO_INCREMENT=1;\n";
                    break;
                case SQLITE:
                    query = "CREATE TABLE IF NOT EXISTS `" + super.getEntityName() + "` (\n"
                            + "`ID` INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                            + "`TYPE` varchar(31) DEFAULT NULL,\n"
                            + "`AGE` int DEFAULT NULL,\n"
                            + "`FIRSTNAME` varchar(255) DEFAULT NULL,\n"
                            + "`ISMASCULIN` tinyint(1) DEFAULT '0',\n"
                            + "`NAME` varchar(255) DEFAULT NULL\n"
                            + ");";
                    break;
            }
            super.executeQuery(query);

        } catch (Exception ex) {
            System.out.println(this.getClass().getSimpleName() + "\n" + super.getDBType() + "\n" + ex);
        }
    }
    
    @Override
    protected Personne createEntity(Map map) throws Exception {
        Personne personne;

        long id = super.getFieldAsLong("ID", map);
        String type = (String) map.get("TYPE");
        int age = (int) map.get("AGE");
        String firstname = (String) map.get("FIRSTNAME");
        String name = (String) map.get("NAME");
        boolean isMsc = super.getFieldAsBoolean("ISMASCULIN", map);

        switch (type) {
            case "Administrateur":
                personne = new Administrateur();
                break;

            case "Agent":
                personne = new Agent();
                break;

            default:
                throw new Exception("Type : " + type + " impossible to instanciate");
        }

        personne.setAge(age);
        personne.setPrenom(firstname);
        personne.setId(id);
        personne.setMasculin(isMsc);
        personne.setNom(name);

        return personne;
    }

    @Override
    public Personne add(Personne personne) throws Exception {
        String type = personne.getClass().getSimpleName();
        int age = personne.getAge();
        String firstname = personne.getPrenom();
        String name = personne.getNom();
        int isMsc = 0;
        if (personne.isMasculin()) {
            isMsc = 1;
        }

        String query = "INSERT INTO " + super.getEntityName() + " (TYPE, AGE, FIRSTNAME, NAME, ISMASCULIN) VALUES ('"
                + type + "','"
                + age + "','"
                + firstname + "','"
                + name + "','"
                + isMsc + "')";

        personne.setId(super.executeAdd(query));

        return personne;
    }

    @Override
    public void update(Personne personne) throws Exception {
        String type = personne.getClass().getSimpleName();
        int age = personne.getAge();
        String firstname = personne.getPrenom();
        String name = personne.getNom();
        int isMsc = 0;
        if (personne.isMasculin()) {
            isMsc = 1;
        }

        long id = personne.getId();

        String query = "UPDATE " + super.getEntityName() + " SET TYPE = '"
                + type + "', AGE = '"
                + age + "',  FIRSTNAME = '"
                + firstname + "',  NAME = '"
                + name + "',  ISMASCULIN = '"
                + isMsc + "' WHERE id = '" + id + "'";

        personne.setId(super.executeAdd(query));
    }

    @Override
    public List<Personne> getAllByType(Class type) throws Exception {
        String typeStr =type.getSimpleName();
        String query = "SELECT * FROM `" + this.getEntityName() + "` WHERE TYPE = '" + typeStr + "'";
        return this.getResults(query);
    }

    @Override
    public List<Personne> getByNom(String nom) throws Exception {
        String query = "SELECT * FROM `" + this.getEntityName() + "` WHERE NAME = '" + nom + "'";
        return this.getResults(query);
    }

    @Override
    public List<Personne> getByGenre(boolean masculin) throws Exception {
        String genre = "0";
        if (masculin) {
            genre = "1";
        }
        String query = "SELECT * FROM `" + this.getEntityName() + "` WHERE ISMASCULIN = '" + genre + "'";
        return this.getResults(query);
    }
    
    @Override
    public List<Personne> getAllSorted() throws Exception {
        String query = "SELECT * FROM `" + this.getEntityName() + "` ORDER BY NAME, FIRSTNAME ASC";
        System.out.println(query);
        return this.getResults(query);
    }
    
    @Override
    public List<Personne> getAllSorted(int begin, int count) throws Exception {
        String query = "SELECT * FROM `" + this.getEntityName() + "` ORDER BY NAME, FIRSTNAME ASC LIMIT " + begin + ", " + count;
        System.out.println(query);
        return this.getResults(query);
    }
    
}
