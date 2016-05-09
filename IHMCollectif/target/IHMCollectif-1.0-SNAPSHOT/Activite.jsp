<%-- 
    Document   : Activite
    Created on : 08-May-2016, 17:10:23
    Author     : Samuel Toko
--%>

<%@page import="fr.insalyon.dasi.collectif.metier.modele.Activite"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Activite</title>
    </head>
    <body>
        <p>
            <%
                Activite act = (Activite) request.getAttribute("activite");
                out.println("<h3>"+ act.getDenomination() + "</h3>"); 
               // out.println("<p>");
                if(act.isParEquipe())
                    out.println("Par équipe : oui");
                else
                    out.println("Par équipe : non");
                out.println("<br/>");
                out.println("Nombre de participants :"+ act.getNbParticipants());
               // out.println("</p>");
               out.println("<a href=\"ControlerServlet\"> Accueil </a>");
            %>
            
            
        </p>
    </body>
</html>
