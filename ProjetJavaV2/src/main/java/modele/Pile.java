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
    private Box optiBoxPile;
    
    //ensemble de produit
    @OneToMany(mappedBy="pileProd")
    private Set<Produit> setProduitPile;
    
        //constructeur
    //constructeur par defaut
    public Pile() {
        this.hPile = 0;
        this.lPile = 0;
        this.setProduitPile = new HashSet<>();
    }
    //constructeur par données
    public Pile(int idPile, int hPile, int lPile, Box boxPile, Set<Produit> setProduitPile) {
        this();
        this.idPile = idPile;
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
    
    
    

   
}
