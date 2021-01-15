/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import io.InstanceReader;
import javax.persistence.*;
import modele.*;

/**
 *
 * @author loic
 */
public class Test4 { //test Pile

    public static void main(String[] args) {

        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OptiBoxPU");
        final EntityManager em = emf.createEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();

                Instance inst = new Instance("nomInstance");
                Box b1 = new Box("petiteBoite", 7, 6, 10, inst);
                Box b2 = new Box("grosseBoite", 15, 10, 17, inst);
                Solution sol = new Solution("nom_solution",inst);

                
                Produit p1 = new Produit("prod1",2,3,inst);
                Produit p2 = new Produit("prod2",3,4,inst);
                Produit p3 = new Produit("prod3",5,4,inst);
                Produit p4 = new Produit("prod4",2,5,inst);
                Produit p5 = new Produit("prod5",1,4,inst);
                Produit p6 = new Produit("prod6",4,2,inst);
                Produit p7 = new Produit("prod7",2,2,inst);
                Produit p8 = new Produit("prod8",2,2,inst);
                Produit p9 = new Produit("prod9",12,9,inst);
                
             
                //pile1.afficherPile();
                em.persist(inst);
                
                sol.creerSolutionTrivial();

                et.commit();
            } catch (Exception ex) {
                System.out.println(ex);
                et.rollback();
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }

}
