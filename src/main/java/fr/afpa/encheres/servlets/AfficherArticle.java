package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.ArticlesVendus;
import fr.afpa.encheres.bo.Categories;
import fr.afpa.encheres.bo.Retraits;
import fr.afpa.encheres.bo.Utilisateurs;
import fr.afpa.encheres.dal.ArticlesVendusSQL;
import fr.afpa.encheres.dal.CategoriesSQL;
import fr.afpa.encheres.dal.RetraitsSQL;
import fr.afpa.encheres.dal.UtilisateursSQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "AfficherArticle", value = "/AfficherArticle")
public class AfficherArticle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        ArticlesVendus articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(request.getParameter("no_article")));
        if (session.getAttribute("no_utilisateur") != null && articlesVendus.getDate_debut_encheres().before(Date.valueOf(LocalDate.now()))) {
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);





            UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
            RetraitsSQL retraitsSQL = new RetraitsSQL();
            CategoriesSQL categoriesSQL = new CategoriesSQL();

            Utilisateurs utilisateursC = utilisateursSQL.selectById(utilisateursCno_utilisateurs);

            Categories categories = categoriesSQL.selectById(articlesVendus.getNo_categorie());
            Utilisateurs utilisateurs = utilisateursSQL.selectById(articlesVendus.getNo_utilisateur());
            Retraits retraits = retraitsSQL.selectById(articlesVendus.getNo_article());

            request.setAttribute("articlesVendus", articlesVendus);
            request.setAttribute("utilisateurs", utilisateurs);
            request.setAttribute("utilisateursC", utilisateursC);
            request.setAttribute("retraits", retraits);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("WEB-INF/detailVente.jsp").forward(request, response);
        }
        request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
