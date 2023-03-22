/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.categorie;
import java.util.List;

/**
 *
 * @author PC
 */
public class ICategorieService {
    public interface iCategorieService<C> {
    public void ajouter(C t) ;
    public void modifier(int id,C t);
    public void supprimer(C t);
    public List<C> recuperer();
    public int getid_categorie_db(categorie c);
     public categorie readById(int id);
       public List<categorie> Rechercher(String recherche);
}}
