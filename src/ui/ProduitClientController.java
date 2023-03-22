 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entities.categorie;
import entities.produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javax.mail.MessagingException;
import service.CategorieService;
import service.OrderService;
import service.ProduitService;



/**
 * FXML Controller class
 *
 * @author PC
 */
public class ProduitClientController implements Initializable {

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
    private TableView<Map> AfficherP1;
    @FXML
    private TableColumn<produit, Integer>Pid1;
    @FXML
    private TableColumn<produit,String> Pnom1;
    @FXML
    private TableColumn<produit,String> Pcat1;
    @FXML
    private TableColumn<produit,Integer> Pq1;
    @FXML
    private TableColumn<produit, Float> Ppu1;
    @FXML
    private TableColumn<produit, Float> Ppro1;
    @FXML
    private TextField total;
    ObservableList<Map> data = FXCollections.observableArrayList();
    HashMap<produit,Integer>panier=new HashMap();
    @FXML
    private TextField rech;
    @FXML
    private ImageView image;
    @FXML
    private TextArea info;
   
 

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
        items.addAll(ProduitService.recuperer());
        AfficherP.setItems(items);
       
        
        
          Pid1.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
          Pnom1.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
          Ppu1.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
          Ppro1.setCellValueFactory(new PropertyValueFactory<>("promotion"));
          Pq1.setCellValueFactory(new PropertyValueFactory<>("quantite"));
          Pcat1.setCellValueFactory((TableColumn.CellDataFeatures<produit, String> param) -> new SimpleStringProperty(param.getValue().getCategorie().getNom_c()));
        
        data.addAll(ProduitService.RecupererPanier(panier));
        AfficherP1.setItems(data);
          
        
        total.setText("Votre total est "+ProduitService.calculerTotal(panier)+"dt"); 
        addButtonToTable();
         
    }
