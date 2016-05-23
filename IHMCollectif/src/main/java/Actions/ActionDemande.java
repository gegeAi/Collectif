/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;
import Controler.ControlerServlet;
import static Controler.ControlerServlet.servMetier;
import fr.insalyon.dasi.collectif.metier.modele.Activite;
import fr.insalyon.dasi.collectif.metier.service.ServiceMetier;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Samuel Toko
 */
public class ActionDemande extends Action{
    
    @Override
    public void execute(HttpServletRequest request, PrintWriter out) {
        List<Activite> activites;
        try {
            activites = servMetier.listerActivites();
            request.setAttribute( "activites", activites );
            Serialization.printListeActivite(out, activites);
        } catch (Throwable ex) {
            Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
}
