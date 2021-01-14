/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author nadae
 */
@Entity
@Access(AccessType.FIELD)
public class Pile implements Serializable {
    private static final long serialVersionUID = 1L;
        //attributs
    //identifiant de la pile
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDPILE")
    private int idPile;
    
    //Hauteur de la pile
    @Column(name = "HAUTEURPILE", nullable = false  )
    private int hPile;
    
    //Longueur de la pile
    @Column(name = "LONGUEURPILE", nullable = false  )
    private int lPile;
    
    //sa Box
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="IDOPTIBOX")
    private OptiBox optiBoxPile;
    
    //ensemble de produit
    @OneToMany(mappedBy="pileProd",cascade = CascadeType.PERSIST)
    private List<Produit> listeProduitPile;
    
        //constructeur
    //constructeur par defaut
    public Pile() {
        this.hPile = 0;
        this.lPile = 0;
        this.listeProduitPile = new LinkedList<>();
    }
    //constructeur par données
    /*
    public Pile( OptiBox optiboxPile) {
        this();
        if (optiboxPile != null)
            optiboxPile.addPileOptiBox(this);
    }
    
    */
        //getters
    //recupere l'id
    public int getIdPile() {
        return idPile;
    }

    public int gethPile() {
        return hPile;
    }

    public int getlPile() {
        return lPile;
    }
    
    
    //renvoie false si la pile appartient a un optiBox
    //renvoie true sinon
    public boolean estLibre() {
        
        if (this.optiBoxPile == null) return true;
        return false;
    }
    
    public boolean setOptiBoxPile(OptiBox b) {
        
        if (b == null) return false;
        this.optiBoxPile = b;
        return true;
    }
    
    //methodes
    /**
     * Methode pour empiler des produits
     */
    public int getLongeurSommet(){
        
        if (this.lPile ==0) return 0;
        return (this.listeProduitPile.get( this.listeProduitPile.size()-1).getLongueur() );
        
    }
    
    /*
    public boolean empiler(Produit p){
        
        if (p == null) return false; //verif si le produit existe
        if (!p.estLibre()) return false; //on verifie si le produit est deja empile
        if (optiBoxPile == null) return false; //on verifie l'existance d'une optiBox
        
        if (this.hPile==0 ) { //premier produit de la pile
            
            if (p.getHauteur() > this.optiBoxPile.getOptiHauteur()) return false;
            if (p.getLongueur() > this.optiBoxPile.getLongueurDispo()) return false;
                        
            this.lPile = p.getLongueur();
            this.listeProduitPile.add(p);
            this.hPile = p.getHauteur();
            p.setPileProd(this);
            return true;
        }
        else {
            
            if (p.getHauteur() + this.hPile > this.optiBoxPile.getOptiHauteur()) return false;
            if (p.getLongueur() > this.getLongeurSommet() ) return false;
            
            this.listeProduitPile.add(p);
            this.hPile = this.hPile + p.getHauteur();
            p.setPileProd(this);
            return true;
        }
    }
   */
   
}
