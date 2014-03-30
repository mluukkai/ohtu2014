package com.mycompany.webkauppa;

import com.mycompany.webkauppa.ulkoiset_rajapinnat.PankkiFasaadi;
import com.mycompany.webkauppa.ulkoiset_rajapinnat.ToimitusjarjestelmaFasaadi;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HallintaServlet extends WebKauppaServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("tuotteet", varasto.tuotteidenLista());
        request.setAttribute("maksut", PankkiFasaadi.getInstance().maksut());
        request.setAttribute("toimitukset", ToimitusjarjestelmaFasaadi.getInstance().toimitukset());

        naytaSivu("/hallinta.jsp", request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String viesti = "";

        try {
            int hinta = Integer.parseInt(request.getParameter("hinta"));
            int saldo = Integer.parseInt(request.getParameter("saldo"));
            
            //if (request.getParameter("toimenpide").equals("paivita")) {
            if (request.getParameter("komento").equals("paivita")) {
                long tuoteId = Long.parseLong(request.getParameter("tuote"));

                varasto.paivitaTuotteenTiedot(tuoteId, hinta, saldo);
                viesti = "tuotteen tietojen muutos tehty";
            } else {
                String tuotteenNimi = request.getParameter("nimi");
                if (varasto.lisaaTuote(tuotteenNimi, hinta, saldo)) {
                    viesti = "lisättiin tuote " + tuotteenNimi;
                } else {
                    viesti = "tuote " + tuotteenNimi + " on jo olemassa";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            viesti = "virheellinen syöte, ei muutoksia tuotteisiin";
        }

        request.setAttribute("viesti", viesti);
        processRequest(request, response);
    }
}
