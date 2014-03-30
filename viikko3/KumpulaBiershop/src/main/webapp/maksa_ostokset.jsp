
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Maksa ostokset</title>
    </head>
    <body>
        <h1>Maksa ostokset</h1>      

        <%@page import="com.mycompany.webkauppa.sovelluslogiikka.Ostos" %>
        <%@page import="java.util.*" %>

        <% List<Ostos> ostokset = (List<Ostos>) request.getAttribute("ostokset");%>

        <h2>Ostoskorissa</h2>

        <ul class="ostoskori">
            <% for (Ostos ostos : ostokset) {%>

            <li id=<%=ostos.tuotteenNimi()%> > 
                <%=ostos.lukumaara()%>  x <%=ostos.tuotteenNimi()%>                              
                hinta <%=ostos.hinta()%> euroa 
                <a href="PoistaOstoskorista?tuoteId=<%=ostos.tuotteenId()%>">poista korista</a> 
            </li>        

            <% }%>
        </ul>

        <% int hinta = (Integer) request.getAttribute("hinta");%>

        <div id="korissa">
            <p> Ostosten yhteenlaskettu hinta on
                <%= hinta%> euroa.</p>                        
        </div>

        <h2>Maksajan tiedot</h2>

        <div id="maksutiedot">
            <form action="MaksaOstokset" method="post">

                <table>
                    <tr>
                        <td>nimi</td>
                        <td><input type="text" name="nimi"></td>
                    </tr>
                    <tr>
                        <td>osoite</td>
                        <td><input type="text" name="osoite"></td>
                    </tr>
                    <tr>
                        <td>luottokorttinumero</td>
                        <td><input type="text" name="luottokorttinumero"></td>
                    </tr>
                </table>

                <p>
                    <input type="submit" value="suorita maksu" name="suorita maksu">
                </p>
            </form>
        </div>

        <p> <a href="Tuotelista">Palaa tekemään ostoksia</a> </p>

        <p> <a href="TyhjennaOstoskori">Tyhjenna ostoskori</a> </p>

    </body>
</html>
