/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.categorie;
import entities.produit;
import entities.produit_like;

import javafx.scene.input.MouseEvent;
import javafx.scene.image.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import service.CSService;
import service.CategorieService;
import service.OrderService;
import service.ProduitService;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ProduitController implements Initializable {

    @FXML
    private TextField nomP;
    @FXML
    private TextField imgP;
    @FXML
    private TextField desP;
    @FXML
    private ChoiceBox<categorie> catP;
    @FXML
    private TextField quP;
    @FXML
    private TextField puP;
    @FXML
    private TextField proP;
    @FXML
    private TableView<produit> AfficherP;
    @FXML
    private TableColumn<produit, Integer> Pid;
    @FXML
    private TableColumn<produit, String> Pnom;
    @FXML
    private TableColumn<produit, String> Pcat;
    @FXML
    private TableColumn<produit, Integer> Pq;
    @FXML
    private TableColumn<produit, String> Pdes;
    @FXML
    private TableColumn<produit, Float> Ppu;
    @FXML
    private TableColumn<produit, Float> Ppro;
    @FXML
    private TableColumn<produit, Image> Pimg;
    private CategorieService CategorieService;
    private ProduitService ProduitService;
     
    ObservableList<categorie> catList =FXCollections.observableArrayList();
    @FXML
    private TextField rech;
    Connection cnx  ;
    public ImageIcon format =null;
int s=0;
byte[] photo=null;
    private Label label;
    @FXML
    private TableColumn <produit, Integer>  like;
    @FXML
    private FontAwesomeIconView order;
    @FXML
    private Button sort;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         this.ProduitService = new ProduitService();
        this.CategorieService = new CategorieService();
        catList.addAll(CategorieService.recuperer());
        catP.setItems(catList);
        
        StringConverter<categorie> converter = new StringConverter<categorie>() {
         

            @Override
            public categorie fromString(String string) {
                return null;
            }

             @Override
             public String toString(categorie object) {
                 return object.getNom_c();
             }
        };

        catP.setConverter(converter);
        
        
        
      ObservableList<produit>items = FXCollections.observableArrayList();
         this.CategorieService= new CategorieService();
         this.ProduitService= new ProduitService();
         
        Pid.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        Pnom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
         Pdes.setCellValueFactory(new PropertyValueFactory<>("description")); 
         Pq.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                  Ppu.setCellValueFactory(new PropertyValueFactory<>("prix_produit")); 
         Ppro.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        
       Pcat.setCellValueFactory((TableColumn.CellDataFeatures<produit, String> param) -> new SimpleStringProperty(param.getValue().getCategorie().getNom_c()));
           Pimg.setCellFactory((TableColumn<produit, javafx.scene.image.Image> param)->{
               final ImageView imageview=new ImageView();
           imageview.setFitHeight(70);
           imageview.setFitWidth(100);
           
           TableCell<produit,javafx.scene.image.Image>cell;
             cell = new TableCell<produit,javafx.scene.image.Image>(){
                 
                 public void updateItem(javafx.scene.image.Image item,boolean empty){
                     if (item !=null)
                     {
                         imageview.setImage(item);
                         
                     }
                     
                     
                     
                 }
             };
                   cell.setGraphic(imageview);
                   return cell;
           
           });
         Pimg.setCellValueFactory(new PropertyValueFactory<>("img"));
          like.setCellValueFactory(new PropertyValueFactory<>("nl"));
         
        items.addAll(ProduitService.recuperer());
        AfficherP.setItems(items);
       
        
        if (ProduitService.recupererQ()!=null){ProduitService.StockNotification();}
    }    
   


    @FXML
    private void AddP(ActionEvent event) {
            
    CSService css=new CSService();
          if((catP.getSelectionModel().getSelectedItem()==null)||(!css.ControleStr1(desP.getText()))||(!css.ControleStr1(desP.getText()))||(!css.ControleStr1(nomP.getText()))||(!css.ControleStr1(puP.getText()))||(!css.ControleStr1(proP.getText()))||(!css.ControleStr1(quP.getText())))
          { Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setContentText("veuillez remplir les champs obligatoire");
        alert.showAndWait();}
          else if(!css.Controlenumpositif(puP.getText()))
              { Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setContentText("le prix doit etre un nombre positif");
        alert.showAndWait();}
           else if(!css.Controlenumpositif(quP.getText()))
              { Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setContentText("la quantite disponible doit etre un nombre positif");
        alert.showAndWait();}
           else if(!css.Controlenumpositif(proP.getText()))
              { Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setContentText("la promotion doit etre un nombre positif");
        alert.showAndWait();}
           else{

        ProduitService ps =new ProduitService(); 
        produit p=new produit();
        p.setCategorie(catP.getSelectionModel().getSelectedItem());
        p.setDescription(desP.getText());
        p.setImage(imgP.getText());
        p.setNom_produit(nomP.getText());
        p.setPrix_produit(Float.parseFloat(puP.getText()));
        p.setPromotion(Float.parseFloat(proP.getText()));
        p.setQuantite(Integer.parseInt(quP.getText()));
       if(css.Produitexiste(p)){
              ps.ajouter(p);
      desP.setText("");
                nomP.setText("");
                quP.setText("");
                        puP.setText("");
                        proP.setText("");
                                imgP.setText("");
                                catP.setValue(null);
          Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("produit ajoutée");
        
        alert.showAndWait();
           }else{  
                   Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("produit existe");
        alert.showAndWait();}
    }}

    @FXML
    private void EditP(ActionEvent event) {
           CSService css=new CSService();
          if((catP.getSelectionModel().getSelectedItem()==null)||(!css.ControleStr1(desP.getText()))||(!css.ControleStr1(desP.getText()))||(!css.ControleStr1(nomP.getText()))||(!css.ControleStr1(puP.getText()))||(!css.ControleStr1(proP.getText()))||(!css.ControleStr1(quP.getText())))
          { Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setContentText("veuillez remplir les champs obligatoire");
        alert.showAndWait();}
          else if(!css.Controlenumpositif(puP.getText()))
              { Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setContentText("le prix doit etre un nombre positif");
        alert.showAndWait();}
           else if(!css.Controlenumpositif(quP.getText()))
              { Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setContentText("la quantite disponible doit etre un nombre positif");
        alert.showAndWait();}
           else if(!css.Controlenumpositif(proP.getText()))
              { Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setContentText("la promotion doit etre un nombre positif");
        alert.showAndWait();}
           else{
          ObservableList<produit> items = FXCollections.observableArrayList();
        
        
        this.CategorieService= new CategorieService();
        this.ProduitService=new ProduitService();
         ProduitService ps = new ProduitService();
        produit selected = AfficherP.getSelectionModel().getSelectedItem();
        produit p = new produit(selected.getId_produit(), selected.getDescription(),selected.getNom_produit(),selected.getQuantite(),selected.getPrix_produit(),selected.getPromotion(),selected.getCategorie(),selected.getImage());
        
       
      
    String description=desP.getText();
    String nom_produit=nomP.getText();
     int quantite=Integer.parseInt(quP.getText()) ;
    float prix_produit=Float.parseFloat(puP.getText()) ;
     float promotion=Float.parseFloat(proP.getText()) ;
     
    String image=imgP.getText();
  categorie categorie=catP.getSelectionModel().getSelectedItem();
        produit p1 = new produit(description,nom_produit,quantite,prix_produit,promotion,categorie,image);
        int x = ProduitService.getid_produit_db(p);
       
        ps.modifier(x,p1);
       AfficherP.getItems().clear();
         Pid.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        Pnom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
         Pdes.setCellValueFactory(new PropertyValueFactory<>("description")); 
         Pq.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                  Ppu.setCellValueFactory(new PropertyValueFactory<>("prix_produit")); 
         Ppro.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        
       Pcat.setCellValueFactory((TableColumn.CellDataFeatures<produit, String> param) -> new SimpleStringProperty(param.getValue().getCategorie().getNom_c()));
           Pimg.setCellFactory((TableColumn<produit, Image> param)->{
               final ImageView imageview=new ImageView();
           imageview.setFitHeight(70);
           imageview.setFitWidth(100);
           
           TableCell<produit,Image>cell;
             cell = new TableCell<produit,Image>(){
                 
                 public void updateItem(Image item,boolean empty){
                     if (item !=null)
                     {
                         imageview.setImage(item);
                         
                     }
                     
                     
                     
                 }
             };
                   cell.setGraphic(imageview);
                   return cell;
           
           });
         Pimg.setCellValueFactory(new PropertyValueFactory<>("img"));
        items.addAll(ProduitService.recuperer());
        AfficherP.setItems(items);
        desP.setText("");
                nomP.setText("");
                quP.setText("");
                        puP.setText("");
                        proP.setText("");
                                imgP.setText("");
                                catP.setValue(null);
           Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("produit modifié");
        alert.showAndWait();
           }
    }

    @FXML
    private void DeleteP(ActionEvent event) {
         produit selected = AfficherP.getSelectionModel().getSelectedItem();
         Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("voulez vous vraiment supprimer "+selected.getNom_produit());
        
        Optional<ButtonType> option = alert.showAndWait();
 
        if (option.get() == ButtonType.OK) {
      ProduitService.supprimer(selected);
        AfficherP.getItems().clear();
          this.ProduitService = new ProduitService();
        this.CategorieService = new CategorieService();
        catList.addAll(CategorieService.recuperer());
        catP.setItems(catList);
        
        StringConverter<categorie> converter = new StringConverter<categorie>() {
         

            @Override
            public categorie fromString(String string) {
                return null;
            }

             @Override
             public String toString(categorie object) {
                 return object.getNom_c();
             }
        };

        catP.setConverter(converter);
        
        
        
      ObservableList<produit>items = FXCollections.observableArrayList();
         this.CategorieService= new CategorieService();
         this.ProduitService= new ProduitService();
         
        Pid.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        Pnom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
         Pdes.setCellValueFactory(new PropertyValueFactory<>("description")); 
         Pq.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                  Ppu.setCellValueFactory(new PropertyValueFactory<>("prix_produit")); 
         Ppro.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        
       Pcat.setCellValueFactory((TableColumn.CellDataFeatures<produit, String> param) -> new SimpleStringProperty(param.getValue().getCategorie().getNom_c()));
           Pimg.setCellFactory((TableColumn<produit, Image> param)->{
               final ImageView imageview=new ImageView();
           imageview.setFitHeight(70);
           imageview.setFitWidth(100);
           
           TableCell<produit,Image>cell;
             cell = new TableCell<produit,Image>(){
                 
                 public void updateItem(Image item,boolean empty){
                     if (item !=null)
                     {
                         imageview.setImage(item);
                         
                     }
                     
                     
                     
                 }
             };
                   cell.setGraphic(imageview);
                   return cell;
           
           });
         Pimg.setCellValueFactory(new PropertyValueFactory<>("img"));
        items.addAll(ProduitService.recuperer());
        AfficherP.setItems(items);
       
     
           Alert alert2=new Alert(Alert.AlertType.INFORMATION);
        alert2.setContentText(" produit supprime");
        alert2.showAndWait();
      } else if (option.get() == ButtonType.CANCEL) {
         this.label.setText("Cancelled!");
      }  
   }
       
    

    @FXML
    private void LoadP(ActionEvent event) {
         produit p= AfficherP.getSelectionModel().getSelectedItem();
        nomP.setText(p.getNom_produit());
        desP.setText(p.getDescription());
        quP.setText(p.getQuantite()+"");
        puP.setText(p.getPrix_produit()+"");
        proP.setText(p.getPromotion()+"");
        imgP.setText(p.getImage());
        catP.setValue(p.getCategorie());
        
    }

    @FXML
    private void ShowP(ActionEvent event) {
           this.ProduitService = new ProduitService();
        this.CategorieService = new CategorieService();
        catList.addAll(CategorieService.recuperer());
        catP.setItems(catList);
        
        StringConverter<categorie> converter = new StringConverter<categorie>() {
         

            @Override
            public categorie fromString(String string) {
                return null;
            }

             @Override
             public String toString(categorie object) {
                 return object.getNom_c();
             }
        };

        catP.setConverter(converter);
        
        
        
      ObservableList<produit>items = FXCollections.observableArrayList();
         this.CategorieService= new CategorieService();
         this.ProduitService= new ProduitService();
         
        Pid.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        Pnom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
         Pdes.setCellValueFactory(new PropertyValueFactory<>("description")); 
         Pq.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                  Ppu.setCellValueFactory(new PropertyValueFactory<>("prix_produit")); 
         Ppro.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        
       Pcat.setCellValueFactory((TableColumn.CellDataFeatures<produit, String> param) -> new SimpleStringProperty(param.getValue().getCategorie().getNom_c()));
           Pimg.setCellFactory((TableColumn<produit, Image> param)->{
               final ImageView imageview=new ImageView();
           imageview.setFitHeight(70);
           imageview.setFitWidth(100);
           
           TableCell<produit,Image>cell;
             cell = new TableCell<produit,Image>(){
                 
                 public void updateItem(Image item,boolean empty){
                     if (item !=null)
                     {
                         imageview.setImage(item);
                         
                     }
                     
                     
                     
                 }
             };
                   cell.setGraphic(imageview);
                   return cell;
           
           });
         Pimg.setCellValueFactory(new PropertyValueFactory<>("img"));
        items.addAll(ProduitService.recuperer());
        AfficherP.setItems(items);
       
        
    
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
         this.ProduitService = new ProduitService();
        this.CategorieService = new CategorieService();
        catList.addAll(CategorieService.recuperer());
        catP.setItems(catList);
        
        StringConverter<categorie> converter = new StringConverter<categorie>() {
         

            @Override
            public categorie fromString(String string) {
                return null;
            }

             @Override
             public String toString(categorie object) {
                 return object.getNom_c();
             }
        };

        catP.setConverter(converter);
        
        
        
      ObservableList<produit>items = FXCollections.observableArrayList();
         this.CategorieService= new CategorieService();
         this.ProduitService= new ProduitService();
         
        Pid.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        Pnom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
         Pdes.setCellValueFactory(new PropertyValueFactory<>("description")); 
         Pq.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                  Ppu.setCellValueFactory(new PropertyValueFactory<>("prix_produit")); 
         Ppro.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        
       Pcat.setCellValueFactory((TableColumn.CellDataFeatures<produit, String> param) -> new SimpleStringProperty(param.getValue().getCategorie().getNom_c()));
           Pimg.setCellFactory((TableColumn<produit, javafx.scene.image.Image> param)->{
               final ImageView imageview=new ImageView();
           imageview.setFitHeight(70);
           imageview.setFitWidth(100);
           
           TableCell<produit,javafx.scene.image.Image>cell;
             cell = new TableCell<produit,javafx.scene.image.Image>(){
                 
                 public void updateItem(javafx.scene.image.Image item,boolean empty){
                     if (item !=null)
                     {
                         imageview.setImage(item);
                         
                     }
                     
                     
                     
                 }
             };
                   cell.setGraphic(imageview);
                   return cell;
           
           });
         Pimg.setCellValueFactory(new PropertyValueFactory<>("img"));
            like.setCellValueFactory(new PropertyValueFactory<>("nl"));
        items.addAll(ProduitService.Rechercher(rech.getText()));
        AfficherP.setItems(items);
    }

    @FXML
    private void Bimg(ActionEvent event) {
   
  ProduitService.filen();
  
    String vpath=ProduitService.getpath();
    
     if(vpath==null)
     {
    
    }else{imgP.setText(vpath);}
    try{
        File image=new File(vpath);
        FileInputStream fs=new FileInputStream(image);
        ByteArrayOutputStream bs=new ByteArrayOutputStream();
        byte[] b =new byte[1024];
       for(int re;(re=fs.read(b))!=-1;){bs.write(b,0,re);
       
       }
       photo=bs.toByteArray();
       
    }
    catch(Exception e){
        e.printStackTrace();    }
    }
    
    
  

    private boolean controleDeSaisie() {
        return false;
    }

    @FXML
    private void chart(ActionEvent event) throws IOException {
            Parent root = null;
         Stage primaryStage = new Stage();
           root = FXMLLoader.load(getClass().getResource("/ui/chart.fxml"));
              primaryStage.setTitle("Statistique de vente");
       
       Scene scene = new Scene(root,900,600);

       primaryStage.setScene(scene);

       primaryStage.show(); 
   
        
               }

    @FXML
    private void sort(ActionEvent event) {
         this.ProduitService = new ProduitService();
        this.CategorieService = new CategorieService();
        catList.addAll(CategorieService.recuperer());
        catP.setItems(catList);
        
        StringConverter<categorie> converter = new StringConverter<categorie>() {
         

            @Override
            public categorie fromString(String string) {
                return null;
            }

             @Override
             public String toString(categorie object) {
                 return object.getNom_c();
             }
        };

        catP.setConverter(converter);
        
        
        
      ObservableList<produit>items = FXCollections.observableArrayList();
         this.CategorieService= new CategorieService();
         this.ProduitService= new ProduitService();
         
        Pid.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        Pnom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
         Pdes.setCellValueFactory(new PropertyValueFactory<>("description")); 
         Pq.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                  Ppu.setCellValueFactory(new PropertyValueFactory<>("prix_produit")); 
         Ppro.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        
       Pcat.setCellValueFactory((TableColumn.CellDataFeatures<produit, String> param) -> new SimpleStringProperty(param.getValue().getCategorie().getNom_c()));
           Pimg.setCellFactory((TableColumn<produit, Image> param)->{
               final ImageView imageview=new ImageView();
           imageview.setFitHeight(70);
           imageview.setFitWidth(100);
           
           TableCell<produit,Image>cell;
             cell = new TableCell<produit,Image>(){
                 
                 public void updateItem(Image item,boolean empty){
                     if (item !=null)
                     {
                         imageview.setImage(item);
                         
                     }
                     
                     
                     
                 }
             };
                   cell.setGraphic(imageview);
                   return cell;
           
           });
         Pimg.setCellValueFactory(new PropertyValueFactory<>("img"));
        items.addAll(ProduitService.recupererTrier());
        AfficherP.setItems(items);
       
        
    
    }

}
