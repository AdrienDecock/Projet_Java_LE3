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
    @ManyToOne
    @JoinColumn(name="IDOPTIBOX")
    private OptiBox optiBoxPile;
    
    //ensemble de produit
    @OneToMany(mappedBy="pileProd")
    private List<Produit> listeProduitPile;
    
        //constructeur
    //constructeur par defaut
    public Pile() {
        this.hPile = 0;
        this.lPile = 0;
        this.listeProduitPile = new LinkedList<>();
    }
    //constructeur par données
    public Pile( int hPile, int lPile, OptiBox optiboxPile, Set<Produit> setProduitPile) {
        this();
        this.hPile = hPile;
        this.lPile = lPile;
    }
    
    
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
    
    //methodes
    /**
     * Methode pour empiler des produits
     */
    
    public boolean empiler(Produit p){
        if (optiBoxPile == null) return false; //on verifie l'existance d'une optiBox
        if (this.hPile==0 ) {
            
        }
    }
   
}
