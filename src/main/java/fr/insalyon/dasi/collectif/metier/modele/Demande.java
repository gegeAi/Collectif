/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.collectif.metier.modele;

import com.google.maps.model.LatLng;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author lboucaud
 */

@Entity
public class Demande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Adherent adherent; // Adhérent ayant effectué la demande
    
    @OneToOne
    private Activite activite;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateActivite; // Date demandée par l'adhérent
    
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateDemande; // Date à laquelle l'adhérent a effectué sa demande

    public Demande()
    {
        
    }
    
    public Demande(Activite activite, Adherent adherent, Date dateActivite, Date dateDemande) {
        
        this.activite = activite;
        this. adherent = adherent;
        this.dateActivite = dateActivite;
        this.dateDemande = dateDemande;
    }

    public Long getId() {
        return id;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public Activite getActivite() {
        return activite;
    }

    public Date getDateActivite() {
        return dateActivite;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public void setDateActivite(Date dateActivite) {
        this.dateActivite = dateActivite;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    @Override
    public String toString() {
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        
        return "Demande{" + "id=" + id + ", adherent=" + adherent + ", activite=" + activite + ", date proposée=" + dateFormat.format(dateActivite) + ", date de la demande=" + dateFormat.format(dateDemande) + '}';
    }
}
