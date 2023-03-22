/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entities.categorie;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.CSService;
import service.CategorieService;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CategorieController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField nomC;
    @FXML
    private TextField typeC;
    @FXML
    private TableView<categorie> AfficherC;
    private CategorieService CategorieService;
    @FXML
    private TableColumn<categorie,Integer> Cid;
    @FXML
    private TableColumn<categorie,String> Cnom;
    @FXML
    private TableColumn<categorie,String> Ctype;
    @FXML
    private TextField rech;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<categorie>items = FXCollections.observableArrayList();
         this.CategorieService= new CategorieService();
        Cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Cnom.setCellValueFactory(new PropertyValueFactory<>("nom_c"));
         Ctype.setCellValueFactory(new PropertyValueFactory<>("type"));
        
         
        items.addAll(CategorieService.recuperer());
        AfficherC.setItems(items);
    }    
   @FXML
    private void AddC(ActionEvent event) {
        CategorieService cp =new CategorieService(); 
        CSService css=new CSService();
        categorie c=new categorie();
        c.setNom_c(nomC.getText());
        c.setType(typeC.getText());
        if((!css.ControleStr1(nomC.getText()))||(!css.ControleStr1(typeC.getText()))){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setContentText("veuillez remplir les champs obligatoire");
        alert.showAndWait();
        }
        else if (!css.ControleNotNum(typeC.getText())){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setContentText("le champs du type ne doit pas contenir des nombres");
        alert.showAndWait();
        }else{
            if(css.catexiste(c)){
        cp.ajouter(c);
        nomC.setText(""); 
        typeC.setText("");
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("categorie ajouté");
        alert.showAndWait();}
            else{
                   Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("categorie existe");
        alert.showAndWait();
            }}
    }

    @FXML
    private void ShowC(ActionEvent event) {
            ObservableList<categorie>items = FXCollections.observableArrayList();
        this.CategorieService= new CategorieService();
         Cnom.setCellValueFactory(new PropertyValueFactory<>("nom_c"));
         Ctype.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        items.addAll(CategorieService.recuperer());
        AfficherC.setItems(items);
    }
    @FXML
    private void EditC(ActionEvent event) {
          ObservableList<categorie>items = FXCollections.observableArrayList();
        this.CategorieService= new CategorieService();
        
        
        categorie selected = AfficherC.getSelectionModel().getSelectedItem();
        categorie c = new categorie(selected.getNom_c(),selected.getType());
        CategorieService cs = new CategorieService();
        int x = cs.getid_categorie_db(c);
          CSService css=new CSService();
        
        String nom_c = nomC.getText();
        String type = typeC.getText();
        
        categorie c1= new categorie(nom_c, type);
         if((!css.ControleStr1(nomC.getText()))||(!css.ControleStr1(typeC.getText()))){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setContentText("veuillez remplir les champs obligatoire");
        alert.showAndWait();
        }
        else if (!css.ControleNotNum(typeC.getText())){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setContentText("le champs du type ne doit pas contenir des nombres");
        alert.showAndWait();
        }else{
        
        cs.modifier( x,c1);
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("categorie modifiée");
        alert.showAndWait();
        items.addAll(CategorieService.recuperer());
        AfficherC.setItems(items);
        
        nomC.setText(""); 
        typeC.setText("");}
        
        
    }

    @FXML
    private void DeleteC(ActionEvent event) {
      categorie selected = AfficherC.getSelectionModel().getSelectedItem();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("voulez vous vraiment supprimer "+selected.getNom_c());
        
        Optional<ButtonType> option = alert.showAndWait();
 
        if (option.get() == ButtonType.OK) {
         CategorieService.supprimer(selected);
        AfficherC.getItems().clear();
        ObservableList<categorie>items = FXCollections.observableArrayList();
        this.CategorieService= new CategorieService();
         Cnom.setCellValueFactory(new PropertyValueFactory<>("nom_c"));
         Ctype.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        items.addAll(CategorieService.recuperer());
        AfficherC.setItems(items);
           Alert alert2=new Alert(Alert.AlertType.INFORMATION);
        alert2.setContentText("categorie supprime");
        alert2.showAndWait();
      } else if (option.get() == ButtonType.CANCEL) {
         this.label.setText("Cancelled!");
      }  
   }
        
         
    

    @FXML
    private void loadC(ActionEvent event) {
        categorie c= AfficherC.getSelectionModel().getSelectedItem();
        nomC.setText(c.getNom_c()); 
        typeC.setText(c.getType());
    }

    private void back(ActionEvent event) {
           Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ui/back.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    
    }

    @FXML
    private void search(ActionEvent event) {
          ObservableList<categorie>items = FXCollections.observableArrayList();
         this.CategorieService= new CategorieService();
         AfficherC.getItems().clear();
        Cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Cnom.setCellValueFactory(new PropertyValueFactory<>("nom_c"));
         Ctype.setCellValueFactory(new PropertyValueFactory<>("type"));
        
         
        items.addAll(CategorieService.Rechercher(rech.getText()));
        AfficherC.setItems(items);
       
    }
    
}
