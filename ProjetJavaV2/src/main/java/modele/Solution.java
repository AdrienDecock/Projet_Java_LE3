package modele;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
        this.prixTotal += opti.getOptiPrix();
        return true;
        
    }

    public List<Produit> getListeProduit(){
        
        List<Produit> listProd = new LinkedList();
        Set<Produit> setProd= this.instanceSolution.getProduit();
        
        for ( Produit p : setProd){
            listProd.add(p);
        } 
        return listProd;
}


    
    
    public List<Box> getListeBox(){ //+trie dans l'ordre croissant prix
        
        List<Box> listBox = new LinkedList();
        Set<Box> setBox= this.instanceSolution.getBox();
        
        for ( Box b : setBox){
            listBox.add(b);
        } 
        return listBox;
           
}
    
    public boolean creerSolutionTrivial(){ 
        
        //recuperer la liste des produits
        //recuperer la liste de box
        
        //parcours de tous les produits
        //   flag = 0
        //   referenceBox = 1
        //   //boucle tant que flag =0
        //   //   on creer un optibox avec la réference de la box,
        //   //   on essaye d'empiler le produit dans l'optibox :
        //   //     -si true : flag = 1
        //   //     -si false : suppression optibox, incrémentation box de reference

        // CODE :
        List<Produit> listeProd = this.getListeProduit();        
        List<Box> listeBox = this.getListeBox();
        
        int flag;
        OptiBox optiBox;
        Box boxRef;
        int indiceBox;
        
        for ( Produit p : listeProd){
            
            flag = 0;
            indiceBox = 0;
            boxRef = listeBox.get(indiceBox);
            optiBox = new OptiBox(boxRef,this);
            
            while (flag == 0)
            {     
                if (optiBox.empilerOptiBox(p))
                    flag = 1;
                
                else {
                    indiceBox++;
                    boxRef = listeBox.get(indiceBox);
                    optiBox.setBoxOptiBox(boxRef);
                }    
            }          
        }
        return true;
    }
    
    
    
    public boolean creerSolutionOptimise(){ 
        
        //recuperer la liste des produits
        //recuperer la liste de box
        
        //parcours de tous les produits
        //   flag = 0
        //   referenceBox = 1
        //   //boucle tant que flag =0
        //   //   on creer un optibox avec la réference de la box,
        //   //   on essaye d'empiler le produit dans l'optibox :
        //   //     -si true : flag = 1
        //   //     -si false : suppression optibox, incrémentation box de reference

        // CODE :
        List<Produit> listeProd = this.getListeProduit();        
        List<Box> listeBox = this.getListeBox();
        
        int flagA,flagB;
        OptiBox optiBox=null;
        Box boxRef;
        int indiceBox;
        
        flagA = 1;
        flagB=1;
        indiceBox=0;
        boxRef = listeBox.get(indiceBox);
        while (flagA ==1){ //temoin qui permet de savoir que tous les produits sont triés
            
                
                if (flagB == 0){
                   indiceBox ++; 
                   if (indiceBox >= listeBox.size()) indiceBox = 0;
                   boxRef = listeBox.get(indiceBox);
                   optiBox.setBoxOptiBox(boxRef);
                }
                else 
                {   
                   flagB=0;
                   optiBox = new OptiBox(boxRef,this); 
                }
                
                
                for ( Produit p : listeProd){
                    if (optiBox.empilerOptiBox(p))
                        flagB=1;
                    }
                
            //permet de savoir si tous les produits sont triés ou pas
            flagA = 0;
            for (Produit p : listeProd)
                if (p.estLibre()) flagA = 1;
            
        }

        return true;
    }
    
    
    public class  SortByPrice implements Comparator<Box>
{
        @Override
	public int compare(Box a, Box b){
            
            if (a.getPrix() < b.getPrix()) return 1;
            else return -1; 
        }
}
    
