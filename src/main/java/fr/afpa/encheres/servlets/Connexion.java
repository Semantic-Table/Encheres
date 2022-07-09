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

        HttpSession session = request.getSession();
        if (session.getAttribute("no_utilisateur") != null) {
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("pseudo")) {
                System.out.println(cookie.getValue());
                request.setAttribute("pseudo", cookie.getValue());
            }
        }

        request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("no_utilisateur") != null) {
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
        }
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        Utilisateurs utilisateursC = utilisateursSQL.selectByPseudoAndByMot_de_passe(request.getParameter("pseudo"), request.getParameter("mot_de_passe"));
        if (utilisateursC != null) {

            session.setAttribute("no_utilisateur", utilisateursC.getNo_utilisateur());
session.setMaxInactiveInterval(300);
            ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
            ArrayList<ArticlesVendus> articlesVenduses = articlesVendusSQL.selectAll();
            request.setAttribute("articlesVenduses", articlesVenduses);
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
            if (request.getParameter("souvenir") != null) {
                Cookie monCookie = new Cookie("pseudo", request.getParameter("pseudo"));
                response.addCookie(monCookie);
            }
            request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
        }
        request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
    }
}
