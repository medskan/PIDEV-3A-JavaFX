/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import entities.categorie;
import entities.orderdetail;
import entities.produit;
import entities.produit_like;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


import util.mydb;

/**
 *
 * @author PC
 */
public class ProduitService implements iProduitService<produit> {
    Connection cnx;
     String filename=null;
         public static String path;
    public  ProduitService(){
        cnx = mydb.getinstance().getConnection();
    }

    @Override
    public void ajouter(produit t) {
        CategorieService cs=new CategorieService();
       
            try {
          String req= "insert into produit(nom_produit,description,quantite,prix_produit,promotion,id,image)"
                    +"values('"+t.getNom_produit()+"','"+t.getDescription()+"','"+t.getQuantite()+"','"+t.getPrix_produit()+"','"+t.getPromotion()+"','"+cs.getid_categorie_db(t.getCategorie())+"','"+t.getImage()+"')";
      
            Statement st;
            st = cnx.createStatement();
            
            st.executeUpdate(req);
            System.out.println(""
                    + "produit ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
           public void filen(){
        try{
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        filename =f.getName();
                this.path=(filename);}
        catch(Exception e){JOptionPane.showMessageDialog(null,"veillez choisir une image");}
   
    }
     public String getpath(){
    return path;}
    
     
   
    
   
    
    @Override
    public void modifier(int id,produit t) {
         String req;
            req = "UPDATE produit SET nom_produit=?,description=?,quantite=?,prix_produit=?,promotion=?,image=? where id_produit='" + id + "'";
           
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom_produit());
            ps.setString(2, t.getDescription());
            ps.setInt(3, t.getQuantite());
            ps.setFloat(4, t.getPrix_produit());
            ps.setFloat(5, t.getPromotion());
            ps.setString(6, t.getImage());
         try {
                FileInputStream inputstream = new FileInputStream("C:/xampp/htdocs/ProjectPi/public/uploads/brochures/"+t.getImage());
                     Image image=new Image(inputstream);
                     t.setImg(image);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                }
            
             
            ps.executeUpdate();
            System.out.println("produit modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
             System.out.println("produit non modifiée");
        }
    }

    @Override
   public int getid_produit_db(produit t){
      CategorieService cs = new CategorieService();
        String req="SELECT * FROM `produit` WHERE nom_produit=? AND description=? AND quantite=? AND prix_produit=? AND promotion=? AND image=?";
        List<produit> list=new ArrayList<>();
        int x=0;
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
             while(rs.next()){
         
                list.add(new produit(rs.getInt("id_produit"),rs.getString("description"),  rs.getString("nom_produit"), rs.getInt("quantite"),rs.getFloat("prix_produit"),rs.getFloat("promotion"), cs.readById(rs.getInt("id")), rs.getString("image")));
                x=list.get(0).getId_produit();
            }
         } catch (SQLException ex) {
             Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return x;   
    }
    @Override
    public int getid_produit(String n){
     
        String req="SELECT * FROM `produit` WHERE `nom_produit`=?";
        List<produit> list=new ArrayList<>();
        int x=0;
         try {
            PreparedStatement  ps;
           ps = cnx.prepareStatement(req);
  
          
      
             ResultSet rs;
           rs = ps.executeQuery();
             while(rs.next()){
           x=list.get(0).getId_produit();
                }
         } catch (SQLException ex) {
             Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return x;   
    }
     
    @Override
    public void supprimer(produit p) {
        try {
            String req = "DELETE FROM produit where id_produit=?";
              PreparedStatement ps = cnx.prepareStatement(req);
             ps.setInt(1, p.getId_produit());
            ps.executeUpdate();
            System.out.println("produit supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
   
   @Override
    public List<produit> recuperer() {
        CategorieService cs=new CategorieService();
                ProduitService ps=new ProduitService();
         String req = "select * from produit";
        List<produit> list = new ArrayList<>();
        try {
           
            FileInputStream inputstream;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){

                
                 
           produit p= new produit(rs.getInt("id_produit"),rs.getString("nom_produit"),cs.readById(rs.getInt("id")),rs.getInt("quantite"),rs.getString("description"),
                 
                    rs.getFloat("prix_produit"),rs.getFloat("promotion"),
                    rs.getString("image"),ps.likesnb(ps.readById(rs.getInt("id_produit"))));
            
            try {
                    inputstream=new FileInputStream("C:/xampp/htdocs/ProjectPi/public/uploads/brochures/"+rs.getString("image"));
                     Image image=new Image(inputstream);
                     p.setImg(image);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                }
            
             
            list.add(p);
           System.out.println("produit récupérée");
            }
        } catch (SQLException ex) {
              Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
       
        }
        return list;
    }
  
    public List<produit> recupererTrier() {
        CategorieService cs=new CategorieService();
                ProduitService ps=new ProduitService();
         String req = "select * from produit order by nl";
        List<produit> list = new ArrayList<>();
        try {
           
            FileInputStream inputstream;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){

                
                 
           produit p= new produit(rs.getInt("id_produit"),rs.getString("nom_produit"),cs.readById(rs.getInt("id")),rs.getInt("quantite"),rs.getString("description"),
                 
                    rs.getFloat("prix_produit"),rs.getFloat("promotion"),
                    rs.getString("image"),ps.likesnb(ps.readById(rs.getInt("id_produit"))));
            
            try {
                    inputstream=new FileInputStream("C:/xampp/htdocs/ProjectPi/public/uploads/brochures/"+rs.getString("image"));
                     Image image=new Image(inputstream);
                     p.setImg(image);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                }
            
             
            list.add(p);
           System.out.println("produit récupérée");
            }
        } catch (SQLException ex) {
              Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
       
        }
        return list;
    }   
    
    
    

    @Override
    public Map AddToCart(Map panier,produit p){
        if(panier.containsKey(p))
        {ProduitService ps=new ProduitService();
        ps.IncrementerQte(panier, p);
        }else
     panier.put(p, 1);
        return panier;
    
    
    }
    @Override
    public void AfficherPanier(Map panier)
    {
        Iterator iterator = panier.entrySet().iterator();
        while (iterator.hasNext()) {
          Map.Entry mapentry = (Map.Entry) iterator.next();
          System.out.println("produit: "+mapentry.getKey()
                            + " | quantite: " + mapentry.getValue());
        } 
    
    }
    @Override
    public List RecupererPanier(Map panier)
    {   List<produit> list = new ArrayList<>();
        Iterator iterator = panier.entrySet().iterator();
        while (iterator.hasNext()) {
          Map.Entry mapentry = (Map.Entry) iterator.next();
          produit p =(produit) mapentry.getKey();
          int q=(int) mapentry.getValue();
          int id=p.getId_produit();
          String nom=p.getNom_produit();
          float promo= p.getPromotion();
          float prix=p.getPrix_produit();
          categorie cat=p.getCategorie();
          produit produit=new produit(id,nom,cat,prix,q,promo);
          list.add(produit);
             System.out.println("panier récupérée");
          
    
    
    }return list;}
    @Override
    public Map IncrementerQte(Map panier,produit p){
   
              int q;
        q =   (int) panier.get(p);
              panier.put(p, q+1);
        return panier;
    
    
    }
    @Override
    public Map DecrementerQte(Map panier,produit p){
   
               int q =(int) panier.get(p);
               if(q!=1){
               panier.put(p, q-1);}
               else panier.remove(p);
        return panier;
    
    
    }
    @Override
    public float calculerTotal(Map panier){
    float total=0;
   Iterator iterator = panier.entrySet().iterator();
        while (iterator.hasNext()) {
          Map.Entry mapentry = (Map.Entry) iterator.next();
          produit p;
          p=(produit) mapentry.getKey();
          
          int q;
                  q=(int) mapentry.getValue();
                  float prix=p.getPrix_produit()-(p.getPrix_produit()*(p.getPromotion()/100));
          total=total+(q*prix);
        } 
    
    return total;
    }
    
    @Override
    public void validerPanier(Map panier) {
         Iterator iterator = panier.entrySet().iterator();
               Random random = new Random();
   int nb;
   nb = 10000+random.nextInt(20000-10000);
        while (iterator.hasNext()) {
          Map.Entry mapentry = (Map.Entry) iterator.next();
          produit p;
          p=(produit) mapentry.getKey();
            int q;
                  q=(int) mapentry.getValue();
          float prix=q*(p.getPrix_produit()-(p.getPromotion()/100));
        int id=p.getId_produit();
        String str="";
         String req2= "insert into order_detail(id_produit,quantity,prix,id_order)"
                    +"values('" + id+ "','" + q + "','" + prix + "','" + nb+ "')";
        try {
           
            Statement st = cnx.createStatement();
            st.executeUpdate(req2);
            p.setQuantite(p.getQuantite()-q);
            str=str+" "+p.getNom_produit();
            System.out.println(""
                    + "order detail ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        } 
        ProduitService ps=new ProduitService();

   int id_c=2;
   float t=ps.calculerTotal(panier);
  LocalDateTime date = LocalDateTime.now();
 
         String req= "INSERT INTO `order` ( `date`, `numero_commande`, `total`, `id_c`)"
                    +"values('" +date+ "','" + nb + "','" + t + "','" + id_c + "')";
        try {
             
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(""
                    + "commande ajoutée");
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
         }
     panier.clear();
        
    }
    public String ProduitAchetes(Map panier){
        Iterator iterator = panier.entrySet().iterator();
             String str="";
        while (iterator.hasNext()) {
          Map.Entry mapentry = (Map.Entry) iterator.next();
          produit p;
          p=(produit) mapentry.getKey();
     str=str+p.getNom_produit()+"\n";
        }return str;
    }
    public void Mailer(Map panier,String p,float t){
       
        String host="kazdaghli86@gmail.com";  //←my email address
        final String user="kazdaghli86@gmail.com";// my email address
        final String password="20479411";// my email password

        String to="kazdaghli86@gmail.com";//the EMAIL i want to send TO

        // session object
        Properties props = new Properties();//he Properties class provides methods to get data from the properties file and store data into the properties file
         //It creates an empty property list with no default values.
        props.put("mail.smtp.ssl.trust", "*");//trust any host
        props.put("mail.smtp.auth", "true");//enable auth
        props.put("mail.smtp.port", "587");//port
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");//enable ssl

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });
   
         
        //My message :
        try {
       
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(" Thank You !!! ");
            //String Nom=o.getId_c().getnom();
            String nom="Line Kazdaghli";
             ProduitService ps=new ProduitService();
       
            message.setContent(
                    "<h1 style =\"color:Blue\" >Thank You !! </h1>Hello "+nom+" Merci d'avoir commander ces produits\n"+p+"<br/>Votre Commande vous sera livrez dans deux jour.Le montant a payer c'est "+t+"dt.",
                    "text/html");

            //send the message
            Transport.send(message);//ask for an "smtp" transport 

            System.out.println("message sent successfully via mail ... !!! ");
       

        } catch (MessagingException e) {e.printStackTrace();}

    
    
      
        }
        
    
    @Override
     public List<produit> Rechercher(String recherche) {
        List<produit> List = new ArrayList<produit>();
CategorieService cs=new CategorieService();
        try {
            String requete = "SELECT * From produit where nom_produit LIKE '%" + recherche + "%'OR  description LIKE '%" + recherche + "%'OR  quantite LIKE '%" + recherche + "%'OR  prix_produit LIKE '%" + recherche + "%'OR  promotion LIKE '%" + recherche + "%' ";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
             produit p=new produit(rs.getInt("id_produit"),rs.getString("nom_produit"),cs.readById(rs.getInt("id")),rs.getInt("quantite"),rs.getString("description"),
                 
                    rs.getFloat("prix_produit"),rs.getFloat("promotion"),
                    rs.getString("image"));
                 try {
                  FileInputStream inputstream = new FileInputStream("C:/xampp/htdocs/ProjectPi/public/uploads/brochures/"+rs.getString("image"));
                     Image image=new Image(inputstream);
                     p.setImg(image);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                }
            List.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return List;

    }
            
            
    @Override
  public produit readById(int id_produit) {
   produit p = new produit();
         try {
             String sql = "select * from produit where id_produit= ?";
        
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.setInt(1, id_produit);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                     
                CategorieService cs=new CategorieService();
             p=new produit(rs.getInt("id_produit"),rs.getString("description"),  rs.getString("nom_produit"), rs.getInt("quantite"),rs.getFloat("prix_produit"),rs.getFloat("promotion"), cs.readById(rs.getInt("id")), rs.getString("image"));            }

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        return p;
    }
   public String recupererQ() {
       
         String req = "select * from produit where quantite<5";
         String str="";
         
        try {
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){

                
                       str=str+",\n"+rs.getString("nom_produit");
                 
            }
        } catch (SQLException ex) {
              Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
       
        }
        return str;
    }
  public void StockNotification() {
       String message="";
       message="Les produits suivants: "+recupererQ()+"\n seront bientot 'out of stock'";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Stock State");
        
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.NOTICE);
        tray.showAndDismiss(Duration.millis(3000));
    }
  
  public void AddLike(produit p,int id_c){
   try {
            String req= "insert into produit_like (id_produit,abonne_id)"
                    +"values('"+p.getId_produit()+"','"+id_c+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(""
                    + "like ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  
  }
   public void DeleteLike(produit p,int id_c){
   try {
            String req= "DELETE from produit_like  where id_produit ='" + p.getId_produit()+ "' AND abonne_id ='" + id_c+ "'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(""
                    + "like supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  
  }
  public int likes(produit p,int id_c){
          int id=p.getId_produit();
     int x = 0;
    
           try{
               
             String req = "SELECT COUNT(*) as nb FROM `produit_like` where id_produit ='" + p.getId_produit()+ "' AND abonne_id ='" + id_c+ "'";
     
               Statement ste = cnx.createStatement();
              ResultSet rs=ste.executeQuery(req);
              while (rs.next())
              {x=rs.getInt("nb");
           
 }
                       
     }catch(SQLException ex) {
            System.out.println("erreur");
                    }

   return x;
}
   public int likesnb(produit p){
          int id=p.getId_produit();
     int x = 0;
    
           try{
               
             String req = "SELECT COUNT(*) as nb FROM `produit_like` where id_produit ='" + p.getId_produit()+ "'";
     
               Statement ste = cnx.createStatement();
              ResultSet rs=ste.executeQuery(req);
              while (rs.next())
              {x=rs.getInt("nb");
           
 }
                       
     }catch(SQLException ex) {
            System.out.println("erreur");
                    }

   return x;
}
   
  public boolean verifL(produit p,int id_c) {
          String req="select * from produit_like where id_produit ='" + p.getId_produit()+ "' AND abonne_id ='" + id_c+ "'";
        List<produit_like> list=new ArrayList<>();
        ProduitService ps=new ProduitService();
        try {
             Statement ste=cnx.createStatement();
            ResultSet rs= ste.executeQuery(req);
            while(rs.next()){
                list.add(new produit_like(rs.getInt("id"),rs.getInt("abonne_id"),ps.readById(rs.getInt("id_produit"))));
            }
            if(list.isEmpty()){return true;
      }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     
    
      return false;
  }
    
}


