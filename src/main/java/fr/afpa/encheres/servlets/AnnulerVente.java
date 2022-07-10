package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.ArticlesVendus;
import fr.afpa.encheres.bo.Utilisateurs;
import fr.afpa.encheres.dal.ArticlesVendusSQL;
import fr.afpa.encheres.dal.UtilisateursSQL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AnnulerVente", value = "/AnnulerVente")
public class AnnulerVente extends HttpServlet {
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
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        ArticlesVendus articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(request.getParameter("no_article")));
        articlesVendusSQL.delete(articlesVendus);
        ArrayList<ArticlesVendus> articlesVenduses = articlesVendusSQL.selectAll();
        request.setAttribute("articlesVenduses", articlesVenduses);

        ArrayList<Utilisateurs> utilisateurses = utilisateursSQL.selectAll();
        request.setAttribute("utilisateurses", utilisateurses);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request,response);
    }
}
