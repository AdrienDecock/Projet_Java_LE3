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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDSOLUTION")
    private int idSolution;
    
    //le nom de la solution
    @Column(name = "NOM", length = 45, nullable = false)
    private String nomSolution;

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
        
        this.nomSolution = "DEFAULT_SOLUTION";
        this.prixTotal = 0.0;
        this.setOptiBoxResultat = new HashSet<>();
    }

    //constructeur par données
    public Solution(String nomSol, Instance instance) {
        this();
        if (nomSol != null)
            this.nomSolution = nomSol;
        
        instance.addSolution(this);
    }
    
    
    public boolean setInstanceSolution(Instance instance) {
        
        if (instance == null) return false;
        this.instanceSolution = instance;
        return true;
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