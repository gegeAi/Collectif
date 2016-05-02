/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.collectif.metier.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * @author lboucaud
 */

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public abstract class Evenement implements Serializable {


    
    public enum Etat {NON_PLANIFIE,PLANIFIE,TRAITE};
     
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    @Version
    @Column(nullable=false)
    private long version=0L;
    
    @OneToOne
    protected Activite activite;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    protected Date date;
    
    @OneToOne
    protected Lieu lieu;
    
    protected int nbParticipants;
    protected Etat statut;

    public Evenement(Activite activite,Date date, Lieu lieu) {
        this.activite = activite;
        this.date = date;
        this.lieu = lieu;
        this.nbParticipants = 0;
        this.statut=Etat.NON_PLANIFIE;
    }

    public Activite getActivite() {
        return activite;
    }

    public Etat getStatut() {
        return statut;
    }
    
    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public int getNbParticipants() {
        return nbParticipants;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public void setStatut(Etat statut) {
        this.statut = statut;
    }

    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    public Evenement() {
    }
    
    public abstract String toString();
    public abstract List<Adherent> getEquipeA();
    public abstract List<Adherent> getEquipeB();
    public abstract List<Adherent> getParticipants();
}
