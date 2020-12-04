/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import javax.persistence.*;
import modele.Medecin;
import modele.Service;

/**
 *
 * @author loic
 */
public class Test2 {

    public static void main(String[] args) {

        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hopital2PU");
        final EntityManager em = emf.createEntityManager();
        try {
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();

                Medecin med1 = new Medecin("Trancen", "Jean", 2135.23);
                Medecin med2 = new Medecin("Gator", "Coralie", 3156.00);
                Medecin med3 = new Medecin("Test", "Magalie", 2545.3723);
                em.persist(med1);
                em.persist(med2);
                em.persist(med3);

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
