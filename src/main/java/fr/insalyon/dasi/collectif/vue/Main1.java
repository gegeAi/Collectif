/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.collectif.vue;


import fr.insalyon.dasi.collectif.metier.modele.Activite;
import fr.insalyon.dasi.collectif.metier.modele.Adherent;
import fr.insalyon.dasi.collectif.metier.modele.Demande;
import fr.insalyon.dasi.collectif.metier.modele.Evenement;
import fr.insalyon.dasi.collectif.metier.modele.Lieu;
import fr.insalyon.dasi.collectif.metier.modele.Responsable;
import fr.insalyon.dasi.collectif.metier.service.ServiceMetier;
import fr.insalyon.dasi.collectif.metier.service.ServiceTechnique;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Laurent
 */
public class Main1 {
    public static void main(String[] args) throws Throwable 
    {
        int choix;
        Scanner sc=new Scanner(System.in);
        ServiceMetier servM=new ServiceMetier();
        ServiceTechnique servT=new ServiceTechnique();
    	Adherent ad = null;
    	Responsable resp = null;
        
        do{
            System.out.println("Options:");
            System.out.println("1. Creer adherent ");
            System.out.println("2. S'authentifier ");
            System.out.println("3. Liste des activités ");
            System.out.println("4. Liste des evenements");
            System.out.println("5. Participants a un evenement");
            System.out.println("6. Liste des evenements en attente d'un lieu");
            System.out.println("7. Liste des lieux");
            System.out.println("8. Liste des lieux par type");
            System.out.println("9. Affecter Lieu"); 
            System.out.println("10.Liste des adherents");
            System.out.println("11. Afficher l'historique des demandes");  
            System.out.println("12. Liste des evenements dont le lieu est affecté");
            System.out.println("13. Quitter");
            choix=sc.nextInt();
            sc.nextLine();
            switch (choix) 
            {
                case 1:
                    System.out.println("Entrez votre nom : ");
                    String nom;
                    nom= sc.nextLine();

                    System.out.println("Entrez votre prénom : ");
                    String prenom = sc.nextLine();

                    System.out.println("Entrez l'adresse de votre lieu de résidence : ");
                    String adresse = sc.nextLine();

                    System.out.println("Entrez votre adresse e-mail : ");
                    String email = sc.nextLine();

                    ad = servM.ajouterAdherent(nom, prenom, adresse, email);
                    System.out.println("L'adherent a été ajouté : ");
                    System.out.println(ad);
                    
                    
                break;

                case 2:
                        System.out.println("Entrez votre mail ");
                        String mail=sc.nextLine();
                        ad=(servT.authentifierAdherent(mail));
                        if(ad!=null)
                        {
                            System.out.println("Vous êtes connectés");
                            System.out.println("Votre numero d'adherent:"+ad.getId());
                        }
                        else
                        {
                            System.out.println("Adresse mail incorrecte");
                        }
                break;

                case 3:
                     List<Activite> activites = servM.listerActivites();
                    for (Activite a : activites)
                    {
                        System.out.println(a.getDenomination() + " :");
                        System.out.println("    - identifiant de l'activité : " + a.getId());
                        System.out.println("    - Nombre de participants : " + a.getNbParticipants());
                        if (a.isParEquipe())
                        {
                            System.out.println("    - activité par équipes");
                        }
                        else
                        {
                            System.out.println("    - activité individuelle");
                        }
                    }
                    
                break;

                case 4:
                    List<Evenement> evenements = servM.listerEvenements();
		       		
		       		for (Evenement e : evenements)
		       		{
                                        System.out.println("Evenement:");
		       			System.out.println("- identifiant : " + e.getId());
		       			System.out.println("- activité : " + e.getActivite());
		       			System.out.println("- date : " + e.getDate() + "\n");
		       		}
                break;

                case 5:
                    System.out.println("Rentrez un identifiant d'evenement:");
                    long id=sc.nextLong();
                    Evenement e=servM.rechercherEvenementId(id);
                    if(e.getActivite().isParEquipe())
                    {
                         List<Adherent> equipeA =e.getEquipeA();
                         List<Adherent> equipeB =e.getEquipeB();
                         System.out.println("Equipe A:");
                         for (Adherent a : equipeA)
                         {
                             System.out.println(a);
                         }
                         System.out.println("Equipe B:");
                         for (Adherent a : equipeB)
                         {
                             System.out.println(a);
                         }
                    }
                    else
                    {
                        List<Adherent> participants =e.getParticipants();
                        System.out.println("Participants:");
                        for (Adherent a : participants)
                         {
                             System.out.println(a);
                         }
                    }
                   
                break;

                case 6:
                    List<Evenement> evenementsA = servM.listerEvenementsPlanifies();
		       		
		       		for (Evenement p : evenementsA)
		       		{
		       			System.out.println("- identifiant : " + p.getId());
		       			System.out.println("- activité : " + p.getActivite());
		       			System.out.println("- date : " + p.getDate() + "\n");
		       		}
                break;

                case 7:
                    List<Lieu> lieux = servM.listerLieux();
		       		
		       		for (Lieu l : lieux)
		       		{
		       			System.out.println("- identifiant : " + l.getId());
		       			System.out.println("- nom : " + l.getDenomination());
		       			System.out.println("- description : " + l.getDescription());
		       			System.out.println("- adresse : " + l.getAdresse() + "\n");
		       		}
                break;

                case 8:
                    System.out.println("Entrez un type de lieu");
                    String type=sc.nextLine();
                    List<Lieu> lieu = servM.listerLieuxParType(type);
		       		
		       		for (Lieu l : lieu)
		       		{
		       			System.out.println("- identifiant : " + l.getId());
		       			System.out.println("- nom : " + l.getDenomination());
		       			System.out.println("- description : " + l.getDescription());
		       			System.out.println("- adresse : " + l.getAdresse() + "\n");
		       		}
                break;

                case 9:
                    System.out.print("Indiquez l'identifiant de l'évènement : ");
		       		long idEv = sc.nextLong();
		       		System.out.print("Indiquez l'identifiant du lieu choisi : ");
		       		int idL = sc.nextInt();
		       		
		       		Evenement f = servM.rechercherEvenementId(idEv);
		       		Lieu l = servM.rechercherLieuId(idL);
		       		
		       		if (f != null && l != null)
		       		{
		       			servM.affecterLieu(l, f);
		       		}
		       		
		       		else
		       		{
				   		if (f == null)
				   		{
				   			System.out.println("Aucun évènement ne correspond à cet identifiant !");
				   		}
				   		else
				   		{
				   			System.out.println("Aucun lieu ne correspond à cet identifiant !");
				   		}
		       		}
                break;

                case 10:
                     List<Adherent> adherents =servM.listerAdherents();
                        System.out.println("Participants:");
                        for (Adherent a : adherents)
                         {
                             System.out.println(a);
                         }
                break;

                case 11:
                    System.out.println("Entrez un identifiant d'utilisateur");
                    long i=sc.nextLong();
                    List<Demande> demande = servM.listerDemandesParID(i);
		       		
		       		for (Demande d : demande)
		       		{
		       			System.out.println(d);
		       			
		       		}
                break;

                case 12:
                    List<Evenement> evenementsTraites = servM.listerEvenementsTraites();
		       		
		       		for (Evenement p : evenementsTraites)
		       		{
		       			System.out.println("- identifiant : " + p.getId());
		       			System.out.println("- activité : " + p.getActivite());
		       			System.out.println("- date : " + p.getDate() + "\n");
		       		}
                break;
                
                case 13:
                break;
                default:
                System.out.println("Choix incorrect");
                break;
            }
        }while(choix!=13);
        
    }
            
     
    
}
