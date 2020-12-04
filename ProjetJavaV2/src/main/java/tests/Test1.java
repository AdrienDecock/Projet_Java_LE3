/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import javax.persistence.*;
import modele.Service;

/**
 *
 * @author loic
 */
public class Test1 {

    public static void main(String[] args) {
        System.out.println("test4");
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hopital2PU");
        System.out.println("oui");
        final EntityManager em = emf.createEntityManager();
        try {
            System.out.println("test3");
            final EntityTransaction et = em.getTransaction();
            try {
                System.out.println("test2");
                et.begin();
                Service serv1 = new Service("Cardiologie", "Bat A, 1er étage");
                Service serv2 = new Service("Pneumologie", "Bat B, 1er étage");
                Service serv3 = new Service("Urgence", "Bat C, 1er étage");
System.out.println("test1");
                em.persist(serv1);
                //em.persist(serv2);
                serv1.setLocalisation("Bat D, 2ème étage");
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
