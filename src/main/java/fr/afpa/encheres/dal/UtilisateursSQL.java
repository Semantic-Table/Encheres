package fr.afpa.encheres.dal;

import fr.afpa.encheres.bo.Utilisateurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtilisateursSQL {
    public ArrayList<Utilisateurs> selectAll() {
        ArrayList<Utilisateurs> utilisateurses = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM utilisateurs"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                utilisateurses.add(
                        new Utilisateurs(
                                rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur")
                        )
                );
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utilisateurses;
    }

    public void insert(Utilisateurs utilisateurs) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO utilisateurs(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)"
            );
            pstmt.setString(1, utilisateurs.getPseudo());
            pstmt.setString(2, utilisateurs.getNom());
            pstmt.setString(3, utilisateurs.getPrenom());
            pstmt.setString(4, utilisateurs.getEmail());
            pstmt.setString(5, utilisateurs.getTelephone());
            pstmt.setString(6, utilisateurs.getRue());
            pstmt.setString(7, utilisateurs.getCode_postal());
            pstmt.setString(8, utilisateurs.getVille());
            pstmt.setString(9, utilisateurs.getMot_de_passe());
            pstmt.setInt(10, utilisateurs.getCredit());
            pstmt.setBoolean(11, utilisateurs.isAdministrateur());
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int no_utilisateur) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM utilisateurs WHERE no_utilisateur = ?"
            );
            pstmt.setInt(1, no_utilisateur);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Utilisateurs selectById(int no_utilisateurs) {
        Utilisateurs utilisateurs = null;
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM utilisateurs WHERE no_utilisateur = ?"
            );
            pstmt.setInt(1,no_utilisateurs);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                utilisateurs = new Utilisateurs(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
            }

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utilisateurs;
    }

    public Utilisateurs selectByPseudoAndByMot_de_passe(String pseudo, String mot_de_passe) {
        Utilisateurs utilisateurs = null;
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM utilisateurs WHERE pseudo = ? AND mot_de_passe = ?"
            );
            pstmt.setString(1,pseudo);
            pstmt.setString(2,mot_de_passe);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                utilisateurs = new Utilisateurs(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
            }

            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utilisateurs;
    }
}
