/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import java.util.Objects;
import javafx.scene.image.Image;

/**
 *
 * @author PC
 */
public class produit {
      private int id_produit;
    private String description;
    private String nom_produit;
    private int quantite;
    private float prix_produit;
    private float promotion;
    private categorie categorie;
    private String image;
    private Image img;
    private int nl;

    public produit() {
    }

    public produit(String description, String nom_produit, int quantite, float prix_produit, float promotion, categorie categorie, String image) {
        this.description = description;
        this.nom_produit = nom_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.promotion = promotion;
        this.categorie = categorie;
        this.image = image;
    }

    public produit(String nom_produit, int quantite, float prix_produit) {
        this.nom_produit = nom_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
    }

    public produit(int id_produit, String description, String nom_produit, int quantite, float prix_produit, float promotion, categorie categorie, String image) {
        this.id_produit = id_produit;
        this.description = description;
        this.nom_produit = nom_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.promotion = promotion;
        this.categorie = categorie;
        this.image = image;
    }
   public produit(int id_produit,String nom_produit,categorie categorie, int quantite,  String description,float prix_produit, float promotion,String image) {
        this.id_produit = id_produit;
        this.description = description;
        this.nom_produit = nom_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.promotion = promotion;
        this.categorie = categorie;
        this.image = image;
    }

    public produit(int id_produit, String nom_produit, categorie categorie, float prix_produit, int quantite, float promotion) {
        this.id_produit = id_produit;
      
        this.nom_produit = nom_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.promotion = promotion;
        this.categorie = categorie;
     
    }

      public produit(int id_produit,String nom_produit,categorie categorie, int quantite,  String description,float prix_produit, float promotion,String image,int nl) {
        this.id_produit = id_produit;
        this.description = description;
        this.nom_produit = nom_produit;
        this.quantite = quantite;
        this.prix_produit = prix_produit;
        this.promotion = promotion;
        this.categorie = categorie;
        this.image = image;
        this.nl=nl;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(float prix_produit) {
        this.prix_produit = prix_produit;
    }

    public float getPromotion() {
        return promotion;
    }

    public void setPromotion(float promotion) {
        this.promotion = promotion;
    }

    public categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(categorie categorie) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final produit other = (produit) obj;
        if (other.id_produit != this.id_produit) {
            return false;
        }
        return true;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id_produit;
        return hash;
    }

    public int getNl() {
        return nl;
    }

    public void setNl(int nl) {
        this.nl = nl;
    }

    @Override
    public String toString() {
        return "produit{" + "id_produit=" + id_produit + ", description=" + description + ", nom_produit=" + nom_produit + ", quantite=" + quantite + ", prix_produit=" + prix_produit + ", promotion=" + promotion + ", categorie=" + categorie + ", image=" + image + ", img=" + img + ", nl=" + nl + '}';
    }



     
    
  
  
}
