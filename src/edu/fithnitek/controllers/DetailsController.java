/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fithnitek.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;
import com.google.gson.reflect.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import edu.fithnitek.utils.MyConnection;
import edu.fithnitek.entities.Sponsor;

/**
 * FXML Controller class
 *
 * @author waelb
 */
public class DetailsController implements Initializable {

    public ObservableList<Sponsor> data=FXCollections.observableArrayList();
    public ObservableList<Sponsor> data1=FXCollections.observableArrayList();
    @FXML
    private Button ee;
    @FXML
    private ComboBox<Sponsor> azert;
    @FXML
    private Button btnaddspsr;
    @FXML
    private ImageView imageFrame;
    @FXML
    private Label labelDuree;
    @FXML
    private Label montanttotal;
    @FXML
    private Label temp;
    @FXML
    private Label humidite;
    @FXML
    private Label speed;
    @FXML
    private ImageView imagetaks;
    @FXML
    private Label nbpa;

    public void setData(ObservableList<Sponsor> data) {
        this.data = data;
    }
    @FXML
    private TextField Lieus;
    @FXML
    private TextField Titres;
    @FXML
    private TextField Descriptions;
    @FXML
    private TextField dates;
    private TextField id;
    @FXML
    public TableView<Sponsor> table_spsr;
    @FXML
    public TableColumn<Sponsor, Integer> fieldID;
    @FXML
    public TableColumn<Sponsor, String> fieldSposnsor;
    @FXML
    public TableColumn<Sponsor, Float> fieldMontant;
    @FXML
    public TableColumn<Sponsor, String> fieldAdresse;
    @FXML
    public TableColumn<Sponsor, LocalDate> fieldDate;
    @FXML
    public TableColumn<Sponsor, String> fieldEmail;
    @FXML
    private TextField idchamp;

    public void setTable_spsr(TableView<Sponsor> table_spsr) {
        this.table_spsr = table_spsr;
    }

    public void setFieldID(TableColumn<Sponsor, Integer> fieldID) {
        this.fieldID = fieldID;
    }

    public void setFieldSposnsor(TableColumn<Sponsor, String> fieldSposnsor) {
        this.fieldSposnsor = fieldSposnsor;
    }

    public void setFieldMontant(TableColumn<Sponsor, Float> fieldMontant) {
        this.fieldMontant = fieldMontant;
    }

    public void setFieldAdresse(TableColumn<Sponsor, String> fieldAdresse) {
        this.fieldAdresse = fieldAdresse;
    }

    public void setFieldDate(TableColumn<Sponsor, LocalDate> fieldDate) {
        this.fieldDate = fieldDate;
    }

    public void setFieldEmail(TableColumn<Sponsor, String> fieldEmail) {
        this.fieldEmail = fieldEmail;
    }

    public TextField getIdchamp() {
        return idchamp;
    }

    public Label getNbpa() {
        return nbpa;
    }

