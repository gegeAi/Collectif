/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;
import fr.insalyon.dasi.collectif.metier.modele.Adherent;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Samuel Toko
 */
public class ActionAccueil extends Action {
    
    @Override
    public void execute(HttpServletRequest request, PrintWriter out) {
        
        HttpSession session = request.getSession(true); 
        Adherent adherentConnexion = (Adherent) session.getAttribute("client");
        
        Serialization.printAdherents(out, adherentConnexion);
    }
    
}
