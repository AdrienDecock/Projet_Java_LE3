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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPRODUIT")
    private int idProd;
    
    //nom du produit
    @Column(name = "NOMPRODUIT")
    private String nomProd;
    
    //la hauteur du Produit
    @Column(name = "HAUTEURPRODUIT", nullable = false  )
    private int hProd;
    
    //la longueur du produit
    @Column(name = "LONGUEURPRODUIT", nullable = false  )
    private int lProd;

    
    //instance
    @ManyToOne
    @JoinColumn(name="IDINSTANCE")
    private Instance instanceProd;
    
    //pile
    @ManyToOne
    @JoinColumn(name="IDPILE")
    private Pile pileProd;
    
    
    //constructeur par defaut
    public Produit() {
        this.nomProd = "DEFAULT_NOM";
        this.hProd = 0;
        this.lProd = 0;
    }
    
        //constructeur par données
    public Produit(String nomProd, int hProd, int lProd) {
        this.nomProd = nomProd;
        this.hProd = hProd;
        this.lProd = lProd;
    }
    
        //getters
    //Recupere l'identifiant du produit
    public int getIdentifiant() {
        return idProd;
    }
    
    public String getNomProd() {
    return nomProd;
    }
    //Recupere la longueur du produit
    public int getLongueur() {
        return lProd;
    }
    //Recupere la hauteur du produit
    public int getHauteur() {
        return hProd;
    }

    //renvoie false si le produit appartient a une pile
    //renvoie true sinon
    public boolean estLibre() {
        
        if (this.pileProd == null) return true;
        return false;
    }
    
    
    //setter
    public void setInstanceProd(Instance instanceProd) {
        
        if (instanceProd == null) return;
        this.instanceProd = instanceProd;
    }
    
        public void setPileProd(Pile pile) {
        
        if (pile == null) return;
        this.pileProd = pile;
    }
    
    
    
}
