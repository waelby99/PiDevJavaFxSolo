/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fithnitek.services;

import java.util.List;

import edu.fithnitek.entities.Evenement;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import edu.fithnitek.utils.MyConnection;

/**
 *
 * @author waelb
 */
public class EvenementCRUD{

   
    
    public void ajouterEntitee(Evenement t) {
    try{
        String requte="INSERT INTO evenement(lieu,date,titre,description,nbparticipants)"
                + "VALUES (?,?,?,?,?)";
        PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requte);
        pst.setString(1,t.getLieu());
        pst.setObject(2,java.sql.Date.valueOf(t.getDatee()));
        pst.setString(3,t.getTitre());
        pst.setString(4, t.getDescription());
        pst.setInt(5, t.getNbparticipants());
        pst.executeUpdate();
        System.out.println("Done");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }    
    }

   
    public List<Evenement> listeDesEntites() {
        List<Evenement> myList = new ArrayList<>();
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

   
    public void supprimerEntite(Evenement t) {
                String query = "DELETE FROM evenement WHERE evenement.id = " + t.getId() + "";
        try{
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(query);
            System.out.println("Evenement Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    
    public void modifierEntite (Evenement t,String x,LocalDate y,String z,String a,int q) {
        try {
            String requte = "update evenement set lieu=?,date=?,titre=?,description=?, nbparticipants=? where id="+ t.getId()+"";
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requte);
            pst.setString(1, t.getLieu());
            pst.setObject(2, t.getDatee());
            pst.setString(3, t.getTitre());
            pst.setString(4, t.getDescription());
            pst.setInt(5, t.getNbparticipants());
            pst.executeUpdate();
            System.out.println("Evenement modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}
    public void modifierEntitt (Evenement t) {
        try {
            String requte = "update evenement set  nbparticipants=nbparticipants+1 where id="+ t.getId()+"";
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requte);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}
}