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

        //Instance de classe (dal) base de données pour les utiliser
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();

        //Création des listes "ArticlesVendus et Utilisateurs" pour affichage
        ArrayList<ArticlesVendus> articlesVenduses = articlesVendusSQL.selectAll();
        ArrayList<Utilisateurs> utilisateurses = utilisateursSQL.selectAll();

        //Requête d'envoie
        request.setAttribute("articlesVenduses", articlesVenduses);
        request.setAttribute("utilisateurses", utilisateurses);

        //Requête d'envoie a la JSP
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("no_utilisateur") != null){
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs",utilisateursCno_utilisateurs);
        }

        //Instance de classe (dal) base de données pour les utiliser
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();

        ArrayList<ArticlesVendus> articlesVenduses = articlesVendusSQL.selectByCategorieAndByNom_article(
                Integer.parseInt(request.getParameter("no_categorie")),
                request.getParameter("nom_article"));
       ArrayList<Utilisateurs> utilisateurses = utilisateursSQL.selectAll();

        //Requête d'envoie
        request.setAttribute("articlesVenduses", articlesVenduses);
        request.setAttribute("utilisateurses", utilisateurses);

    request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

    }
}
