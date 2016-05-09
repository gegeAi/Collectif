/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasUsage.Actions;
import CasUsage.ControlerServlet;
import static CasUsage.ControlerServlet.servMetier;
import fr.insalyon.dasi.collectif.metier.modele.Activite;
import fr.insalyon.dasi.collectif.metier.service.ServiceMetier;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Samuel Toko
 */
public class ActionListeActivite extends Action{
    
    @Override
    public void execute(HttpServletRequest request) {
        List<Activite> activites;
        try {
            activites = servMetier.listerActivites();
            request.setAttribute( "activites", activites );
        } catch (Throwable ex) {
            Logger.getLogger(ControlerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
}
