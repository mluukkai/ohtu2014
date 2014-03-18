
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hallinta</title>
    </head>
    <body>
        <h1>Hallinta</h1>

        <%@page import="com.mycompany.webkauppa.sovelluslogiikka.Tuote" %>
        <%@page import="java.util.*" %>

        <% List<Tuote> tuotteet = (List<Tuote>) request.getAttribute("tuotteet");%>
        <% String viesti = (String) request.getAttribute("viesti");%>

        <% if (viesti != null) {%>        
        <b><%=viesti%></b>
        <% }%>

        <h2>Tuotteen saldon ja hinnan muutos</h2>

        <table CELLPADDING="4"> 
            <tr>                                
                <td width="200">tuote</td>
                <td width="50">hinta</td>
                <td width="80">uusi hinta</td>
                <td width="50">saldo</td>
                <td width="90">uusi saldo</td>
            </tr>
        </table>  


        <% for (Tuote tuote : tuotteet) {%>

        <form action="Hallinta" method="post">  

            <div id="<%=tuote.getNimi()%>">

                <table CELLPADDING="4"> 
                    <tr id ="<%=tuote.getId()%>">
                        <td id="nimi" width="200">
                            <%=tuote.getNimi()%>
                        </td>
                        <td id="hinta" width="50">
                            <%=tuote.getHinta()%>
                        </td>
                        <td width="80">
                            <input type="text" name="hinta" value="<%=tuote.getHinta()%>" size="4" >
                        </td>                    
                        <td id="saldo" width="80"> 
                            <%=tuote.getSaldo()%> 
                        </td>
                        <td width="90">
                            <input type="text" name="saldo" value="<%=tuote.getSaldo()%>" size="4" >
                        </td>                    
                        <td>
                            <input type="submit" name="toimenpide" value="paivita" id="komento">
                        </td>
                    </tr>
                    <input type="hidden" name="komento" value="paivita" > 
                    <input type="hidden" name="tuote" value="<%=tuote.getId()%>" >              
                </table>
            </div>                    
        </form>               

        <% }%>

        <h2>Uuden tuotteen lisäys</h2>

        <div id="uudenLisays"">


             <form action="Hallinta" method="post">

                <table>
                    <tr>
                        <td>tuotteen nimi</td>
                        <td><input type="text" name="nimi"></td>
                    </tr>
                    <tr>
                        <td>hinta</td>
                        <td><input type="text" name="hinta"></td>
                    </tr>
                    <tr>
                        <td>varastosaldo</td>
                        <td><input type="text" name="saldo"></td>
                    </tr>
                    <input type="hidden" name="komento" value="lisaa" > 
                </table>              

                <p>
                    <input type="submit" name="toimenpide" value="lisaa">
                </p>

            </form>

        </div>

        <h2>Maksuliikenne</h2>

        <% List<String> maksut = (List<String>) request.getAttribute("maksut");%>

        <div id="maksut">

            <ul>
                <% for (String maksu : maksut) {%>
                <li><%=maksu%></li>
                <% }%>
            </ul>

        </div>

        <h2>Toimitukset</h2>

        <% List<String> toimitukset = (List<String>) request.getAttribute("toimitukset");%>

        <div id="toimitukset">

            <ul>
                <% for (String toimitus : toimitukset) {%>
                <li><%=toimitus%></li>
                <% }%>
            </ul>

        </div>

    </body>

</html>
