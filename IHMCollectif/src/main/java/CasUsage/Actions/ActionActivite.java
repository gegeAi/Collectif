/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasUsage.Actions;

import CasUsage.ControlerServlet;
import static CasUsage.ControlerServlet.servMetier;
import fr.insalyon.dasi.collectif.metier.modele.Activite;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Samuel Toko
 */
public class ActionActivite extends Action {

    @Override
    public void execute(HttpServletRequest request, PrintWriter out) {
        List<Activite> activites;
        try {
            activites = servMetier.listerActivites();
            
            Activite activiteEnCours = null;
            String type = request.getParameter("type");
            request.setAttribute( "type", type );
            for (Activite activite : activites) {
                if (activite.getDenomination().equals(type))     
                    activiteEnCours = activite;
            }
                request.setAttribute( "activite", activiteEnCours );
        } catch (Throwable ex) {
            Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
