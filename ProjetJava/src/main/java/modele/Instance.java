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
public class Instance {
        //2 attributs
    //l'identifiant de l'instance
    private String idInstance;
    //le nom de l'instance
    private String nom;
    
        //constructeur par données
    public Instance(String idInstance, String nom) {
        this.idInstance = idInstance;
        this.nom = nom;
    }
    
        //getters
    //permet de recuperer l'identifiant de l'instance
    public String getIdInstance() {
        return idInstance;
    }
    //permet de recuperer le nom de l'instance
    public String getNom() {
        return nom;
    }
    
    
}
