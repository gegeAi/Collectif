/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import fr.insalyon.dasi.collectif.metier.service.ServiceMetier;
import Actions.Action;
import Actions.ActionAccueil;
import Actions.ActionActivite;
import Actions.ActionActivitesPlanifiees;
import Actions.ActionConnexion;
import Actions.ActionConnexionResponsable;
import Actions.ActionDeconnexion;
import Actions.ActionDemande;
import Actions.ActionInscriptionAdherent;
import Actions.ActionLieux;
import Actions.ActionListeActivite;
import java.io.IOException;
import java.util.Enumeration;
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
        response.setContentType("application/json;charset=UTF-8");
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
            
            case "Demande":
                nouvelleAction = new ActionDemande();
                break;
                
            case "ConnexionAdherent":
                nouvelleAction = new ActionConnexion();
                //System.out.println("epizjgzgji izejgzej 54");
                break;
                
            case "ConnexionResponsable":
                nouvelleAction = new ActionConnexionResponsable();
                break;
                
            case "listeNonPlanifies":
                nouvelleAction = new ActionActivitesPlanifiees();
                break;
                
            case "listeLieux":
                nouvelleAction = new ActionLieux();
                break;
                
            case "Deconnexion":
                nouvelleAction = new ActionDeconnexion();
                break;
                
                
            case "InscriptionAdherent":
                nouvelleAction = new ActionInscriptionAdherent();
                System.out.println("epizjgzgji izejgzej 54");
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
