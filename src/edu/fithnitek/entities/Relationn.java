/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.fithnitek.entities;

/**
 *
 * @author waelb
 */
public class Relationn {
    
    private int id;
    private int id_sponsor;
    private int id_evenement;

    public Relationn() {
    }

    public Relationn(int id, int id_sponsor, int id_evenement) {
        this.id = id;
        this.id_sponsor = id_sponsor;
        this.id_evenement = id_evenement;
    }
    
    public int getId() {
        return id;
    }

    public int getId_sponsor() {
        return id_sponsor;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_sponsor(int id_sponsor) {
        this.id_sponsor = id_sponsor;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    @Override
    public String toString() {
        return "Relation{" + "id=" + id + ", id_sponsor=" + id_sponsor + ", id_evenement=" + id_evenement + '}';
    }
}
