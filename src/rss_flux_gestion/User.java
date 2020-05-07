package rss_flux_gestion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe content les d�claration des informations sur l'utilisateur ainsi que les methodes de connexion et inscription
 * @author LeoHerbert
 *
 */

public class User {
	protected String nom;
	protected String prenom;
	protected String mail;
	protected String identifiant;
	protected String mdp;
	

	public User () {
		this.nom =("");
		this.prenom =("");
		this.mail =("");
		this.identifiant =("");
		this.mdp = ("");
	}
	
	
	/**
	 * R�cup�rer le nom
	 * @return  nom 
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Ajouter/Modifier le nom
	 * @param nom Setter  nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * R�cup�rer le pr�nom
	 * @return pr�nom
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Ajouter/Modifier le pr�nom
	 * @param prenom Setter pr�nom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * R�cup�rer le mail
	 * @return  mail
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * Ajouter/Modifier mail
	 * @param mail Setter mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * R�cup�rerl'identifiant
	 * @return  identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}
	
	/**
	 * Ajouter/Modifier de l'identifiant
	 * @param identifiant Setter identifiant
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	/**
	 * R�cup�rer mot de passe
	 * @return  mot de passe
	 */
	public String getMdp() {
		return mdp;
	}
	
	/**
	 * Ajouter/Modifier mot de passe
	 * @param mdp Setter mdp
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	/**Infos user
	 * @return Information sur l'utilisateur (toString)
	 */
	public String toString () {
		return new String ("nom : " + getNom() + "\nprenom : " + getPrenom() + "\nmail : " + getMail() + "\nidentifiant : " + getIdentifiant() + "\nmot de passe : " + getMdp());
	}

	/**
	 *Inscription d'une personne
	 */
	
