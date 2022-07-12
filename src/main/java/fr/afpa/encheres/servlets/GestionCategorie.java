package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.Categories;
import fr.afpa.encheres.bo.Utilisateurs;
import fr.afpa.encheres.dal.CategoriesSQL;
import fr.afpa.encheres.dal.UtilisateursSQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GestionCategorie", value = "/GestionCategorie")
public class GestionCategorie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        CategoriesSQL categoriesSQL = new CategoriesSQL();

        if (session.getAttribute("no_utilisateur") != null){
            Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
            request.setAttribute("utilisateursC",utilisateursC);
        }


        categoriesSQL.delete(Integer.parseInt(request.getParameter("no_categorie")));
        ArrayList<Categories> categorieses = categoriesSQL.selectAll();
        Utilisateurs utilisateurs = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));

        request.setAttribute("categorieses",categorieses);
        request.setAttribute("utilisateurs",utilisateurs);

        request.getRequestDispatcher("WEB-INF/profil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        CategoriesSQL categoriesSQL = new CategoriesSQL();

        if (session.getAttribute("no_utilisateur") != null){
            Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
            request.setAttribute("utilisateursC",utilisateursC);
        }

        categoriesSQL.insert(new Categories(request.getParameter("libelle")));
        ArrayList<Categories> categorieses = categoriesSQL.selectAll();
        Utilisateurs utilisateurs = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));

        request.setAttribute("categorieses",categorieses);
        request.setAttribute("utilisateurs",utilisateurs);
        request.getRequestDispatcher("WEB-INF/profil.jsp").forward(request, response);
    }
}
