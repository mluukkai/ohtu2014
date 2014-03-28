package com.mycompany.webkauppa;

import com.mycompany.webkauppa.ohjaus.OstoksenPoistoKorista;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PoistaOstoskoristaServlet extends WebKauppaServlet {
             
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        long tuoteId = Long.parseLong( request.getParameter("tuoteId") );
        
        OstoksenPoistoKorista poisto = new OstoksenPoistoKorista(haeSessionOstoskori(request),  tuoteId );          
        poisto.suorita();        
        
        naytaSivu("/MaksaOstokset", request, response);
    }
}