private void addButtonToTable() {
        TableColumn<produit, Void> colBtn = new TableColumn("");

        Callback<TableColumn<produit, Void>, TableCell<produit, Void>> cellFactory;
        cellFactory = (final TableColumn<produit, Void> param) -> {
            final TableCell<produit, Void> cell;
            cell = new TableCell<produit, Void>() {
                
                private final Button btn = new Button("J'aime");
                
                {  // int i;
//for(i=0;i<ProduitService.recuperer().size();i++){
                  //  int likesnb;
                  //  likesnb = ProduitService.likesnb(getTableView().getItems().get(getIndex()));

                    //btn.setText(likesnb+"J'aime");}
                    btn.setOnAction((ActionEvent event) -> {
                        
                        produit p = getTableView().getItems().get(getIndex());
                        btn.setText(  ProduitService.likesnb(p)+"J'aime");
                        if (ProduitService.verifL(p, 2)==false){
                           
                            ProduitService.DeleteLike(p, 2);
                            btn.setText(  ProduitService.likesnb(p)+"J'aime");}
                        else{  ProduitService.AddLike(p, 2);
                        btn.setText(  ProduitService.likesnb(p)+"J'aime");
                        
                        }
                    });
                }
                
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        };

        colBtn.setCellFactory(cellFactory);

        AfficherP.getColumns().add(colBtn);
         

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
    private void AddCart(ActionEvent event) {
        
        ProduitService ps=new ProduitService();
       produit p=(produit) AfficherP.getSelectionModel().getSelectedItem();
       ps.AddToCart(panier, p);
       AfficherP1.getItems().clear();
           Pid1.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
          Pnom1.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
          Ppu1.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
          Ppro1.setCellValueFactory(new PropertyValueFactory<>("promotion"));
          Pq1.setCellValueFactory(new PropertyValueFactory<>("quantite"));
          Pcat1.setCellValueFactory((TableColumn.CellDataFeatures<produit, String> param) -> new SimpleStringProperty(param.getValue().getCategorie().getNom_c()));
        
        data.addAll(ProduitService.RecupererPanier(panier));
        AfficherP1.setItems(data);
         total.setText(ProduitService.calculerTotal(panier)+""); 
         
      
    }

    @FXML
    private void valider(ActionEvent event) {
           String str=ProduitService.ProduitAchetes(panier);
Float t=ProduitService.calculerTotal(panier);
        ProduitService ps=new ProduitService();
        OrderService os=new OrderService();
        if(panier.isEmpty()){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setContentText("votre panier est vide");
        alert.showAndWait();}
        else{
            ps.validerPanier(panier);
           
        ps.Mailer(panier,str,t);
         AfficherP1.getItems().clear();
         Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("commande valider");
      
        
        alert.showAndWait();
        total.setText(ProduitService.calculerTotal(panier)+""); }
        
    }

   
    

    @FXML
    private void qup(ActionEvent event) {
          ProduitService ps=new ProduitService();
        produit p=(produit) AfficherP1.getSelectionModel().getSelectedItem();
        ps.IncrementerQte(panier, p);
  AfficherP1.getItems().clear();    
              Pid1.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
          Pnom1.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
          Ppu1.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
          Ppro1.setCellValueFactory(new PropertyValueFactory<>("promotion"));
          Pq1.setCellValueFactory(new PropertyValueFactory<>("quantite"));
          Pcat1.setCellValueFactory((TableColumn.CellDataFeatures<produit, String> param) -> new SimpleStringProperty(param.getValue().getCategorie().getNom_c()));
        
        data.addAll(ProduitService.RecupererPanier(panier));
        AfficherP1.setItems(data);
        total.setText(ProduitService.calculerTotal(panier)+""); 
        
    }

    @FXML
    private void qdown(ActionEvent event) {
          ProduitService ps=new ProduitService();
        produit p=(produit) AfficherP1.getSelectionModel().getSelectedItem();

    
        ps.DecrementerQte(panier, p);
          AfficherP1.getItems().clear();
              Pid1.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
          Pnom1.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
          Ppu1.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
          Ppro1.setCellValueFactory(new PropertyValueFactory<>("promotion"));
          Pq1.setCellValueFactory(new PropertyValueFactory<>("quantite"));
          Pcat1.setCellValueFactory((TableColumn.CellDataFeatures<produit, String> param) -> new SimpleStringProperty(param.getValue().getCategorie().getNom_c()));
        
        data.addAll(ProduitService.RecupererPanier(panier));
        AfficherP1.setItems(data);
        total.setText(ProduitService.calculerTotal(panier)+""); 
    }

    @FXML
    private void LoadP(ActionEvent event) {
          
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
         AfficherP1.getItems().clear();
            Pid1.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
          Pnom1.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
          Ppu1.setCellValueFactory(new PropertyValueFactory<>("prix_produit"));
          Ppro1.setCellValueFactory(new PropertyValueFactory<>("promotion"));
          Pq1.setCellValueFactory(new PropertyValueFactory<>("quantite"));
          Pcat1.setCellValueFactory((TableColumn.CellDataFeatures<produit, String> param) -> new SimpleStringProperty(param.getValue().getCategorie().getNom_c()));
        
        data.addAll(ProduitService.RecupererPanier(panier));
        AfficherP1.setItems(data);
    }

    @FXML
    private void clear(ActionEvent event) {
        panier.clear();
        AfficherP1.getItems().clear();
    }

    @FXML
    private void search(ActionEvent event) {
           
      ObservableList<produit>items = FXCollections.observableArrayList();
         this.CategorieService= new CategorieService();
         this.ProduitService= new ProduitService();
         AfficherP.getItems().clear();
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
         
        items.addAll(ProduitService.Rechercher(rech.getText()));
        AfficherP.setItems(items);
    }

    @FXML
    private void detail(ActionEvent event) {
              ProduitService ps=new ProduitService();
       produit p2=(produit) AfficherP.getSelectionModel().getSelectedItem();
            try{
           String str;
             str = "C:/xampp/htdocs/ProjectPi/public/uploads/brochures/"+p2.getImage();
       FileInputStream stream2 = new FileInputStream(str);
            Image b = new Image(stream2);
             image.setImage(b);
         } catch (FileNotFoundException ex) {
            Logger.getLogger(BackController.class.getName()).log(Level.SEVERE, null, ex);
        }
       info.setText("Nom:"+p2.getNom_produit()+"\n"+"Categorie:"+p2.getCategorie().getNom_c()+"\n Description:"+p2.getDescription()+"\n Quantite Disponible:"+p2.getQuantite()+"Articles \n Prix:"+p2.getPrix_produit()+"dt \n Promo:"+p2.getPromotion()+"%\n reviews:"+p2.getNl()+"likes");
    System.out.println(p2.toString()); 
    }
    
}
