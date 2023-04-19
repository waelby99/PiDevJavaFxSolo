/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fithnitek.entities;


import java.time.LocalDate;

/**
 *
 * @author waelb
 */
public class Evenement {
    private int id;
    private String lieu;
    private LocalDate datee;
    private String titre;
    private String description;
    private int nbparticipants;
    
    
    public Evenement(){
        
    }
    public Evenement(int id){
        this.id=id;
    }
    public Evenement(int id,String lieu,LocalDate datee,String titre,String description,int nbparticipants){
        this.id=id;
        this.lieu=lieu;
        this.datee=datee;
        this.titre=titre;
        this.description=description;
        this.nbparticipants=nbparticipants;
    }
  /*  public Evenement(int id,String lieu,LocalDate datee,String titre,String description){
        this.id=id;
        this.lieu=lieu;
        this.datee=datee;
        this.titre=titre;
        this.description=description;
    
    }*/

    public void setNbparticipants(int nbparticipants) {
        this.nbparticipants = nbparticipants;
    }

    public int getNbparticipants() {
        return nbparticipants;
    }

    public Evenement(String lieu, LocalDate datee, String titre, String description) {
        this.lieu = lieu;
        this.datee = datee;
        this.titre = titre;
        this.description = description;
    }
    
    public Evenement(int id,String lieu,LocalDate datee,String titre,String description){
        this.id=id;
        this.lieu=lieu;
        this.datee=datee;
        this.titre=titre;
        this.description=description;
        
    }
    public int getId() {
        return id;
    }

    public String getLieu() {
        return lieu;
    }

    public LocalDate getDatee() {
        return datee;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDatee(LocalDate datee) {
        this.datee = datee;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", lieu=" + lieu + ", datee=" + datee + ", titre=" + titre + ", description=" + description +" } ";
    }    
}
