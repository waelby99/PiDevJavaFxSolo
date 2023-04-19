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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import edu.fithnitek.services.SponsorCRUD;
import edu.fithnitek.utils.MyConnection;
import edu.fithnitek.entities.Sponsor;
import java.util.Properties;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import edu.fithnitek.entities.PDF;

/**
 * FXML Controller class
 *
 * @author waelb
 */
public class ListeSponsorController implements Initializable {

    public ObservableList<Sponsor> data=FXCollections.observableArrayList();
    public ObservableList<Sponsor> data1=FXCollections.observableArrayList();
    @FXML
    private TableView<Sponsor> table_sp;
    @FXML
    private TextField fieldChercher1;
    @FXML
    private Button btnListesDesEvenemets1;
    @FXML
    private TextField fieldSponsore;
    @FXML
    private TextField fieldMontant;
    @FXML
    private TextField fieldAdresse;
    @FXML
    private DatePicker fieldDate1;
    @FXML
    private TextField fieldEmail;
    @FXML
    private TableColumn<Sponsor, String> champSponsor;
    @FXML
    private TableColumn<Sponsor, Float> champMontant;
    @FXML
    private TableColumn<Sponsor, String> champAdresse;
    @FXML
    private TableColumn<Sponsor, LocalDate> champDateSign;
    @FXML
    private TableColumn<Sponsor, String> champEmail;
    @FXML
    private Button btnQuitter1;
    @FXML
    private Button btnModifier1;
    @FXML
    private Button btnAjouter1;
    @FXML
    private Button btnSupp1;
    @FXML
    private Button actualierBtn;
    @FXML
    private TableColumn<Sponsor, Integer> champId;
    @FXML
    private Button aaa;
    @FXML
    private Button btnPDF;

    public TextField getFieldSponsore() {
        return fieldSponsore;
    }

    public TextField getFieldMontant() {
        return fieldMontant;
    }

    public TextField getFieldAdresse() {
        return fieldAdresse;
    }

    public DatePicker getFieldDate1() {
        return fieldDate1;
    }

