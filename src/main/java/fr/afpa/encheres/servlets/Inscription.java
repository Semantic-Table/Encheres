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

@WebServlet(name = "Inscription", value = "/Inscription")
public class Inscription extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("no_utilisateur") != null) {
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
        }
        request.getRequestDispatcher("WEB-INF/inscription.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        System.out.println(utilisateursSQL.selectByPseudo(request.getParameter("pseudo")));
        System.out.println(utilisateursSQL.selectByEmail( request.getParameter("email")));
        if (utilisateursSQL.selectByPseudo(request.getParameter("pseudo")) == null && utilisateursSQL.selectByEmail( request.getParameter("email")) == null && request.getParameter("pseudo").matches("^\\p{Alnum}*$")){


            utilisateursSQL.insert(new Utilisateurs(request.getParameter("pseudo"), request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("email"), request.getParameter("telephone"), request.getParameter("rue"), request.getParameter("code_postal"), request.getParameter("ville"), request.getParameter("mot_de_passe"), 0, false));
            ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
            ArrayList<ArticlesVendus> articlesVenduses = articlesVendusSQL.selectAll();
            request.setAttribute("articlesVenduses", articlesVenduses);

            Utilisateurs utilisateurs = utilisateursSQL.selectByLast();
            session.setAttribute("no_utilisateur", utilisateurs.getNo_utilisateur());
            if (session.getAttribute("no_utilisateur") != null) {
                int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
                request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);

            }
            request.getRequestDispatcher("WEB-INF/profil.jsp").forward(request, response);
        }
        request.getRequestDispatcher("WEB-INF/echecInscription.jsp").forward(request,response);
    }
}