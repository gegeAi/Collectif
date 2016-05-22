/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import Controler.ControlerServlet;
import static Controler.ControlerServlet.servMetier;
import fr.insalyon.dasi.collectif.metier.modele.Adherent;
import fr.insalyon.dasi.collectif.metier.service.ServiceTechnique;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jliermann
 */
public class ActionInscriptionAdherent extends Action{

    @Override
    public void execute(HttpServletRequest request, PrintWriter out) {
        try {
            System.out.println("------EXECUTE------");
            ServiceTechnique st = new ServiceTechnique();
            String email = request.getParameter("mail");
            String adresse = request.getParameter("adresse");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            System.out.println("mail "+email);
            boolean reussite = true;
            Adherent inscrit = servMetier.ajouterAdherent(nom, prenom, adresse, email);
            if(inscrit == null)
            {
                reussite=false;
            }
            Serialization.printInscriptionAdherent(out, reussite);
        } catch (Throwable ex) {
            Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
