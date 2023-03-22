/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Abonnement;
import entities.Abonnement;
import entities.categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.mydb;

/**
 *
 * @author HP
 */
public class AbonnementServices implements iAbonnement {
     Connection    cnx = mydb.getinstance().getConnection();

    /**
     *
     * @param c
     */
    @Override
    public void AjouterAbonnement(Abonnement c) {
        try {
             String req = "INSERT INTO abonnement (type_a,tarif_a ,date_debut, date_fin  ) VALUES ('" + c.getTypeA() + "','" + c.getTarifA() + "','" + c.getDateDebut() + "','" + c.getDateFin() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("abonnement ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void SupprimerAbonnement(Abonnement a) {
        String requete = "delete from abonnement where id=?";
        try {
          PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1,a.getIdab() );
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           Logger.getLogger(AbonnementServices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }
        
    }

    @Override
    public List<Abonnement> afficher() {
        ObservableList<Abonnement> Abonnement = FXCollections.observableArrayList();
        String sql ="select Abonnement.id,Abonnement.type_a,Abonnement.tarif_a,Abonnement.date_debut,Abonnement.date_fin from Abonnement  ";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Abonnement s = new Abonnement();
                s.setIdab(rs.getInt("id"));
                s.setTypeA(rs.getString("type_a"));
                s.setTarifA(rs.getDouble("tarif_a"));
                s.setDateDebut(rs.getDate("date_debut"));
                s.setDateFin(rs.getDate("date_fin"));
              
                Abonnement.add(s);
                System.out.println(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Abonnement;   }

   /* @Override
    public void ModifierAbonnement(Abonnement c, String typeA) {
        String sq13="UPDATE `abonnement` SET `tarif_a`=?,`date_debut`=?,`date_fin`=?WHERE type_a =?";
            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(1, c.getTypeA());
            pst.setDouble(2, c.getTarifA());
                        pst.setString(3, c.getDateDebut());

            pst.setString(4, c.getDateFin());
                      

                        pst.setString(5, c.getTypeA());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AbonnementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }*/

    
    public void modifier(Abonnement c,int id) {
         String sq13="UPDATE `abonnement` SET  `type_a`=?,`tarif_a`=?,`date_debut`=?,`date_fin`=? WHERE id ='" + id + "'";
            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setString(1, c.getTypeA());
            pst.setDouble(2, c.getTarifA());
            pst.setDate(3, c.getDateDebut());
          pst.setDate(4, c.getDateFin());
            pst.executeUpdate();
            System.out.println("abonnement mis à jour avec succès !");
            System.out.println(c.toString());
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AbonnementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    }
 public Abonnement readById(int id) {
        Abonnement c = new Abonnement();
        String sql = "select * from abonnement where id= ? ;";
        try {
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
              c.setIdab(rs.getInt(1));
              c.setTypeA(rs.getString(2));
              c.setTarifA(rs.getDouble(3));
              c.setDateDebut(rs.getDate(4));
              c.setDateFin(rs.getDate(5));
            
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    @Override
    public void SupprimerAbonnement(String type_a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int modifier(Abonnement c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Abonnement readById(String abonnement_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}