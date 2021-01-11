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
 * @author nadae
 */
@Entity
@Access(AccessType.FIELD)
public class Box implements Serializable {
        //4 attributs
    
    private static final long serialVersionUID = 1L;
    
    @Id   
    // l'identifiant du Box
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBOX")
    private int idBox;
    
    //nom de la box
    @Column(name = "NOMBOX")
    private String nomBox;
    
    
    //la hauteur de la Box
    @Column(name = "HAUTEURBOX", nullable = false  )
    private int hBox;
    
    //la longueur de la Box
    @Column(name = "LONGUEURBOX", nullable = false  )
    private int lBox;
    
    //le prix de la Box
    @Column(name = "PRIXBOX", nullable = false  )
    private double prixBox;
    

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="IDINSTANCE")
    private Instance instanceBox;
    
    
    //association vers OptiBox
    @OneToMany(mappedBy="boxOptiBox",cascade = CascadeType.PERSIST)
    private Set<OptiBox> setOptiBox;
    
    //constructeur dÃ©faut
    public Box() {
        this.nomBox = "DEFAULT_IDBOX";
        this.hBox = 0;
        this.lBox = 0;
        this.prixBox = 0.0;
        this.instanceBox = new Instance();
      //  this.setOptiBox = new HashSet<>();
    }
    
        
    //constructeur par copie
    public Box(Box b) {
        this();
        this.nomBox = b.nomBox;
        this.hBox = b.hBox;
        this.lBox = b.lBox;
        this.prixBox = b.prixBox;
    }
    
        //constructeur donnÃ©es
    public Box(String nomBox, int hBox, int lBox, double prixBox) {
        this.nomBox = nomBox;
        this.hBox = hBox;
        this.lBox = lBox;
        this.prixBox = prixBox;
    }
    
        //getters
    //Recupere l'identifiant de la Box
    public int getIdentifiant() {
        return idBox;
    }

    public String getNomBox() {
        return nomBox;
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
    
    
    
    public boolean addBoxOptibox(OptiBox opti){
        
        if (opti == null) return false;
        if (!opti.estLibreBox()) return false;
        
        if (!opti.setBoxOptiBox(this)) return false;
        this.setOptiBox.add(opti);
        return true;
        
        
    }
    
    
    
}
