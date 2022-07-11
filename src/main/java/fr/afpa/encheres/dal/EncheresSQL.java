package fr.afpa.encheres.dal;

import fr.afpa.encheres.bo.Encheres;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EncheresSQL {
    public ArrayList<Encheres> selectAll() {
        ArrayList<Encheres> enchereses = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_encheres,no_utilisateur,no_article,date_enchere,time_enchere,montant_enchere FROM encheres"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                enchereses.add(
                        new Encheres(
                                rs.getInt("no_encheres"),rs.getInt("no_utilisateur"),rs.getInt("no_article"),rs.getDate("date_enchere"),rs.getTime("time_enchere"),rs.getInt("montant_enchere")
                        )
                );
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return enchereses;
    }

    public ArrayList<Encheres> selectByNo_utilisateur(int no_utilisateur) {
        ArrayList<Encheres> enchereses = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_encheres,no_utilisateur,no_article,date_enchere,time_enchere,montant_enchere FROM encheres WHERE no_utilisateur = ?"
            );
            pstmt.setInt(1,no_utilisateur);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                enchereses.add(
                        new Encheres(
                                rs.getInt("no_encheres"),rs.getInt("no_utilisateur"),rs.getInt("no_article"),rs.getDate("date_enchere"),rs.getTime("time_enchere"),rs.getInt("montant_enchere")
                        )
                );
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return enchereses;
    }

    public ArrayList<Encheres> selectByNo_article(int no_article) {
        ArrayList<Encheres> enchereses = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_encheres,no_utilisateur,no_article,date_enchere,time_enchere,montant_enchere from encheres where no_article = ? order by montant_enchere desc;"
            );
            pstmt.setInt(1,no_article);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                enchereses.add(
                        new Encheres(
                                rs.getInt("no_encheres"),rs.getInt("no_utilisateur"),rs.getInt("no_article"),rs.getDate("date_enchere"),rs.getTime("time_enchere"),rs.getInt("montant_enchere")
                        )
                );
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return enchereses;
    }

    public int rendezLargent(int no_utilisateur){
        int argent = 0;
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_encheres,no_utilisateur,no_article,date_enchere,time_enchere,MAX(montant_enchere) as montant_enchere FROM encheres group by no_article;"
            );

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                if (rs.getInt("no_utilisateur") == no_utilisateur){
                    argent+= rs.getInt("montant_enchere");
                }
                System.out.println(argent + "argent a rendre");
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return argent;
    }

    public void insert(Encheres encheres){
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO encheres(no_utilisateur, no_article, date_enchere, time_enchere, montant_enchere) VALUES (?,?,?,?,?)"
            );
            pstmt.setInt(1, encheres.getNo_utilisateur());
            pstmt.setInt(2, encheres.getNo_article());
            pstmt.setDate(3, encheres.getDate_enchere());
            pstmt.setTime(4,encheres.getTime_enchere());
            pstmt.setInt(5,encheres.getMontant_enchere());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Encheres encheres){
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM encheres WHERE no_encheres = ?"
            );
            pstmt.setInt(1, encheres.getNo_encheres());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByNo_utilisateur(int no_utilisateur){
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM encheres WHERE no_utilisateur = ?"
            );
            pstmt.setInt(1, no_utilisateur);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Encheres selectByNo_articleOrderByMontant_enchere(int no_article){
        Encheres encheres = null;
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_encheres,no_utilisateur,no_article,date_enchere,time_enchere,montant_enchere FROM encheres WHERE no_article = ? ORDER BY montant_enchere DESC LIMIT 1"
            );
            pstmt.setInt(1, no_article);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                encheres = new Encheres(rs.getInt("no_encheres"),rs.getInt("no_utilisateur"),rs.getInt("no_article"),rs.getDate("date_enchere"),rs.getTime("time_enchere"),rs.getInt("montant_enchere"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return encheres;
    }

    public void update(int no_encheres, Encheres encheres) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE encheres SET date_enchere=?, time_enchere=?, montant_enchere=? WHERE no_encheres = ?"
            );

            pstmt.setDate(1, encheres.getDate_enchere());
            pstmt.setTime(2,encheres.getTime_enchere());
            pstmt.setInt(3,encheres.getMontant_enchere());
            pstmt.setInt(4,no_encheres);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
