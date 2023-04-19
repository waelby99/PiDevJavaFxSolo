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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import edu.fithnitek.services.EvenementCRUD;
import edu.fithnitek.utils.MyConnection;
import edu.fithnitek.entities.Evenement;
import edu.fithnitek.entities.Sponsor;

/**
 * FXML Controller class
 *
 * @author waelb
 */
public class ListeEvenementsController implements Initializable {

    @FXML
    private TableColumn<Evenement, String> champLieu;
    @FXML
    private TableColumn<Evenement, String> champDate;
    @FXML
    private TableColumn<Evenement, String> champTitre;
    @FXML
    private TableColumn<Evenement, String> champDesciption;
    public ObservableList<Evenement> data=FXCollections.observableArrayList();
    public ObservableList<Evenement> data1=FXCollections.observableArrayList();
    TableView<Sponsor> table_spsr;
    @FXML
    private TableView<Evenement> table_ev;
    @FXML
    private TextField fieldLieu;
    @FXML
    private DatePicker fieldDate;
    @FXML
    private TextField fieldTitre;
    @FXML
    private TextField fieldDescription;
    @FXML
    private TableColumn<Evenement, Integer> champId;
    @FXML
    private TextField fieldChercher;
    @FXML
    private Button btnQuitter;
    @FXML
    private Button btnListesDesSponsors;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnSupp;
    @FXML
    private Button actualierBtn;
    @FXML
    private Button aaa;
    @FXML
    private TableColumn<Evenement, Integer> champNb;


    public TableColumn<Evenement, Integer> getChampId() {
        return champId;
    }

    public void setChampId(TableColumn<Evenement, Integer> champId) {
        this.champId = champId;
    }
    
    public void setChampLieu(TableColumn<Evenement, String> champLieu) {
        this.champLieu = champLieu;
    }

    public void setChampDate(TableColumn<Evenement, String> champDate) {
        this.champDate = champDate;
    }

    public void setChampTitre(TableColumn<Evenement, String> champTitre) {
        this.champTitre = champTitre;
    }

    public void setChampDesciption(TableColumn<Evenement, String> champDesciption) {
        this.champDesciption = champDesciption;
    }

    public TableColumn<Evenement, String> getChampLieu() {
        return champLieu;
    }

    public TableColumn<Evenement, String> getChampDate() {
        return champDate;
    }

    public TableColumn<Evenement, String> getChampTitre() {
        return champTitre;
    }

    public TableColumn<Evenement, String> getChampDesciption() {
        return champDesciption;
    }
    
    /**
     * Initializes the controller class.
     */
    List<Evenement> Evenement;
    EvenementCRUD ecd= new EvenementCRUD();
    public void Refresh(){
        Evenement=ecd.listeDesEntites();
        table_ev.getItems().setAll(Evenement);
    }
    private ObservableList<Evenement> evenementList;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
        evenementList =listeDesEntites();
        FilteredList<Evenement> filteredList = new FilteredList<>(evenementList, p -> true);
        fieldChercher.textProperty().addListener((observable, oldValue, newValue) -> {
        filterProduits(filteredList, newValue);
        table_ev.setItems(filteredList);});
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
                 table_ev.setRowFactory(aa -> new TableRow<Evenement>() {
                 @Override
                 protected void updateItem(Evenement cel, boolean vide) {
                 super.updateItem(cel, vide);
                    if (cel == null || vide) {
                        setStyle("");
                        } else {
                        if (cel.getDatee().isBefore(LocalDate.now())) {
                        setStyle("-fx-background-color: #E0EEEE;");
                        } else {
                        setStyle("");
            }
        }
    }
});
      }
    
    
    public static String anticommercial(String desc) {
    String[] commercials = {"vendre", "achats","acheter","vendu","vente","achat","seller","buyer","buy","sell"};
    String sanitizedInput = desc;
    for (String mot : commercials) {
        if (sanitizedInput.contains(mot)) {
            sanitizedInput = sanitizedInput.replace(mot, getStars(mot)); 
        }
    }
    return sanitizedInput;
}

