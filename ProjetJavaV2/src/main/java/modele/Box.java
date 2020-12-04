/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author nadae
 */
@Entity
public class Box implements Serializable {
        //4 attributs
    
    private static final long serialVersionUID = 1L;
    // l'identifiant du Box
    @Id
    @Column(name = "IDBOX")
    private String idBox;
    
    //la hauteur de la Box
    @Column(name = "HAUTEURBOX", nullable = false  )
    private int hBox;
    
    //la longueur de la Box
    @Column(name = "LONGUEURBOX", nullable = false  )
    private int lBox;
    
    //le prix de la Box
    @Column(name = "PRIXBOX", nullable = false  )
    private double prixBox;
    

    @ManyToOne
    @JoinColumn(name="IDINSTANCE")
    private Instance instanceBox;
    
    //constructeur défaut
    public Box() {
        this.idBox = "DEFAULT_IDBOX";
        this.hBox = 0;
        this.lBox = 0;
        this.prixBox = 0.0;
    }
    
        
    //constructeur par copie
    public Box(Box b) {
        this();
        this.idBox = b.idBox;
        this.hBox = b.hBox;
        this.lBox = b.lBox;
        this.prixBox = b.prixBox;
    }
    
        //constructeur données
    public Box(String idBox, int hBox, int lBox, double prixBox) {
        this.idBox = idBox;
        this.hBox = hBox;
        this.lBox = lBox;
        this.prixBox = prixBox;
    }
    
        //getters
    //Recupere l'identifiant de la Box
    public String getIdentifiant() {
        return idBox;
    }
    //Recupere la longueur de la Box
    public int getLongueur() {
        return lBox;
    }
    //Recupere la hauteur de la Box
    public int getHauteur() {
        return hBox;
    }
    //Recupere le prix de la Box
    public double getPrix() {
        return prixBox;
    }
    
        //setter
    //setter de instanceBox
    
    public void setInstanceBox(Instance instanceBox) {
        
        if (instanceBox == null) return;
        
        this.instanceBox = instanceBox;
    }
    
    
}
