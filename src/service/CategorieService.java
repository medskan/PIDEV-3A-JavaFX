/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
import service.ICategorieService.iCategorieService;
import util.mydb;

/**
 *
 * @author HP
 */
public class CategorieService implements iCategorieService<categorie> {
    Connection cnx;
    public  CategorieService(){
        cnx = mydb.getinstance().getConnection();
    }

    @Override
    public void ajouter(categorie t) {
        try {
            String req= "insert into categorie(nom_c,type)"
                    +"values('"+t.getNom_c()+"','"+t.getType()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(""
                    + "categorie ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     
    @Override
    public void modifier(int id,categorie t) {
         String req;
            req = "UPDATE categorie SET nom_c=? ,type=? where id='" + id + "'";
        try {
           
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom_c());
            ps.setString(2, t.getType());
      
            ps.executeUpdate();
            System.out.println("categorie modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(categorie c) {
        try {
            String req = "DELETE FROM categorie where id=?";
              PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1, c.getId());
            ps.executeUpdate();
            System.out.println("Categorie supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    @Override
    public List<categorie> recuperer() {
          String req="select * from categorie";
        List<categorie> list=new ArrayList<>();
        try {
             Statement ste=cnx.createStatement();
            ResultSet rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new categorie(rs.getInt("id"), rs.getString("nom_C"), rs.getString("type")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

   
    @Override
 public int getid_categorie_db(categorie c){
        String req="select * from categorie where nom_c='"+c.getNom_c()+"' AND type='"+c.getType()+"'";
        List<categorie> list=new ArrayList<>();
        int x=0;
         try {
            PreparedStatement  pst = cnx.prepareStatement(req);
            
             ResultSet rs=pst.executeQuery();
             while(rs.next()){
                list.add(new categorie(rs.getInt("id"), rs.getString("Nom_c"), rs.getString("type")));
                x=list.get(0).getId();
            }
         } catch (SQLException ex) {
             Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return x;   
    }
 
    @Override
    public categorie readById(int id) {
        categorie c = new categorie();
        String sql = "select * from categorie where id= ? ;";
        try {
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setNom_c(rs.getString(2));
                c.setType(rs.getString(3));
            
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    @Override
      public List<categorie> Rechercher(String recherche) {
        List<categorie> List = new ArrayList<>();

        try {
            String requete = "SELECT * From categorie where nom_c like '%" + recherche + "%'OR type like '%" +recherche+ "%'  ";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                 List.add(new categorie(rs.getInt("id"), rs.getString("nom_C"), rs.getString("type")));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return List;

    }

  
}
