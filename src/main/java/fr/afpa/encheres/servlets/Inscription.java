package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.ArticlesVendus;
import fr.afpa.encheres.bo.Utilisateurs;
import fr.afpa.encheres.dal.ArticlesVendusSQL;
import fr.afpa.encheres.dal.UtilisateursSQL;
import fr.afpa.encheres.exceptions.ChampVideException;
import org.apache.commons.codec.digest.DigestUtils;

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
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();

        //si les champs sont rempli correctement
        if (utilisateursSQL.selectByPseudo(request.getParameter("pseudo")) == null && utilisateursSQL.selectByEmail(request.getParameter("email")) == null && request.getParameter("pseudo").matches("^\\p{Alnum}*$")) {

            try {
                String sha256hex = DigestUtils.sha256Hex(request.getParameter("mot_de_passe"));
                System.out.println(sha256hex);
                utilisateursSQL.insert(new Utilisateurs(request.getParameter("pseudo"), request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("email"), request.getParameter("telephone"), request.getParameter("rue"), request.getParameter("code_postal"), request.getParameter("ville"), sha256hex, 0, false,true));
            } catch (Exception e) {
                request.getRequestDispatcher("WEB-INF/echecInscription.jsp").forward(request, response);
            }

            ArrayList<ArticlesVendus> articlesVenduses = articlesVendusSQL.selectAll();
            Utilisateurs utilisateurs = utilisateursSQL.selectByLast();
            //variables de session et on set le temsp d'inactivite a 300s (5mn)
            session.setAttribute("no_utilisateur", utilisateurs.getNo_utilisateur());
            session.setMaxInactiveInterval(300);

            Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
            request.setAttribute("utilisateursC",utilisateursC);

            if (session.getAttribute("no_utilisateur") != null) {
                int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
                request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);

            }

            request.setAttribute("articlesVenduses", articlesVenduses);

            request.getRequestDispatcher("WEB-INF/profil.jsp").forward(request, response);
        }
        request.getRequestDispatcher("WEB-INF/echecInscription.jsp").forward(request, response);
    }
}