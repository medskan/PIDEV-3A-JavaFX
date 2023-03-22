/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import pijava.FXMLDocumentController;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class BackController implements Initializable {

    @FXML
    private Pane secpane;
    @FXML
    private ImageView logo;
 ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            InputStream stream = new FileInputStream("C:/Users/PC/Downloads/logo.png");
            Image image = new Image(stream);
             logo.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Gcat(ActionEvent event) {
             try {
                  secpane.getChildren().clear();
         Pane newLoadedPane=FXMLLoader.load(getClass().getResource("/ui/Categorie.fxml"));
  secpane.getChildren().add(newLoadedPane) ;
          } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);}
       
    }

    @FXML
    private void Gpro(ActionEvent event) {
            try {
                 secpane.getChildren().clear();
         Pane newLoadedPane=FXMLLoader.load(getClass().getResource("/ui/Produit.fxml"));
  secpane.getChildren().add(newLoadedPane) ;
          } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);}
       
    }

    @FXML
    private void Gord(ActionEvent event) {
              try {
              secpane.getChildren().clear();
         Pane newLoadedPane=FXMLLoader.load(getClass().getResource("/ui/Order.fxml"));
  secpane.getChildren().add(newLoadedPane) ;
          } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);}
       
    }

    @FXML
    private void back(ActionEvent event) {
          Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/pijava/FXMLDocument.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    
    }

    @FXML
    private void Gper(ActionEvent event) {
         try {
              secpane.getChildren().clear();
         Pane newLoadedPane;
             newLoadedPane = FXMLLoader.load(getClass().getResource("/ui/personnel.fxml"));
  secpane.getChildren().add(newLoadedPane) ;
          } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);}
       
        
    }

    @FXML
    private void Gabo(ActionEvent event) {
          try {
              secpane.getChildren().clear();
         Pane newLoadedPane=FXMLLoader.load(getClass().getResource("/ui/Abonne.fxml"));
  secpane.getChildren().add(newLoadedPane) ;
          } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);}
       
        
    }

    @FXML
    private void Gab(ActionEvent event) {
          try {
              secpane.getChildren().clear();
         Pane newLoadedPane=FXMLLoader.load(getClass().getResource("/ui/Abonnement.fxml"));
  secpane.getChildren().add(newLoadedPane) ;
          } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);}
       
        
    }

    @FXML
    private void Gequi(ActionEvent event) {
          try {
              secpane.getChildren().clear();
         Pane newLoadedPane=FXMLLoader.load(getClass().getResource("/ui/equipement.fxml"));
  secpane.getChildren().add(newLoadedPane) ;
          } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);}
       
        
    }

    @FXML
    private void Gfou(ActionEvent event) {
          try {
              secpane.getChildren().clear();
         Pane newLoadedPane=FXMLLoader.load(getClass().getResource("/ui/fournss.fxml"));
  secpane.getChildren().add(newLoadedPane) ;
          } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);}
       
        
    }

    @FXML
    private void Gliv(ActionEvent event) {
          try {
              secpane.getChildren().clear();
         Pane newLoadedPane=FXMLLoader.load(getClass().getResource("/ui/Livraison.fxml"));
  secpane.getChildren().add(newLoadedPane) ;
          } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);}
       
        
    }

    @FXML
    private void Gsal(ActionEvent event) {
          try {
              secpane.getChildren().clear();
         Pane newLoadedPane=FXMLLoader.load(getClass().getResource("/ui/Salle.fxml"));
  secpane.getChildren().add(newLoadedPane) ;
          } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);}
       
        
    }

    @FXML
    private void Gmail(ActionEvent event) {
          try {
              secpane.getChildren().clear();
         Pane newLoadedPane=FXMLLoader.load(getClass().getResource("/ui/Mailing.fxml"));
  secpane.getChildren().add(newLoadedPane) ;
          } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);}
       
        
    }

    @FXML
    private void GP(ActionEvent event) {
          try {
              secpane.getChildren().clear();
         Pane newLoadedPane=FXMLLoader.load(getClass().getResource("/ui/Permissionn.fxml"));
  secpane.getChildren().add(newLoadedPane) ;
          } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);}
       
        
    }

    @FXML
    private void Guser(ActionEvent event) {
         try {
              secpane.getChildren().clear();
         Pane newLoadedPane=FXMLLoader.load(getClass().getResource("/ui/AdminUser.fxml"));
  secpane.getChildren().add(newLoadedPane) ;
          } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);}
       
    }
    
    
    
}
