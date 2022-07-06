package fr.afpa.encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class ConnectionProvider {
    private static DataSource dataSource;

    /**
     * Au chargement de la classe, la DataSource est recherchée dans le ficheir context.xml
     */
    static
    {
        Context context;
        try {
            context = new InitialContext();
            ConnectionProvider.dataSource = (DataSource)context.lookup("java:comp/env/jdbc/encheres");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException("Impossible d'accéder à la base de données");
        }
    }

    /**
     * Cette méthode retourne une connection opérationnelle issue du pool de connexion
     * vers la base de données.
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException
    {
        return ConnectionProvider.dataSource.getConnection();
    }
}
