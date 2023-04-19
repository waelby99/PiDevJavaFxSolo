/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fithnitek.entities;

import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.Button;


/**
 *
 * @author waelb
 */
public class Sponsor {
    
    private int id;
    private String sponsore;
    private float montant;
    private String adresse;
    private LocalDate dateSignature;
    private String email;
   // private Button btn;
    
    public Sponsor(){
        
    }
    public Sponsor(int id,String sponsore){
        this.id=id;
        this.sponsore=sponsore;
    }
    public Sponsor(int id,String sponsore,float montant,String adresse,LocalDate dateSignature,String email){
        this.id=id;
        this.sponsore=sponsore;
        this.montant=montant;
        this.adresse=adresse;
        this.dateSignature=dateSignature;
        this.email=email;
    }
    public Sponsor(String sponsore,float montant,String adresse,LocalDate dateSignature,String email){
        this.sponsore=sponsore;
        this.montant=montant;
        this.adresse=adresse;
        this.dateSignature=dateSignature;
        this.email=email;
    }
    /*public Sponsor(String sponsore,float montant,String adresse,LocalDate dateSignature,String email,Button btn){
        this.sponsore=sponsore;
        this.montant=montant;
        this.adresse=adresse;
        this.dateSignature=dateSignature;
        this.email=email;
        this.btn=new Button("dgfuyy");
    }*/
    public int getId() {
        return id;
    }
    /*public Button getBtn(){
        return btn;
    }*/
    public String getSponsore() {
        return sponsore;
    }

    public float getMontant() {
        return montant;
    }

    public String getAdresse() {
        return adresse;
    }

    public LocalDate getDateSignature() {
        return dateSignature;
    }

    public String getEmail() {
        return email;
    }
   /*public void setBtn(Button btn){
        this.btn=btn;
    }*/
    

    public void setId(int id) {
        this.id = id;
    }

    public void setSponsore(String sponsore) {
        this.sponsore = sponsore;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDateSignature(LocalDate dateSignature) {
        this.dateSignature = dateSignature;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    @Override
    public String toString() {
        return  sponsore ;
    }
    
}
