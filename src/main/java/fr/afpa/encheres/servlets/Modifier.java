package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.Utilisateurs;
import fr.afpa.encheres.dal.UtilisateursSQL;
import fr.afpa.encheres.exceptions.ChampVideException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Modifier", value = "/Modifier")
public class Modifier extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();


        Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
        request.setAttribute("utilisateursC", utilisateursC);
        String sha256Hex;
        if (request.getParameter("mot_de_passe").equals("")) {
            sha256Hex = utilisateursC.getMot_de_passe();
        } else {
            sha256Hex = DigestUtils.sha256Hex(request.getParameter("mot_de_passe"));
        }


        try {
            utilisateursSQL.update((int) session.getAttribute("no_utilisateur"), new Utilisateurs(request.getParameter("pseudo"), request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("email"), request.getParameter("telephone"), request.getParameter("rue"), request.getParameter("code_postal"), request.getParameter("ville"), sha256Hex, 0, false, true));
        } catch (ChampVideException e) {
        }

        Utilisateurs utilisateurs = utilisateursSQL.selectById((int) session.getAttribute("no_utilisateur"));

        request.setAttribute("utilisateurs", utilisateurs);

        request.getRequestDispatcher("WEB-INF/profil.jsp").forward(request, response);
    }
}
