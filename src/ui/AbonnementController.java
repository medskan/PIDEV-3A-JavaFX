/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entities.Abonnement;
import entities.categorie;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.AbonnementServices;
import service.CategorieService;
import util.mydb;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AbonnementController implements Initializable {
    private  ObservableList<Abonnement> data ;
int  index= -1; 
    private TextField ida;
    @FXML
    private TextField typea;
    @FXML
    private TextField tarifa;
    @FXML
    private DatePicker dda;
     
    
    @FXML
    private Button ajout;
    @FXML
    private Button modif;
    @FXML
    private Button supp;
     
    private Label label;
 
    @FXML
    private DatePicker dfa;
   @FXML
    private TableView<Abonnement> table;
    @FXML
    private TableColumn<Abonnement, Integer> id;
    @FXML
    private TableColumn<Abonnement, String> type;
    @FXML
    private TableColumn<Abonnement, Double> tarif;
    @FXML
    private TableColumn<Abonnement, Date> dd;
    @FXML
    private TableColumn<Abonnement, Date> df;
 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Abonnement r = new Abonnement();
        
       AbonnementServices sp = new AbonnementServices();
       
        ObservableList<Abonnement>list = FXCollections.observableArrayList();
        //table_view.setItems(list);
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type_a"));
        tarif.setCellValueFactory(new PropertyValueFactory<>("tarif_a"));
        dd.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
       df.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
  
  
        list.addAll(sp.afficher());
        table.setItems(list);
    } 
   /* private void setCellTable(){
             
    ida.setCellValueFactory(new  PropertyValueFactory<>("id"));
    typea.setCellValueFactory(new  PropertyValueFactory<>("typeA"));
    tarifa.setCellValueFactory(new  PropertyValueFactory<>("tarifA"));
   dda.setCellValueFactory(new  PropertyValueFactory<>("dateDebut"));
    dfa.setCellValueFactory(new  PropertyValueFactory<>("dateFin"));
   
   
   // idppc.setCellValueFactory(new  PropertyValueFactory<>("id_Panniers"));
     
            
        
    }*/

    private void selected(MouseEvent event) {
   Abonnement evt = table.getSelectionModel().getSelectedItem();
    if (index<= -1)
        {return; } 
        typea.setText(evt.getTypeA());
       dda.setValue(evt.getDateDebut().toLocalDate());
      dfa.setValue(evt.getDateFin().toLocalDate());
        
        String a = Integer.toString(evt.getIdab());
        ida.setText(a);
        
    }
    
    private void loadDatafromDB (){
          try {
              String requete3 = "SELECT * FROM abonnement ";
              Statement st = new mydb().getConnection().createStatement();
              ResultSet rs =st.executeQuery(requete3);
              while(rs.next()) {
                  
                 data.add(new Abonnement( rs.getString(1), rs.getDouble(2), rs.getDate(3),
                         rs.getDate(4)));
          
              
              }
          } catch (SQLException ex) {
              Logger.getLogger(AbonnementController.class.getName()).log(Level.SEVERE, null, ex);   
          }
     table.setItems(data);
      }

    
    
    
    
    
    
    
    @FXML
    private void ajouter(ActionEvent event) {
         if(typea.getText().trim().isEmpty())
        {
              Alert fail= new Alert(Alert.AlertType.INFORMATION);
        fail.setHeaderText("failure");
        fail.setContentText("you havent typed something");
        fail.showAndWait();
        
            
        }
         else
        {

        
                  AbonnementServices ts = new AbonnementServices();
                   String type1 = typea.getText();
        Double tarif1 =Double.parseDouble(tarifa.getText());
      Date date1 = Date.valueOf(dda.getValue());
      Date date2 = Date.valueOf(dfa.getValue());
        System.out.print(dfa.getValue());
      Abonnement c1= new Abonnement(type1,tarif1,date1,date2);
       ts.AjouterAbonnement(c1);
                
              
        AbonnementServices ab=new  AbonnementServices();
       
        //table_view.setItems(list);
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type_a"));
        tarif.setCellValueFactory(new PropertyValueFactory<>("tarif_a"));
        dd.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
       df.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
  
   ObservableList<Abonnement>  ob1= FXCollections.observableArrayList();
        ob1.addAll(ab.afficher());
       table.setItems(ob1);
       System.out.println(""+ob1);
      
              Alert fail= new Alert(Alert.AlertType.INFORMATION);
        fail.setHeaderText("ajout avec succées");
        fail.setContentText("ajout terminé");
        fail.showAndWait();
        typea.clear();
        
        tarifa.clear();
             
        
    }
    }

          
 
       
        

     
    

    @FXML
    private void modifier(ActionEvent event) {
         ObservableList<Abonnement>items = FXCollections.observableArrayList();
        AbonnementServices ab=new AbonnementServices();
        
        
        Abonnement selected = table.getSelectionModel().getSelectedItem();
        Abonnement c = new Abonnement(selected.getTypeA(),selected.getTarifA(),selected.getDateDebut(),selected.getDateFin());
        
        int x = selected.getIdab();
          
        
        String type = typea.getText();
        Double tarif =Double.parseDouble(tarifa.getText());
        Date date1= Date.valueOf(dda.getValue());
        Date date2= Date.valueOf(dfa.getValue());
        
       Abonnement c1= new Abonnement(type,tarif,date1,date2);
        
       ab.modifier(c1,x);
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Abonnement modifiée");
        alert.showAndWait();
       
        items.addAll(ab.afficher());
        table.setItems(items);
        
        }
            
    

    @FXML
    private void supprimer(ActionEvent event) {
         Abonnement selected = table.getSelectionModel().getSelectedItem();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("voulez vous vraiment supprimer "+selected.getTypeA());
        
        Optional<ButtonType> option = alert.showAndWait();
 
        if (option.get() == ButtonType.OK) {
         AbonnementServices ab=new AbonnementServices();
         ab.SupprimerAbonnement(selected);
        table.getItems().clear();
        ObservableList<Abonnement>list= FXCollections.observableArrayList();
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type_a"));
        tarif.setCellValueFactory(new PropertyValueFactory<>("tarif_a"));
        dd.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
       df.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
  
  
        list.addAll(ab.afficher());
        table.setItems(list);
           Alert alert2=new Alert(Alert.AlertType.INFORMATION);
        alert2.setContentText("Abonnement supprime");
        alert2.showAndWait();
      } else if (option.get() == ButtonType.CANCEL) {
         this.label.setText("Cancelled!");
      }  
    }
   
    
    
    
    
    public ObservableList<Abonnement> show1()
    { 
       

           try {
               ObservableList<Abonnement> obl =FXCollections.observableArrayList();
                             Connection cnx = mydb.getinstance().getConnection();
 //exécution de la réquette et enregistrer le resultat dans le resultset
                  PreparedStatement pt= cnx.prepareStatement("SELECT id, type_a, tarif_a, date_debut,date_fin FROM abonnement ");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  //obl.add(new Note(rs.getFloat(1),rs.getFloat(2),rs.getFloat(3),rs.getInt(4),rs.getString(5)));
                 Abonnement ls = new Abonnement();
                 ls.setIdab(rs.getInt("id"));
                 ls.setTypeA(rs.getString("typeA"));
                 ls.setTarifA(rs.getDouble("tarifA"));
                 ls.setDateDebut(rs.getDate("dateDebut"));
                 ls.setDateFin(rs.getDate("dateFin"));
                           
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
        
           
                      AbonnementServices ab=new AbonnementServices();   
      id.setCellValueFactory(new PropertyValueFactory<>("id"));
      type.setCellValueFactory(new PropertyValueFactory<>("type_a"));
      tarif.setCellValueFactory(new PropertyValueFactory<>("tarif_a"));
      dd.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
      df.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
     
       // AbonnementServices sp = new AbonnementServices();
        //List<Abonnement> Abonnement = sp.afficher();
        ObservableList<Abonnement>  ob1= FXCollections.observableArrayList();
        ob1.addAll(ab.afficher());
       table.setItems(ob1);
       System.out.println(""+ob1);
                      
    }

    @FXML
    private void load(ActionEvent event) {
         Abonnement selected = table.getSelectionModel().getSelectedItem();
        typea.setText(selected.getTypeA());
        tarifa.setText(selected.getTarifA()+"");
        
             
    }
    
}
