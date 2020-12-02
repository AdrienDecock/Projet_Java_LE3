/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author loic
 */
public class Produit {
        //4 attributs
    //l'identifiant du Produit
    private String idProd;
    //la hauteur du Produit
    private int hProd;
    //la longueur du produit
    private int lProd;
    //la quantité du produit
    private int quantite;
    
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
