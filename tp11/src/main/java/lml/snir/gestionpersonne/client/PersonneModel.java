package lml.snir.gestionpersonne.client;

import java.io.Serializable;

/**
 *
 * @author fanou
 */
public class PersonneModel implements Serializable {
    private long id;
    private String nom;
    private String prenom;
    private boolean masculin;
    private int age;
    private String type;

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the masculin
     */
    public boolean isMasculin() {
        return masculin;
    }

    /**
     * @param masculin the masculin to set
     */
    public void setMasculin(boolean masculin) {
        this.masculin = masculin;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}
