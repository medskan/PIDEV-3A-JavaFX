/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Abonnement;
import java.util.List;

/**
 *
 * @author HP
 */
public interface iAbonnement {
    public void AjouterAbonnement(Abonnement c);
    public void SupprimerAbonnement(String type_a);
//public void ModifierTrip(Trip p);
         public List<Abonnement> afficher();
        //public void ModifierAbonnement(Abonnement c, String typeA);
        public int modifier (Abonnement c);
}
