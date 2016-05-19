/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.collectif.metier.modele.Activite;
import fr.insalyon.dasi.collectif.metier.modele.Adherent;
import java.io.PrintWriter;
import java.util.List;
/**
 *
 * @author Samuel Toko
 */
public class Serialization {
    
    public static void printListeActivite( PrintWriter out, List<Activite> activites)
    {
        JsonArray jsonListe = new JsonArray();
        
        for(Activite a : activites)
        {
           JsonObject jsonActivite = new JsonObject();
           
           jsonActivite.addProperty("id",a.getId());
           jsonActivite.addProperty("denomination",a.getDenomination());
           jsonActivite.addProperty("nbParticipants",a.getNbParticipants());
           jsonActivite.addProperty("parEquipe", a.isParEquipe()? "oui":"non");
           
           jsonListe.add(jsonActivite);
        }
        
        // Objet JSON "Conteneur"
        JsonObject container = new JsonObject();
        container.add("activites", jsonListe);
        
        //Serialisation et ecriture dans le flot de sortie
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(container);
        out.println(json);
    }
    
    public static void printActivite( PrintWriter out, Activite activite)
    {

        

           JsonObject jsonActivite = new JsonObject();
           
           jsonActivite.addProperty("id",activite.getId());
           jsonActivite.addProperty("parEquipe", activite.isParEquipe()? "oui":"non");
           jsonActivite.addProperty("denomination",activite.getDenomination());
           jsonActivite.addProperty("nbParticipants",activite.getNbParticipants());
        
        // Objet JSON "Conteneur"
        JsonObject container = new JsonObject();
        container.add("activite", jsonActivite);
        
        //Serialisation et ecriture dans le flot de sortie
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(container);
        out.println(json);
        
    }

    static void printListeDemande(PrintWriter out, List<Activite> activites, List<Adherent> adherents) {
        
        JsonArray jsonListe = new JsonArray();
        
        for(Activite a : activites)
        {
           JsonObject jsonActivite = new JsonObject();
           
           jsonActivite.addProperty("id",a.getId());
           jsonActivite.addProperty("denomination",a.getDenomination());
           jsonActivite.addProperty("nbParticipants",a.getNbParticipants());
           jsonActivite.addProperty("parEquipe", a.isParEquipe()? "oui":"non");
           
           jsonListe.add(jsonActivite);
        }
        
        JsonArray jsonListeAdherent = new JsonArray();
        
        for(Adherent a : adherents)
        {
           JsonObject jsonAdherent = new JsonObject();
           
           jsonAdherent.addProperty("id",a.getId());
           jsonAdherent.addProperty("mail",a.getMail());
           jsonAdherent.addProperty("prenom",a.getPrenom());
           jsonAdherent.addProperty("adresse",a.getAdresse());
           jsonAdherent.addProperty("nom", a.getNom());
           
           jsonListeAdherent.add(jsonAdherent);
        }
        
        // Objet JSON "Conteneur"
        JsonObject container = new JsonObject();
        container.add("activites", jsonListe);
        container.add("adherent", jsonListeAdherent);
        
        //Serialisation et ecriture dans le flot de sortie
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(container);
        out.println(json);
    }

    static void printAdherents(PrintWriter out, Adherent a) {
        
        JsonObject jsonAdherent = new JsonObject();
           
        if(a==null)
        {
            jsonAdherent.addProperty("existe", false);
        }
        else
        {
           jsonAdherent.addProperty("id",a.getId());
           jsonAdherent.addProperty("mail",a.getMail());
           jsonAdherent.addProperty("prenom",a.getPrenom());
           jsonAdherent.addProperty("adresse",a.getAdresse());
           jsonAdherent.addProperty("nom", a.getNom());
           jsonAdherent.addProperty("existe", true);
        }
        // Objet JSON "Conteneur"
        JsonObject container = new JsonObject();
        container.add("adherent", jsonAdherent);
        
        //Serialisation et ecriture dans le flot de sortie
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(container);
        out.println(json);
        
    }
    
}
