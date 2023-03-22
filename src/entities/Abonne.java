/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author yassine
 */
public class Abonne {

    @Override
    public String toString() {
        return "Abonne{" + "id_abonne=" + id_abonne + ", abonnement=" + abonnement + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", sexe=" + sexe + ", email=" + email + ", mdp=" + mdp + ", tel=" + tel + ", adresse=" + adresse + ", message=" + message + ", img=" + img + '}';
    }

    public int getId_abonne() {
        return id_abonne;
    }

    public void setId_abonne(int id_abonne) {
        this.id_abonne = id_abonne;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Abonne(int id_abonne, Abonnement abonnement, String nom, String prenom, int age, String sexe, String email, String mdp, int tel, String adresse, String message, String img) {
        this.id_abonne = id_abonne;
        this.abonnement = abonnement;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.email = email;
        this.mdp = mdp;
        this.tel = tel;
        this.adresse = adresse;
        this.message = message;
        this.img = img;
    }

    public Abonne() {
    }
private int id_abonne;
   private Abonnement abonnement; 
   private String nom;
   private String prenom;
   private int age;
   private String sexe;
    private String email;
     private String mdp;
     private int tel;
     private String adresse;
     private String message;
     private String img;
     
}
