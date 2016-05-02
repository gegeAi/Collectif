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
 * @author lboucaud
 */
public class Main
{
    
    public static void main(String[] args) throws Throwable 
    {
    	ServiceMetier servM=new ServiceMetier();
        Scanner scan=new Scanner(System.in);
   
        System.out.println("Entrez l'id de l'adherent");
        long id=scan.nextLong();
        Integer idActivite=18;
        Date demande=new Date();
        
        Activite a=servM.rechercherActiviteId(idActivite);
        
        Adherent b=servM.rechercherAdherentId(id);
        
   
       
        servM.creerDemande(b,a, demande);

        
    }
    
}  

 