	//Ajout un nouvel utilisateur dans la table users
	public void inscription () throws SQLException {
		 Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");  
		 nom = nom.toUpperCase();
		 String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, this.prenom);
		 data.setString(2, this.nom);
		 data.setString(3, this.mail);
		 data.setString(4, this.identifiant);
		 data.setString(5, this.mdp);
		 data.executeUpdate();
		 data.close();
	}

	/**
	 * Connexion user
	 * @return Renvoi True si identifiant et mot de passe correcte, false sinon
	 */
	//V�rifier si le mot de passe correspodant � l'identifiant
	public boolean connexion () throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
       String sql = "SELECT mdp FROM users WHERE identifiant = ?";
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
	 * Abonnement d'un utilisateur � un flux
	 * @param user = utilisateur 
	 * @param flux = flux concern�
	 * @param nbJours = nombre de jours d'abonnement
	 */
	public void abonnement (User user, Flux flux, int nbJours) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");  
		Date dateDebut = new Date();
		long dateD=dateDebut.getTime();
		Date dateFin = new Date();
		dateFin = ajouterJour(dateFin, nbJours);
		long longDateF = dateFin.getTime();
		 String sql = "INSERT INTO abonnement VALUES (?, ?, ?, ?)";
		 String url = flux.getUrl().toString();
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, user.identifiant);
		 data.setString(2,url);
		 data.setLong(3, dateD);
		 data.setLong(4, longDateF);
		 data.executeUpdate();
		 data.close();
	}
	
	/**
	 * Abonnement � une cat�gorie
	 * @param nomCat = nom de la cat�gorie � s'abonner
	 */
	public void abonCat (String nomCat) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");  
		 String sql = "INSERT INTO aboncat VALUES (?, ?)";
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, this.identifiant);
		 data.setString(2,nomCat.toUpperCase());
		 data.executeUpdate();
		 data.close();
	}
	
	
	/**
	 * D�sabonnement d'un utilisateur
	 * @param flux = flux auquel user se desabonne
	 */
	public void desabonner(Flux flux) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", ""); 
		String url = flux.getUrl().toString();
		 String sql = "DELETE FROM abonnement WHERE identifiant = ? AND url = ?"; //supprimer un abonnement
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, this.identifiant);
		 data.setString(2,url);
		 data.executeUpdate();
		 data.close();
	}
	
	/**
	 * Ajout d'une cat�gorie � la base de donn�e
	 * @param nomCat = nom de la cat�gorie
	 */
	public void ajoutCat (String nomCat) throws SQLException {
		 boolean verifCat = false;
		 Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");  
		 String sql = "SELECT nomCat FROM categories";
		 PreparedStatement data = cn.prepareStatement(sql);
		 ResultSet resq = data.executeQuery();
		 resq = data.getResultSet();
	   	 ResultSetMetaData resMeta = data.getMetaData();
	     while(resq.next()){ 
	       for(int i = 1; i <= resMeta.getColumnCount(); i++) {
	    	   String cat = resq.getString(i);
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
	}
	
	/**
	 * Compter le nombre d'users abonn�s � une cat�gorie
	 * @param nomCat =  nom de la cat�gorie
	 * @return Le nombre d'abonn�s
	 */
	public int nbAbonCat (String nomCat) throws SQLException {
		int nbAbonCat = 0;
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
		String sql = "SELECT COUNT(identifiant) FROM aboncat WHERE nomcat = ?"; //compter le nombre d'abonn�s � une cat�gorie
	    PreparedStatement data = cn.prepareStatement(sql);
	    data.setString(1, nomCat.toUpperCase());
	    ResultSet resq = data.executeQuery();
		resq = data.getResultSet();
		ResultSetMetaData resMeta = data.getMetaData();
       while(resq.next()){ 
         for(int i = 1; i <= resMeta.getColumnCount(); i++)
         	nbAbonCat = resq.getInt(1);
       }
       return nbAbonCat;
	}
	
	/**
	 * Modifier le nombre d'users abonn�s
	 * @param nomCat =  nom de la cat�gorie � modifier
	 * @param nbAbonCat = nouveau nombre d'abonn�s
	 */
	public void ajoutAbonCat (String nomCat, int nbAbonCat) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
		String sql = "UPDATE categories SET nbabo = ? WHERE nomcat = ?";  //Modification de nombre d'abonn�s
	    PreparedStatement data = cn.prepareStatement(sql);
	    data.setInt(1,  nbAbonCat);
		data.setString(2, nomCat);
		data.executeUpdate();
		data.close();
	}
		
	/**
	 * Ajout d'un nombre de jours donn� � une date d'abonnement
	 * @param date = la date � prolonger 
	 * @param nbJours = nombre de jours � ajouter
	 * @return la nouvelle date.
	 */
	public Date ajouterJour(Date date, int nbJours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, nbJours);
		return cal.getTime();
	}
	
	/**
	 * Verifier si le nombre max d'abonn�s est atteint, sinon on peut s'abonner
	 * @return True si l'utilisateur peut s'abonner, false sinon
	 */
	public boolean verifContrainte() throws SQLException {
		
		int nbAbos = 0;
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");  
		String sql = "SELECT nbabos FROM contraintes WHERE identifiant = ?";
		PreparedStatement data = cn.prepareStatement(sql);
		data.setString(1, this.identifiant);
		ResultSet resq = data.executeQuery();
		resq = data.getResultSet();
		ResultSetMetaData meta = data.getMetaData();
		while(resq.next()){	//Verifier nb abonement maximum �tablie 
           for(int i = 1; i <= meta.getColumnCount(); i++) {
           	int verif = resq.getInt(i);
           	sql = "SELECT COUNT(url) FROM abonnement WHERE identifiant = ? GROUP BY identifiant";
       		data = cn.prepareStatement(sql);
       		data.setString(1, this.identifiant);
       		resq.close();
       		resq = data.executeQuery();
       		resq = data.getResultSet();
       		meta = data.getMetaData();
       		data.close();
       		while(resq.next()){//Compte nb d'abonnements
                   for(int j = 1; j <= meta.getColumnCount(); j++) {
                   	nbAbos = resq.getInt(j);
                   }
       		}
       		//Si le nombre d'abonnement d�passe la contrainte
           	if (nbAbos >= verif) {
           		return false;
           	}
           }
		}
		return true;
	}
	
}
