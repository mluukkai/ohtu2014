package com.mycompany.webkauppa;

import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TyhjennaOstoskoriServlet extends WebKauppaServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Ostoskori ostoskori = haeSessionOstoskori(request);     
        ostoskori.tyhjenna();               

        naytaSivu("/Tuotelista", request, response);
    }
}