    public TextField getFieldEmail() {
        return fieldEmail;
    }
    private ObservableList<Sponsor> sponsorList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        show();
        sponsorList =listeDesEntites();
        FilteredList<Sponsor> filteredList = new FilteredList<>(sponsorList, p -> true);
        fieldChercher1.textProperty().addListener((observable, oldValue, newValue) -> {
        filterProduits(filteredList, newValue);
        table_sp.setItems(filteredList);});
    }
     public ObservableList<Sponsor> listeDesEntites() {
        ObservableList<Sponsor> myList = FXCollections.observableArrayList();
        try{
     
     String requete = "SELECT * FROM sponsoring";
     Statement st = MyConnection.getInstance().getCnx().createStatement();
     ResultSet rs = st.executeQuery(requete);
     while(rs.next()){
         Sponsor e = new Sponsor();
         e.setId(rs.getInt(1));
         e.setSponsore(rs.getString(2));
         e.setMontant(rs.getFloat(3));
         e.setAdresse(rs.getString(4));
         e.setDateSignature(rs.getDate(5).toLocalDate());
         e.setEmail(rs.getString(6));
         myList.add(e);
     }
    }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public void sendMail(String recepient) throws MessagingException{
       Properties properties = new Properties();
       properties.put("mail.smtp.auth","true");
       properties.put("mail.smtp.starttls.enable","true");
       properties.put("mail.smtp.host","smtp.gmail.com");
       properties.put("mail.smtp.port","587");
       
       String email="waelbenyoussef19991@gmail.com";
       String passwrd="cdaqwvvvgfwzyanv";
    
       Session session = Session.getInstance(properties,new Authenticator(){
           @Override
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(email,passwrd);
           }
       });
       Message message =prepareMessage(session,email,recepient);
       Transport.send(message);
       System.out.println("sent successfully");
    }  
    
    private Message prepareMessage(Session session,String email,String recepient) {
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Cher Nouveau Sponsor Inscrit "+fieldSponsore.getText());
            message.setContent("<html>\n" +
                               "    <body style=\"background-color: #7035a1;\">\n" +
                               "        <table>\n" +
                               "        <tr><td><img src=\"https://scontent.ftun9-1.fna.fbcdn.net/v/t39.30808-6/332874625_548243220619858_8178574408692685316_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=8bfeb9&_nc_ohc=svtSh4t2JhYAX_Njqmn&_nc_ht=scontent.ftun9-1.fna&oh=00_AfAs6cXw9OhnJJ4lHsgYDPxdGeQAZDAQD3tJhNWQF-rHpQ&oe=63FF4652\" width=\"100px\" length=\"100px\"></td>\n" +
                               "        <td><h1 style=\"color:#fd8735\">Bonjour notre nouveau Sponsor </h1></td></tr>\n" +
                               "        </table>\n" +
                               "        <p style=\"color:#fd8735\">Bonjour Monsieur/Madame,</p>\n" +
                               "        <p style=\"color:#fd8735\">Votre Société "+fieldSponsore.getText()+" est ajoutée à notre base de données des sponsors des évènements.A prés la signature du contrat en "+fieldDate1.getValue()+"</p>\n" +
                               "        <p style=\"color:#fd8735\">Merci pour votre charité de "+fieldMontant.getText()+" et Au revoir !</p>\n" +
                               "    </body>\n" +
                               "</html>","text/html");
            return message;
        } catch (MessagingException ex) {
            System.out.println(ex);
        }
        return null;
    }
    public void show(){
          try {
            String requete="SELECT * FROM sponsoring"; 
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(requete);
            while(rs.next()){
              data.add(new Sponsor(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getDate(5).toLocalDate(),rs.getString(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                 champId.setCellValueFactory(new PropertyValueFactory<>("id"));
                 champSponsor.setCellValueFactory(new PropertyValueFactory<>("sponsore"));
                 champMontant.setCellValueFactory(new PropertyValueFactory<>("montant")); 
                 champAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
                 champDateSign.setCellValueFactory(new PropertyValueFactory<>("dateSignature"));
                 champEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                 table_sp.setItems(data);
                 
      }
    List<Sponsor> Sponsor;
    SponsorCRUD scd= new SponsorCRUD();
    public void Refresh(){
        Sponsor=scd.listeDesEntites();
        table_sp.getItems().setAll(Sponsor);
    }  
    private void viderLesChamps() {
       fieldSponsore.clear();
       fieldMontant.clear();
       fieldAdresse.clear();
       fieldDate1.setValue(null);
       fieldEmail.clear();  
    }
    @FXML
    private void LienVersMenu(ActionEvent event) {
    try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/Interface1.fxml"));
            Parent root = loader.load();
            btnQuitter1.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void RefreshSelf(ActionEvent event) {
        Refresh();
        viderLesChamps();
        fieldChercher1.clear();
    }
    @FXML
    private void modifierevent(ActionEvent event) {
          if ( (fieldMontant.getText().isEmpty()) || (fieldAdresse.getText().isEmpty())){
              Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait();
          }else{
              if (!ValidationMontant()){
              Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Montant invalide");
        alert.showAndWait();
          }else if(ValidationMontant()){
               Sponsor s ;
        s=table_sp.getSelectionModel().getSelectedItem();
             
        SponsorCRUD sc = new SponsorCRUD();
        String var1=s.getSponsore();
        float var2=Float.parseFloat(fieldMontant.getText());
        String var3=fieldAdresse.getText();
        LocalDate var4=s.getDateSignature();
        String var5=s.getEmail();
       
        s=table_sp.getSelectionModel().getSelectedItem();
        s.setSponsore(var1);
        s.setMontant(var2);
        s.setAdresse(var3);
        s.setDateSignature(var4);
        s.setEmail(var5);
        sc.modifierEntite(s,var1,var2,var3,var4,var5);
        Refresh();
        viderLesChamps();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Sponsor Modifié");
        alert.showAndWait();
              }
          }
    }
    public boolean ValidationEmail(){ 
        Pattern pattern = Pattern.compile("^[\\w!#$%&amp;'+//=?{|}~^-]+(?:\\.[\\w!#$%&amp;'+//=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher match = pattern.matcher(fieldEmail.getText());
        if(match.find() && match.group().equals(fieldEmail.getText()))
        { return true;
        }else {return false;
        }
    }
    public boolean ValidationMontant() {
    Pattern pattern = Pattern.compile("^([0-9]*)$");
        Matcher match; 
        match = pattern.matcher((fieldMontant.getText()));
        if(match.find() && match.group().equals(fieldMontant.getText()))
        { 
            return true;
        }
        else {
            return false;
        }
}
    public boolean ValidationDateSign() {
        LocalDate aa= LocalDate.now();
        if(fieldDate1.getValue().isEqual(aa))
        { 
            return true;
        }
        else {
            return false;
        }
}
    public boolean exiteNomsponsor(){
        String newSponsorName = fieldSponsore.getText();
        String newSponsorEmail = fieldEmail.getText();
        boolean sponsorExists = false;
    for (Sponsor sponsor : table_sp.getItems()) { 
        if (sponsor.getSponsore().equals(newSponsorName) || sponsor.getEmail().equals(newSponsorEmail)) {
            sponsorExists = true;
            break;
        }
    }
    return sponsorExists;
    }
    
    @FXML
    private void ajouterEv(ActionEvent event) throws MessagingException {
      if ((fieldSponsore.getText().isEmpty()) || (fieldMontant.getText().isEmpty()) || (fieldAdresse.getText().isEmpty()) || (fieldDate1.getValue()==null)){
       Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.showAndWait(); 
        }
        else{
          if (!ValidationEmail()){
           Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Email invalide");
        alert.showAndWait(); 
          }
          if(exiteNomsponsor()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Ce sponsor existe déja");
        alert.showAndWait();
          }
          if (!ValidationMontant()){
              Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Montant invalide");
        alert.showAndWait();
          }
          if (!ValidationDateSign()){
              Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir la date d'aujourdhui !");
        alert.showAndWait();
          }
          else if (ValidationEmail() && ValidationMontant() && ValidationDateSign() && !exiteNomsponsor()){
        SponsorCRUD sc = new SponsorCRUD();  
        String var1=fieldSponsore.getText();
        float var2=Float.parseFloat(fieldMontant.getText());
        String var3=fieldAdresse.getText();
        String var4=fieldEmail.getText();
        Sponsor s =new Sponsor();  
        s.setId(78);
        s.setSponsore(var1);
        s.setMontant(var2);
        s.setAdresse(var3);
        s.setDateSignature(fieldDate1.getValue());
        s.setEmail(var4);
        sc.ajouterEntitee(s);
        System.out.println("ajouté");
        sendMail(fieldEmail.getText());
        Refresh();
        viderLesChamps();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attention ");
        alert.setHeaderText(null);
        alert.setContentText("Sponsor Ajouté");
        alert.showAndWait();  
      }
      }
    }

    @FXML
    private void supprimerEvenement(ActionEvent event) {
        SponsorCRUD  spn = new  SponsorCRUD ();
           Alert alert = new Alert(Alert.AlertType.ERROR);
             Sponsor s1= new Sponsor();
              s1= table_sp.getSelectionModel().getSelectedItem();
           alert.setTitle("Confirmation de suppression");
           alert.setHeaderText(null);
           alert.setContentText(String.valueOf("Etes vous sur de supprimer ce sponsor: "+s1.getSponsore()));
           ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
           ButtonType buttonTypeOk = new ButtonType("OK", ButtonData.CANCEL_CLOSE);
           alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);
           Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeOk){
                spn.supprimerEntite(s1);
                Refresh();}
    }
    
    private void filterProduits(FilteredList<Sponsor> filteredList, String searchValue) {
    filteredList.setPredicate(sponsor -> {
        if (searchValue == null || searchValue.isEmpty()) {
            return true;
        }
        String lowerCaseFilter = searchValue.toLowerCase();
        if (sponsor.getSponsore().toLowerCase().contains(lowerCaseFilter)) {
            return true; // filtre sur le nom du produit
        } 
        return false; // ne correspond pas au filtre
    });
}
    
   /* public void updateItem(Sponsor item, boolean empty) {
        table_sp.setRowFactory(tv -> new TableRow<Sponsor>() {
        if (item == null) {
            setStyle("");
        } else if (item.getAdresse().equals("1070")) {
            setStyle("-fx-background-color: tomato;");
        } else {
            setStyle("");
        }});
    }*/

    @FXML
    private void BtnVersEvenements(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/ListeEvenements.fxml"));
            Parent root = loader.load();
            btnListesDesEvenemets1.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void act(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/ListeSponsor.fxml"));
            Parent root = loader.load();
            btnQuitter1.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void gett(ActionEvent event) throws IOException, SQLException{
           try {
               Sponsor s;
            s= table_sp.getSelectionModel().getSelectedItem();
               FXMLLoader loader=new FXMLLoader(getClass().getResource("/edu/fithnitek/gui/Detailss.fxml"));
            Parent root = loader.load();
            DetailssController dc=loader.getController();
            dc.setIdchamp(String.valueOf(s.getId()));
            dc.setSponsorchamp(s.getSponsore());
            dc.setMontantchamp(String.valueOf(s.getMontant()));
            dc.setAdresseChamp(s.getAdresse());
            dc.setDatesignatureChamp(s.getDateSignature().toString());
            dc.setEmailChamp(s.getEmail());
            dc.show();
            dc.miaaw();
            fieldEmail.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex);
        }       
    }

    @FXML
    private void ToPdf(ActionEvent event) {
     Sponsor s = table_sp.getSelectionModel().getSelectedItem();

        PDF pd=new PDF();
        try{
            pd.GeneratePdf("Sponsor",s,s.getId());
        } catch (Exception ex) {
            System.out.println(ex);
       }}
    
}
