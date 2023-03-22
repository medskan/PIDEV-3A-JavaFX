/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class Abonnement {

  
    private int idab;
    private String typeA;
    private double tarifA;
    private Date dateDebut,dateFin;

    public Abonnement() {
    }

    public Abonnement(int idab, String typeA, double tarifA, Date dateDebut, Date dateFin) {
        this.idab = idab;
        this.typeA = typeA;
        this.tarifA = tarifA;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Abonnement(String typeA, double tarifA, Date dateDebut, Date dateFin) {
        this.typeA = typeA;
        this.tarifA = tarifA;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getIdab() {
        return idab;
    }

    public void setIdab(int idab) {
        this.idab = idab;
    }

    public String getTypeA() {
        return typeA;
    }

    public void setTypeA(String typeA) {
        this.typeA = typeA;
    }

    public double getTarifA() {
        return tarifA;
    }

    public void setTarifA(double tarifA) {
        this.tarifA = tarifA;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "idab=" + idab + ", typeA=" + typeA + ", tarifA=" + tarifA + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }

   
    
    
}
