package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.ArticlesVendus;
import fr.afpa.encheres.bo.Utilisateurs;
import fr.afpa.encheres.dal.ArticlesVendusSQL;
import fr.afpa.encheres.dal.UtilisateursSQL;
import fr.afpa.encheres.exceptions.ChampVideException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AcheterCredits", value = "/AcheterCredits")
public class AcheterCredits extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();


        if (session.getAttribute("no_utilisateur") != null){
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs",utilisateursCno_utilisateurs);
        }




        request.getRequestDispatcher("WEB-INF/acheterCredits.jsp").forward(request, response);
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
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        ArrayList<ArticlesVendus> articlesVenduses = articlesVendusSQL.selectAll();
        request.setAttribute("articlesVenduses",articlesVenduses);

        ArrayList<Utilisateurs> utilisateurses = utilisateursSQL.selectAll();
        request.setAttribute("utilisateurses",utilisateurses);
        int nombrePoint = Integer.parseInt(request.getParameter("nombrePoint"));
        Utilisateurs utilisateurs = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
        utilisateurs.setCredit(utilisateurs.getCredit() + nombrePoint );
        try {
            utilisateursSQL.update(utilisateurs.getNo_utilisateur(),utilisateurs);
        } catch (ChampVideException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}