package lml.snir.gestionpersonne.metier.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author fanou
 */
public abstract class Personne implements Serializable {

    private long id;
    private String nom;
    private String prenom;
    private boolean masculin;
    private int age;
    
  
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (this.isMasculin()) {
            builder.append("M. ");
        } else {
            builder.append("Mme. ");
        }

        builder.append(this.nom).append(" ").append(this.prenom).append(" (").append(this.age).append(" ans)");
        builder.append(" [").append(this.getClass().getSimpleName()).append("]");

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        boolean equals = false;
        if (o != null) {
            if (o instanceof Personne) {
               equals = (this.hashCode()== o.hashCode());
            }
        }

        return equals;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nom);
        hash = 29 * hash + Objects.hashCode(this.prenom);
        hash = 29 * hash + (this.masculin ? 1 : 0);
        hash = 29 * hash + this.age;
        return hash;
    }

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
