package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.*;
import fr.afpa.encheres.dal.*;

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
        if (session.getAttribute("no_utilisateur") != null){
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs",utilisateursCno_utilisateurs);
        }
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        ArticlesVendus articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(request.getParameter("no_article")));
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
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
        if(session.getAttribute("no_utilisateur") != null && articlesVendus.getDate_fin_encheres().before(Date.valueOf(LocalDate.now()))) {
            Utilisateurs utilisateursGagnant = utilisateursSQL.selectById(encheres.getNo_utilisateur());
            request.setAttribute("utilisateursGagnant",utilisateursGagnant);
            request.getRequestDispatcher("WEB-INF/detailVenteTermine.jsp").forward(request, response);
        } else if( (int) session.getAttribute("no_utilisateur") == articlesVendus.getNo_utilisateur() && articlesVendus.getDate_debut_encheres().after(Date.valueOf(LocalDate.now()))) {
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
            Utilisateurs utilisateursC = utilisateursSQL.selectById(utilisateursCno_utilisateurs);
            request.setAttribute("encheres",encheres);
            request.setAttribute("utilisateursC", utilisateursC);
            request.getRequestDispatcher("WEB-INF/DetailVenteModifiable.jsp").forward(request, response);
        }
        else if (session.getAttribute("no_utilisateur") != null && articlesVendus.getDate_debut_encheres().before(Date.valueOf(LocalDate.now()))) {
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
            Utilisateurs utilisateursC = utilisateursSQL.selectById(utilisateursCno_utilisateurs);
            request.setAttribute("utilisateursC", utilisateursC);
            request.getRequestDispatcher("WEB-INF/detailVente.jsp").forward(request, response);
        }else
        request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
