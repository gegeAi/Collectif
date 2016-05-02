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
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 *
 * @author lboucaud
 */
@Entity
public class EvenementIndividuel extends Evenement {
   
    @OneToMany
    private List<Adherent> participants;

    public EvenementIndividuel(Activite activite, Date date, Lieu lieu) {
        super(activite, date, lieu);
        participants=new ArrayList<Adherent>();
    }

    public List<Adherent> getParticipants() {
        return participants;
    }

     public List<Adherent> getEquipeA() {
        return null;
    }

    public List<Adherent> getEquipeB() {
        return null;
    }
  
    
    public EvenementIndividuel() {
    }

    @Override
    public String toString() {
        
        if(this.statut==Etat.TRAITE)
        {
            return "EvenementIndividuel{" + "id=" + id + ", activite=" + activite + ", date=" + date + ", lieu=" + lieu.getDenomination() + ", nbParticipants=" + nbParticipants + ", état : " + statut +  '}';

        }
        else
        {
            return "EvenementIndividuel{" + "id=" + id + ", activite=" + activite + ", date=" + date +  ", nbParticipants=" + nbParticipants + ", état : " + statut +  '}';

        }
    }
    
    
}
