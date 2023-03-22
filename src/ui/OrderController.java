/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import entities.order;
import entities.orderdetail;
import entities.produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.util.StringConverter;
import service.CategorieService;
import service.OrderService;
import service.ProduitService;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class OrderController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TableColumn<order, Integer> Cid;
    @FXML
    private TableView<order> AfficherO;
    @FXML
    private TableColumn<order,Integer> num;
    @FXML
    private TableColumn<order, Date> date;
    @FXML
    private TableColumn<order, Integer> id_c;
    @FXML
    private TableColumn<order, Float> total;
    private OrderService OrderService;
    @FXML
    private TextField rech;
    @FXML
    private TableColumn<orderdetail,Integer> id;
    @FXML
    private TableColumn<orderdetail,String> produit;
    @FXML
    private TableColumn<orderdetail,Integer> quantite;
    @FXML
    private TableColumn<orderdetail,Float> prix;
    @FXML
    private TextField num1;
    private ProduitService ProduitService;
    @FXML
    private TableView<orderdetail> AfficherD;
   
   
  

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<order>items = FXCollections.observableArrayList();
        this.OrderService= new OrderService();
         Cid.setCellValueFactory(new PropertyValueFactory<>("id"));
         num.setCellValueFactory(new PropertyValueFactory<>("numero_commande"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));
          id_c.setCellValueFactory(new PropertyValueFactory<>("id_c"));
           total.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        items.addAll(OrderService.recuperer());
       AfficherO.setItems(items);
    }    

    @FXML
    private void ShowC(ActionEvent event) {
          ObservableList<order>items = FXCollections.observableArrayList();
        this.OrderService= new OrderService();
         Cid.setCellValueFactory(new PropertyValueFactory<>("id"));
         num.setCellValueFactory(new PropertyValueFactory<>("numero_commande"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));
          id_c.setCellValueFactory(new PropertyValueFactory<>("id_c"));
           total.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        items.addAll(OrderService.recuperer());
       AfficherO.setItems(items);
    }

    @FXML
    private void DeleteC(ActionEvent event) {
        OrderService OrderService=new OrderService();
         order selected = AfficherO.getSelectionModel().getSelectedItem();
   
          Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("voulez vous vraiment supprimer commande n° "+selected.getNumero_commande());
        
        Optional<ButtonType> option = alert.showAndWait();
 
        if (option.get() == ButtonType.OK) {
         
        OrderService.supprimer(selected);
        AfficherO.getItems().clear();
        ObservableList<order>items = FXCollections.observableArrayList();
        this.OrderService= new OrderService();
         Cid.setCellValueFactory(new PropertyValueFactory<>("id"));
         num.setCellValueFactory(new PropertyValueFactory<>("numero_commande"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));
          id_c.setCellValueFactory(new PropertyValueFactory<>("id_c"));
           total.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        items.addAll(OrderService.recuperer());
        AfficherO.setItems(items);
      Alert alert2=new Alert(Alert.AlertType.INFORMATION);
        alert2.setContentText("commande supprime");
        alert2.showAndWait();
      } else if (option.get() == ButtonType.CANCEL) {
         this.label.setText("Cancelled!");
         
      }  
   }

    private void back(ActionEvent event) {
              Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ui/back.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void search(ActionEvent event) {
         OrderService OrderService=new OrderService();
        
        AfficherO.getItems().clear();
        ObservableList<order>items = FXCollections.observableArrayList();
        this.OrderService= new OrderService();
         Cid.setCellValueFactory(new PropertyValueFactory<>("id"));
         num.setCellValueFactory(new PropertyValueFactory<>("numero_commande"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));
          id_c.setCellValueFactory(new PropertyValueFactory<>("id_c"));
           total.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        items.addAll(OrderService.Rechercher(rech.getText()));
        AfficherO.setItems(items);
    }

    @FXML
    private void ShowOD(ActionEvent event) {
         StringConverter<produit> converter = new StringConverter<produit>() {
         
            @Override
            public produit fromString(String string) {
                return null;
            }

             @Override
             public String toString(produit object) {
                 return object.getNom_produit();
             }
        };
            
        
              ObservableList<orderdetail>items = FXCollections.observableArrayList();
              OrderService os=new OrderService();
               order selected = AfficherO.getSelectionModel().getSelectedItem();
               num1.setText(selected.getNumero_commande()+"");
         this.ProduitService= new ProduitService();
         
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
         quantite.setCellValueFactory(new PropertyValueFactory<>("quantity")); 
         prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
       produit.setCellValueFactory((TableColumn.CellDataFeatures<orderdetail, String> param) -> new SimpleStringProperty(param.getValue().getProduit().getNom_produit()));
        items.addAll(OrderService.recupererOrderDetails(selected));
        AfficherD.setItems(items); 
        
      System.out.println(items); 
    }
    
}
