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
        if (session.getAttribute("no_utilisateur") != null){
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
            request.setAttribute("utilisateursC",utilisateursC);
            request.setAttribute("utilisateursCno_utilisateurs",utilisateursCno_utilisateurs);
        }
        EncheresSQL encheresSQL = new EncheresSQL();
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();


        int no_utilisateurs = (int) session.getAttribute("no_utilisateur");

        RetraitsSQL retraitsSQL = new RetraitsSQL();
        CategoriesSQL categoriesSQL = new CategoriesSQL();


        Utilisateurs utilisateursC = utilisateursSQL.selectById(no_utilisateurs);

        Categories categories = categoriesSQL.selectById(Integer.parseInt(request.getParameter("no_categorie")));
        ArticlesVendus articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(request.getParameter("no_article")));

        Retraits retraits = retraitsSQL.selectById(articlesVendus.getNo_article());
        try {


            if (Integer.parseInt(request.getParameter("montant_enchere")) > articlesVendus.getPrix_vente() && utilisateursC.getCredit() > Integer.parseInt(request.getParameter("montant_enchere"))) {
                if (encheresSQL.selectByNo_articleOrderByMontant_enchere(Integer.parseInt(request.getParameter("no_article"))) != null) {
                    Encheres encheres = encheresSQL.selectByNo_articleOrderByMontant_enchere(Integer.parseInt(request.getParameter("no_article")));
                    System.out.println(encheres);

                    Utilisateurs utilisateurs = utilisateursSQL.selectById(encheres.getNo_utilisateur());

                    System.out.println(utilisateurs.getCredit());
                    System.out.println(encheres.getMontant_enchere());
                    System.out.println(utilisateurs.getCredit() + encheres.getMontant_enchere());
                    utilisateurs.setCredit(utilisateurs.getCredit() + encheres.getMontant_enchere());
                    System.out.println(utilisateurs.getCredit());
                    utilisateursSQL.update(utilisateurs.getNo_utilisateur(), utilisateurs);

                }
                utilisateursC = utilisateursSQL.selectById(no_utilisateurs);
                articlesVendusSQL.updatePrix_vente(Integer.parseInt(request.getParameter("no_article")), Integer.parseInt(request.getParameter("montant_enchere")));
                System.out.println("hey");
                utilisateursC.setCredit(utilisateursC.getCredit() - Integer.parseInt(request.getParameter("montant_enchere")));
                utilisateursSQL.update(utilisateursC.getNo_utilisateur(), utilisateursC);
                encheresSQL.insert(new Encheres((int) session.getAttribute("no_utilisateur"), Integer.parseInt(request.getParameter("no_article")), Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), Integer.parseInt(request.getParameter("montant_enchere"))));

            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (ChampVideException e) {
            throw new RuntimeException(e);
        }
        articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(request.getParameter("no_article")));
        Utilisateurs utilisateurs = utilisateursSQL.selectById(articlesVendus.getNo_utilisateur());
        request.setAttribute("articlesVendus", articlesVendus);

        request.setAttribute("utilisateurs", utilisateurs);
        request.setAttribute("utilisateursC", utilisateursC);
        request.setAttribute("retraits", retraits);
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("WEB-INF/detailVente.jsp").forward(request, response);
    }
}
