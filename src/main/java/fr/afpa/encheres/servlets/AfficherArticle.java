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
        //celle ci est assez lourde a cause des nombreuses possibilités, si l'article est en cours de ventes ou terminé ou propriétaires etc...
        HttpSession session = request.getSession();

        RetraitsSQL retraitsSQL = new RetraitsSQL();
        CategoriesSQL categoriesSQL = new CategoriesSQL();
        EncheresSQL encheresSQL = new EncheresSQL();
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();

        if (session.getAttribute("no_utilisateur") != null){
            Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
            request.setAttribute("utilisateursC",utilisateursC);
        } else {
            request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
        }

        ArticlesVendus articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(request.getParameter("no_article")));
        Categories categories = categoriesSQL.selectById(articlesVendus.getNo_categorie());
        Utilisateurs utilisateurs = utilisateursSQL.selectById(articlesVendus.getNo_utilisateur());
        Retraits retraits = retraitsSQL.selectById(articlesVendus.getNo_article());
        Encheres encheres = encheresSQL.selectByNo_articleOrderByMontant_enchere(Integer.parseInt(request.getParameter("no_article")));

        request.setAttribute("articlesVendus", articlesVendus);
        request.setAttribute("utilisateurs", utilisateurs);
        request.setAttribute("retraits", retraits);
        request.setAttribute("categories", categories);

        if(session.getAttribute("no_utilisateur") != null && LocalDateTime.now().isAfter(LocalDateTime.of(articlesVendus.getDate_fin_encheres().toLocalDate(),articlesVendus.getHeure_fin_encheres().toLocalTime()))) {

            Utilisateurs utilisateursGagnant = utilisateursSQL.selectById(encheres.getNo_utilisateur());
            request.setAttribute("utilisateursGagnant",utilisateursGagnant);
            request.getRequestDispatcher("WEB-INF/detailVenteTermine.jsp").forward(request, response);
        } else if(
                (int) session.getAttribute("no_utilisateur") == articlesVendus.getNo_utilisateur() && LocalDateTime.now().isBefore(LocalDateTime.of(articlesVendus.getDate_debut_encheres().toLocalDate(),articlesVendus.getHeure_debut_encheres().toLocalTime()))) {
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
            Utilisateurs utilisateursC = utilisateursSQL.selectById(utilisateursCno_utilisateurs);
            ArrayList<Categories> categorieses = categoriesSQL.selectAll();
            request.setAttribute("categorieses",categorieses);
            request.setAttribute("encheres",encheres);
            request.setAttribute("utilisateursC", utilisateursC);
            //les deux suivantes servent a set les value min et max pour les dates d'encheres
            request.setAttribute("now", LocalDate.now());
            request.setAttribute("nowend", LocalDate.now().plusDays(1));
            request.getRequestDispatcher("WEB-INF/DetailVenteModifiable.jsp").forward(request, response);
        }
        else if (session.getAttribute("no_utilisateur") != null && LocalDateTime.now().isAfter(LocalDateTime.of(articlesVendus.getDate_debut_encheres().toLocalDate(),articlesVendus.getHeure_debut_encheres().toLocalTime()))) {

            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            Utilisateurs utilisateursC = utilisateursSQL.selectById(utilisateursCno_utilisateurs);
            ArrayList<Encheres> enchereses = encheresSQL.selectByNo_article(articlesVendus.getNo_article());
            ArrayList<Utilisateurs> utilisateurses = utilisateursSQL.selectAll();

            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
            request.setAttribute("enchereses",enchereses);
            request.setAttribute("utilisateurses",utilisateurses);
            request.setAttribute("utilisateursC", utilisateursC);
            request.getRequestDispatcher("WEB-INF/detailVente.jsp").forward(request, response);
        }

        //necessaire a la pagination
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

        ArrayList<Categories> categorieses = categoriesSQL.selectAll();
        ArrayList<Utilisateurs> utilisateurses = utilisateursSQL.selectAll();

        //Requête d'envoie
        request.setAttribute("nbPages",nbPages);
        request.setAttribute("articlesVenduses", articlesVenduses);
        request.setAttribute("utilisateurses", utilisateurses);
        request.setAttribute("categorieses",categorieses);

        //Requête d'envoie a la JSP
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}