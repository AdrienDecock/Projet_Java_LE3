/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author loic
 */
public class Instance {
        //1 attribut
    //le nom de l'instance
    private String nom;
    //la liste des box
    private List<Box> listeBox;
    //la liste des produits
    private List<Produit> listeProduit;
        
        //constructeur par données
    public Instance(String nom) {
        this.nom = nom;
        listeBox = new ArrayList<>();
        listeProduit = new ArrayList<>();
    }
    
        //getters
    //permet de recuperer le nom de l'instance
    public String getNom() {
        return nom;
    }
    
    
}
