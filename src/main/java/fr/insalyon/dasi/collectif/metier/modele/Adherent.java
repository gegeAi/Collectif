package fr.insalyon.dasi.collectif.metier.modele;

import com.google.maps.model.LatLng;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

 
@Entity
public class Adherent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String mail;
   // private String motDePasse;
    private String adresse;
    private Double latitude;
    private Double longitude;
    
    /*@ManyToOne
    @JoinColumn(nullable=true)
    private Evenement evenementA;
    
    @ManyToOne
    @JoinColumn(nullable=true)
    private Evenement evenementB;*/
    

    public Adherent() {
    }

    public Adherent(String nom, String prenom, String adresse, String mail,/*String motDePasse,*/ Double latitude, Double longitude) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.adresse = adresse;
       //this.motDePasse=motDePasse;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }
    
  /*public String getMotDePasse() {
        return motDePasse;
    }*/
    

    public String getAdresse() {
        return adresse;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
  /*  public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }*/

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Adherent{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", adresse=" + adresse + ", latitude=" + latitude +", longitude=" + longitude +  '}';
    }
}