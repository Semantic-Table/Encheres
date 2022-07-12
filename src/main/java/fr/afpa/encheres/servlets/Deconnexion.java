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
        CategoriesSQL categoriesSQL = new CategoriesSQL();

        //pagination
        ArrayList<ArticlesVendus> articlesVenduses = articlesVendusSQL.selectAll();
        int nbPages = 0;
        int nbArticles = 0;
        nbArticles = articlesVendusSQL.nombreArticle(articlesVenduses);
        if (nbArticles%6==0){
            nbPages = nbArticles / 6;
        } else {
            nbPages = (nbArticles / 6) + 1;
        }

        int pages = 0;
        if (request.getParameter("pages") != null){
            pages = Integer.parseInt(request.getParameter("pages"));
        }
        articlesVenduses = articlesVendusSQL.selectBySix(pages * 6,articlesVenduses);

        //supprime la variable de session/deconnecte
        HttpSession session = request.getSession();
        session.removeAttribute("no_utilisateur");

        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        ArrayList<Utilisateurs> utilisateurses = utilisateursSQL.selectAll();
        ArrayList<Categories> categorieses = categoriesSQL.selectAll();

        request.setAttribute("categorieses",categorieses);
        request.setAttribute("articlesVenduses",articlesVenduses);
        request.setAttribute("nbPages",nbPages);
        request.setAttribute("utilisateurses",utilisateurses);

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}
