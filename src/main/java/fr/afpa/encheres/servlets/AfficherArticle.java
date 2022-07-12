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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

@WebServlet(name = "AfficherArticle", value = "/AfficherArticle")
public class AfficherArticle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        if (session.getAttribute("no_utilisateur") != null){
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
            request.setAttribute("utilisateursC",utilisateursC);
            request.setAttribute("utilisateursCno_utilisateurs",utilisateursCno_utilisateurs);
        } else {
            request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
        }
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        ArticlesVendus articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(request.getParameter("no_article")));

        RetraitsSQL retraitsSQL = new RetraitsSQL();
        CategoriesSQL categoriesSQL = new CategoriesSQL();
        EncheresSQL encheresSQL = new EncheresSQL();

        System.out.println(articlesVendus.getNo_utilisateur());
        System.out.println(session.getAttribute("no_utilisateur"));

        Categories categories = categoriesSQL.selectById(articlesVendus.getNo_categorie());
        Utilisateurs utilisateurs = utilisateursSQL.selectById(articlesVendus.getNo_utilisateur());
        Retraits retraits = retraitsSQL.selectById(articlesVendus.getNo_article());

        request.setAttribute("articlesVendus", articlesVendus);
        request.setAttribute("utilisateurs", utilisateurs);

        request.setAttribute("retraits", retraits);
        request.setAttribute("categories", categories);
        Encheres encheres = encheresSQL.selectByNo_articleOrderByMontant_enchere(Integer.parseInt(request.getParameter("no_article")));
        System.out.println(request.getParameter("no_article"));
        if(session.getAttribute("no_utilisateur") != null && articlesVendus.getDate_fin_encheres().before(Date.valueOf(LocalDate.now().plusDays(1))) && articlesVendus.getHeure_fin_encheres().before(Time.valueOf(LocalTime.now()))) {
            Utilisateurs utilisateursGagnant = utilisateursSQL.selectById(encheres.getNo_utilisateur());
            request.setAttribute("utilisateursGagnant",utilisateursGagnant);
            request.getRequestDispatcher("WEB-INF/detailVenteTermine.jsp").forward(request, response);
        } else if(
                (int) session.getAttribute("no_utilisateur") ==
                        articlesVendus.getNo_utilisateur() &&
                        articlesVendus.getDate_debut_encheres().after(Date.valueOf(LocalDate.now()))) {
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
            Utilisateurs utilisateursC = utilisateursSQL.selectById(utilisateursCno_utilisateurs);
            ArrayList<Categories> categorieses = categoriesSQL.selectAll();
            request.setAttribute("categorieses",categorieses);
            request.setAttribute("encheres",encheres);
            request.setAttribute("utilisateursC", utilisateursC);
            request.setAttribute("now", LocalDate.now());
            request.setAttribute("nowend", LocalDate.now().plusDays(1));
            request.getRequestDispatcher("WEB-INF/DetailVenteModifiable.jsp").forward(request, response);
        }
        else if (session.getAttribute("no_utilisateur") != null && LocalDateTime.now().isAfter(LocalDateTime.of(articlesVendus.getDate_debut_encheres().toLocalDate(),articlesVendus.getHeure_debut_encheres().toLocalTime()))) {
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
            Utilisateurs utilisateursC = utilisateursSQL.selectById(utilisateursCno_utilisateurs);
            ArrayList<Encheres> enchereses = encheresSQL.selectByNo_article(articlesVendus.getNo_article());
            request.setAttribute("enchereses",enchereses);
            ArrayList<Utilisateurs> utilisateurses = utilisateursSQL.selectAll();
            request.setAttribute("utilisateurses",utilisateurses);
            request.setAttribute("utilisateursC", utilisateursC);
            request.getRequestDispatcher("WEB-INF/detailVente.jsp").forward(request, response);
        }

        //Création des listes "ArticlesVendus et Utilisateurs" pour affichage

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
        System.out.println(pages);
        articlesVenduses = articlesVendusSQL.selectBySix(pages * 6,articlesVenduses);
        ArrayList<Categories> categorieses = categoriesSQL.selectAll();
        request.setAttribute("categorieses",categorieses);
        ArrayList<Utilisateurs> utilisateurses = utilisateursSQL.selectAll();

        //Requête d'envoie
        request.setAttribute("articlesVenduses", articlesVenduses);
        request.setAttribute("utilisateurses", utilisateurses);

        //Requête d'envoie a la JSP
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}