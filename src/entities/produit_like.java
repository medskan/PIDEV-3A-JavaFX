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
public class produit_like {
    int id,abonne_id;
    produit produit;

    public produit_like() {
    }

    public produit_like(int abonne_id, produit produit) {
        this.abonne_id = abonne_id;
        this.produit = produit;
    }

    
    public produit_like(int id, int abonne_id, produit produit) {
        this.id = id;
        this.abonne_id = abonne_id;
        this.produit = produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAbonne_id() {
        return abonne_id;
    }

    public void setAbonne_id(int abonne_id) {
        this.abonne_id = abonne_id;
    }

    public produit getProduit() {
        return produit;
    }

    public void setProduit(produit produit) {
        this.produit = produit;
    }
    
    
}
