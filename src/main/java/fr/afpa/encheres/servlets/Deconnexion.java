package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.ArticlesVendus;
import fr.afpa.encheres.bo.Categories;
import fr.afpa.encheres.bo.Utilisateurs;
import fr.afpa.encheres.dal.ArticlesVendusSQL;
import fr.afpa.encheres.dal.CategoriesSQL;
import fr.afpa.encheres.dal.UtilisateursSQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Deconnexion", value = "/Deconnexion")
public class Deconnexion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        ArrayList<ArticlesVendus> articlesVenduses = articlesVendusSQL.selectAll();
        int nbPages = 0;
        int nbArticles = 0;
        nbArticles = articlesVendusSQL.nombreArticle(articlesVenduses);

        if (nbArticles%6==0){
            nbPages = nbArticles / 6;
        } else {
            nbPages = (nbArticles / 6) + 1;
        }
        request.setAttribute("nbPages",nbPages);
        int pages = 0;
        if (request.getParameter("pages") != null){
            pages = Integer.parseInt(request.getParameter("pages"));
        }
        articlesVenduses = articlesVendusSQL.selectBySix(pages * 6,articlesVenduses);
        CategoriesSQL categoriesSQL = new CategoriesSQL();
        ArrayList<Categories> categorieses = categoriesSQL.selectAll();
        request.setAttribute("categorieses",categorieses);
        request.setAttribute("articlesVenduses",articlesVenduses);
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        ArrayList<Utilisateurs> utilisateurses = utilisateursSQL.selectAll();

        request.setAttribute("utilisateurses",utilisateurses);
        HttpSession session = request.getSession();
        session.removeAttribute("no_utilisateur");
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
