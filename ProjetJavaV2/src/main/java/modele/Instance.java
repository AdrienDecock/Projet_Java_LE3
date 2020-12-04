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
 * @author loic
 */
@Entity
public class Instance implements Serializable {
        //2 attributs
    //identifiant de l'instance
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDINSTANCE")
    private int idInstance;
    
    //le nom de l'instance
    @Column(name = "NOM", length = 45, nullable = false)
    private String nom;
    
    
    //la liste des box
    @OneToMany(mappedBy="instanceBox")
    private Set<Box> setBox;
    
    //la liste des produits
    @OneToMany(mappedBy="instanceProd")
    private Set<Produit> setProduit;
    
            
        //constructeur par defaut
    public Instance() {
        this.nom = "DEFAULT_NAME";
        //this.setBox = new HashSet<>();
        //this.setProduit = new HashSet<>();
    }
    
    
    
        //constructeur par données
    public Instance(String nom) {
        this();
        this.nom = nom;
        //this.setBox = new HashSet<>();
        //this.setProduit = new HashSet<>();
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
    
        //methodes
  
    /**
     * Ajouter les box aux instances
     * A REVOIR
     */
    
    public boolean addBox(Box b){
        b.setInstanceBox(this);
        this.setBox.add( b );
        return true;
    }
    
        
    public boolean addProduit(Produit p){
        p.setInstanceProd(this);
        this.setProduit.add(p);
        return true;
    }
 
}
