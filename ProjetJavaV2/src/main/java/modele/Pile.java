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
    
    public Pile( OptiBox optiboxPile) {
        this();
        if (optiboxPile != null)
            optiboxPile.addPileOptiBox(this);
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
    méthode empiler :
    
    si le prodiut passé en paramètre n'existe pas, ou si il est deja dans une autre pile, on quitte la fonction
    
    Si il n'y a pas de place en terme de hauteur pour placer le produit, on quitte la fonction
    
    On test ensuite si le produit peut être placé en terme de longueur :
        on parcours toute la pile du sommet vers le bas et pour chaque produit :
            si le produit peut être placé au dessus
                ON INSERT LE PRODUIT ---> mise a jour des attributs
            si le produit n'as pas pu etre inséré, on test si on a la place dans la boite pour le mettre tout en bas
                -si oui ON INSERT LE PRODUIT ---> mise a jour des attributs
                -sinon, on quitte la fonction
    */  
    public boolean empiler(Produit p){
        
        if (p == null) return false; //verif si le produit existe
        if (!p.estLibre()) return false; //on verifie si le produit est deja empile
        if (optiBoxPile == null) return false; //on verifie l'existance d'une optiBox
        //on vérifie si le produit peut etre placer en terme de hauteur
        if (p.getHauteur() + this.hPile > this.optiBoxPile.getOptiHauteur()) return false; 
        
        Produit prod;
        int i = listeProduitPile.size()-1;
        //parcours du sommet de la pile vers le bas pour savoir si on peut inserer le produit
        while (i>=0) {
            prod = listeProduitPile.get(i);
            // si le produit peut etre intégré, on le fait
            if ( p.getLongueur() <= prod.getLongueur()){
                listeProduitPile.add(i+1, p);
                this.hPile = this.hPile + p.getHauteur();
                p.setPileProd(this);
                return true;
            }
            i--;
        }
        
        //ici, on se trouve en bas de la pile, il faut tester si on la la place pour placer le produit dans la box
        if (p.getLongueur() > this.lPile + this.optiBoxPile.getLongueurDispo()) return false;
        
            this.lPile = p.getLongueur();
            this.listeProduitPile.add(0,p);
            this.hPile = this.hPile + p.getHauteur();
            p.setPileProd(this);
            return true;  
    }
    /*
    public void afficherPile(){
        
        for (Produit p : listeProduitPile){
            
            System.out.println( "PRODUIT : " + p.getNomProd() + 
                                " | HAUTEUR : " + p.getHauteur() +
                                " | LONGUEUR : " + p.getHauteur());
            
        }
    }
    */
}
