/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author loic
 */

@Entity
public class Service {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERVNO")
    private int id;

    @Column(name = "NOM",
            length = 45,
            unique = true,
            nullable = false
    )
    private String nom;

    @Column(name = "LOCALISATION",
            length = 45,
            nullable = false
    )
    private String localisation;
    
    //association dirige
    @ManyToOne
    @JoinColumn(name="MEDENO")
    private Medecin dirigeMedecin;
    
    //association regroupe
    @OneToMany(mappedBy="regroupeService")
    private List<Medecin> regroupeMedecin;

    public Service() {
        this.localisation = "LOCALISATION";
        this.nom = "NOM";
    }

    public Service(String nom, String loc) {
        this();
        if (nom != null) {
            this.nom = nom;
        }
        if (loc != null) {
            this.localisation = loc;
        }
    }
    
        public boolean addMedecin(Medecin m){
        
        if (m == null)
            return false;
        
            this.regroupeMedecin.add(m);
            m.getRegroupeService();
            
        
        
        return true;
    }
    
/*
    @Override
    public String toString() {
        return "modele.Service[ id=" + id + " ]";
    }
*/
    public void setLocalisation(String loc) {
        if (loc != null) {
            this.localisation = loc;
        }
    }
    
    
    
}