public class  SortByLongeur implements Comparator<Box>
{
        @Override
	public int compare(Box a, Box b){
            return (int) (a.getLongueur() - b.getLongueur()); 
        }
}

    /*
    
    	/// permet trier la longueur des box par ordre croissant
class trierBoxParLongueur implements Comparator<Box>
{
	public int compare(Box a, Box b)
		return a.lBox - b.lBox;
}

	/// permet trier la longueur des box par ordre décroissant
class trierProduitParLongueurDecroissant implements Comparator<Produit>
{
	public int compare(Produit a, Produit b)
		return a.lProd - b.lProd;
}

	/// permet d'obternir la liste de box d'une instance à partir de la BDD
List<Box> getListeBox(Instance instanceSélectionnee){

}


	/// permet d'obternir la liste de box d'une instance à partir de la BDD
List<Produit> getListeBox(Instance instanceSélectionnee){

}

	// permet de choisir une box qui a son aire la plus proche de la somme des aires des produits
Box choisirUneBox( int nombreDeProduit, int longueurProduit, int hauteurProduit, list<Box> listeBox ){

	Box boxRetenue = listeBox.get(0); 
	int aireP = nombreDeProduit * ( longueurProduit * hauteurProduit );
	int aireB = listeBox.get(0).getHauteur() * listeBox.get(0).getLongueur();

	int rapportRetenu = Math.abs(aireB - aireP);

        for (int i=1; i<listeBox.size(); i++){
            
		aireB = listeBox.get(i).getHauteur() * listeBox.get(i).getLongueur();
		if( rapportRetenu - Math.abs(aireB - aireP) >= 0 ){
			rapportRetenu = Math.abs(aireB - aireP);
			boxRetenue = listeBox.get(i)
		}
	}

	return boxRetenue;
}

/////////////////////////////////////// solution Triviale/////////////////////////


// prix de la solution
double prixTT;

// on récupere les box du fichier instance dans la liste à partir de la BDD
list<Box> listeBox;
listeBox.clear();
listeBox=getListeBox(instanceSélectionnee);


// on récupere les produits du fichier instance dans la liste à partir de la BDD
list<Produit> listeProduit;
listeProduit.clear();
liste Produit = getListeProduit(instanceSélectionnee);


// on trie la liste des boxes du plus petit au plus grand par rapport à la longueur
Collection.sort(listeBox, new trierBoxParLongueur());


// on trie la liste des produits du plus grand au plus petit par rapport à la longueur
Collection.sort(listeProduit, new trierProduitsParLongueurDecroissant());


// on crée aussi une liste OptiBox
list<OptiBox> listeOptiBox;

// enfin on crée le debut de la liste ListeProduit qui permettra de séparer les produits rangés des produits non rangés 
int debutListeProduit = 0;

// chaque unite de produit est caratérisée par la longueur et la hauteur de son produit
int longueurProduit = 0;
int hauteurProduit = 0;

// modéle de box choisie
Box boxChoisie;

// lorsque le programme traitera les box une par une
OptiBox boxOpti;

// liste qui contiendra une liste de produit par unité
list<Produit> listeProduitMemeUnite;
listeProduitMemeUnite.clear();

// tant qu'il y a des produits à ranger 
while(debutListeProduit != listeProduit.size() )
{
	
	if( (longueurProduit == listeProduit.get(0).getLongueur() && hauteurProduit == listeProduit.get(0).getHauteur() ) || ( longueurProduit == 0 && hauteurProduit == 0)){
		listeProduitMemeUnite.add( listeProduit.get(0) );
		debutListeProduit++;
	}
	else{
		int i = 0;
		while ( i < listeProduitMemeUnite.size() ){
			
			int longueurPiles = 0;
			// dans ListeProduitMemeUnite se trouve toutes les mêmes unitiés d'un produit
			boxChoisie = choisirUneBox( ListeProduitMemeUnite.size() - i, longueurProduit, hauteurProduit, listeBox );	
			boxOpti = new Optibox();	
			prixTT = prixTT + boxChoisie.getPrix();

			while( longueurPiles + listeProduit.get(0).getLongueur() < boxChoisie.getLongueur() && i < listeProduitMemeUnite.size() ){
				Pile pile2 = new Pile( listeProduit.get(0).getLongueur(), boxChoisie.getHauteur() );
				longueurPiles = longueurPiles + listeProduit.get(0).getLongueur();
				while( pile2.emplier(ListeProduitMemeUnite.get(i) ) == true && i < listeProduitMemeUnite.size() ){ i++; }
				boxOpti.addPileOptiBox(pile2);
			}
			listeOptibox.add(boxOpti);		
		}
		listeProduitMemeUnite.clear();
	}
	
	longueurProduit == listeProduit.get(0).getLongueur();
	hauteurProduit == listeProduit.get(0).getHauteur();
	
}

Solution solutionOpti = new Solution(listeOptibox, prixTT); 

    
    
    
    
    
    */
    
    // permet de choisir une box qui a son aire la plus proche de la somme des aires des produits
