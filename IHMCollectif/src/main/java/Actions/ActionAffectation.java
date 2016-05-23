/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;

import fr.insalyon.dasi.collectif.metier.modele.Evenement;
import fr.insalyon.dasi.collectif.metier.modele.Lieu;
import fr.insalyon.dasi.collectif.metier.service.ServiceMetier;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jerome
 */
public class ActionAffectation extends Action{

    @Override
    public void execute(HttpServletRequest request, PrintWriter out) {
        ServiceMetier sm = new ServiceMetier();
        
        try {
            Lieu l = sm.rechercherLieuId(Long.parseLong(request.getParameter("lieu").split(" ")[0]));
            Evenement e = sm.rechercherEvenementId(Long.parseLong(request.getParameter("event").split(" ")[0]));
            sm.affecterLieu(l, e);
        } catch (Throwable ex) {
            Logger.getLogger(ActionAffectation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
