/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.order;
import entities.orderdetail;
import entities.produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import service.IOrderService.iOrderService;
import util.mydb;

/**
 *
 * @author PC
 */
public class OrderService implements iOrderService<order> {
    Connection cnx;
    public  OrderService(){
        cnx = mydb.getinstance().getConnection();
    }
    @Override
    public void supprimer(order o) {
        
        try {
            String req = "DELETE FROM `order` where id=?";
              PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1, o.getId());
            ps.executeUpdate();
            System.out.println("commande supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
    @Override
    public List<order> recuperer() {
        List<order> list = new ArrayList<>();
            String req = "SELECT * FROM `order`";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                order o = new order();
                o.setId(rs.getInt("id"));
             o.setDate(rs.getDate("date"));
                o.setNumero_commande(rs.getInt("numero_commande"));
                 o.setTotal(rs.getFloat("total"));
                   o.setId_c(rs.getInt("id_c"));
                
               
            list.add(o);
                System.out.println("order récupérée");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }


    @Override
      public List<order> Rechercher(String recherche) {
        List<order> List = new ArrayList<>();

        try {
            String requete = "SELECT * From `order` where numero_commande LIKE '%" + recherche + "%'OR  id_c LIKE '%" + recherche + "%' OR  date LIKE '%" + recherche + "%' ";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
              order o = new order();
                o.setId(rs.getInt("id"));
             o.setDate(rs.getDate("date"));
                o.setNumero_commande(rs.getInt("numero_commande"));
                 o.setTotal(rs.getFloat("total"));
                   o.setId_c(rs.getInt("id_c"));
               
                List.add(o);
           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return List;

    }
   
    @Override
      public List recupererOrderDetails(order o){
        List<orderdetail> list = new ArrayList<>();
        ProduitService ps=new ProduitService();
        int id=o.getNumero_commande();
            
       
          try{
               
             String req = "SELECT * FROM `order_detail` where id_order =?";
        
              PreparedStatement pst;
            pst = cnx.prepareStatement(req);
             pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
            orderdetail orderdetail=new orderdetail(rs.getInt("id"),rs.getInt("quantity"),rs.getFloat("prix"),ps.readById(rs.getInt("id_produit")));
         
            list.add(orderdetail);
            
            
            }}catch(SQLException ex) {
            System.out.println("erreur");
                    }
     
     
       return list;
      }
      public int nbvente(produit p){
          int id=p.getId_produit();
     int x = 0;
     int  v= 0;
      int q = 0;
           try{
               
             String req = "SELECT COUNT(*) as vente,quantity as q FROM `order_detail` where id_produit ='" + id + "'";
     
               Statement ste = cnx.createStatement();
              ResultSet rs=ste.executeQuery(req);
              while (rs.next())
              {v=rs.getInt("vente");
              q=rs.getInt("q");
 }
                       
     }catch(SQLException ex) {
            System.out.println("erreur");
                    }

   return x=v*q;
}
  
}