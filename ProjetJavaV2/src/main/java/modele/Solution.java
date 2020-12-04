package modele;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author nadae
 */
@Entity
public class Solution implements Serializable {

    private static final long serialVersionUID = 1L;
        //attributs
    //identifiant de la solution
    @Id
    @Column(name = "IDSOLUTION")
    private String idSolution;

    //prix de la solution
    @Column(name = "PRIXTOTAL", nullable = false  )
    private double prixTotal;

    //instance
    @ManyToOne
    @JoinColumn(name="IDINSTANCE")
    private Instance instanceSolution;
    //optiBox
    @OneToMany(mappedBy="solutionOptiBox")
    private Set<OptiBox> setOptiBoxResultat;

        //constructeur
    //constructeur par defaut
    public Solution() {
        
        this.prixTotal = 0.0;
        this.setOptiBoxResultat = new HashSet<>();
    }

    //constructeur par données
    public Solution(String idSolution, double prixTotal) {
        this();
        this.idSolution = idSolution;
        this.prixTotal = prixTotal;
    }

        //getter
    //recuper l'id
    public String getIdSolution() {
        return idSolution;
    }

    //recupere le prix total
    public double getPrixTotal() {
        return prixTotal;
    }

}