Box choisirUneBox( int nombreDeProduit, int longueurProduit, int hauteurProduit, List<Box> listeBox ){

	Box boxRetenue = listeBox.get(0); 
	int aireP = nombreDeProduit * ( longueurProduit * hauteurProduit );
	int aireB = listeBox.get(0).getHauteur() * listeBox.get(0).getLongueur();

	int rapportRetenu = Math.abs(aireB - aireP);

        for (int i=1; i<listeBox.size(); i++){
            
		aireB = listeBox.get(i).getHauteur() * listeBox.get(i).getLongueur();
		if( rapportRetenu - Math.abs(aireB - aireP) >= 0 ){
			rapportRetenu = Math.abs(aireB - aireP);
			boxRetenue = listeBox.get(i);
		}
	}
	return boxRetenue;
}
        
    
    @SuppressWarnings("empty-statement")
    public boolean creerSolutionComplexe(){ // creation optiBox avec la box de réference
        
        //recuperer la liste des produits
                List<Produit> listeProd = this.getListeProduit();        
        //recuperer la liste de box
       
                List<Box> listeBox = this.getListeBox();
                Collections.sort(listeBox, new SortByLongeur());
                
      // enfin on crée le debut de la liste ListeProduit qui permettra de séparer les produits rangés des produits non rangés 
int debutListeProduit = 0;

// chaque unite de produit est caratérisée par la longueur et la hauteur de son produit
int longueurProduit = 0;
int hauteurProduit = 0;

// liste qui contiendra une liste de produit par unité
List<Produit> listeProduitMemeUnite = new LinkedList();

// modéle de box choisie
Box boxChoisie;

// tant qu'il y a des produits à ranger 
while(debutListeProduit != listeProd.size() )
{
	
	if( (longueurProduit == listeProd.get(0).getLongueur() && hauteurProduit == listeProd.get(0).getHauteur() ) || ( longueurProduit == 0 && hauteurProduit == 0)){
		listeProduitMemeUnite.add( listeProd.get(0) );
		debutListeProduit++;
	}
	else{
		int i = 0;
		while ( i < listeProduitMemeUnite.size() ){
			
			// dans ListeProduitMemeUnite se trouve toutes les mêmes unitiés d'un produit
			boxChoisie = choisirUneBox( listeProduitMemeUnite.size() - i, longueurProduit, hauteurProduit, listeBox );	
                            OptiBox optiBox = new OptiBox(boxChoisie,this);
                            optiBox.setBoxOptiBox(boxChoisie);
                            while(optiBox.empilerOptiBox( listeProduitMemeUnite.get(i) ));
                            i++;  
                }
                listeProduitMemeUnite.clear();
        }                             
	longueurProduit = listeProd.get(0).getLongueur();
	hauteurProduit = listeProd.get(0).getHauteur();
}       
        
        return true; 
}
    

}