    public void setNbpa(String nbpa) {
        this.nbpa.setText(nbpa);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }  
    public static Map<String, Object> jsonToMap(String str){
        Map<String, Object> map=new Gson().fromJson(
        str, new TypeToken<HashMap<String, Object>>() {}.getType()
        );
   return map;
    }
    public void meteo(){
        String API_KEY="5b491eb9b69dd529d5cb765278c52609";
        String LOCATION=Lieus.getText();
        String urlString="https://api.openweathermap.org/data/2.5/weather?q="+LOCATION+"&appid="+API_KEY+"&units=metric";
        try{
            StringBuilder result =new StringBuilder();
            URL url=new URL(urlString);
            URLConnection conn=url.openConnection();
            BufferedReader rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine())!=null){
                result.append(line);
            }
            rd.close();
            Map<String, Object> respMap= jsonToMap(result.toString());
            Map<String, Object> mainMap= jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap= jsonToMap(respMap.get("wind").toString());  
            List<Map<String, Object>> weatherList = (List<Map<String, Object>>) respMap.get("weather");
            Map<String, Object> weatherMap = weatherList.get(0);
            String icon = (String) weatherMap.get("icon");
            temp.setText("Température: "+mainMap.get("temp")+"°C");
            humidite.setText("Humidité: "+mainMap.get("humidity"));
            speed.setText("Vitesse: "+windMap.get("speed")+"km/h");
            String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";
            URL url1 = new URL(iconUrl);
            InputStream in = url1.openStream();
            Image image = new Image(in);
            imagetaks.setImage(image);
        }catch(IOException e){
            System.out.println(e);
        }
}
    public void miaaw(){
        LocalDate aa=LocalDate.parse(dates.getText());
        LocalDate to = LocalDate.now();
        Period period = Period.between(aa, to);
        labelDuree.setText(period.getYears() + " année,"+period.getMonths() + " mois,"+period.getDays() + " jours");
    }
    public void miaaw2(){
       TableColumn<Sponsor, Float> colonneMontant = table_spsr.getColumns().stream().filter(colonne -> colonne.getText().equals("fieldMontant")).map(colonne -> (TableColumn<Sponsor, Float>) colonne).findFirst().orElse(null);
        float somme = table_spsr.getItems().stream().map(Sponsor::getMontant).reduce(0f, Float::sum);
        montanttotal.setText("Le montant total est : "+somme);
    }
    private boolean isObjectExistsInTable(Object object, TableView tableView) {
    for (Object item : tableView.getItems()) {
        if (item.equals(object)) {
            return true;
        }
    }
    return false;
}
    public void show(){
          try {
              int var1=Integer.parseInt(idchamp.getText());
            String requete = "SELECT s.* FROM sponsoring s "
                + "INNER JOIN relation1 rr ON rr.id_sponsor = s.id "
                + "INNER JOIN evenement e ON "+var1+"=rr.id_evenement "
                + "WHERE e.id="+var1+"";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            
            while(rs.next()){
              
               data.add(new Sponsor(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getDate(5).toLocalDate(),rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                 fieldID.setCellValueFactory(new PropertyValueFactory<>("id"));
                 fieldSposnsor.setCellValueFactory(new PropertyValueFactory<>("sponsore"));
                 fieldMontant.setCellValueFactory(new PropertyValueFactory<>("montant")); 
                 fieldAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                 fieldDate.setCellValueFactory(new PropertyValueFactory<>("dateSignature"));
                 fieldEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                 
                 table_spsr.setItems(data);
      }
   public void show1() {
    try {
        String requete = "SELECT sponsoring.id, sponsoring.sponsor FROM sponsoring ";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);

        while (rs.next()) {
            boolean existsInTableView = false; 

            for (Sponsor tableViewObj : table_spsr.getItems()) {
                if (isEqual(tableViewObj, rs)) {
                    existsInTableView = true;
                    break;
                }
            }
            if (!existsInTableView) {
                data1.add(new Sponsor(rs.getInt(1), rs.getString(2)));
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    azert.setItems(data1);
}

    private boolean isEqual(Sponsor tableViewObj, ResultSet rs) throws SQLException {
        return tableViewObj.getId() == rs.getInt(1) && tableViewObj.getSponsore().equals(rs.getString(2));
}

    public TextField getLieus() {
        return Lieus;
    }

    public TextField getTitres() {
        return Titres;
    }

    public TextField getDescriptions() {
        return Descriptions;
    }

    public TextField getDates() {
        return dates;
    }

    public void setLieus(String lessage) {
        this.Lieus.setText(lessage);
    }

    public void setTitres(String lessage) {
        this.Titres.setText(lessage);
    }

    public void setDescriptions(String lessage) {
        this.Descriptions.setText(lessage);
    }

    public void setDates(String dates) {
        this.dates.setText(dates);
    }

        public void setIdchamp(String msg) {
        this.idchamp.setText(msg);
    }
    @FXML
    public void brrr(ActionEvent event){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/ListeEvenements.fxml"));
            Parent root = loader.load();
            ee.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }   
    @FXML
    private void addspsr(ActionEvent event){
        try{
        Sponsor s;
        s= azert.getSelectionModel().getSelectedItem();
        int var1=Integer.parseInt(idchamp.getText());
        String requte="INSERT INTO relation1(id_evenement,id_sponsor)"
                + "VALUES (?,?)";
        PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requte);
        pst.setInt(1,var1);
        pst.setInt(2,s.getId());
        
        pst.executeUpdate();
        
        System.out.println("Done");
        table_spsr.getItems().clear();
        show();
        azert.getSelectionModel().clearSelection();
        azert.getItems().clear();
        show1();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        miaaw2();
        
    }

    
}
