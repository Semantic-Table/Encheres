package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.Utilisateurs;
import fr.afpa.encheres.dal.UtilisateursSQL;
import fr.afpa.encheres.exceptions.ChampVideException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Reinitialisation", value = "/Reinitialisation")
public class Reinitialisation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        if (session.getAttribute("no_utilisateur") != null){
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
            request.setAttribute("utilisateursC",utilisateursC);
            request.setAttribute("utilisateursCno_utilisateurs",utilisateursCno_utilisateurs);
        }
        request.getRequestDispatcher("WEB-INF/reinitialisation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        if (session.getAttribute("no_utilisateur") != null){
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
            request.setAttribute("utilisateursC",utilisateursC);
            request.setAttribute("utilisateursCno_utilisateurs",utilisateursCno_utilisateurs);
        }
        Utilisateurs utilisateurs = utilisateursSQL.selectByPseudo(request.getParameter("pseudo"));
        utilisateurs.setMot_de_passe(request.getParameter("mot_de_passe"));
        try {
            utilisateursSQL.update(utilisateurs.getNo_utilisateur(),utilisateurs);
        } catch (ChampVideException e) {
            throw new RuntimeException(e);
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
}
