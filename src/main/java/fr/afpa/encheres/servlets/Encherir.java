package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.ArticlesVendus;
import fr.afpa.encheres.bo.Encheres;
import fr.afpa.encheres.dal.ArticlesVendusSQL;
import fr.afpa.encheres.dal.EncheresSQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet(name = "Encherir", value = "/Encherir")
public class Encherir extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("no_utilisateur") != null) {
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
        }
        EncheresSQL encheresSQL = new EncheresSQL();
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        articlesVendusSQL.updatePrix_vente(
                Integer.parseInt(request.getParameter("no_article")), Integer.parseInt(request.getParameter("montant_enchere")));


        encheresSQL.insert(new Encheres((int) session.getAttribute("no_utilisateur"), Integer.parseInt(request.getParameter("no_article")), Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), Integer.parseInt(request.getParameter("montant_enchere"))));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("no_utilisateur") != null) {
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
        }
    }
}
