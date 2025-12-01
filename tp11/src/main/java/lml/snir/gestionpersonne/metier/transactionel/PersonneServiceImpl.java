package lml.snir.gestionpersonne.metier.transactionel;

import java.util.ArrayList;
import java.util.Arrays;
import lml.snir.gestionpersonne.metier.entity.Personne;
import java.util.List;
import lml.snir.gestionpersonne.metier.sort.ExtractionSort;
import lml.snir.gestionpersonne.metier.sort.Sort;
import lml.snir.gestionpersonne.physique.data.PersonneDataService;
import lml.snir.gestionpersonne.physique.data.PhysiqueDataFactory;

/**
 *
 * @author fanou
 */
public final class PersonneServiceImpl implements PersonneService {
    private final boolean javaSort = true;
    private final PersonneDataService personneDataSrv;

    public PersonneServiceImpl() throws Exception {
        this.personneDataSrv = PhysiqueDataFactory.getPersonneDataService();
    }
    
    @Override
    public List<Personne> getAllByType(Class type) throws Exception {
        return this.personneDataSrv.getAllByType(type);
    }

    @Override
    public List<Personne> getByNom(String nom) throws Exception {
        return this.personneDataSrv.getByNom(nom);
    }

    @Override
    public List<Personne> getByGenre(boolean masculin) throws Exception {
        return this.personneDataSrv.getByGenre(masculin);
    }

    @Override
    public Personne add(Personne t) throws Exception {
        return this.personneDataSrv.add(t);
    }

    @Override
    public void remove(Personne t) throws Exception {
        this.personneDataSrv.remove(t);
    }

    @Override
    public void update(Personne t) throws Exception {
        this.personneDataSrv.update(t);
    }

    @Override
    public Personne getById(Long id) throws Exception {
        return this.personneDataSrv.getById(id);
    }

    @Override
    public long getCount() throws Exception {
        return this.personneDataSrv.getCount();
    }

    @Override
    public List<Personne> getAll() throws Exception {
        return this.personneDataSrv.getAll();
    }

    @Override
    public List<Personne> getAll(int begin, int count) throws Exception {
        return this.personneDataSrv.getAll(begin, count);
    }

    @Override
    public List<Personne> getAllSorted() throws Exception {
        if (this.javaSort) {
            Personne[] personnes = (Personne[]) this.personneDataSrv.getAll().toArray(new Personne[0]);
            
            //PersonneComparator comparator = new PersonneComparator();
            
            // use Java Arrays.sort
            Arrays.sort(personnes);
            //Arrays.sort(personnes, comparator);
            
            // or Local sort
            //Sort sort = new ExtractionSort();
            //sort.sort(personnes);
            //sort.sort(personnes, comparator);
            
            return new ArrayList<>(Arrays.asList(personnes));
        } else {
            return this.personneDataSrv.getAllSorted();
        }
    }

    @Override
    public List<Personne> getAllSorted(int begin, int count) throws Exception {
        if (this.javaSort) {
            Personne[] personnes = (Personne[]) this.personneDataSrv.getAll().toArray(new Personne[0]);
            Arrays.sort(personnes);
            
            return new ArrayList<>(Arrays.asList(personnes)).subList(begin, begin + count);
        } else {
            return this.personneDataSrv.getAllSorted(begin, count);
        }
    }
    
}
