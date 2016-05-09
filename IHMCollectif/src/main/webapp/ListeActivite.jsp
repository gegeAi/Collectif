<%-- 
    Document   : listeActivite
    Created on : 03-May-2016, 11:04:25
    Author     : Samuel Toko
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="fr.insalyon.dasi.collectif.metier.modele.Activite"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste Activite</title>
    </head>
    <body>
        <h3>Liste des activités</h3>
        <p>
           <% 
            List <Activite> activites = (List <Activite>) request.getAttribute("activites");
            //out.println("Liste des activités");
            for(int i=0; i < activites.size(); i++)
            {
                out.println("[" + activites.get(i).getId() + "]") ;
                out.println( ( activites.get(i).getDenomination() ) ) ;
                String nom = activites.get(i).getDenomination();
                out.println("<a href=\"ControlerServlet?todo=Activite&type="+ nom +"\"> voir </a>");
                out.println("<br/>");
            }
           
            out.println("<a href=\"ControlerServlet\"> Accueil </a>");
            %>
        </p>
    </body>
</html>
