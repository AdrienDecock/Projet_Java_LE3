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
public class Box {
        //4 attributs
    // l'identifiant du Box
    private String idBox;
    //la hauteur de la Box
    private int hBox;
    //la longueur de la Box
    private int lBox;
    //le prix de la Box
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
}
