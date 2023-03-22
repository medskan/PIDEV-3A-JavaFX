/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.order;
import entities.orderdetail;
import entities.produit;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
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
public class OrderClientController implements Initializable {

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
     ObservableList<produit> produitList =FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
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

    private void back(ActionEvent event) {
          Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/ui/Front.fxml"));
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

    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
         OrderService OrderService=new OrderService();
         order selected = AfficherO.getSelectionModel().getSelectedItem();
         PdfPTable table = new PdfPTable(3);
       PdfPCell c1 = new PdfPCell(new Phrase("produit"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);
    c1 = new PdfPCell(new Phrase("quantite"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("prix"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

   
    
   
for(int i=0;i<OrderService.recupererOrderDetails(selected).size();i++){
   List<orderdetail> list=OrderService.recupererOrderDetails(selected);
    table.addCell(list.get(i).getProduit().getNom_produit()+"");
    table.addCell(list.get(i).getQuantity()+"");
    table.addCell(list.get(i).getPrix()+"");
   

}

       
   
        Document doc=new Document();
        PdfWriter.getInstance(doc,new FileOutputStream("C:/xampp/htdocs/ProjectPi/public/uploads/brochures/"+selected.getNumero_commande()+".pdf"));
        doc.open();
        doc.addAuthor("Easy Fit");
    Image img=  Image.getInstance("C:/Users/PC/Downloads/logo.png");
       doc.add(img);
     
    doc.addTitle("Facture n°"+selected.getNumero_commande());
        
        doc.add(new Paragraph("Nom client:  Line Kazdaghli \n Num° commande"+selected.getNumero_commande()+"\n Date:"+selected.getDate()+"\n\n\n\n"));
        doc.add(table);
        doc.add(new Paragraph("votre total est  "+selected.getTotal()+"   dt"));
    doc.close();
    Desktop.getDesktop().open(new File("C:/xampp/htdocs/ProjectPi/public/uploads/brochures/"+selected.getNumero_commande()+".pdf"));
    
}

 


}
