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

@WebServlet(name = "NouvelleVente", value = "/NouvelleVente")
public class NouvelleVente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/nouvelleVente.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("no_utilisateur") != null){
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs",utilisateursCno_utilisateurs);
        }

        int no_utilisateurs = (int) session.getAttribute("no_utilisateur");

        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        RetraitsSQL retraitsSQL = new RetraitsSQL();
        CategoriesSQL categoriesSQL = new CategoriesSQL();


        Utilisateurs utilisateursC = utilisateursSQL.selectById(no_utilisateurs);

        Categories categories = categoriesSQL.selectById(Integer.parseInt(request.getParameter("no_categorie")));

        articlesVendusSQL.insert(new ArticlesVendus(
                request.getParameter("nom_article"),
                request.getParameter("description"),
                Date.valueOf(request.getParameter("date_debut_enchere")),
                Date.valueOf(request.getParameter("date_fin_enchere")),
                Integer.parseInt(request.getParameter("prix_initial")),
                Integer.parseInt(request.getParameter("prix_initial")),
                no_utilisateurs,
                Integer.parseInt(request.getParameter("no_categorie"))));
        ArticlesVendus articlesVendus = articlesVendusSQL.selectByLast();
        Utilisateurs utilisateurs = utilisateursSQL.selectById(articlesVendus.getNo_utilisateur());
        Retraits retraits = retraitsSQL.selectById(articlesVendus.getNo_article());
        retraitsSQL.insert(new Retraits(articlesVendus.getNo_article(),request.getParameter("rue"),request.getParameter("code_postal"),request.getParameter("ville")));

        request.setAttribute("articlesVendus",articlesVendus);
        request.setAttribute("utilisateurs",utilisateurs);
        request.setAttribute("utilisateursC",utilisateursC);
        request.setAttribute("retraits",retraits);
        request.setAttribute("categories",categories);
        request.getRequestDispatcher("WEB-INF/detailVente.jsp").forward(request, response);
    }
}
