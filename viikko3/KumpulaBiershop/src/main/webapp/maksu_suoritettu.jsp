
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vahvistus maksusta</title>
    </head>
    <body>
        <h1>Vahvistus maksusta</h1>
               
        <% int hinta = (Integer) request.getAttribute("hinta"); %>
        <% String osoite = (String) request.getAttribute("osoite"); %>
        
        <p>Luottokortiltasi on veloitettu <%=hinta%> euroa</p> 
        <p>tilaamasi tuotteet toimitetaan osoitteeseesi <%=osoite%></p>            
            
        <p><a href="index.jsp">palaa etusivulle</a></p>            
        </p>
    </body>
</html>
