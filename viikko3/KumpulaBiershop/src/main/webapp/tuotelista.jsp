
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tuotelista</title>
    </head>
    <body>
        <h1>Tuotteet</h1>
    
        <%@page import="com.mycompany.webkauppa.sovelluslogiikka.Tuote" %>
        <%@page import="java.util.*" %>
    
        <% List<Tuote> tuotteet = (List<Tuote>) request.getAttribute("tuotteet"); %>
               
        <ul>
        <% for ( Tuote tuote : tuotteet ) { %>
            <li id ="<%=tuote.getNimi()%>"> 
                <%=tuote.getNimi()%> 
                hinta <%=tuote.getHinta()%> euroa 
                <a href="LisaaOstoskoriin?tuoteId=<%=tuote.getId()%>">lisaa koriin</a> 
            </li>        
        <% } %>
        </ul>
        
        <% int korissa = (Integer) request.getAttribute("korissa"); %>
        <% int hinta = (Integer) request.getAttribute("hinta"); %>
        
        <div id="korissa">
            <p> Ostoskorissa tuotteita <%= korissa %> ja niiden yhteenlaskettu hinta on
            <%= hinta %> euroa.</p>                        
        </div>
        
        <p> <a href="MaksaOstokset">maksa ostokset</a> </p>        
        
        <p> <a href="TyhjennaOstoskori">tyhjennä ostoskori</a> </p>
    </body>
    
    
</html>
