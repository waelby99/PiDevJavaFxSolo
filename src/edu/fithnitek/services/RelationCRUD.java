/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fithnitek.services;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.fithnitek.utils.MyConnection;
import edu.fithnitek.entities.Relationn;
import edu.fithnitek.entities.Sponsor;
import edu.fithnitek.entities.Evenement;
/**
 *
 * @author waelb
 */
public class RelationCRUD{
    public void ajouterEntitee(Relationn r) {
        try{
        String requte="INSERT INTO relation1(id_evenement,id_sponsor)"
                + "VALUES (?,?)";
        PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requte);
        pst.setInt(1,r.getId_evenement());
        pst.setInt(2,r.getId_sponsor());
        
        pst.executeUpdate();
        System.out.println("Done");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }    
        
    }
    public List<Evenement> afficher(Evenement eff) {
      List<Evenement> myList1= new ArrayList<>();
        String query = "SELECT c.* FROM evenement c "
                + "INNER JOIN relation1 rr ON rr.id_evenement = c.id "
                + "INNER JOIN sponsoring s ON s.id=rr.id_sponsor"
                +"WHERE s.id="+eff.getId()+"";
        
        try{
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println(query);
            while (rs.next()){
                Evenement e = new Evenement();
                e.setId(rs.getInt(1));
                e.setLieu(rs.getString(2));
                e.setDatee(rs.getDate(3).toLocalDate());
                e.setTitre(rs.getString(4));
                e.setDescription(rs.getString(5));
                e.setNbparticipants(rs.getInt(6));
                myList1.add(e);
                
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return myList1; 
    }
    public List<Sponsor> afficher1(Sponsor sp) {
      List<Sponsor> myList= new ArrayList<>();
        String query = "SELECT s.* FROM sponsoring s "
                + "INNER JOIN relation1 rr ON rr.id_sponsor = s.id "
                + "INNER JOIN evenement e ON e.id=rr.id_evenement"
                + "WHERE e.id="+sp.getId()+"";
        try{
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(query);
            System.out.println(query);
            while (rs.next()){
                Sponsor s = new Sponsor();
                s.setId(rs.getInt(1));
                s.setSponsore(rs.getString(2));
                s.setMontant(rs.getFloat(3));
                s.setAdresse(rs.getString(4));
                s.setDateSignature(rs.getDate(5).toLocalDate());
                s.setEmail(rs.getString(6));
                myList.add(s);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return myList; 
    }
}
