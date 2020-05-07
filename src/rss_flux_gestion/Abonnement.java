package rss_flux_gestion;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;*/

import rss_flux_gestion.User;

public class Abonnement extends User{
	
	/**
	 * Abonnement d'un utilisateur à un flux / Deplacer dans user
	 * Cette classe n'est pas utiliser
	 * @param u = utilisateur 
	 * @param f = flux 
	 * @param nbJours = nombre de jours d'abonnement
	 */
	/*public void abonnement (Users u, Flux f, int nbJours) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");  
		Date dateDebut = new Date();
		long dateD=dateDebut.getTime();
		Date dateFin = new Date();
		dateFin = ajouterJour(dateFin, nbJours);
		long longDateF = dateFin.getTime();
		 String sql = "INSERT INTO abonnement VALUES (?, ?, ?, ?)";
		 String url = f.getUrl().toString();
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, u.identifiant);
		 data.setString(2,url);
		 data.setLong(3, dateD);
		 data.setLong(4, longDateF);
		 data.executeUpdate();
		 data.close();
	}*/
	
	/**
	 * Abonnement à une catégorie
	 * @param nomCat = nom de la catégorie à s'abonner
	 */
	/*public void abonCat (String nomCat) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");  
		 String sql = "INSERT INTO abocat VALUES (?, ?)";
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, this.identifiant);
		 data.setString(2,nomCat.toUpperCase());
		 data.executeUpdate();
		 data.close();
	}*/
	
	
	/**
	 * Désabonnement d'un utilisateur
	 * @param f = flux auquel user se desabonne
	 */
	/*public void desabonner(Flux f) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", ""); 
		String url = f.getUrl().toString();
		 String sql = "DELETE FROM abonnement WHERE identifiant = ? AND url = ?"; //supprimer un abonnement
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, this.identifiant);
		 data.setString(2,url);
		 data.executeUpdate();
		 data.close();
	}*/
	
	/**
	 * Ajout d'une catégorie à la base de donnée
	 * @param nomCat = nom de la catégorie
	 */
	/*public void ajoutCat (String nomCat) throws SQLException {
		 boolean verifCat = false;
		 Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");  
		 String sql = "SELECT nomCat FROM categories";
		 PreparedStatement data = cn.prepareStatement(sql);
		 ResultSet res = data.executeQuery();
		 res = data.getResultSet();
	   	 ResultSetMetaData resMeta = data.getMetaData();
	     while(res.next()){ 
	       for(int i = 1; i <= resMeta.getColumnCount(); i++) {
	    	   String cat = res.getString(i);
	    	   if (cat.toUpperCase().equals(nomCat.toUpperCase())) {
	    		   verifCat = true;
	    	   }
          } 	
	     }
	     if (verifCat == false) {  //ajouter une nouvelle catégorie
			 sql = "INSERT INTO categories VALUES (?, 0)";
			 data = cn.prepareStatement(sql);
			 data.setString(1, nomCat);
			 data.executeUpdate();
			 data.close();
	     }
	}*/
	
	/**
	 * Compter le nombre d'users abonnés à une catégorie
	 * @param nomCat =  nom de la catégorie
	 * @return Le nombre d'abonnés
	 */
	/*public int nbAbonCat (String nomCat) throws SQLException {
		int nbAbonCat = 0;
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
		String sql = "SELECT COUNT(identifiant) FROM abocat WHERE nomcat = ?"; //compter le nombre d'abonnés à une catégorie
	    PreparedStatement data = cn.prepareStatement(sql);
	    data.setString(1, nomCat.toUpperCase());
	    ResultSet res = data.executeQuery();
		res = data.getResultSet();
		ResultSetMetaData resMeta = data.getMetaData();
       while(res.next()){ 
         for(int i = 1; i <= resMeta.getColumnCount(); i++)
         	nbAbonCat = res.getInt(1);
       }
       return nbAbonCat;
	}*/
	
	/**
	 * Modifier le nombre d'users abonnés
	 * @param nomCat =  nom de la catégorie à modifier
	 * @param nbAbonCat = nouveau nombre d'abonnés
	 */
	/*public void ajoutAbonCat (String nomCat, int nbAbonCat) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
		String sql = "UPDATE categories SET nbabo = ? WHERE nomcat = ?";  //Modification de nombre d'abonnés
	    PreparedStatement data = cn.prepareStatement(sql);
	    data.setInt(1,  nbAbonCat);
		data.setString(2, nomCat);
		data.executeUpdate();
		data.close();
	}*/
		
	/**
	 * Ajout d'un nombre de jours donné à une date d'abonnement
	 * @param date = la date à prolonger 
	 * @param nbJours = nombre de jours à ajouter
	 * @return la nouvelle date.
	 */
	/*public static Date ajouterJour(Date date, int nbJours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, nbJours);
		return cal.getTime();
	}*/
	
	/**
	 * Verifier si le nombre max d'abonnés est atteint, sinon on peut s'abonner
	 * @return True si l'utilisateur peut s'abonner, false sinon
	 */
	/*public boolean verifContrainte() throws SQLException {
		
		int nbAbos = 0;
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");  
		String sql = "SELECT nbabos FROM contraintes WHERE identifiant = ?";
		PreparedStatement data = cn.prepareStatement(sql);
		data.setString(1, this.identifiant);
		ResultSet res = data.executeQuery();
		res = data.getResultSet();
		ResultSetMetaData meta = data.getMetaData();
		while(res.next()){	//Verifier nb abonement maximum établie 
           for(int i = 1; i <= meta.getColumnCount(); i++) {
           	int verif = res.getInt(i);
           	sql = "SELECT COUNT(url) FROM abonnement WHERE identifiant = ? GROUP BY identifiant";
       		data = cn.prepareStatement(sql);
       		data.setString(1, this.identifiant);
       		res = data.executeQuery();
       		res = data.getResultSet();
       		meta = data.getMetaData();
       		data.close();
       		while(res.next()){//Compte nb d'abo
                   for(int j = 1; j <= meta.getColumnCount(); j++) {
                   	nbAbos = res.getInt(j);
                   }
       		}
           	if (nbAbos >= verif) {//Si le nombre d'abonnement dépasse la contrainte
           		return false;
           	}
           }
		}
		return true;
	}*/
}
