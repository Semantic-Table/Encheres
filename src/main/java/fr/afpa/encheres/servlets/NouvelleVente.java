package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.ArticlesVendus;
import fr.afpa.encheres.dal.ArticlesVendusSQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "NouvelleVente", value = "/NouvelleVente")
public class NouvelleVente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/nouvelleVente.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int no_utilisateurs = (int) session.getAttribute("no_utilisateur");
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        //articlesVendusSQL.insert(new ArticlesVendus());
        request.getRequestDispatcher("WEB-INF/nouvelleVente.jsp").forward(request, response);
    }
}
