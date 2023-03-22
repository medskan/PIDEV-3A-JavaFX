/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entities.categorie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.CategorieService;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CategorieClientController implements Initializable {

    @FXML
    private Label label;
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
    private void ShowC(ActionEvent event) {
           ObservableList<categorie>items = FXCollections.observableArrayList();
         this.CategorieService= new CategorieService();
        Cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Cnom.setCellValueFactory(new PropertyValueFactory<>("nom_c"));
         Ctype.setCellValueFactory(new PropertyValueFactory<>("type"));
        
         
        items.addAll(CategorieService.recuperer());
        AfficherC.setItems(items);
    }

    private void back(ActionEvent event) {
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ui/Front.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
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
