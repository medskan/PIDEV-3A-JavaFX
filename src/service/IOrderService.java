/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.order;
import java.util.List;

/**
 *
 * @author PC
 */
public class IOrderService {
    public interface iOrderService<O> {

     public void supprimer(order o);
      public List<order> recuperer();
      public List<order> Rechercher(String recherche);
        public List recupererOrderDetails(order o);
   
}}
