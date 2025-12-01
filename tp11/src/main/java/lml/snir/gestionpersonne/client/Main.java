package lml.snir.gestionpersonne.client;

import java.util.List;
import lml.snir.gestionpersonne.metier.entity.Administrateur;
import lml.snir.gestionpersonne.metier.entity.Agent;
import lml.snir.gestionpersonne.metier.MetierFactory;
import lml.snir.gestionpersonne.metier.entity.Attribution;
import lml.snir.gestionpersonne.metier.entity.Badge;
import lml.snir.gestionpersonne.metier.entity.Personne;
import lml.snir.gestionpersonne.metier.transactionel.AttributionService;
import lml.snir.gestionpersonne.metier.transactionel.BadgeService;
import lml.snir.gestionpersonne.metier.transactionel.PersonneService;

/**
 *
 * @author fanou
 */
public class Main {
    private final PersonneService pSrv;
    private final BadgeService bSrv;
    private final AttributionService attrSrv;

    public static void main(String[] args) throws Exception {        
        Main m = new Main();
//        m.populate();
//        m.test();
        
        JavaFXGUI gui = new JavaFXGUI();
        gui.launch();
    }
    
    private Main() throws Exception {
        this.pSrv = MetierFactory.getPersonneService();
        this.bSrv = MetierFactory.getBadgeService();
        this.attrSrv = MetierFactory.getAttributionService();
    }

    private void populate() throws Exception {        
        Personne p;
        Badge b;
        Attribution attr;
        
        
        p = new Administrateur();
        p.setAge(45);
        p.setMasculin(true);
        p.setNom("Simpson");
        p.setPrenom("Homer");
        p = this.pSrv.add(p);
        
        b = new Badge();
        b.setContenu("0009966230");
        b = this.bSrv.add(b);
        
        attr = new Attribution();
        attr.setBadge(b);
        attr.setPersonne(p);
        this.attrSrv.add(attr);
        
        p = new Agent();
        p.setAge(42);
        p.setMasculin(false);
        p.setNom("Simpson");
        p.setPrenom("Marge");
        p = this.pSrv.add(p);
        
        p = new Administrateur();
        p.setAge(53);
        p.setMasculin(true);
        p.setNom("ALONSO");
        p.setPrenom("Stéphane");
        this.pSrv.add(p);
        
        b = new Badge();
        b.setContenu("0014511054");
        b = this.bSrv.add(b);
        
        attr = new Attribution();
        attr.setBadge(b);
        attr.setPersonne(p);
        this.attrSrv.add(attr);
        
        p = new Agent();
        p.setAge(15);
        p.setMasculin(true);
        p.setNom("Simpson");
        p.setPrenom("Bart");
        p = this.pSrv.add(p);
        
        b = new Badge();
        b.setContenu("0006250103");
        b = this.bSrv.add(b);
        
        attr = new Attribution();
        attr.setBadge(b);
        attr.setPersonne(p);
        this.attrSrv.add(attr);
        
        b = new Badge();
        b.setContenu("9914511054");
        b = this.bSrv.add(b);
    }

    private void testPersonneService() throws Exception {
        System.out.println("PersonneService");
        System.out.println("getAll");
        this.display(this.pSrv.getAll());
        
        System.out.println("getById(1)");
        System.out.println(this.pSrv.getById(1L));
        
        System.out.println("getAllByType(Administrateur.class)");
        this.display(this.pSrv.getAllByType(Administrateur.class));
        
        System.out.println("getAllByType(Agent.class)");
        this.display(this.pSrv.getAllByType(Agent.class));
        
        System.out.println("getByGenre(true)");
        this.display(this.pSrv.getByGenre(true));
        
        System.out.println("getByNom(\"ALONSO\")");
        this.display(this.pSrv.getByNom("ALONSO"));        
    }
    
    private void testBadgeService() throws Exception {
        System.out.println("BadgeService");
        System.out.println("getAll");
        this.display(this.bSrv.getAll());
        
        System.out.println("getByAttribution(true)");
        this.display(this.bSrv.getByAttribution(true));
        
        System.out.println("getByAttribution(false)");
        this.display(this.bSrv.getByAttribution(false));
        
        System.out.println("getByContenu(\"0006250103\")");
        System.out.println(this.bSrv.getByContenu("0006250103"));
        
        System.out.println("getByContenu(\"0776250103\")");
        System.out.println(this.bSrv.getByContenu("0776250103"));
    }
    
    private void testAttributionService() throws Exception {
        System.out.println("AttributionService");
        
        System.out.println("this.attrSrv.getByPersonne(this.pSrv.getById(1L))");
        System.out.println(this.attrSrv.getByPersonne(this.pSrv.getById(1L)));
        
        System.out.println("this.attrSrv.getByPersonne(this.pSrv.getById(2L))");
        System.out.println(this.attrSrv.getByPersonne(this.pSrv.getById(2L)));
        
        System.out.println("this.attrSrv.getByBadge(this.bSrv.getById(2L))");
        System.out.println(this.attrSrv.getByBadge(this.bSrv.getById(2L)));
    }
    
    private void test() throws Exception {
        this.testPersonneService();
        this.testBadgeService();
        this.testAttributionService();
    }
    
    private void display(List datas) {
        for (Object o :  datas) {
            System.out.println(o);
        }
    }
}