/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author PC
 */
public class categorie {
    int id;
    String nom_c,type;
  

    public categorie() {
    }

    public categorie(int id, String nom_c, String type) {
        this.id = id;
        this.nom_c = nom_c;
        this.type = type;
    }

    public categorie(String nom_c, String type) {
        this.nom_c = nom_c;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    public String getNom_c() {
        return nom_c;
    }

    public void setNom_c(String nom_c) {
        this.nom_c = nom_c;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "categorie{" + "id=" + id + ", nom_c=" + nom_c + ", type=" + type + '}';
    }

     

  
    
    
}
