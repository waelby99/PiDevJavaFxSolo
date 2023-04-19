/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fithnitek.services;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import edu.fithnitek.utils.MyConnection;
import edu.fithnitek.entities.Sponsor;

/**
 *
 * @author waelb
 */
public class SponsorCRUD{

    
    
    public void ajouterEntitee(Sponsor t) {
        try{
        String requte="INSERT INTO sponsoring(sponsor,montant,adresse,dateSignature,email)"
                + "VALUES (?,?,?,?,?)";
        PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requte);
        pst.setString(1,t.getSponsore());
        pst.setFloat(2,t.getMontant());
        pst.setString(3,t.getAdresse());
        pst.setObject(4, java.sql.Date.valueOf(t.getDateSignature()));
        pst.setString(5, t.getEmail());
        pst.executeUpdate();
        System.out.println("Done");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }    
        
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

   
    public void supprimerEntite(Sponsor t) {
        String query = "DELETE FROM sponsoring WHERE sponsoring.id = " + t.getId() + "";
        try{
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(query);
            System.out.println("Sponsor Deleted Successfully");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    
    public void modifierEntite(Sponsor t,String aa,float bb,String cc,LocalDate dd,String ee) {
        try {
            String requte = "update sponsoring set sponsor=?,montant=?,adresse=?,dateSignature=? ,email=? where id="+t.getId();
            PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requte);
            pst.setString(1, t.getSponsore());
            pst.setFloat(2, t.getMontant());
            pst.setString(3, t.getAdresse());
            pst.setObject(4, t.getDateSignature());
            pst.setString(5,t.getEmail());
            
            pst.executeUpdate();
            System.out.println("Sponsor modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
