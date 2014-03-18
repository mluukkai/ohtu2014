
package com.mycompany.webkauppa;

import com.mycompany.webkauppa.ohjaus.OstoksenLisaysKoriin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LisaaOstoskoriinServlet extends WebKauppaServlet {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        long tuoteId = Long.parseLong( request.getParameter("tuoteId") );
                        
        OstoksenLisaysKoriin lisays = new OstoksenLisaysKoriin(haeSessionOstoskori(request), tuoteId);
        lisays.suorita();
        
        naytaSivu("/Tuotelista", request, response);   
    }
}
