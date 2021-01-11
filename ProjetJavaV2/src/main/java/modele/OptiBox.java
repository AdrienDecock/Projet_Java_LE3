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
@Access(AccessType.FIELD)
public class OptiBox implements Serializable {
    
        private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDOPTIBOX")
    private int idOptiBox;
    
    
    //association vers box
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="IDBOX")
    private Box boxOptiBox;
    
    //association vers solution
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="IDSOLUTION")
    private Solution solutionOptiBox;
    
    
    //association vers pile
    @OneToMany(mappedBy="optiBoxPile",cascade = CascadeType.PERSIST)
    private Set<Pile> setPile;
    
        //constructeurs
    //constructeur par defaut
    public OptiBox(){
        this.setPile = new HashSet<>();
    }
    //constructeir par données
    public OptiBox(Box box, Solution sol) {
        this();
        box.addBoxOptibox(this);
        sol.addOptiboxSolution(this);
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
    public boolean setBoxOptiBox(Box boxOptiBox) {
        if (boxOptiBox == null) return false;
        this.boxOptiBox = boxOptiBox;
        return true;
    }

    public boolean setSolutionOptiBox(Solution solutionOptiBox) {
        if ( solutionOptiBox == null) return false;
        this.solutionOptiBox = solutionOptiBox;
        return true;
    }
    
    //return la longueur non utilisée dans la boite
    public int getLongueurDispo(){
        
        int lDispo = this.getOptiLongueur();
        
        for (Pile p : setPile){
            
            lDispo = lDispo - p.getlPile(); 
        }
        return lDispo;
    }
    
    public boolean estLibreBox(){
        
      if (this.boxOptiBox == null) return true;
        return false;
        
    }
    
    public boolean estLibreSolution(){
        
      if (this.boxOptiBox == null) return true;
        return false;
        
    }
    
    
    public boolean addPileOptiBox(Pile p){
        
        if (p == null) return false;
        if (!p.estLibre()) return false;
        
        if ( this.getLongueurDispo() + p.getlPile() > this.getOptiLongueur() ) return false;
        
        if (!p.setOptiBoxPile(this)) return false;
        this.setPile.add(p);
        return true;
    }
    
    
}
