/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entities.produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import service.OrderService;
import service.ProduitService;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ChartController implements Initializable {

    @FXML
    private Label caption;
    @FXML
    private PieChart piechart;
    @FXML
    private ImageView logo;

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
              List list;
        ProduitService ps=new ProduitService();
        OrderService os=new OrderService();
        list=ps.recuperer();
        ObservableList<PieChart.Data>pieData=FXCollections.observableArrayList();
        int i;
               int t=0;
        for(i=0;i<list.size();i++){
            produit p=(produit) list.get(i);
        PieChart.Data d =new PieChart.Data(p.getNom_produit(),os.nbvente(p));
        pieData.add(i,d);
        t=t+os.nbvente(p);
        }
     
              PieChart pchart =new PieChart(pieData); 
           
         piechart.setData(pieData);
     piechart.setLabelsVisible(true);
     piechart.setLegendVisible(true);
   
piechart.setLabelLineLength(10);
piechart.setLegendSide(Side.LEFT);
        piechart.getData().forEach((PieChart.Data data) -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
              
    
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 24 arial;");

                caption.setTranslateX(e.getSceneX() - caption.getLayoutX());
                caption.setTranslateY(e.getSceneY() - caption.getLayoutX());
                caption.setText(String.valueOf( data.getPieValue())+   "   articles vendus");
                System.out.println(caption.getText());
            });
});
                }
              
    
}
