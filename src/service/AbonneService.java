/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Abonne;
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
 * @author yassine
 */
public class AbonneService implements iAbonne {
       
    Connection cnx = mydb.getinstance().getConnection();
    
    public void AjouterAbonne(Abonne a) {
        AbonneService ab= new AbonneService();
try {
             String req = "INSERT INTO `abonne` ( id, id, nom_a , prenom_a, age_a, sexe_a, email_a, mdp_a, tel_a, adresse_a, message, image  ) VALUES ('" + a.getId_abonne() + "','" + a.getAbonnement() + "','" + a.getNom() + "'"
             + ",'" + a.getPrenom() + "','" + a.getAge() + "','" + a.getSexe() + "','" + a.getEmail() + "','" + a.getMdp() + "','" + a.getTel() + "','" + a.getAdresse()+ "','" + a.getMessage() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Abonne ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SupprimerAbonne(int id_abonne) {
        String requete = "delete from abonne where id=?";
        try {
           PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setInt(1,id_abonne);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
           Logger.getLogger(AbonneService.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression "+ex.getMessage());
        }       }

    public List<Abonne> afficher() {
    List<Abonne> list = new ArrayList<>();
        AbonnementServices abs = new AbonnementServices();
       ObservableList<Abonne> Abonne = FXCollections.observableArrayList();
        String sql ="select Abonne.id,Abonne.id,Abonne.nom_a,Abonne.prenom_a,Abonne.age_a,Abonne.sexe_a,Abonne.email_a,Abonne.mdp_a,Abonne.tel_a,Abonne.adresse_a,Abonne.message,Abonne.image from Abonne  ";
        try {
            Statement ste= cnx.createStatement();
            ResultSet rs =ste.executeQuery(sql);
            while(rs.next()){
                Abonne s = new Abonne();
                s.setId_abonne(rs.getInt("id"));
                s.setAbonnement(abs.readById("abonnement_id"));
                s.setNom(rs.getString("nom_a"));
                s.setPrenom(rs.getString("prenom_a"));
                s.setAge(rs.getInt("age_a"));
                s.setSexe(rs.getString("sexe_a"));
                s.setEmail(rs.getString("email_a"));
                s.setMdp(rs.getString("mdp_a"));
                s.setTel(rs.getInt("tel_a"));
                s.setAdresse(rs.getString("adresse_a"));
                s.setMessage(rs.getString("message"));
                s.setImg(rs.getString("image"));
                
              
                Abonne.add(s);
                System.out.println(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Abonne;  }

    
    public int modifier(Abonne a,int id_abonne) {
                AbonnementServices abs = new AbonnementServices();

         String sq13="UPDATE `abonne` SET  `id`=?,`nom_a`=?,`prenom_a`=? ,`age_a`=?,`sexe_a`=?,`email_a`=?,`mdp_a`=?"
                 + ",`tel_a`=?,`adresse_a`=?,`message`=?" + ",`image`=? WHERE id ='" + id_abonne + "'";
            
        try {
            PreparedStatement pst = cnx.prepareStatement(sq13);
            pst.setInt(1, a.getId_abonne());
            pst.setString(2, a.getNom());
            pst.setString(3, a.getPrenom());
            pst.setInt(4, a.getAge());
            pst.setString(5, a.getSexe());
            pst.setString(6, a.getEmail());
            pst.setString(7, a.getMdp());
            pst.setInt(8, a.getTel());
            pst.setString(9, a.getAdresse());
            pst.setString(10, a.getMessage());
            pst.setString(11, a.getImg());
            pst.executeUpdate();
            System.out.println("abonne mis à jour avec succès !");
            System.out.println(a.toString());
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(AbonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    
    
    public ObservableList<Abonne> chercherTitreplat(String chaine){
          String sql="SELECT * FROM abonne WHERE (nom_a LIKE ? or prenom_a LIKE ?)";
            
             Connection Cnx= mydb.getinstance().getConnection();
            String ch="%"+chaine+"%";
            ObservableList<Abonne> myList= FXCollections.observableArrayList();
        try {
           
            Statement ste= Cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =Cnx.prepareStatement(sql);  
            stee.setString(1, ch);
            stee.setString(2, ch);
          //  stee.setString(3, ch);
            
            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                
               
                Abonne p = new Abonne ();
                p.setId_abonne(rs.getInt(1));
                p.setTel(rs.getInt(2));
                p.setAge(rs.getInt(3));
                p.setNom(rs.getString(4));
                p.setPrenom(rs.getString(5));
                p.setSexe(rs.getString(6));
                p.setEmail(rs.getString(7));
                p.setAdresse(rs.getString(8));
                p.setImg(rs.getString(9));
                p.setMdp(rs.getString(10));
                p.setMessage(rs.getString(11));
               
                
            /* p.setId(rs.getInt(1));
             p.setNom(rs.getString(2));
              p.setPrenom(rs.getString(3));
                p.setAge(rs.getInt(4));
                 p.setSexe(rs.getString(5));
                 p.setEmail(rs.getString(6));
                p.setMdp(rs.getString(7));
                p.setTel(rs.getInt(8));
                p.setAdresse(rs.getString(9));
                 p.setMessage(rs.getString(10));
                   p.setImage(rs.getString(11));*/
                
                myList.add(p);
                System.out.println("abonné trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }

    @Override
    public int modifier(Abonne a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
