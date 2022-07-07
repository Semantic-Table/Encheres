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
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(name = "Accueil", value = "/Accueil")
public class Accueil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("no_utilisateur") != null){
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs",utilisateursCno_utilisateurs);
        }

        //Instance de classe (dal) base de données
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        CategoriesSQL categoriesSQL = new CategoriesSQL();

        //Récupération des paramètres en insert. de la base SQL
        articlesVendusSQL.insert(new ArticlesVendus(request.getParameter("nom_article"), request.getParameter("description"), Date.valueOf(request.getParameter("date_debut_encheres")), Date.valueOf(request.getParameter("date_fin_encheres")), Integer.parseInt(request.getParameter("prix_initial")), Integer.parseInt(request.getParameter("prix_vente")), Integer.parseInt(request.getParameter("no_utilisateur")),Integer.parseInt(request.getParameter("no_categorie"))));

        //Insertion des variables à partir des instances
        ArticlesVendus articlesVendus = articlesVendusSQL.selectBylast();
        Utilisateurs utilisateurs = utilisateursSQL.selectById(ArticlesVendus.getNo_utilisateur());
        Utilisateurs utilisateursBis = utilisateursSQL.selectById(no_utilisateur());
        Categories categories = categoriesSQL.selectById(Integer.parseInt(request.getParameter("no_categorie")));

        //Requête d'envoie
        request.setAttribute("articlesVendus", articlesVendus);
        request.setAttribute("utilisateurs", utilisateurs);
        request.setAttribute("utilisateursBis", utilisateursBis);
        request.setAttribute("categories", categories);

        //Requête d'envoie a la JSP
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
