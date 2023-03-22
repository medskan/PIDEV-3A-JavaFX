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
public class orderdetail {
     private int id;
    private int quantity;
    private float prix;
    private produit produit;
    private  int id_order;
    public orderdetail() {
    }

    public orderdetail(int id, int quantity, float prix, produit produit, int id_order) {
        this.id = id;
        this.quantity = quantity;
        this.prix = prix;
        this.produit = produit;
        this.id_order = id_order;
    }

    public orderdetail(int quantity, float prix, produit produit, int id_order) {
        this.quantity = quantity;
        this.prix = prix;
        this.produit = produit;
        this.id_order = id_order;
    }

    public orderdetail(int id, int quantity, float prix, produit produit) {
        this.id = id;
        this.quantity = quantity;
        this.prix = prix;
        this.produit = produit;
    }
 

    

     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public produit getProduit() {
        return produit;
    }

    public void setProduit(produit produit) {
        this.produit = produit;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    @Override
    public String toString() {
        return "orderdetail{" + "id=" + id + ", quantity=" + quantity + ", prix=" + prix + ", produit=" + produit + ", id_order=" + id_order + '}';
    }

   
   
}
