package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.ArticlesVendus;
import fr.afpa.encheres.bo.Categories;
import fr.afpa.encheres.bo.Utilisateurs;
import fr.afpa.encheres.dal.ArticlesVendusSQL;
import fr.afpa.encheres.dal.CategoriesSQL;
import fr.afpa.encheres.dal.UtilisateursSQL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FiltreParticipation", value = "/FiltreParticipation")
public class FiltreParticipation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        CategoriesSQL categoriesSQL = new CategoriesSQL();
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();

        if (session.getAttribute("no_utilisateur") != null){
            Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
            request.setAttribute("utilisateursC",utilisateursC);
        }

        //pagination
        ArrayList<ArticlesVendus> articlesVenduses = articlesVendusSQL.selectParticipation((int) session.getAttribute("no_utilisateur"));
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

        ArrayList<Categories> categorieses = categoriesSQL.selectAll();
        ArrayList<Utilisateurs> utilisateurses = utilisateursSQL.selectAll();

        request.setAttribute("categorieses",categorieses);
        request.setAttribute("nbPages",nbPages);
        request.setAttribute("utilisateurses", utilisateurses);
        request.setAttribute("articlesVenduses",articlesVenduses);

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request,response);
    }
}
