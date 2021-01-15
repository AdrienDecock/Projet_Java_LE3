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

                
                Instance inst = new Instance("nomInstance");
                Box b1 = new Box("petiteBoite", 7, 6, 10, inst);
                Box b2 = new Box("petiteBoite", 15, 10, 17, inst);
                Solution sol = new Solution("nom_solution",inst);

                
                Produit p1 = new Produit("prod1",2,3,inst);
                Produit p2 = new Produit("prod2",3,4,inst);
                Produit p3 = new Produit("prod3",5,4,inst);
                Produit p4 = new Produit("prod4",2,5,inst);
                Produit p5 = new Produit("prod5",1,2,inst);
                Produit p6 = new Produit("prod6",4,2,inst);
                Produit p7 = new Produit("prod7",2,2,inst);
                Produit p8 = new Produit("prod8",2,2,inst);
                Produit p9 = new Produit("prod9",12,9,inst);
                
                
                OptiBox optiBox1 = new OptiBox(b1,sol);
                Pile pile1 = new Pile(optiBox1);
                pile1.empiler(p4);
                pile1.empiler(p2);
                pile1.empiler(p1);
                
                OptiBox optiBox2 = new OptiBox(b1,sol);
                Pile pile2 = new Pile(optiBox2);
                pile2.empiler(p3);
                pile2.empiler(p7);
                Pile pile3 = new Pile(optiBox2);
                pile3.empiler(p5);
                pile3.empiler(p6);
                pile3.empiler(p8);
                
                OptiBox optiBox3 = new OptiBox(b2,sol);
                Pile pile4 = new Pile(optiBox3);
                pile4.empiler(p9);
                
                //em.persist(b);
                em.persist(inst);
                //em.persist(sol);
                //em.persist(optiBox);
                //em.persist(p1);
                //em.persist(p2);
                //em.persist(p3);
                //em.persist(pile);
                

                
                
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
