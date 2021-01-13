/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraph;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import modele.Box;
import modele.Instance;
import modele.Produit;
import modele.Solution;

/**
 *
 * @author nadae
 */
public class OptiAcceuil extends javax.swing.JFrame {
    
        //attributs
    //liste instances
    private List<Instance> RequeteInstance;
    /**
     * Creates new form OptiAcceuil
     */
    public OptiAcceuil() {
        initComponents();
        initFenetre();
        AfficherListInstance();
    }
        //methodes

    
    
    private void initFenetre(){
        this.setTitle("OptiBox");
        //this.setSize(1430, 700); 
        
        //couleurs
        //les boutons
        this.jButton_affInstance.setBackground(Color.decode("#90CAF9"));
        this.jButton_affInstance.setForeground(Color.WHITE);
        //le titre
        this.jTitre.setForeground(Color.decode("#546E7A"));
        this.jTitre.setFont(new Font("Tratto", Font.BOLD, 30));
        this.setVisible(true);
    }

    // gestion requete et affichage liste instance
    
    
    
    /**
     * recuperation des instances
     * afficher les noms des instances dans l'acceuil
     * le type d'affichage est string
     */
    
    public void AfficherListInstance(){
                
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("OptiBoxPU");
        final EntityManager em = emf.createEntityManager();
        try {
            
            final EntityTransaction et = em.getTransaction();
            try {
                et.begin();
                String strQuery = "SELECT i FROM Instance i";
                Query query = em.createQuery(strQuery);
                //requete instance : quelques parties récupérés du tp 5
                
                RequeteInstance = (List<Instance>) query.getResultList();
                DefaultListModel listeInstanceNom = new DefaultListModel();
                this.jListNomInstance.setModel(listeInstanceNom);
                for (Instance instanceNom : RequeteInstance){
                    System.out.println(instanceNom);
                    listeInstanceNom.addElement(instanceNom);  
                }
                et.commit();
            } catch (Exception ex) {
                et.rollback();
                System.out.println(ex);
            }
            
        } finally {
            if(em != null && em.isOpen()){
                em.close();
            }
            if(emf != null && emf.isOpen()){
                emf.close();
            }
        }         
    }
      
    //gestion boutton
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane1 = new java.awt.ScrollPane();
        jTitre = new javax.swing.JLabel();
        jButton_affInstance = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListNomInstance = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTitre.setText("OptiBox");

        jButton_affInstance.setText("Afficher Instance");
        jButton_affInstance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_InstanceMouseClicked(evt);
            }
        });
        jButton_affInstance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_affInstanceActionPerformed(evt);
            }
        });

        jListNomInstance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListNomInstanceMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListNomInstance);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 38, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_affInstance)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 39, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTitre, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_affInstance)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_affInstanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_affInstanceActionPerformed
        // TODO add your handling code here: 
        //on ouvre la page des dessins de box et produit
        try {
           
            //on recupere le nom de l'instance selectionée
            Instance instance = this.jListNomInstance.getSelectedValue();
            Set<Box> b = instance.getBox();
            Set<Produit> p = instance.getProduit();
            for (Box object : b) {
                System.out.println(object);
            }
            for (Produit objProd : p) {
                System.out.println(objProd);
            }
            OptiAfficherDessinInstance optiDessin = new OptiAfficherDessinInstance(b, p); 
            System.out.println(optiDessin.toString());
            
            //afficherDessinInstance();
            //OptiAfficherDessin optiAfficherDessin = new OptiAfficherDessin();
           
           
        } catch (Exception e) {
            System.out.println("erreur click bouton (afficher Instance)");
        }
            
        
    }//GEN-LAST:event_jButton_affInstanceActionPerformed

    private void jButton_InstanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_InstanceMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton_InstanceMouseClicked

    private void jListNomInstanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListNomInstanceMouseClicked
        // TODO add your handling code here:
        //Instance inst = this.jListNomInstance.getSelectedValue();
        
        //String nomInstance = inst.getNom();
    }//GEN-LAST:event_jListNomInstanceMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OptiAcceuil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OptiAcceuil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OptiAcceuil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OptiAcceuil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //OptiAcceuil optiacceuil = new OptiAcceuil();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OptiAcceuil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_affInstance;
    private javax.swing.JList<Instance> jListNomInstance;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jTitre;
    private java.awt.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables
}
