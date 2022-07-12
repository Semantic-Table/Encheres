package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.*;
import fr.afpa.encheres.dal.*;
import fr.afpa.encheres.exceptions.ChampVideException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet(name = "Encherir", value = "/Encherir")
public class Encherir extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        EncheresSQL encheresSQL = new EncheresSQL();
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        RetraitsSQL retraitsSQL = new RetraitsSQL();
        CategoriesSQL categoriesSQL = new CategoriesSQL();

        //pas besoin de verifies la variable a ce stade l'utilisateur est forcement connectes
        Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));

        int no_utilisateurs = (int) session.getAttribute("no_utilisateur");
        ArticlesVendus articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(request.getParameter("no_article")));

        //update des credit rend l'argent a l'ancien encherisseur ajout de la nouvelle encheres en DB
        try {
            if (Integer.parseInt(request.getParameter("montant_enchere")) > articlesVendus.getPrix_vente() && utilisateursC.getCredit() > Integer.parseInt(request.getParameter("montant_enchere"))) {
                if (encheresSQL.selectByNo_articleOrderByMontant_enchere(Integer.parseInt(request.getParameter("no_article"))) != null) {
                    Encheres encheres = encheresSQL.selectByNo_articleOrderByMontant_enchere(Integer.parseInt(request.getParameter("no_article")));
                    Utilisateurs utilisateurs = utilisateursSQL.selectById(encheres.getNo_utilisateur());
                    utilisateurs.setCredit(utilisateurs.getCredit() + encheres.getMontant_enchere());
                    utilisateursSQL.update(utilisateurs.getNo_utilisateur(), utilisateurs);
                }
                utilisateursC = utilisateursSQL.selectById(no_utilisateurs);
                articlesVendusSQL.updatePrix_vente(Integer.parseInt(request.getParameter("no_article")), Integer.parseInt(request.getParameter("montant_enchere")));
                utilisateursC.setCredit(utilisateursC.getCredit() - Integer.parseInt(request.getParameter("montant_enchere")));
                utilisateursSQL.update(utilisateursC.getNo_utilisateur(), utilisateursC);
                encheresSQL.insert(new Encheres((int) session.getAttribute("no_utilisateur"), Integer.parseInt(request.getParameter("no_article")), Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), Integer.parseInt(request.getParameter("montant_enchere"))));

            }
        } catch (NumberFormatException e) {}
        catch (ChampVideException e) {}

        Categories categories = categoriesSQL.selectById(Integer.parseInt(request.getParameter("no_categorie")));
        Retraits retraits = retraitsSQL.selectById(articlesVendus.getNo_article());
        articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(request.getParameter("no_article")));
        Utilisateurs utilisateurs = utilisateursSQL.selectById(articlesVendus.getNo_utilisateur());

        request.setAttribute("articlesVendus", articlesVendus);
        request.setAttribute("utilisateursC",utilisateursC);
        request.setAttribute("utilisateurs", utilisateurs);
        request.setAttribute("utilisateursC", utilisateursC);
        request.setAttribute("retraits", retraits);
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("WEB-INF/detailVente.jsp").forward(request, response);
    }
}
