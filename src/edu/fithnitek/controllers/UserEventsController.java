/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fithnitek.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import edu.fithnitek.utils.MyConnection;
import edu.fithnitek.entities.Evenement;
import edu.fithnitek.services.EvenementCRUD;

/**
 * FXML Controller class
 *
 * @author waelb
 */
public class UserEventsController implements Initializable {

    @FXML
    private Button aaa;
    @FXML
    private Button actualierBtn;
    @FXML
    private Button btnQuitter;
    @FXML
    private TableView<Evenement> table_ev;
    public ObservableList<Evenement> data=FXCollections.observableArrayList();
    @FXML
    private TableColumn<Evenement, Integer> champId;
    @FXML
    private TableColumn<Evenement, String> champLieu;
    @FXML
    private TableColumn<Evenement, LocalDate> champDate;
    @FXML
    private TableColumn<Evenement, String> champTitre;
    @FXML
    private TableColumn<Evenement, String> champDesciption;
    @FXML
    private TableColumn<Evenement, Integer> champNb;
    @FXML
    private TextField fieldChercher;
        private ObservableList<Evenement> evenementList;
    @FXML
    private Button nnn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show();
        evenementList =listeDesEntites();
        FilteredList<Evenement> filteredList = new FilteredList<>(evenementList, p -> true);
        fieldChercher.textProperty().addListener((observable, oldValue, newValue) -> {
        filterProduits(filteredList, newValue);
        table_ev.setItems(filteredList);});
    }    
    private void filterProduits(FilteredList<Evenement> filteredList, String searchValue) {
    filteredList.setPredicate(evenement -> {
        if (searchValue == null || searchValue.isEmpty()) {
            return true;
        }
        String lowerCaseFilter = searchValue.toLowerCase();
        if (evenement.getTitre().toLowerCase().contains(lowerCaseFilter)) {
            return true; 
        } 
        return false; 
    });
}
public ObservableList<Evenement> listeDesEntites() {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try{
     
     String requete = "SELECT * FROM evenement";
     Statement st = MyConnection.getInstance().getCnx().createStatement();
     ResultSet rs = st.executeQuery(requete);
     while(rs.next()){
         Evenement e = new Evenement();
         e.setId(rs.getInt(1));
         e.setLieu(rs.getString(2));
         e.setDatee(rs.getDate(3).toLocalDate());
         e.setTitre(rs.getString(4));
         e.setDescription(rs.getString(5));
         e.setNbparticipants(rs.getInt(6));
         myList.add(e);
     }
    }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public void show(){
          try {
            String requete="SELECT * FROM evenement"; 
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next()){
              data.add(new Evenement(rs.getInt(1),rs.getString(2),rs.getDate(3).toLocalDate(),rs.getString(4),rs.getString(5),rs.getInt(6)));
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                 champId.setCellValueFactory(new PropertyValueFactory<>("id"));
                 champLieu.setCellValueFactory(new PropertyValueFactory<>("lieu")); 
                 champDate.setCellValueFactory(new PropertyValueFactory<>("datee"));
                 champTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
                 champDesciption.setCellValueFactory(new PropertyValueFactory<>("description"));
                 champNb.setCellValueFactory(new PropertyValueFactory<>("nbparticipants"));
                 table_ev.setItems(data);
                 table_ev.setRowFactory(tv -> new TableRow<Evenement>() {
                 @Override
                 protected void updateItem(Evenement item, boolean empty) {
                 super.updateItem(item, empty);
                    if (item == null || empty) {
                        setStyle("");
                        } else {
                        if (item.getDatee().isBefore(LocalDate.now())) {
                        setStyle("-fx-background-color: #E0EEEE;");
                        } else {
                        setStyle("");
            }
        }
    }
});
      }
    @FXML
    private void act(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/UserEvents.fxml"));
            Parent root = loader.load();
            btnQuitter.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gett(ActionEvent event) {
        try {
               Evenement e;
            e= table_ev.getSelectionModel().getSelectedItem();
               FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/UserEventsDetails.fxml"));
            Parent root = loader.load();
            UserEventsDetailsController dc=loader.getController();
            dc.setIdchamp(String.valueOf(e.getId()));
            dc.setLieus(e.getLieu());
            dc.setDates(e.getDatee().toString());
            dc.setTitres(e.getTitre());
            dc.setDescriptions(e.getDescription());
            dc.setNbpa( "Nombre de participants: "+String.valueOf(e.getNbparticipants()));            
            dc.show();
            dc.miaaw();
            dc.meteo();
            aaa.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex);
        }       
    }
    

    @FXML
    private void LienVersMenu(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/Interface1.fxml"));
            Parent root = loader.load();
            btnQuitter.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void participate(ActionEvent event) {
        Evenement e;
        EvenementCRUD ec = new EvenementCRUD();
        e= table_ev.getSelectionModel().getSelectedItem();
        ec.modifierEntitt(e);
        table_ev.getItems().clear();
        show();
    }
    
}
