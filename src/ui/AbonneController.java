/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entities.Abonne;
import entities.Abonnement;
import entities.categorie;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import service.AbonneService;
import service.AbonnementServices;
import util.mydb;
import util.javaMail;


/**
 * FXML Controller class
 *
 * @author yassine
 */
public class AbonneController implements Initializable {
    private  ObservableList<Abonne> data ;

    @FXML
    private TextField cina;
    @FXML
    private TextField abo;
    @FXML
    private TextField noma;
    @FXML
    private TextField prenoma;
    @FXML
    private TextField agea;
    @FXML
    private TextField sexea;
    @FXML
    private TextField emaila;
    @FXML
    private TextField adressea;
    @FXML
    private TextField imga;
    @FXML
    private TextField mdpa;
    @FXML
    private TextField msga;
    @FXML
    private TextField tela;
    @FXML
    private TableView<Abonne> tablea;
    @FXML
    private TableColumn<Abonne, String> CINA;
    @FXML
    private TableColumn<Abonne, String> ABOA;
    @FXML
    private TableColumn<Abonne, String> NOMA;
    @FXML
    private TableColumn<Abonne, String> PRENOMA;
    @FXML
    private TableColumn<Abonne, String> AGEA;
    @FXML
    private TableColumn<Abonne, String > SEXEA;
    @FXML
    private TableColumn<Abonne, String > MAILA;
    @FXML
    private TableColumn<Abonne, String > ADRESSEA;
    @FXML
    private TableColumn<Abonne, String > IMGA;
    @FXML
    private TableColumn<Abonne, String > MDPA;
    @FXML
    private TableColumn<Abonne, String > MSGA;
    @FXML
    private TableColumn<Abonne, String > TELA;
    @FXML
    private Button ajouta;
    @FXML
    private Button modifa;
    @FXML
    private Button suppa;
     @FXML
    private ChoiceBox<Abonnement> catP;
    
    int index=-1;
    private TextField objet;
    private TextField corps;
    @FXML
    private TextField id;
    @FXML
    private AnchorPane pagination;
    private AbonneService AbonneService;
    private AbonnementServices AbonnementServices;
     
ObservableList<Abonnement> catList =FXCollections.observableArrayList();
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       Abonne a = new Abonne();
       AbonneService abs = new AbonneService();
          this.AbonneService = new AbonneService();
        this.AbonnementServices = new AbonnementServices();
        catList.addAll(AbonnementServices.afficher());
        catP.setItems(catList);
        
        StringConverter<Abonnement> converter = new StringConverter<Abonnement>() {
         

            @Override
            public Abonnement fromString(String string) {
                return null;
            }

             @Override
             public String toString(Abonnement object) {
                 return object.getTypeA();
             }
        };
         ObservableList<Abonne>items = FXCollections.observableArrayList();
         this.AbonneService= new AbonneService();
         this.AbonnementServices= new AbonnementServices();

        catP.setConverter(converter);
         ObservableList<Abonne>list = FXCollections.observableArrayList();
              CINA.setCellValueFactory(new PropertyValueFactory<>("id"));
        ABOA.setCellValueFactory(new PropertyValueFactory<>("abonnement_id"));
        NOMA.setCellValueFactory(new PropertyValueFactory<>("nom_a"));
        PRENOMA.setCellValueFactory(new PropertyValueFactory<>("prenom_a"));
       AGEA.setCellValueFactory(new PropertyValueFactory<>("age_a"));
       SEXEA.setCellValueFactory(new PropertyValueFactory<>("sexe_a"));
       MAILA.setCellValueFactory(new PropertyValueFactory<>("email_a"));
       ADRESSEA.setCellValueFactory(new PropertyValueFactory<>("adresse_a"));
       IMGA.setCellValueFactory(new PropertyValueFactory<>("image"));
       MDPA.setCellValueFactory(new PropertyValueFactory<>("mdp_a"));
          list.addAll(abs.afficher());
        tablea.setItems(list);
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
        

