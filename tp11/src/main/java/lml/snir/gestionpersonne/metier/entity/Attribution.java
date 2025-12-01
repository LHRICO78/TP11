package lml.snir.gestionpersonne.metier.entity;


import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author fanou
 */

public class Attribution implements Serializable {
    private Long id;
   
    private Badge badge;
    private Personne personne;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attribution)) {
            return false;
        }
        Attribution other = (Attribution) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.badge);
        hash = 79 * hash + Objects.hashCode(this.getPersonne());
        return hash;
    }

    @Override
    public String toString() {
        return "lml.snir.gestiontemperature.metier.entity.Attribution[ id=" + getId() + " ]";
    }

    /**
     * @return the badge
     */
    public Badge getBadge() {
        return badge;
    }

    /**
     * @param badge the badge to set
     */
    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    /**
     * @return the personne
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * @param personne the personne to set
     */
    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

   
    
}
