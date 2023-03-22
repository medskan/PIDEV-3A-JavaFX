/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.produit;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 * @param <T>
 */
public interface iProduitService<T> {
  public void ajouter(produit t);
    public void modifier(int id,produit t) ;
   public int getid_produit_db(produit t);
    public int getid_produit(String n);
    public void supprimer(produit p) ;
    public List<produit> recuperer();
    public Map AddToCart(Map panier,produit p);
  
    public void AfficherPanier(Map panier);
   
    public List RecupererPanier(Map panier);
    public Map IncrementerQte(Map panier,produit p);
    public Map DecrementerQte(Map panier,produit p);
    public float calculerTotal(Map panier);
    
    public void validerPanier(Map panier) ;
     public List<produit> Rechercher(String recherche)  ;   
            
  public produit readById(int id_produit);
}
