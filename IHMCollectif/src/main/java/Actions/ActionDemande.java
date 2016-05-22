/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;
import Controler.ControlerServlet;
import static Controler.ControlerServlet.servMetier;
import fr.insalyon.dasi.collectif.metier.modele.Activite;
import fr.insalyon.dasi.collectif.metier.modele.Adherent;
import fr.insalyon.dasi.collectif.metier.modele.Demande;
import fr.insalyon.dasi.collectif.metier.service.ServiceMetier;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Samuel Toko
 */
public class ActionDemande extends Action{
    
    @Override
    public void execute(HttpServletRequest request, PrintWriter out) {
        
        HttpSession session = request.getSession(true); 
        Adherent adherentConnexion = (Adherent) session.getAttribute("client");
        String idString = request.getParameter("activite");
        int id = Integer.parseInt(idString); 
        boolean reussite = false;
        Activite activiteEnCours = null;
        List<Activite> listAct;
        try {
            listAct = servMetier.listerActivites();
            for (Activite activite : listAct) {
                if (activite.getId() == id)     
                    activiteEnCours = activite;
            }
                String heure = request.getParameter("heure");
                String dateString = request.getParameter("date")+"-"+heure;
                dateString.concat(heure);

                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy-HH:mm");
                try {

                    Date date = formatter.parse(dateString);
                    System.out.println(date);
                    System.out.println(formatter.format(date));
                    System.out.println(activiteEnCours.getDenomination());

                    Demande d = servMetier.creerDemande(adherentConnexion, activiteEnCours, date);
                    
                    
                    if(d != null)
                       reussite = true;
                        
                    Serialization.printInscriptionAdherent(out, reussite);

                } catch (Exception e) {
                        e.printStackTrace();
                } catch (Throwable ex) {
                    Logger.getLogger(ActionDemande.class.getName()).log(Level.SEVERE, null, ex);
                }
        } catch (Throwable ex) {
            Logger.getLogger(ActionDemande.class.getName()).log(Level.SEVERE, null, ex);
        }      
        //request.setAttribute( "activites", activites );

    }
    
}
