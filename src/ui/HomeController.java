/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lamis
 */
public class HomeController implements Initializable {

    @FXML
    private Button ss;
    @FXML
    private Button per;
    @FXML
    private Button permiss;
    @FXML
    private Button sendmail;
    @FXML
    private Button four;
    @FXML
    private Button llllllivv;
    @FXML
    private Button eqqqqqq;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gestionsalleeee(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("/Gui/Salle.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void perso(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/personnel.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void permission(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/Gui/Permissionn.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void sendmail(ActionEvent event)  throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/Gui/Mailing.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void fournisssssssss(ActionEvent event) throws IOException {
        
           Parent root = FXMLLoader.load(getClass().getResource("/Gui/fournss.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void livraisonnnnnnn(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/Livraison.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void equipementtttttttt(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/equipement.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

  

  
}
