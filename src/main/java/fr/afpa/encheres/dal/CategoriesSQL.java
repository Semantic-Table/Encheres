package fr.afpa.encheres.dal;

import fr.afpa.encheres.bo.Categories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriesSQL {
    public ArrayList<Categories> selectAll() {
        ArrayList<Categories> categorieses = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_categorie,libelle FROM categories"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                categorieses.add(
                        new Categories(
                                rs.getInt("no_categorie"), rs.getString("libelle")
                        )
                );
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categorieses;
    }

    public void insert(Categories categories) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO categories(libelle) VALUES (?)"
            );
            pstmt.setString(1, categories.getLibelle());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int no_categorie){
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM categories WHERE no_categorie = ?"
            );
            pstmt.setInt(1, no_categorie);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Categories selectById(int no_categorie) {
        Categories categories = null;
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_categorie,libelle FROM categories WHERE no_categorie = ?"
            );
            pstmt.setInt(1,no_categorie);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                categories = new Categories(rs.getInt("no_categorie"), rs.getString("libelle"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public void update(Categories categories) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE categories SET libelle = ? WHERE no_categorie = ?"
            );
            pstmt.setString(1, categories.getLibelle());
            pstmt.setInt(2,categories.getNo_categorie());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
