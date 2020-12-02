/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import javax.persistence.*;

/**
 *
 * @author loic
 */
@Entity
public class Box {
        //4 attributs
    
    @ManyToOne
    @JoinColumn(name="IdInstanceBox")
    private Instance instanceBox;
    
    // l'identifiant du Box
    @Id
    @Column(name = "IdBox")
    private String idBox;
    
    //la hauteur de la Box
    @Column(name = "HauteurBox", nullable = false  )
    private int hBox;
    
    //la longueur de la Box
    @Column(name = "LongueurBox", nullable = false  )
    private int lBox;
    
    //le prix de la Box
    @Column(name = "PrixBox", nullable = false  )
    private double prixBox;
    
        //constructeur
    public Box(String idBox, int hBox, int lBox, double prixBox) {
        this.idBox = idBox;
        this.hBox = hBox;
        this.lBox = lBox;
        this.prixBox = prixBox;
    }
    
        //getters
    //Recupere l'identifiant de la Box
    public String getIdentifiant() {
        return idBox;
    }
    //Recupere la longueur de la Box
    public int getLongueur() {
        return lBox;
    }
    //Recupere la hauteur de la Box
    public int getHauteur() {
        return hBox;
    }
    //Recupere le prix de la Box
    public double getPrix() {
        return prixBox;
    }
    
        //setter
    //setter de instanceBox
    public void setInstanceBox(Instance instanceBox) {
        this.instanceBox = instanceBox;
    }
    
}
