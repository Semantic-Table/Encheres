package fr.afpa.encheres.servlets;

import fr.afpa.encheres.bo.*;
import fr.afpa.encheres.dal.*;
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
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

// Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

// Parse the request
        ArrayList<String> formValue = new ArrayList<>();

        List<FileItem> items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }

        // Process the uploaded items
        Iterator<FileItem> iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = iter.next();

            if (item.isFormField()) {
                String name = item.getFieldName();
                String value = item.getString();
                formValue.add(value);
            } else {
                String fieldName = item.getFieldName();
                String fileName = item.getName();
                String contentType = item.getContentType();
                boolean isInMemory = item.isInMemory();
                long sizeInBytes = item.getSize();
                System.out.println(fileName);
                if (fileName != null) {
                    fileName = FilenameUtils.getName(fileName);
                }
                formValue.add(servletContext.getRealPath("img/" + fileName));
                File uploadedFile = new File(servletContext.getRealPath("img/" + fileName));
                System.out.println(formValue);
                try {
                    item.write(uploadedFile);
                } catch (FileExistsException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(formValue);
//fin du reve

        ArticlesVendusSQL articlesVendusSQL = new ArticlesVendusSQL();
        RetraitsSQL retraitsSQL = new RetraitsSQL();
        CategoriesSQL categoriesSQL = new CategoriesSQL();
        EncheresSQL encheresSQL = new EncheresSQL();
        if (session.getAttribute("no_utilisateur") != null){
            int utilisateursCno_utilisateurs = (int) session.getAttribute("no_utilisateur");
            Utilisateurs utilisateursC = utilisateursSQL.selectById((Integer) session.getAttribute("no_utilisateur"));
            request.setAttribute("utilisateursC",utilisateursC);
            request.setAttribute("utilisateursCno_utilisateurs",utilisateursCno_utilisateurs);
        }
        int no_utilisateurs = (int) session.getAttribute("no_utilisateur");
        Utilisateurs utilisateursC = utilisateursSQL.selectById(no_utilisateurs);
        articlesVendusSQL.update(Integer.parseInt(formValue.get(12)), new ArticlesVendus(
                formValue.get(0),
                formValue.get(1),
                Date.valueOf(formValue.get(5)),
                Time.valueOf(formValue.get(6) + ":00"),
                Date.valueOf(formValue.get(7)),
                Time.valueOf(formValue.get(8) + ":00"),
                Integer.parseInt(formValue.get(4)),
                Integer.parseInt(formValue.get(4)),
                no_utilisateurs,
                Integer.parseInt(formValue.get(2)),
                formValue.get(3)));
        ArticlesVendus articlesVendus = articlesVendusSQL.selectById(Integer.parseInt(formValue.get(12)));
        Utilisateurs utilisateurs = utilisateursSQL.selectById(articlesVendus.getNo_utilisateur());

        retraitsSQL.update(new Retraits(articlesVendus.getNo_article(), formValue.get(9), formValue.get(10), formValue.get(11)));
        Retraits retraits = retraitsSQL.selectById(articlesVendus.getNo_article());
        encheresSQL.update(
                Integer.parseInt(formValue.get(13)),
                new Encheres(
                        (int) session.getAttribute("no_utilisateur"),
                        articlesVendusSQL.selectByLast().getNo_article(),
                        Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()),
                        Integer.parseInt(formValue.get(4))));
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
}
