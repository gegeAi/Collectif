/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.collectif.metier.service;

import com.google.maps.model.LatLng;
import fr.insalyon.dasi.collectif.dao.JpaUtil;
import fr.insalyon.dasi.collectif.dao.ResponsableDao;
import fr.insalyon.dasi.collectif.metier.modele.Adherent;
import fr.insalyon.dasi.collectif.metier.modele.Evenement;
import fr.insalyon.dasi.collectif.metier.modele.Responsable;
import static fr.insalyon.dasi.collectif.util.GeoTest.getFlightDistanceInKm;
import java.util.List;

/**
 *
 * @author lboucaud
 */
public class ServiceTechnique {
    
    public enum Type_User {UTILISATEUR,RESPONSABLE};
    
    public Adherent adresseMailExisteAdherent(String a) throws Throwable
    {
        ServiceMetier serv = new ServiceMetier();
        List<Adherent> liste = serv.listerAdherents();
        
        for (Adherent ad : liste)
        {
            if (ad.getMail().equals(a))
            {
                return ad;
            }
        }
        
        return null;
    }
    
    public Responsable adresseMailExisteResponsable(String a) throws Throwable
    {
        ServiceMetier serv = new ServiceMetier();
        JpaUtil.creerEntityManager();
        ResponsableDao rDao=new ResponsableDao();
        
        
        
        List<Responsable> liste = rDao.findAll();
        JpaUtil.fermerEntityManager();
        
        for (Responsable r : liste)
        {
            if (r.getMail().equals(a))
            {
                return r;
            }
        }
        
        return null;
    }
    
    public Adherent authentifierAdherent(String mail) throws Throwable
    {
        
           Adherent a =adresseMailExisteAdherent(mail);
           if((a!=null)/*&&(a.getMotDePasse().equals(mdp))*/)
           {
               return a;
          }
           else
           {
               return null;
           }
        
    }
    public Responsable authentifierResponsable(String mail,String mdp) throws Throwable
    {
      
            Responsable r=adresseMailExisteResponsable(mail);
            if((r!=null)&&(r.getMotDePasse().equals(mdp)))
           {
               return r;
           }
           else
           {
               return null;
           }
    }
      
    
    
    public void envoyerMailInscriptionAcceptee(Adherent a)
    {
        System.out.println("Expediteur : collectif@collectif.org");
        System.out.println("Pour : " + a.getMail());
        System.out.println("Sujet : Bienvenue chez Collect'IF");
        System.out.println("Corps :");
        System.out.println("Bonjour " + a.getPrenom() + ",");
        System.out.println("Nous vous confirmons votre adhésion à l'association COLLECT'IF. Votre numéro d'adhérent est : " + a.getId() + ".");
    }
    
    public void envoyerMailInscriptionRefusee(Adherent a)
    {
        System.out.println("Expediteur : collectif@collectif.org");
        System.out.println("Pour : " + a.getMail());
        System.out.println("Sujet : Bienvenue chez Collect'IF");
        System.out.println("Corps :");
        System.out.println("Bonjour " + a.getPrenom() + ",");
        System.out.println("Votre adhésion à l'association COLLECT'IF a malencontreusement échoué... Merci de recommencer ultérieurement.");
    }
    
    public void envoyerMailEvenement(Adherent a, Evenement e)
    {
        System.out.println("Expediteur : collectif@collectif.org");
        System.out.println("Pour : " + a.getMail());
        System.out.println("Sujet : Nouvel Évènement Collect'IF");
        System.out.println("Corps :");
        System.out.println("Bonjour " + a.getPrenom() + ",");
        System.out.println("    Comme vous l'aviez souhaité, COLLECT'IF organise un évènement de " + e.getActivite().getDenomination() + " le " + e.getDate() + ".");
        System.out.println("Vous trouverez ci-dessous les détails de cet évènement.\n");
        System.out.println("    Associativement vôtre,");
        System.out.println("        Le Responsable de l'Association\n\n");
        System.out.println("Evènement : " + e.getActivite().getDenomination());
        System.out.println("Date : " + e.getDate());
        System.out.println("Lieu : " + e.getLieu().getDenomination() + ", " + e.getLieu().getAdresse());
        System.out.println("(à " + getFlightDistanceInKm(new LatLng(a.getLatitude(), a.getLongitude()), new LatLng(e.getLieu().getLatitude(), e.getLieu().getLongitude())) + " km de chez vous)");
        
        if (e.getActivite().isParEquipe())
        {
            List<Adherent> eqA = e.getEquipeA();
            List<Adherent> eqB = e.getEquipeB();
            
            if (eqA.contains(a))
            {
                System.out.println("Vous jouerez avec : ");
                
                for (Adherent ad : eqA)
                {
                    if (ad.getId() != a.getId())
                    {
                        System.out.println("    " + ad.getPrenom() + " " + ad.getNom());
                    }
                }
                
                System.out.println("Contre : ");
                
                for (Adherent ad : eqB)
                {
                    System.out.println("    " + ad.getPrenom() + " " + ad.getNom());
                }
            }
            else
            {
                System.out.println("Vous jouerez avec : ");
                
                for (Adherent ad : eqB)
                {
                    if (ad.getId() != a.getId())
                    {
                        System.out.println("    " + ad.getPrenom() + " " + ad.getNom());
                    }
                }
                
                System.out.println("Contre : ");
                
                for (Adherent ad : eqA)
                {
                    System.out.println("    " + ad.getPrenom() + " " + ad.getNom());
                }
            }
        }
        else
        {
            System.out.println("Vous jouerez avec : ");
            
            List<Adherent> participants = e.getParticipants();
            
            for (Adherent ad : participants)
            {
                System.out.println("    " + ad.getPrenom() + " " + ad.getNom());
            }
        }
    }
}