public static String getStars(String input) {
    String stars = "";
    for (int i = 0; i < input.length(); i++) {
        stars += "*";
    }
    return stars;
}

    
    private void viderLesChamps() {
       fieldLieu.clear();
       fieldDate.setValue(null);
       fieldTitre.clear();
       fieldDescription.clear();    
    }
        private boolean titreValide(){
      Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(fieldTitre.getText());
        if(m.find() && m.group().equals(fieldTitre.getText())){
            return true;
        }else{
                
           
            return false;            
        }
     }
        private boolean descriptionValide(){
      Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(fieldDescription.getText());
        if(m.find() && m.group().equals(fieldDescription.getText())){
            return true;
        }else{
       
           
            return false;            
        }
     }
        public boolean ValidationDateEvent() {
        LocalDate aa= LocalDate.now();
        if(fieldDate.getValue().isAfter(aa))
        { 
            return true;
        }
        else {
            return false;
        }
}
        public boolean ValidationLieu() {
        Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(fieldLieu.getText());
        if(m.find() && m.group().equals(fieldLieu.getText())){
            return true;
        }else{           
            return false;            
        }
}
         public boolean existeevent(){
        String Lieu = fieldLieu.getText();
        LocalDate dats = fieldDate.getValue();
        //String desc=fieldDescription.getText();
        String titre=fieldTitre.getText();
        boolean sponsorExists = false;
    for (Evenement evenement : table_ev.getItems()) { 
        if ( (evenement.getLieu().equals(Lieu)) && (evenement.getTitre().equals(titre)) && (evenement.getDatee().equals(dats))) {
            sponsorExists = true;
            break;
        }
    }
    return sponsorExists;
    }
    @FXML
    private void ajouterEv(ActionEvent event) {
       if ((fieldLieu.getText().isEmpty())|| (fieldTitre.getText().isEmpty()) || (fieldDescription.getText().isEmpty()) || (fieldDate.getValue()==null)){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();    
       }else{
           if (existeevent()){
           Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("L'evenement deja existe");
        alert.showAndWait(); 
          }
           
           if (!ValidationDateEvent()){
           Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez vhoisir une date supérieur à celle que vous avez déjà choisis !");
        alert.showAndWait(); 
          }
          if (!descriptionValide()){
              Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez mettre une description valide ");
        alert.showAndWait();
          }
          if(!titreValide()){
               Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez mettre un titre valide ");
        alert.showAndWait();
          }
          if(!ValidationLieu()){
              Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez mettre un lieu valide ");
        alert.showAndWait();
          }
          else if(titreValide() && descriptionValide() && ValidationDateEvent() && ValidationLieu() && !existeevent()){
        try{ EvenementCRUD ec = new EvenementCRUD();
        String var2=fieldLieu.getText();
        String var4=fieldTitre.getText();
        
        String var5=anticommercial(fieldDescription.getText());
        String var6=fieldDescription.getText();
        if(!var6.equals(var5)){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Attention !");
        alert.setHeaderText(null);
        alert.setContentText("Cette plateforme n'est pas une platforme pour vendre et acheter des biens , s'il vous plait merci de respecter les conditions d'utiliation d'aministration !");
        alert.showAndWait(); 
        }
        Evenement e =new Evenement();  
        e.setId(88);
        e.setLieu(var2);
        e.setDatee(fieldDate.getValue());
        e.setTitre(var4);
        e.setDescription(var5);
        e.setNbparticipants(0);
        ec.ajouterEntitee(e);
        System.out.println("ajouté");}
        catch(Exception ex){
            System.out.println(ex);
       }
            Refresh();
            viderLesChamps();    
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Evenment Ajouté");
        alert.showAndWait(); 
        }
       }  }
    @FXML
    private void supprimerEvenement(ActionEvent event) {
        EvenementCRUD  eccd = new  EvenementCRUD ();
        Evenement e;   
        Alert alert = new Alert(Alert.AlertType.ERROR);
        e= table_ev.getSelectionModel().getSelectedItem();
       alert.setTitle("Confirmation de suppression");
           alert.setHeaderText(null);
           alert.setContentText(String.valueOf("Etes vous sur de supprimer cévènement: "+e.getTitre()));
           ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
           ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
           alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
           Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeOk){
                eccd.supprimerEntite(e);
                Refresh();}  
    }
    @FXML
    private void modifierevent(ActionEvent event) {
                  if ((fieldLieu.getText().isEmpty())|| (fieldDescription.getText().isEmpty()) || (fieldDate.getValue()==null)){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();    
       }else{
        EvenementCRUD ec = new EvenementCRUD();
        Evenement e;
        
        e=table_ev.getSelectionModel().getSelectedItem();
        String var1=fieldLieu.getText();
        LocalDate var2=fieldDate.getValue();
        String var3=e.getTitre();
        String var4=fieldDescription.getText();
        int var5=e.getNbparticipants();
        e.setLieu(var1);
        e.setDatee(var2);
        e.setTitre(var3);
        e.setDescription(var4);
        e.setNbparticipants(e.getNbparticipants());
        ec.modifierEntite(e,var1,var2,var3,var4,var5);
        Refresh();
        viderLesChamps();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Evenement Modifié");
        alert.showAndWait(); 
}}
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
    @FXML
    private void LienVersListeSponsor(ActionEvent event){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/ListeSponsor.fxml"));
            Parent root = loader.load();
            btnListesDesSponsors.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void RefreshSelf(ActionEvent event){
        Refresh();
        viderLesChamps();
        fieldChercher.clear();
    }
    @FXML
     private void LienVersMenu(ActionEvent event){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/Interface1.fxml"));
            Parent root = loader.load();
            btnQuitter.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }     

    @FXML
    private void act(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/ListeEvenements.fxml"));
            Parent root = loader.load();
            btnQuitter.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gett(ActionEvent event) throws IOException, SQLException{
           try {
               Evenement e;
            e= table_ev.getSelectionModel().getSelectedItem();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/Details.fxml"));
            Parent root = loader.load();
            DetailsController dc=loader.getController();
            dc.setIdchamp(String.valueOf(e.getId()));
            dc.setLieus(e.getLieu());
            dc.setDates(e.getDatee().toString());
            dc.setTitres(e.getTitre());
            dc.setDescriptions(e.getDescription());
            dc.setNbpa( "Nombre de participants: "+String.valueOf(e.getNbparticipants()));
            
            dc.show();
            dc.show1();
            dc.miaaw();
            dc.miaaw2();
            dc.meteo();
            fieldDescription.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex);
        }       
    }
    
   }