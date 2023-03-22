/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
 

/**
 *
 * @author PC
 */
public class order {
    int id,numero_commande,id_c;
    float total;
    Date date;
    

    public order() {
    }

    public order(int id, int numero_commande, int id_c, float total, Date date) {
        this.id = id;
        this.numero_commande = numero_commande;
        this.id_c = id_c;
        this.total = total;
        this.date = date;
    }

    public order(int numero_commande, int id_c, float total, Date date) {
        this.numero_commande = numero_commande;
        this.id_c = id_c;
        this.total = total;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   

 
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero_commande() {
        return numero_commande;
    }

    public void setNumero_commande(int numero_commande) {
        this.numero_commande = numero_commande;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    

  

    @Override
    public String toString() {
        return "order{" + "id=" + id + ", numero_commande=" + numero_commande + ", id_c=" + id_c + ", total=" + total + ", date=" + date + '}';
    }

    

  


   
   
  
    
    
    
}
