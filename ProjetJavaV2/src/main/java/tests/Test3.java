/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import javax.persistence.*;
import modele.*;

/**
 *
 * @author loic
 */
public class Test3 { //test Pile

    public static void main(String[] args) {

        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OptiBoxPU");
        final EntityManager em = emf.createEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();

                Box b = new Box("petiteBoite", 10, 10, 10);
                OptiBox optiBox = new OptiBox(b);
                
                Produit p1 = new Produit("prod1",2,3);
                Produit p2 = new Produit("prod2",3,4);
                Produit p3 = new Produit("prod3",5,3);
                
                Pile pile = new Pile(optiBox);
                System.out.println(pile.empiler(p1));
                System.out.println(pile.empiler(p2));
                System.out.println(pile.empiler(p1));
                System.out.println(pile.empiler(p3));
                //pile.empiler(p1);
                
                
                em.persist(b);
                em.persist(optiBox);
                em.persist(p1);
                em.persist(p2);
                em.persist(p3);
                em.persist(pile);
                

                


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
