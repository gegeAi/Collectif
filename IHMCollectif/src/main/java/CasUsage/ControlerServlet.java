/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasUsage;

import fr.insalyon.dasi.collectif.metier.modele.Activite;
import fr.insalyon.dasi.collectif.metier.service.ServiceMetier;
import CasUsage.Actions.Action;
import CasUsage.Actions.ActionAccueil;
import CasUsage.Actions.ActionActivite;
import CasUsage.Actions.ActionListeActivite;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samuel Toko
 */
public class ControlerServlet extends HttpServlet {

    public static ServiceMetier servMetier = new ServiceMetier();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         //Parcours de la liste des paramètres et les affiches
         
        Enumeration<String> result = request.getParameterNames();
        while (result.hasMoreElements()) {
            String nextElement = result.nextElement();
            System.out.println("param : " + nextElement + " = " + request.getParameter(nextElement));
        }

        //Recupere le paramètre todo
        String todo = request.getParameter("todo");

        //String vue = "";

        // Recherche de l'action correspondante
        Action action = this.getAction(todo);
        action.execute(request, response.getWriter());

        
       /* vue = this.setVue(todo, request);

        
        request.getRequestDispatcher(vue).forward(request, response);*/
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	
        
        processRequest(request, response);
 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private Action getAction(String todo)
    {
        Action nouvelleAction = null;
        if(todo == null)
        {
            todo = "";
        }
        switch(todo){
            case "listeActivite":
                nouvelleAction = new ActionListeActivite();
                break;
            
            case "Activite":
                nouvelleAction = new ActionActivite();
                break;
                
            default:
                nouvelleAction = new ActionAccueil();
            
        }
        return nouvelleAction;
    } 
    
    private String setVue(String todo, HttpServletRequest request) {
        String vue = "";
        if(todo == null)
        {
            todo = "";
        }
        
        switch(todo)
        {
            case "listeActivite":
                vue = "ListeActivite.jsp";
                break;
            
            case "Activite":
                String type = request.getParameter("type");
                vue = "Activite.jsp?type="+type;
                break;
                
            case "":
                vue = "Accueil.jsp";
                break;
        }
        
        return vue;
    }
    

}
