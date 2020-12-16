/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author loic
 */
@Entity
public class OptiBox implements Serializable {
    
        private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDOPTIBOX")
    private int idOptiBox;
    
    
    //association vers box
    @ManyToOne
    @JoinColumn(name="IDBOX")
    private Box boxOptiBox;
    
    //association vers solution
    @ManyToOne
    @JoinColumn(name="IDSOLUTION")
    private Solution solutionOptiBox;
    
    
    //association vers pile
    @OneToMany(mappedBy="optiBoxPile")
    private Set<Pile> setPile;
    
        //constructeurs
    //constructeur par defaut
    OptiBox(){
        this.setPile = new HashSet<>();
    }
    //constructeir par données
    public OptiBox(Box boxOptiBox) {
        this();
        this.boxOptiBox = boxOptiBox;
    }
    
    
        //getters
    //Recupere la longueur de la Box
    public int getOptiLongueur() {
        if (boxOptiBox == null) return 0;
        return boxOptiBox.getLongueur();
    }
    //Recupere la hauteur de la Box
    public int getOptiHauteur() {
        if (boxOptiBox == null) return 0;
        return boxOptiBox.getHauteur();
    }
    //Recupere le prix de la Box
    public double getOptiPrix() {
        if (boxOptiBox == null) return 0;
        return boxOptiBox.getPrix();
    }
   
    
    
        //setters
    public void setBoxOptiBox(Box boxOptiBox) {
        if (boxOptiBox == null) return;
        this.boxOptiBox = boxOptiBox;
    }

    public void setSolutionOptiBox(Solution solutionOptiBox) {
        if ( solutionOptiBox == null) return;
        this.solutionOptiBox = solutionOptiBox;
    }
    
    
    
    
}
