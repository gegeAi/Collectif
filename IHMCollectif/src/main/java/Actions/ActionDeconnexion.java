/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actions;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Samuel Toko
 */
public class ActionDeconnexion extends Action {
    
    @Override
    public void execute(HttpServletRequest request, PrintWriter out) {
        
    // Recupere la session
    HttpSession session = request.getSession(true); 
    session.invalidate();
        
    }
    
}

