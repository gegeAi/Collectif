/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.collectif.metier.service;

import com.google.maps.model.LatLng;
import fr.insalyon.dasi.collectif.dao.ActiviteDao;
import fr.insalyon.dasi.collectif.dao.AdherentDao;
import fr.insalyon.dasi.collectif.dao.DemandeDao;
import fr.insalyon.dasi.collectif.dao.EvenementDao;
import fr.insalyon.dasi.collectif.dao.JpaUtil;
import fr.insalyon.dasi.collectif.dao.LieuDao;
import fr.insalyon.dasi.collectif.dao.ResponsableDao;
import fr.insalyon.dasi.collectif.metier.modele.Activite;
import fr.insalyon.dasi.collectif.metier.modele.Adherent;
import fr.insalyon.dasi.collectif.metier.modele.Demande;
import fr.insalyon.dasi.collectif.metier.modele.Evenement;
import fr.insalyon.dasi.collectif.metier.modele.Evenement.Etat;
import fr.insalyon.dasi.collectif.metier.modele.EvenementEquipes;
import fr.insalyon.dasi.collectif.metier.modele.EvenementIndividuel;
import fr.insalyon.dasi.collectif.metier.modele.Lieu;
import fr.insalyon.dasi.collectif.metier.modele.Responsable;
import static fr.insalyon.dasi.collectif.util.GeoTest.getLatLng;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.OptimisticLockException;
import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

/**
 *
 * @author lboucaud
 */
public class ServiceMetier {
    
    public Adherent ajouterAdherent(String nom, String prenom, String adressePostale, String adresseMail/*, String motDePasse*/) throws Throwable
    {
        ServiceTechnique servT = new ServiceTechnique();
        
        LatLng coord = getLatLng(adressePostale);
        Adherent ad = new Adherent(nom, prenom, adressePostale, adresseMail/*, motDePasse*/, coord.lat, coord.lng);
        System.out.println(ad);
        
        if ((servT.adresseMailExisteAdherent(adresseMail) == null))
        {
            
            JpaUtil.creerEntityManager();

            AdherentDao adDao = new AdherentDao();
            
            try
        {
         JpaUtil.ouvrirTransaction();
           adDao.create(ad);

            JpaUtil.validerTransaction();
         
        }
        catch(Exception ex)//DerbySQLIntegrityConstraintViolationException
        {
            if(ex.getCause() instanceof DerbySQLIntegrityConstraintViolationException)
            {
               ajouterAdherent(nom, prenom, adressePostale, adresseMail);
                //System.out.println("echec");
            }
            
        }

           

            JpaUtil.fermerEntityManager();

            servT.envoyerMailInscriptionAcceptee(ad);

            return ad;
        }
        else
        {            
            servT.envoyerMailInscriptionRefusee(ad);
            
            return null;
        }
        
    }
    
    public void ajouterResponsable(String adresseMail, String motDePasse) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        Responsable ad = new Responsable(adresseMail, motDePasse);

        ResponsableDao respDao = new ResponsableDao();
        
        JpaUtil.ouvrirTransaction();
        
