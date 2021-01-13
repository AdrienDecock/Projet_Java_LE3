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
@Access(AccessType.FIELD)
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
    @OneToMany(mappedBy="instanceBox",
            cascade = CascadeType.PERSIST)
    private Set<Box> setBox;
    
    //la liste des produits
    @OneToMany(mappedBy="instanceProd",
            cascade = CascadeType.PERSIST)
    private Set<Produit> setProduit;
    
    //association vers solution
    @OneToMany(mappedBy="instanceSolution",
            cascade = CascadeType.PERSIST)
    private Set<Solution> setSolution;
            
        //constructeur par defaut
    public Instance() {
        this.nom = "DEFAULT_NAME";
        this.setBox = new HashSet<>();
        this.setProduit = new HashSet<>();
        this.setSolution = new HashSet<>();
    }
    
    
    
        //constructeur par données
    public Instance(String nom) {
        this();
        this.nom = nom;
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
    
    public Set<Box> getBox(){
        return setBox;
    }
    public Set<Produit> getProduit(){
        return setProduit;
    }
        //methodes
  
    /**
     * Ajouter les box aux instances
     */
    
    public boolean addBox(Box b){
        
        if (b == null) return false;
        b.setInstanceBox(this);
        this.setBox.add( b );
        return true;
    }
    
    
    public boolean addSolution(Solution sol){
        
        if (sol == null) return false;
        sol.setInstanceSolution(this);
        this.setSolution.add( sol );
        return true;
    }
    
    /**
     * Ajouter les produits aux instances
     */
        
    public boolean addProduit(Produit p){
        
        if (p == null) return false;
        p.setInstanceProd(this);
        this.setProduit.add(p);
        return true;
    }
    
    /**
     * Methode getString qui affiche le nom
     */
    @Override
    public String toString() {
        return this.getNom(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
