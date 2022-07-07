package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.ArticlesVendus;
import fr.afpa.encheres.bo.Utilisateurs;
import fr.afpa.encheres.dal.ArticlesVendusSQL;
import fr.afpa.encheres.dal.UtilisateursSQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Connexion", value = "/Connexion")
public class Connexion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        Utilisateurs utilisateursC = utilisateursSQL.selectByPseudoAndByMot_de_passe(request.getParameter("pseudo"), request.getParameter("mot_de_passe"));
        if(utilisateursC != null){
            HttpSession session = request.getSession();
            session.setAttribute( "no_utilisateur" ,utilisateursC.getNo_utilisateur());
            ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
            ArrayList<ArticlesVendus> articlesVenduses = articlesVendusSQL.selectAll();
            request.setAttribute("articlesVenduses", articlesVenduses );
            request.getRequestDispatcher("WEB-INF/Accueil.jsp").forward(request, response);
        }
        request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}