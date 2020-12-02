/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
/**
 *
 * @author loic
 */
public class Instance {
        //2 attributs
    //le nom de l'instance
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdInstance")
    private int idInstance;
    
    @Column(name = "Nom",
            length = 45,
            nullable = false
    )
    private String nom;
    
    //la liste des box
    private List<Box> listeBox;
    
    //la liste des produits
    private List<Produit> listeProduit;
        
        //constructeur par données
    public Instance(String nom) {
        this.nom = nom;
        this.listeBox = new ArrayList<>();
        this.listeProduit = new ArrayList<>();
    }
    
        //getters
    //permet de recuperer l'id de l'instance
    public int getIdInstance() {    
        return idInstance;
    }

    //permet de recuperer le nom de l'instance
    public String getNom() {
        return nom;
    }
    
    
}
