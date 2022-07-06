package fr.afpa.encheres.dal;

import fr.afpa.encheres.bo.Retraits;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RetraitsSQL {
    public ArrayList<Retraits> selectAll() {
        ArrayList<Retraits> retraitses = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_article, rue, code_postal, ville FROM retraits"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                retraitses.add(
                        new Retraits(
                                rs.getInt("no_article"),rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville")
                        )
                );
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return retraitses;
    }

    public void insert(Retraits retraits){
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO retraits(no_article, rue, code_postal, ville) VALUES (?,?,?,?)"
            );
            pstmt.setInt(1, retraits.getNo_article());
            pstmt.setString(2, retraits.getRue());
            pstmt.setString(3, retraits.getCode_postal());
            pstmt.setString(4,retraits.getVille());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Retraits retraits){
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM retraits WHERE no_article = ?"
            );
            pstmt.setInt(1, retraits.getNo_article());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
