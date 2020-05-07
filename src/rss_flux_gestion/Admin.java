package rss_flux_gestion;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


/**
 * Classe contenant toutes les m�thodes concernant les administrateurs ainsi que leurs informations
 * @author LeoHerbert
 *
 */
public class Admin extends User{
	/**
	 * Connexion d'un administrateur
	 * @return True si l'identifiant et le mot de passe sont correcte, false sinon
	 */
	public boolean connexion () throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
        String sql = "SELECT mdp FROM admin WHERE identifiant = ?";
        PreparedStatement data = cn.prepareStatement(sql);
		data.setString(1, this.identifiant);
		ResultSet resq = data.executeQuery();
		resq = data.getResultSet();
		ResultSetMetaData meta = data.getMetaData();
		while(resq.next()){
            for(int i = 1; i <= meta.getColumnCount(); i++) {
            	String ok = resq.getObject(i).toString();
            	if (ok.equals(this.mdp) == true) {
            		return true;
            	}
            }
		}
			data.close();
			return false;
	}
	
	/**
	 * Supprimer d'un user
	 * @param user Utilisateur � supprimer
	 */
	public void suppUser(User user) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", ""); 
		 String sql = "DELETE FROM users WHERE identifiant = ?";
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, user.identifiant);
		 data.executeUpdate();
		 data.close();
	}
	
	/**
	 * Supprimer des abonnements d'un user
	 * @param user Utilisateur concern�
	 */
	public void suppAbonnements (User user) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", ""); 
		 String sql = "DELETE FROM abonnement WHERE identifiant = ?";
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, user.getIdentifiant());
		 data.executeUpdate();
		 data.close();
	}
	
	/**
	 * Supprimmer des contraintes de l'utilisateur
	 * @param user  Utilisateur
	 */
	public void suppContraintes (User user) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", ""); 
		 String sql = "DELETE FROM contraintes WHERE identifiant = ?";
		 PreparedStatement data = conn.prepareStatement(sql);
		 data.setString(1, user.getIdentifiant());
		 data.executeUpdate();
		 data.close();
	}
	
	/**
	 * Ajouter une contrainte � un user
	 * @param user Utilisateur concern�
	 * @param nb Nombre maximum d'abonnements
	 */
	public void contraintes(User user, int nb) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", ""); 
		 String sql = "INSERT INTO contraintes VALUES (?, ?)";
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, user.getIdentifiant());
		 data.setInt(2, nb);
		 data.executeUpdate();
		 data.close();
	}
	
	/**
	 * Supprimer un flux
	 * @param flux Flux � supprimer
	 */
	public void suppFlux(Flux flux) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", ""); 
		 String sql = "DELETE FROM flux WHERE url = ?";
		 String url = flux.getUrl().toString();
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, url);
		 data.executeUpdate();
		 data.close();
	}
	
	/**
	 * Supprimer les abonnements correspondant au flux concern�
	 * @param flux Flux concern�
	 */
	public void suppAbonnementsF (Flux flux) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", ""); 
		 String sql = "DELETE FROM abonnement WHERE url = ?";
		 String url = flux.getUrl().toString();
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, url);
		 data.executeUpdate();
		 data.close();
	}
	
	/**
	 * Supprimer les abonnements aux cat�gories d'un user
	 * @param user Utilisateur concern�
	 */
	public void suppAbonnementsC (User user) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", ""); 
		 String sql = "DELETE FROM aboncat WHERE identifiant = ?";
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, user.getIdentifiant());
		 data.executeUpdate();
		 data.close();
	}
}
