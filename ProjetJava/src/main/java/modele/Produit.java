/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author loic
 */
@Entity
public class Produit implements Serializable {
        //4 attributs
    

    private static final long serialVersionUID = 1L;
    //l'identifiant du Produit
    @Id
    @Column(name = "IdProduit")
    private String idProd;
    
    //la hauteur du Produit
    @Column(name = "HauteurProduit", nullable = false  )
    private int hProd;
    
    //la longueur du produit
    @Column(name = "LongueurProduit", nullable = false  )
    private int lProd;
    
    //la quantité du produit
    @Column(name = "QuantiteProduit", nullable = false  )
    private int quantite;
    
    /*
    @ManyToOne
    @JoinColumn(name="IdInstance")
    private Instance instanceProd;
*/
    
        //constructeur par données
    public Produit(String idProd, int hProd, int lProd, int quantite) {
        this.idProd = idProd;
        this.hProd = hProd;
        this.lProd = lProd;
        this.quantite = quantite;
    }
    
        //getters
    //Recupere l'identifiant du produit
    public String getIdentifiant() {
        return idProd;
    }
    //Recupere la longueur du produit
    public int getLongueur() {
        return lProd;
    }
    //Recupere la hauteur du produit
    public int getHauteur() {
        return hProd;
    }
    //Recupere la quantité du produit
    public int getQuantite() {
        return quantite;
    }
    
}
