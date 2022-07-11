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

@WebServlet(name = "ModifierCategorie", value = "/ModifierCategorie")
public class ModifierCategorie extends HttpServlet {
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
        CategoriesSQL categoriesSQL = new CategoriesSQL();
        categoriesSQL.update(new Categories(Integer.parseInt(request.getParameter("no_categorie")),request.getParameter("libelle")));
        ArrayList<Categories> categorieses = categoriesSQL.selectAll();
        request.setAttribute("categorieses",categorieses);
        Utilisateurs utilisateurs = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
        request.setAttribute("utilisateurs",utilisateurs);
        request.getRequestDispatcher("WEB-INF/profil.jsp").forward(request, response);
    }
}
