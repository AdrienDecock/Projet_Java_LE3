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
@Access(AccessType.FIELD)
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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="IDINSTANCE")
    private Instance instanceProd;
    
    //pile
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="IDPILE")
    private Pile pileProd;
    
    
    //constructeur par defaut
    public Produit() {
        this.nomProd = "DEFAULT_NOM";
        this.hProd = 0;
        this.lProd = 0;
        this.instanceProd = new Instance();
    }
    
        //constructeur par données
    public Produit(String nomProd, int hProd, int lProd, Instance inst) {
        if (nomProd != null)
            this.nomProd = nomProd;
        if (hProd > 0)
            this.hProd = hProd;
        if (lProd > 0)
            this.lProd = lProd;
        
        if (inst != null)
            inst.addProduit(this);
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
    public boolean setInstanceProd(Instance instanceProd) {
        
        if (instanceProd == null) return false;
        this.instanceProd = instanceProd;
        return true;
    }
    
        public boolean setPileProd(Pile pile) {    
        
        if (pile == null) return false;
        this.pileProd = pile;
        return true;
    }
    

    
}
