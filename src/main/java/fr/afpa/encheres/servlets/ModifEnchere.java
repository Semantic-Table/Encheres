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
import java.time.LocalTime;

@WebServlet(name = "ModifEnchere", value = "/ModifEnchere")
public class ModifEnchere extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("no_utilisateur") != null) {
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
        }
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        RetraitsSQL retraitsSQL = new RetraitsSQL();
        CategoriesSQL categoriesSQL = new CategoriesSQL();
        EncheresSQL encheresSQL = new EncheresSQL();
        if (session.getAttribute("no_utilisateur") != null) {
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            request.setAttribute("utilisateursCno_utilisateurs", utilisateursCno_utilisateurs);
        }
        int no_utilisateurs = (int) session.getAttribute("no_utilisateur");
        Utilisateurs utilisateursC = utilisateursSQL.selectById(no_utilisateurs);
        articlesVendusSQL.update(Integer.parseInt(request.getParameter("no_article")), new ArticlesVendus(
                request.getParameter("nom_article"),
                request.getParameter("description"),
                Date.valueOf(request.getParameter("date_debut_enchere")),
                Date.valueOf(request.getParameter("date_fin_enchere")),
                Integer.parseInt(request.getParameter("prix_initial")),
                Integer.parseInt(request.getParameter("prix_initial")),
                no_utilisateurs,
                Integer.parseInt(request.getParameter("no_categorie"))));
        ArticlesVendus articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(request.getParameter("no_article")));
        Utilisateurs utilisateurs = utilisateursSQL.selectById(articlesVendus.getNo_utilisateur());

        retraitsSQL.update(new Retraits(articlesVendus.getNo_article(), request.getParameter("rue"), request.getParameter("code_postal"), request.getParameter("ville")));
        Retraits retraits = retraitsSQL.selectById(articlesVendus.getNo_article());
        encheresSQL.update(
                Integer.parseInt(request.getParameter("no_encheres")),
                new Encheres(
                        (int) session.getAttribute("no_utilisateur"),
                        articlesVendusSQL.selectByLast().getNo_article(),
                        Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()),
                        Integer.parseInt(request.getParameter("prix_initial"))));
        Categories categories = categoriesSQL.selectById(articlesVendus.getNo_categorie());
        Encheres encheres = encheresSQL.selectByNo_articleOrderByMontant_enchere(articlesVendus.getNo_article());
        request.setAttribute("articlesVendus", articlesVendus);
        request.setAttribute("encheres", encheres);
        request.setAttribute("utilisateurs", utilisateurs);
        request.setAttribute("utilisateursC", utilisateursC);
        request.setAttribute("retraits", retraits);
        request.setAttribute("categories", categories);
        if (Date.valueOf(LocalDate.now()).before(articlesVendus.getDate_debut_encheres())) {
            System.out.println("kiki");
            request.getRequestDispatcher("WEB-INF/DetailVenteModifiable.jsp").forward(request,response);
        } else {
            System.out.println("koukou");
            request.getRequestDispatcher("WEB-INF/detailVente.jsp").forward(request,response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
