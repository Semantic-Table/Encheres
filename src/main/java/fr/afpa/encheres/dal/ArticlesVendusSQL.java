package fr.afpa.encheres.dal;

import fr.afpa.encheres.bo.ArticlesVendus;
import fr.afpa.encheres.bo.Categories;

import java.sql.*;
import java.util.ArrayList;

public class ArticlesVendusSQL {
    public ArrayList<ArticlesVendus> selectAll() {
        ArrayList<ArticlesVendus> articlesVenduses = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie FROM articles_vendus"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                articlesVenduses.add(
                        new ArticlesVendus(
                                rs.getInt("no_article"),rs.getString("nom_article"),rs.getString("description"),rs.getDate("date_debut_encheres"),rs.getDate("date_fin_encheres"),rs.getInt("prix_initial"),rs.getInt("prix_vente"),rs.getInt("no_utilisateur"),rs.getInt("no_categorie")
                        )
                );
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articlesVenduses;
    }

    public void insert(ArticlesVendus articlesVendus){
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO articles_vendus(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?)"
            );
            pstmt.setString(1, articlesVendus.getNom_article());
            pstmt.setString(2, articlesVendus.getDescription());
            pstmt.setDate(3, articlesVendus.getDate_debut_encheres());
            pstmt.setDate(4,articlesVendus.getDate_fin_encheres());
            pstmt.setInt(5,articlesVendus.getPrix_initial());
            pstmt.setInt(6,articlesVendus.getPrix_vente());
            pstmt.setInt(7,articlesVendus.getNo_utilisateur());
            pstmt.setInt(8,articlesVendus.getNo_categorie());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(ArticlesVendus articlesVendus){
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM articles_vendus WHERE no_article = ?"
            );
            pstmt.setInt(1, articlesVendus.getNo_article());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArticlesVendus selectByLast() {
        ArticlesVendus articlesVendus = null;
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie FROM articles_vendus ORDER BY no_article DESC LIMIT 1"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                articlesVendus = new ArticlesVendus(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
            }

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articlesVendus;
    }

    public ArticlesVendus selectById(int no_article) {
        ArticlesVendus articlesVendus = null;
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie FROM articles_vendus WHERE no_article = ?"
            );
            pstmt.setInt(1,no_article);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                articlesVendus = new ArticlesVendus(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"));
            }

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articlesVendus;
    }

    public void updatePrix_vente(int no_article,int montant){
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE articles_vendus SET prix_vente = ? WHERE no_article = ?"
            );
            pstmt.setInt(1,montant);
            pstmt.setInt(2,no_article);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<ArticlesVendus> selectByCategorieAndByNom_article(int no_categorie, String nom_article) {
        ArrayList<ArticlesVendus> articlesVenduses = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie FROM articles_vendus WHERE nom_article LIKE ? AND no_categorie = ?"
            );
            pstmt.setString(1,"%" + nom_article + "%");
            pstmt.setInt(2,no_categorie);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                articlesVenduses.add(
                        new ArticlesVendus(
                                rs.getInt("no_article"),rs.getString("nom_article"),rs.getString("description"),rs.getDate("date_debut_encheres"),rs.getDate("date_fin_encheres"),rs.getInt("prix_initial"),rs.getInt("prix_vente"),rs.getInt("no_utilisateur"),rs.getInt("no_categorie")
                        )
                );
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articlesVenduses;
    }
}
