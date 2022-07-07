package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.Utilisateurs;
import fr.afpa.encheres.dal.UtilisateursSQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Profil", value = "/Profil")
public class Profil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("no_utilisateur") != null){
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs",utilisateursCno_utilisateurs);
        }
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        Utilisateurs utilisateurs = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
        request.setAttribute("utilisateurs",utilisateurs);
        request.getRequestDispatcher("WEB-INF/profil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}