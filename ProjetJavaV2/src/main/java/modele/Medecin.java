/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

/**
 *
 * @author loic
 */
@Entity
public class Medecin {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEDENO")
    private int id;

    @Column(name = "PRENOM",
            length = 45,
            nullable = false
    )
    private String prenom;

    @Column(name = "NOM",
            length = 45,
            unique = true,
            nullable = false
    )
    private String nom;

    @Column(name = "SALAIRE",
            scale = 2
    )
    private double salaire;

    //association dirige
    @OneToMany(mappedBy="dirigeMedecin")
    private List<Service> dirigeService;
    
    
    //association regroupe
    @ManyToOne
    @JoinColumn(name="SERVNO")
    private Service regroupeService;
    
    
    
    
    
    /*
    //relation reflexive sur medecin
    @ManyToOne
    @JoinColumn(name="MEDENO")
    private Medecin medecinClient;
    
    @OneToMany(mappedBy="medecinClient")
    private List<Medecin> listMedecins;
     */
    public Medecin() {
        this.nom = "NOM";
        this.prenom = "PRENOM";
    }

    public Medecin(String nom, String prenom, double salaire) {
        this();
        if (nom != null) {
            this.nom = nom.toUpperCase();
        }

        if (prenom != null) {
            this.prenom = prenom.toUpperCase();
        }

        if (salaire >= 0) {
            this.salaire = salaire;
        }
    }

    public Service getRegroupeService() {
        return regroupeService;
    }
    
    

}