        respDao.create(ad);
        
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
    }
    
    public List<Activite> listerActivites() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        ActiviteDao acDao = new ActiviteDao();
        
        List<Activite> liste = acDao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return liste;
    }
    
    public List<Lieu> listerLieux() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        LieuDao lDao = new LieuDao();
        
        List<Lieu> liste = lDao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return liste;
    }
    
    public List<Lieu> listerLieuxParType (String type) throws Throwable
    {
        List<Lieu> liste = listerLieux();
        
        List<Lieu> listeParType = new ArrayList<Lieu>();
        
        for (Lieu l : liste)
        {
            if (l.getDescription().equals(type))
            {
                listeParType.add(l);
            }
        }
        
        return listeParType;
    }
    
    public Evenement creerEvenement(Demande demande) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        EvenementDao eDao=new EvenementDao();
        Evenement e;
        if(demande.getActivite().isParEquipe())
        {
            e=new EvenementEquipes(demande.getActivite(),demande.getDateActivite(),null);

        }
        else
        {
            e=new EvenementIndividuel(demande.getActivite(),demande.getDateActivite(),null);

        }
                    
             
        JpaUtil.ouvrirTransaction();
        eDao.create(e);
        
        
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
        
        return e;
    }
    
    public Demande creerDemande(Adherent ad, Activite ac, Date date) throws Throwable
    {
        DemandeDao dDao = new DemandeDao();
        EvenementDao eDao = new EvenementDao();
        
        JpaUtil.creerEntityManager();
        
        Demande d = new Demande(ac, ad, date, new Date());
        
        Evenement e = rechercherEvenement(d);
        
        
        ajouterParticipantEvenement(ad, e, d);
         
        JpaUtil.creerEntityManager();
        
        JpaUtil.ouvrirTransaction();
        
        dDao.create(d);
        
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
        
        return d;
    }
    
    public Evenement rechercherEvenement(Demande demande) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        EvenementDao eDao = new EvenementDao();
        
        List<Evenement> liste = eDao.findAll();
        
        Evenement e = null;
        Boolean trouve = false;
        
        for (Evenement eCur : liste)
        {
            if (eCur.getStatut()==Etat.NON_PLANIFIE&&eCur.getActivite().getId().equals(demande.getActivite().getId()) && (eCur.getDate().getYear()==demande.getDateActivite().getYear()) && (eCur.getDate().getMonth()==demande.getDateActivite().getMonth())&&(eCur.getDate().getDay()==demande.getDateActivite().getDay()))
            {
                trouve = true;
                e = eCur;
                break;
            }
        }
        
        if (trouve)
        {            
            JpaUtil.fermerEntityManager();
            
            return e;
        }
        else
        {
            e = creerEvenement(demande);
            
            return e;
        }
        
    }
    
    public Boolean ajouterParticipantEvenement(Adherent a,Evenement e,Demande demande) throws Throwable
    {
        if(e.getNbParticipants()<demande.getActivite().getNbParticipants())
        {
            List<Adherent> liste;
            
             if(demande.getActivite().isParEquipe())
            {
               if(e.getEquipeA().size()<demande.getActivite().getNbParticipants()/2)
               {
                   liste=e.getEquipeA();
               }
               else
               {
                   liste=e.getEquipeB();
               }

            }
             else 
            {
                  liste=e.getParticipants();
            }
             
            liste.add(a);
            Integer nb=e.getNbParticipants();
            nb++;
            e.setNbParticipants(nb);
            
            if (e.getNbParticipants() == demande.getActivite().getNbParticipants())
            {
                e.setStatut(Etat.PLANIFIE);
            }
            System.out.println("Appuyez sur une touche pour continuer");
            Scanner sc =new Scanner(System.in);
            sc.nextLine();
             
            JpaUtil.creerEntityManager();
        
            EvenementDao eDao = new EvenementDao();
             
            
            try
             {
                 JpaUtil.ouvrirTransaction();
             
                 eDao.update(e);
               
                JpaUtil.validerTransaction();
             }
             catch(Exception ex)
             {
                 if (ex.getCause()!=null && ex.getCause() instanceof OptimisticLockException)
                 {
                     return ajouterParticipantEvenement(a,rechercherEvenement(demande),demande);
                 }
             }
            
            
               
            JpaUtil.fermerEntityManager();
            
        
             return true;
             
             
        }
        else
        {
            return false;
        }

    }
    

    
    public List<Evenement> listerEvenementsTraites() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        EvenementDao eDao = new EvenementDao();
        
        List<Evenement> liste = eDao.findAll();
        
        List<Evenement> listeTraites = new ArrayList<Evenement>();
        
        for (Evenement e : liste)
        {
            if (e.getStatut() == Etat.TRAITE)
            {
                listeTraites.add(e);
            }
        }
        
        JpaUtil.fermerEntityManager();
        
        return listeTraites;
    }
    
    public List<Evenement> listerEvenementsPlanifies() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        EvenementDao eDao = new EvenementDao();
        
        List<Evenement> liste = eDao.findAll();
        
        List<Evenement> listePlanifies = new ArrayList<Evenement>();
        
        for (Evenement e : liste)
        {
            if (e.getStatut() == Etat.PLANIFIE)
            {
                listePlanifies.add(e);
            }
        }
        
        JpaUtil.fermerEntityManager();
        
        return listePlanifies;
    }
    
    
      public List<Evenement> listerEvenements() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        EvenementDao eDao = new EvenementDao();
        
        List<Evenement> liste = eDao.findAll();
        
        List<Evenement> listePlanifies = new ArrayList<Evenement>();
        
        for (Evenement e : liste)
        {
                listePlanifies.add(e);
        }
        
        JpaUtil.fermerEntityManager();
        
        return listePlanifies;
    }
    
    public void affecterLieu(Lieu l,Evenement e) throws Throwable
    {
        JpaUtil.creerEntityManager();
        EvenementDao eDao=new EvenementDao();
        e.setLieu(l);
        e.setStatut(Etat.TRAITE);
        JpaUtil.ouvrirTransaction();
        eDao.update(e);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        
        ServiceTechnique servT = new ServiceTechnique();
        
        if (e.getActivite().isParEquipe())
        {
            for (Adherent a : e.getEquipeA())
            {
                servT.envoyerMailEvenement(a, e);
            }
            
            for (Adherent a : e.getEquipeB())
            {
                servT.envoyerMailEvenement(a, e);
            }
        }
        else
        {
            for (Adherent a : e.getParticipants())
            {
                servT.envoyerMailEvenement(a, e);
            }
        }
    }
    
    
    
    
    
    public List<Adherent> listerAdherents() throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        AdherentDao adDao = new AdherentDao();
        
        List<Adherent> liste = adDao.findAll();
        
        JpaUtil.fermerEntityManager();
        
        return liste;
    }
    
     public List<Demande> listerDemandesParID(long id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        DemandeDao adDao = new DemandeDao();
        
        List<Demande> liste = adDao.findAll();
        List<Demande> sel=new ArrayList();
        for (Demande d : liste)
        {
            if (d.getAdherent().getId()==id)
            {
                sel.add(d);
            }
        }
        
        
        JpaUtil.fermerEntityManager();
        
        return sel;
    }
    
    public Activite ajouterActivite(String denomination, Boolean parEquipes, Integer nbParticipants) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        Activite ac = new Activite(denomination, parEquipes, nbParticipants);

        ActiviteDao acDao = new ActiviteDao();
        
        JpaUtil.ouvrirTransaction();
        
        acDao.create(ac);
        
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
        
        return ac;
    }
    
    public Lieu ajouterLieu(String denomination, String description, String adresse) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        LatLng coord = getLatLng(adresse);
        
        Lieu l = new Lieu(denomination, description, adresse, coord.lat, coord.lng);

        LieuDao lDao = new LieuDao();
        
        JpaUtil.ouvrirTransaction();
        
        lDao.create(l);
        
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
        
        return l;
    }
    
    public Activite rechercherActiviteId(Integer id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        ActiviteDao aDao = new ActiviteDao();
        
        Activite a = aDao.findById(id);
        
        
        JpaUtil.fermerEntityManager();
        
        return a;
    }
    
    public Adherent rechercherAdherentId(long id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        AdherentDao aDao = new AdherentDao();
        
        Adherent a = aDao.findById(id);
        
        JpaUtil.fermerEntityManager();
        
        return a;
    }
    
    public Lieu rechercherLieuId(long id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        LieuDao lDao = new LieuDao();
        
        Lieu l = lDao.findById(id);
        
        JpaUtil.fermerEntityManager();
        
        return l;
    }
    
    public Evenement rechercherEvenementId(long id) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        EvenementDao eDao = new EvenementDao();
        
        Evenement e = eDao.findById(id);
        
        JpaUtil.fermerEntityManager();
        
        return e;
    }
    
    // -------------------------------------------------------------------------

    public ServiceMetier() {
    }
    
}
