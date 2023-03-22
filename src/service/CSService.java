/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.categorie;
import entities.produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import util.mydb;

/**
 *
 * @author PC
 */
public class CSService {
        Connection cnx;
   
        
    public CSService(){
        cnx = mydb.getinstance().getConnection();
    }
   public boolean ControleStr1(String s) {
   if(s.length()==0)
   {
   return false;
  
   }
   
   return true;
   }
   
   public boolean ControleStr2(String s){
   char[] charArray=s.toCharArray();
   for(int i=0;i<charArray.length;i++){
       char ch=charArray[i];
   if(!((ch>='a' && ch<='z')||(String.valueOf(ch)).equals(" "))){
       return false;
   }
   
   }
   return true;
}
public boolean ControleNum(String str){
   char[] charArray=str.toCharArray();
   for(int i=0;i<charArray.length;i++){
       char ch=charArray[i];
    if(str.contains("a") || str.contains("b") || str.contains("c") || str.contains("d") || str.contains("e") || str.contains("f") || str.contains("g") || str.contains("h") || str.contains("i") || str.contains("j")|| str.contains("k")|| str.contains("l")|| str.contains("m")|| str.contains("n")|| str.contains("o")|| str.contains("p")|| str.contains("q")|| str.contains("r")|| str.contains("s")|| str.contains("t")|| str.contains("u")|| str.contains("v")|| str.contains("w")|| str.contains("y")|| str.contains("z")){
            
       return false;
   }
   
   }
   return true;
}
public boolean Produitexiste(produit t){
        CategorieService cs = new CategorieService();
        String req="SELECT * FROM `produit` WHERE nom_produit=? AND description=? AND quantite=? AND prix_produit=? AND promotion=? AND image=?";
        List<produit> list=new ArrayList<>();
       
         try {
            PreparedStatement  ps;
           ps = cnx.prepareStatement(req);
          ps.setString(1, t.getNom_produit());
          ps.setString(2, t.getDescription());
          ps.setInt(3, t.getQuantite());
          ps.setFloat(4, t.getPrix_produit());
          ps.setFloat(5,t.getPromotion());
          ps.setString(6, t.getImage());
             ResultSet rs;
           rs = ps.executeQuery();
            if(rs.next()){
                
                return false;
            }
    } catch (SQLException ex) {
             Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return true;}

public boolean catexiste(categorie c){
        String req="select * from categorie where nom_c='"+c.getNom_c()+"' AND type='"+c.getType()+"'";
        List<categorie> list=new ArrayList<>();
        int x=0;
         try {
            PreparedStatement  pst = cnx.prepareStatement(req);
            
             ResultSet rs=pst.executeQuery();
             
           rs = pst.executeQuery();
            if(rs.next()){
                
                return false;
            }
         } catch (SQLException ex) {
             Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
         }
      return   true; 
    }
public boolean ControleNotNum(String str){
   char[] charArray=str.toCharArray();
   for(int i=0;i<charArray.length;i++){
       char ch=charArray[i];
    if(str.contains("0") || str.contains("1") || str.contains("2") || str.contains("3") || str.contains("4") || str.contains("5") || str.contains("6") || str.contains("7") || str.contains("8") || str.contains("9")){
                    
       return false;
   }
   
   }
   return true;
}
public boolean Controlenumpositif(String str){
   char[] charArray=str.toCharArray();
   
   for(int i=0;i<charArray.length;i++){
       char ch=charArray[i];
    if(str.contains("a") || str.contains("b") || str.contains("c") || str.contains("d") || str.contains("e") || str.contains("f") || str.contains("g") || str.contains("h") || str.contains("-") ||str.contains("i") || str.contains("j")|| str.contains("k")|| str.contains("l")|| str.contains("m")|| str.contains("n")|| str.contains("o")|| str.contains("p")|| str.contains("q")|| str.contains("r")|| str.contains("s")|| str.contains("t")|| str.contains("u")|| str.contains("v")|| str.contains("w")|| str.contains("y")|| str.contains("z")){
            
       return false;
   }
   
   }
   return true;
}
}