                  AbonneService ts1 = new  AbonneService();
                  ts1.AjouterAbonne(new Abonne(Integer.parseInt(cina.getText()),Integer.parseInt(agea.getText()),Integer.parseInt(tela.getText())
                          ,noma.getText(),prenoma.getText(),sexea.getText(),emaila.getText(),adressea.getText()
                  ,imga.getText(),mdpa.getText(),msga.getText()));
                  try {
            String Object = objet.getText();
            String Corps = corps.getText();
            javaMail.sendMail("yassine.hkiri@esprit.tn", Object, Corps);
        } catch (Exception e) {
            e.printStackTrace();
        }
               affiche();
               
                   
    
 
    }

    @FXML
    private void modifier(ActionEvent event) {
        AbonneService ab=new AbonneService();
       Abonne selected  = (Abonne) tablea.getSelectionModel().getSelectedItem();
       cina.setText(selected.getId()+"");
       noma.setText(selected.getNom());
       prenoma.setText(selected.getPrenom());
       agea.setText(selected.getAge()+"");
       sexea.setText(selected.getSexe());
       emaila.setText(selected.getEmail());
       mdpa.setText(selected.getMdp());
      
       adressea.setText(selected.getAdresse());
       imga.setText(selected.getImage());
      
       msga.setText(selected.getMessage());
      
    Abonne abonne=(new Abonne(Integer.parseInt(cina.getText()),Integer.parseInt(agea.getText()),Integer.parseInt(tela.getText())
                          ,noma.getText(),prenoma.getText(),sexea.getText(),emaila.getText(),adressea.getText()
                  ,imga.getText(),mdpa.getText(),msga.getText()));
    ab.modifier(abonne, selected.getId());
    affiche();
       }
        
        
        
    

    @FXML
    private void supprimer(ActionEvent event) {
         AbonneService r = new AbonneService();
       
       Abonne rl = (Abonne) tablea.getSelectionModel().getSelectedItem();
                

        r.SupprimerAbonne(rl.getId_abonne());
         affiche();
        
        
    }

    @FXML
    private void selected(MouseEvent event) {
   Abonne evt = tablea.getSelectionModel().getSelectedItem();
       if (index<= -1)
        {return; } 
       noma.setText(evt.getNom());
       prenoma.setText(evt.getPrenom());
       sexea.setText(evt.getSexe());
       emaila.setText(evt.getEmail());
       adressea.setText(evt.getAdresse());
       imga.setText(evt.getImg());
       mdpa.setText(evt.getMdp());
       msga.setText(evt.getMessage());
        //String a = Integer.toString(evt.getIdab());
        //abo.setText(a);
        String b = Integer.toString(evt.getId_abonne());
        cina.setText(b);
        String c =Integer.toString(evt.getAge());
        agea.setText(c);
        String d =Integer.toString(evt.getTel());
        tela.setText(d);

    }
    
    
    
     public ObservableList<Abonne> show1()
    { 
       

           try {
               ObservableList<Abonne> obl =FXCollections.observableArrayList();
                             Connection cnx = mydb.getinstance().getConnection();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                  PreparedStatement pt= cnx.prepareStatement("SELECT id, nom_a, prenom_a, age_a, sexe_a, email_a, mdp_a, tel_a, adresse_a, message, image  from abonne ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Abonne ls = new Abonne();
                 ls.setId(rs.getInt("id"));
                 ls.setNom(rs.getString("nom_a"));
                 ls.setPrenom(rs.getString("prenom_a"));
                 ls.setAge(rs.getInt("age_a"));
                 ls.setSexe(rs.getString("sexe_a"));
                 ls.setEmail(rs.getString("email_a"));
                 ls.setMdp(rs.getString("mdp_a"));
                 ls.setTel(rs.getInt("tel_a"));
                 ls.setAdresse(rs.getString("adresse_a"));
                 ls.setMessage(rs.getString("message"));
                 ls.setImage(rs.getString("image"));
                 
             

                  
                  System.out.println("");
                   obl.add(ls);
                  }
                  return obl;
                  
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
           return null;
    } 
    
    
     public void affiche() {
        
           
                         
      CINA.setCellValueFactory(new PropertyValueFactory<>("id"));
      //ABOA.setCellValueFactory(new PropertyValueFactory<>("abonnement_id"));
      NOMA.setCellValueFactory(new PropertyValueFactory<>("nom_a"));
      PRENOMA.setCellValueFactory(new PropertyValueFactory<>("prenom_a"));
      AGEA.setCellValueFactory(new PropertyValueFactory<>("age_a"));
      SEXEA.setCellValueFactory(new PropertyValueFactory<>("sexe_a"));
      MAILA.setCellValueFactory(new PropertyValueFactory<>("email_a"));
      MDPA.setCellValueFactory(new PropertyValueFactory<>("mdp_a"));
      TELA.setCellValueFactory(new PropertyValueFactory<>("tel_a"));
      ADRESSEA.setCellValueFactory(new PropertyValueFactory<>("adresse_a"));
      MSGA.setCellValueFactory(new PropertyValueFactory<>("message"));
      IMGA.setCellValueFactory(new PropertyValueFactory<>("image"));
      
     
      ObservableList<Abonne> obl =FXCollections.observableArrayList();
      AbonneService ab= new AbonneService();
      obl.addAll(ab.afficher());
      tablea.setItems(obl);
      

                      
    }
    
    public void mailfonction(MouseEvent event) {
        try {
            String Object = objet.getText();
            String Corps = corps.getText();
            javaMail.sendMail("yassine.hkiri@esprit.tn", Object, Corps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateTable(ObservableList<Abonne> branlist){
       tablea.setItems(branlist);
       }
    @FXML
    private void search(KeyEvent event) {
        AbonneService bs=new AbonneService(); 
        Abonne b= new Abonne();
        ObservableList<Abonne>filter= bs.chercherTitreplat(id.getText());
        populateTable(filter);
    } 

    @FXML
    private void load(ActionEvent event) {
            Abonne p= tablea.getSelectionModel().getSelectedItem();
            NOMA.setText(p.getNom());
        PRENOMA.setText(p.getPrenom());
        AGEA.setText(p.getAge()+"");
        SEXEA.setText(p.getSexe()+"");
        MAILA.setText(p.getEmail()+"");
        ADRESSEA.setText(p.getAdresse()+"");
        MDPA.setText(p.getMdp());
        TELA.setText(p.getTel()+"");
       // ABOA.setValue(p.getAbonnement());
    }



  
    
    
    
    
    
    
    
    
    
    
    
}
