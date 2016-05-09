/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasUsage.Actions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.collectif.metier.modele.Activite;
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
    
}
