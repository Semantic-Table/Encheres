package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.*;
import fr.afpa.encheres.dal.*;

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


        int no_utilisateurs = (int) session.getAttribute("no_utilisateur");


        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        RetraitsSQL retraitsSQL = new RetraitsSQL();
        CategoriesSQL categoriesSQL = new CategoriesSQL();


        Utilisateurs utilisateursC = utilisateursSQL.selectById(no_utilisateurs);

        Categories categories = categoriesSQL.selectById(Integer.parseInt(request.getParameter("no_categorie")));
        ArticlesVendus articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(request.getParameter("no_article")));
        Utilisateurs utilisateurs = utilisateursSQL.selectById(articlesVendus.getNo_utilisateur());
        Retraits retraits = retraitsSQL.selectById(articlesVendus.getNo_article());

        request.setAttribute("articlesVendus", articlesVendus);
        request.setAttribute("utilisateurs", utilisateurs);
        request.setAttribute("utilisateursC", utilisateursC);
        request.setAttribute("retraits", retraits);
        request.setAttribute("categories", categories);
        if (Integer.parseInt(request.getParameter("montant_enchere")) > articlesVendus.getPrix_vente()) {
            encheresSQL.insert(new Encheres((int) session.getAttribute("no_utilisateur"), Integer.parseInt(request.getParameter("no_article")), Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), Integer.parseInt(request.getParameter("montant_enchere"))));

        }
        request.getRequestDispatcher("WEB-INF/detailVente.jsp").forward(request, response);
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
