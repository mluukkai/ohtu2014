package com.mycompany.webkauppa;

import com.mycompany.webkauppa.sovelluslogiikka.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TuotelistaServlet extends WebKauppaServlet {
           
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
        
        Ostoskori ostoskori = haeSessionOstoskori(request);
        
        request.setAttribute("korissa", ostoskori.tuotteitaKorissa() );
        request.setAttribute("hinta", ostoskori.hinta() );       
        request.setAttribute("tuotteet", varasto.tuotteidenLista() );
        
        naytaSivu("/tuotelista.jsp", request, response);        
    }
}
