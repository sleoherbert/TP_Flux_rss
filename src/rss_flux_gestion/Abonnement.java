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
	 * Abonnement d'un utilisateur � un flux / Deplacer dans user
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
	 * Abonnement � une cat�gorie
	 * @param nomCat = nom de la cat�gorie � s'abonner
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
	 * D�sabonnement d'un utilisateur
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
	 * Ajout d'une cat�gorie � la base de donn�e
	 * @param nomCat = nom de la cat�gorie
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
	     if (verifCat == false) {  //ajouter une nouvelle cat�gorie
			 sql = "INSERT INTO categories VALUES (?, 0)";
			 data = cn.prepareStatement(sql);
			 data.setString(1, nomCat);
			 data.executeUpdate();
			 data.close();
	     }
	}*/
	
	/**
	 * Compter le nombre d'users abonn�s � une cat�gorie
	 * @param nomCat =  nom de la cat�gorie
	 * @return Le nombre d'abonn�s
	 */
	/*public int nbAbonCat (String nomCat) throws SQLException {
		int nbAbonCat = 0;
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
		String sql = "SELECT COUNT(identifiant) FROM abocat WHERE nomcat = ?"; //compter le nombre d'abonn�s � une cat�gorie
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
	 * Modifier le nombre d'users abonn�s
	 * @param nomCat =  nom de la cat�gorie � modifier
	 * @param nbAbonCat = nouveau nombre d'abonn�s
	 */
	/*public void ajoutAbonCat (String nomCat, int nbAbonCat) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
		String sql = "UPDATE categories SET nbabo = ? WHERE nomcat = ?";  //Modification de nombre d'abonn�s
	    PreparedStatement data = cn.prepareStatement(sql);
	    data.setInt(1,  nbAbonCat);
		data.setString(2, nomCat);
		data.executeUpdate();
		data.close();
	}*/
		
	/**
	 * Ajout d'un nombre de jours donn� � une date d'abonnement
	 * @param date = la date � prolonger 
	 * @param nbJours = nombre de jours � ajouter
	 * @return la nouvelle date.
	 */
	/*public static Date ajouterJour(Date date, int nbJours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, nbJours);
		return cal.getTime();
	}*/
	
	/**
	 * Verifier si le nombre max d'abonn�s est atteint, sinon on peut s'abonner
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
		while(res.next()){	//Verifier nb abonement maximum �tablie 
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
           	if (nbAbos >= verif) {//Si le nombre d'abonnement d�passe la contrainte
           		return false;
           	}
           }
		}
		return true;
	}*/
}
