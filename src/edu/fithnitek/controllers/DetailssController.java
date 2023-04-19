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
import java.time.Period;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import edu.fithnitek.utils.MyConnection;
import edu.fithnitek.entities.Evenement;
import edu.fithnitek.entities.Sponsor;

/**
 * FXML Controller class
 *
 * @author waelb
 */
public class DetailssController implements Initializable {

    public ObservableList<Evenement> data=FXCollections.observableArrayList();
    @FXML
    private TextField sponsorchamp;
    @FXML
    private TextField datesignatureChamp;
    @FXML
    private TextField montantchamp;
    @FXML
    private TextField adresseChamp;
    @FXML
    private TextField idchamp;
    private TableView<Evenement> table_spsr;
    @FXML
    private TableColumn<Evenement, Integer> fieldID;
    @FXML
    private TableColumn<Evenement, String> fieldLieu;
    @FXML
    private TableColumn<Evenement, LocalDate> fieldDate;
    @FXML
    private TableColumn<Evenement, String> fieldTitre;
    @FXML
    private TableColumn<Evenement, String> fieldDescription;
    @FXML
    private Button ee;
    @FXML
    private TextField emailChamp;
    @FXML
    private TableView<Evenement> table_evt;
    @FXML
    private Label signedepuis;
    @FXML
    private TableColumn<Evenement, Sponsor> fieldNb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public TextField getSponsorchamp() {
        return sponsorchamp;
    }

    public TextField getDatesignatureChamp() {
        return datesignatureChamp;
    }

    public TextField getMontantchamp() {
        return montantchamp;
    }

    public TextField getAdresseChamp() {
        return adresseChamp;
    }

    public TextField getIdchamp() {
        return idchamp;
    }

    public TableView<Evenement> getTable_spsr() {
        return table_spsr;
    }

    public TableColumn<Evenement, Integer> getFieldID() {
        return fieldID;
    }

    public TableColumn<Evenement, String> getFieldLieu() {
        return fieldLieu;
    }

    public TableColumn<Evenement, LocalDate> getFieldDate() {
        return fieldDate;
    }

    public TableColumn<Evenement, String> getFieldTitre() {
        return fieldTitre;
    }

    public TableColumn<Evenement, String> getFieldDescription() {
        return fieldDescription;
    }

    public Button getEe() {
        return ee;
    }

    public TextField getEmailChamp() {
        return emailChamp;
    }

    public void setSponsorchamp(String sponsorchamp) {
        this.sponsorchamp.setText(sponsorchamp);
    }

    public void setDatesignatureChamp(String datesignatureChamp) {
        this.datesignatureChamp.setText(datesignatureChamp);
    }

    public void setMontantchamp(String montantchamp) {
        this.montantchamp.setText(montantchamp);
    }

    public void setAdresseChamp(String adresseChamp) {
        this.adresseChamp.setText(adresseChamp);
    }

    public void setIdchamp(String idchamp) {
        this.idchamp.setText(idchamp);
    }

    public void setTable_spsr(TableView<Evenement> table_spsr) {
        this.table_spsr = table_spsr;
    }

    public void setFieldID(TableColumn<Evenement, Integer> fieldID) {
        this.fieldID = fieldID;
    }

    public void setFieldLieu(TableColumn<Evenement, String> fieldLieu) {
        this.fieldLieu = fieldLieu;
    }

    public void setFieldDate(TableColumn<Evenement, LocalDate> fieldDate) {
        this.fieldDate = fieldDate;
    }

    public void setFieldTitre(TableColumn<Evenement, String> fieldTitre) {
        this.fieldTitre = fieldTitre;
    }

    public void setFieldDescription(TableColumn<Evenement, String> fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public void setEe(Button ee) {
        this.ee = ee;
    }

    public void setEmailChamp(String emailChamp) {
        this.emailChamp.setText(emailChamp);
    }
public void miaaw(){
        LocalDate aa=LocalDate.parse(datesignatureChamp.getText());
        LocalDate to = LocalDate.now();
        Period period = Period.between(aa, to);
        signedepuis.setText("Notre sponsor depuis "+period.getYears() + " année,"+period.getMonths() + " mois,"+period.getDays() + " jours");
    }
    @FXML
    private void brrr(ActionEvent event) {
         try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/ListeSponsor.fxml"));
            Parent root = loader.load();
            ee.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void show(){
          try {
              int var1=Integer.parseInt(idchamp.getText());
            String query = "SELECT c.* FROM evenement c "
                + "INNER JOIN relation1 rr ON rr.id_evenement = c.id "
                + "INNER JOIN sponsoring s ON s.id=rr.id_sponsor "
                +"WHERE s.id="+var1+"";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(query);
            
            while(rs.next()){
              
               data.add(new Evenement(rs.getInt(1),rs.getString(2),rs.getDate(3).toLocalDate(),rs.getString(4),rs.getString(5),rs.getInt(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                fieldID.setCellValueFactory(new PropertyValueFactory<>("id"));
                fieldLieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
                fieldDate.setCellValueFactory(new PropertyValueFactory<>("datee"));
                fieldTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
                fieldDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                fieldNb.setCellValueFactory(new PropertyValueFactory<>("nbparticipants"));
                table_evt.setItems(data);
      }
}
