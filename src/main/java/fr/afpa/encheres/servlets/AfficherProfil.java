package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.Utilisateurs;
import fr.afpa.encheres.dal.UtilisateursSQL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AfficherProfil", value = "/AfficherProfil")
public class AfficherProfil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();

        if (session.getAttribute("no_utilisateur") != null){
            Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
            Utilisateurs utilisateurs = utilisateursSQL.selectById(Integer.parseInt(request.getParameter("no_utilisateur")));

            request.setAttribute("utilisateurs",utilisateurs);
            request.setAttribute("utilisateursC",utilisateursC);

            request.getRequestDispatcher("WEB-INF/profilUtilisateur.jsp").forward(request,response);
        } else {
            request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request,response);
        }

    }
}
