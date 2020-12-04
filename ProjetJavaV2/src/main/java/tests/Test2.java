/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import javax.persistence.*;
import modele.Box;
import modele.Instance;
import modele.Produit;

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

                Instance i1 = new Instance("Trancen");
                //Instance med2 = new Instance("Gator");
                //Instance med3 = new Instance("Test");
                //Box b1 = new Box("petite boite", 30, 15, 12.5);
                //Box b2 = new Box("grande boite", 50, 35, 22.5);
                //Produit p1 = new Produit("rouge",10,7,3);
                
                
                em.persist(i1);
                //em.persist(med2);
                //em.persist(med3);
                //em.persist(b1);
                //em.persist(b2);
                //em.persist(p1);


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
