/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.collectif.metier.modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 *
 * @author lboucaud
 */
@Entity
public class EvenementEquipes extends Evenement {
    @OneToMany //(mappedBy="evenementA")
    @JoinTable(name = "equipeA_Id")
    private List<Adherent> equipeA;
    
    @OneToMany //(mappedBy="evenementB")
    @JoinTable(name = "equipeB_Id")
    private List<Adherent> equipeB;

    public EvenementEquipes(Activite activite, Date date, Lieu lieu) {
        super(activite, date, lieu);
        equipeA=new ArrayList<Adherent>();
        equipeB=new ArrayList<Adherent>();
    }
    
    public List<Adherent> getEquipeA() {
        return equipeA;
    }
    
    public List<Adherent> getEquipeB() {
        return equipeB;
    }

    public List<Adherent> getParticipants() {
        return null;
    }
 

    public EvenementEquipes() {
    }

    @Override
    public String toString() {
        
        if(this.statut==Etat.TRAITE)
        {
            return "EvenementEquipes{" + "id=" + id + ", activite=" + activite + ", date=" + date + ", lieu=" + lieu.getDenomination() + ", nbParticipants=" + nbParticipants + ", état : " + statut +  '}';

        }
        else
        {
            return "EvenementEquipes{" + "id=" + id + ", activite=" + activite + ", date=" + date +  ", nbParticipants=" + nbParticipants + ", état : " + statut + '}';

        }
    }
    
    
    
}
