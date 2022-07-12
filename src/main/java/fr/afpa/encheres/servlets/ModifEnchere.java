package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.*;
import fr.afpa.encheres.dal.*;
import fr.afpa.encheres.exceptions.ChampVideException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "ModifEnchere", value = "/ModifEnchere")
public class ModifEnchere extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UtilisateursSQL utilisateursSQL = new UtilisateursSQL();
        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        RetraitsSQL retraitsSQL = new RetraitsSQL();
        CategoriesSQL categoriesSQL = new CategoriesSQL();
        EncheresSQL encheresSQL = new EncheresSQL();

        if (session.getAttribute("no_utilisateur") != null) {
            Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
            request.setAttribute("utilisateursC", utilisateursC);
        }

        //gestion du formulaire ou l'on recupere des data et non du txt
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        ArrayList<String> formValue = new ArrayList<>();
        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }
        Iterator<FileItem> iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = iter.next();
            if (item.isFormField()) {
                String value = item.getString();
                formValue.add(value);
            } else {
                String fileName = item.getName();
                System.out.println(fileName);
                if (fileName != null) {
                    fileName = FilenameUtils.getName(fileName);
                }
                formValue.add("img/" + fileName);
                File uploadedFile = new File(servletContext.getRealPath("img/" + fileName));
                System.out.println(formValue);
                try {
                    item.write(uploadedFile);
                } catch (Exception e) {}
            }
        }

        int no_utilisateurs = (int) session.getAttribute("no_utilisateur");
        Utilisateurs utilisateursC = utilisateursSQL.selectById(no_utilisateurs);
        ArticlesVendus articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(formValue.get(12)));
        retraitsSQL.update(new Retraits(articlesVendus.getNo_article(), formValue.get(9), formValue.get(10), formValue.get(11)));
        Retraits retraits = retraitsSQL.selectById(articlesVendus.getNo_article());

        if (formValue.get(3).equals("img/")) {
            formValue.set(3, articlesVendus.getImage());
        }
        //update si tout est ok
        try {
            articlesVendusSQL.update(Integer.parseInt(formValue.get(12)), new ArticlesVendus(formValue.get(0), formValue.get(1), Date.valueOf(formValue.get(5)), Time.valueOf(formValue.get(6)), Date.valueOf(formValue.get(7)), Time.valueOf(formValue.get(8)), Integer.parseInt(formValue.get(4)), Integer.parseInt(formValue.get(4)), no_utilisateurs, Integer.parseInt(formValue.get(2)), formValue.get(3)));

            articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(formValue.get(12)));

            Utilisateurs utilisateurs = utilisateursSQL.selectById(articlesVendus.getNo_utilisateur());
            ArrayList<Categories> categorieses = categoriesSQL.selectAll();
            Categories categories = categoriesSQL.selectById(articlesVendus.getNo_categorie());
            Encheres encheres = encheresSQL.selectByNo_articleOrderByMontant_enchere(articlesVendus.getNo_article());

            request.setAttribute("categorieses", categorieses);
            request.setAttribute("retraits", retraits);
            request.setAttribute("encheres", encheres);
            request.setAttribute("utilisateurs", utilisateurs);
            request.setAttribute("utilisateursC", utilisateursC);
            request.setAttribute("articlesVendus", articlesVendus);
            request.setAttribute("categories", categories);

            if (Date.valueOf(LocalDate.now()).before(articlesVendus.getDate_debut_encheres())) {
                request.getRequestDispatcher("WEB-INF/DetailVenteModifiable.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("WEB-INF/detailVente.jsp").forward(request, response);
            }
        } catch (ChampVideException e) {

            articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(formValue.get(12)));
            ArrayList<Categories> categorieses = categoriesSQL.selectAll();
            request.setAttribute("articlesVendus", articlesVendus);
            request.setAttribute("now", LocalDate.now());
            request.setAttribute("nowend", LocalDate.now().plusDays(1));
            request.setAttribute("categorieses", categorieses);
            request.getRequestDispatcher("WEB-INF/DetailVenteModifiable.jsp").forward(request, response);
        }
    }
}
