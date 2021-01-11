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
@Access(AccessType.FIELD)
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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="IDINSTANCE")
    private Instance instanceSolution;
    //optiBox
    @OneToMany(mappedBy="solutionOptiBox",cascade = CascadeType.PERSIST)
    private Set<OptiBox> setOptiBoxResultat;

        //constructeur
    //constructeur par defaut
    public Solution() {
        
        this.prixTotal = 0.0;
        this.setOptiBoxResultat = new HashSet<>();
    }

    //constructeur par données
    public Solution(String idSolution, Instance instance) {
        this();
        this.idSolution = idSolution;
        instance.addSolution(this);
    }
    
    
    public boolean setInstanceSolution(Instance instance) {
        
        if (instance == null) return false;
        this.instanceSolution = instance;
        return true;
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

    
    public boolean addOptiboxSolution(OptiBox opti){
        
        if (opti == null) return false;
        if (!opti.estLibreSolution()) return false;
        
        if (!opti.setSolutionOptiBox(this)) return false;
        this.setOptiBoxResultat.add(opti);
        return true;
        
    }
}