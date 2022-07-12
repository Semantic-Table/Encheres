package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.ArticlesVendus;
import fr.afpa.encheres.bo.Utilisateurs;
import fr.afpa.encheres.dal.ArticlesVendusSQL;
import fr.afpa.encheres.dal.EncheresSQL;
import fr.afpa.encheres.dal.UtilisateursSQL;
import fr.afpa.encheres.exceptions.ChampVideException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminDesac", value = "/AdminDesac")
public class AdminDesac extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        EncheresSQL encheresSQL = new EncheresSQL();
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();

        if (session.getAttribute("no_utilisateur") != null){
            Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
            request.setAttribute("utilisateursC",utilisateursC);
        }

        //rend l'argent a l'utilisateur et desactive son compte ou le reactive
        Utilisateurs utilisateurs = utilisateursSQL.selectById(Integer.parseInt(request.getParameter("no_utilisateur")));
        int aRendre = encheresSQL.rendezLargent(Integer.parseInt(request.getParameter("no_utilisateur")));
        utilisateurs.setCredit(utilisateurs.getCredit()+aRendre);
        //la c'est technique ca inverse le boolean actif
        utilisateurs.setActif(!utilisateurs.isActif());
        encheresSQL.deleteByNo_utilisateur(Integer.parseInt(request.getParameter("no_utilisateur")));
        articlesVendusSQL.deleteByNo_utilisateur(Integer.parseInt(request.getParameter("no_utilisateur")));
        try {
            utilisateursSQL.update(Integer.parseInt(request.getParameter("no_utilisateur")),utilisateurs);
        } catch (ChampVideException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("utilisateurs",utilisateurs);

        request.getRequestDispatcher("WEB-INF/profilUtilisateur.jsp").forward(request,response);
    }
}
