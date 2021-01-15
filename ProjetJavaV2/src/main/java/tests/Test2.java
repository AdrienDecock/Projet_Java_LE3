/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import io.InstanceReader;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import modele.*;

/**
 *
 * @author loic
 */
public class Test2 { 

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
                Box b3 = new Box("petiteBoite", 7, 6, 9, inst);
                Box b4 = new Box("grosseBoite", 15, 10, 30, inst);
                Box b5 = new Box("petiteBoite", 7, 6, 45, inst);
                Box b6 = new Box("grosseBoite", 15, 10, 20, inst);
                
                List<Box> listBox = new ArrayList();
                
                
             
                //pile1.afficherPile();
                em.persist(inst);
                
                //sol.creerSolutionTrivial();

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
