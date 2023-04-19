/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.fithnitek.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import edu.fithnitek.utils.MyConnection;
import edu.fithnitek.entities.Sponsor;

/**
 * FXML Controller class
 *
 * @author waelb
 */
public class UserEventsDetailsController implements Initializable {

    @FXML
    private TextField Lieus;
    @FXML
    private TextField Titres;
    @FXML
    private TextField Descriptions;
    @FXML
    private TextField dates;
    @FXML
    private TextField idchamp;
    @FXML
    private TableView<Sponsor> table_spsr;
    @FXML
    private TableColumn<Sponsor, String> fieldSposnsor;
    @FXML
    private TableColumn<Sponsor, String> fieldAdresse;
    @FXML
    private TableColumn<Sponsor, String> fieldEmail;
    @FXML
    private Button ee;
    @FXML
    private ImageView imageFrame;
    @FXML
    private Label nbpa;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    public TextField getIdchamp() {
        return idchamp;
    }

    public Label getNbpa() {
        return nbpa;
    }

    public void setLieus(String Lieus) {
        this.Lieus.setText(Lieus);
    }

    public void setTitres(String Titres) {
        this.Titres.setText(Titres);
    }

    public void setDescriptions(String Descriptions) {
        this.Descriptions.setText(Descriptions);
    }

    public void setDates(String dates) {
        this.dates.setText(dates);
    }

    public void setIdchamp(String idchamp) {
        this.idchamp.setText(idchamp);
    }

    public void setNbpa(String nbpa) {
        this.nbpa.setText(nbpa);
    }
    public ObservableList<Sponsor> data=FXCollections.observableArrayList();
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
                 fieldSposnsor.setCellValueFactory(new PropertyValueFactory<>("sponsore"));               
                 fieldAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                 fieldEmail.setCellValueFactory(new PropertyValueFactory<>("email"));                 
                 table_spsr.setItems(data);
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
    @FXML
    private void brrr(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/UserEvents.fxml"));
            Parent root = loader.load();
            ee.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}
