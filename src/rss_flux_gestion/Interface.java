package rss_flux_gestion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat; 


/**
 * Classe contentant toutes les m�thodes qui contiennent de l'affichage ou des lectures d'entr�es clavier
 * @author LeoHerbert
 */
public interface Interface {
	
	public static int jour = 28; //nombre jour d'abonnemenr par defaut
	
	/**
	 * Afichage du premier menu de l'application
	 * @return User choix action
	 */
	public default char bienvenu () {//choix des actions que l'user peut faire	
		//lecture se saisie clavier 
		Scanner sc = new Scanner(System.in);
		String scch = ""; //lit a clavier
        char ch = ' ';
		System.out.println("Bienvenue \n Veillez vous connecter ou vous inscrire ! (A (si admin) ou C(se connecter) ou I(s'inscrire )");
		scch = sc.nextLine();
	    ch = scch.charAt(0);//puis converit en char.
		while (ch != 'C' && ch != 'I' && ch != 'A') {//Tant que le caract�re entr� n'est pas A,C ou I
			System.out.println("Veillez vous connecter ou vous inscrire ! (A (si admin) ou C(se connecter) ou I(s'inscrire )");
			scch = sc.nextLine();
			ch = scch.charAt(0);
		}
		sc.close();
		return ch;
	}
	
	/**
	 * Afficher le menu utilisateur
	 * @return User connect� choix Action
	 */
	public default char choixUser() {//choix des actions que l'user peut faire		
		//lecture se saisie clavier  
		Scanner sc = new Scanner(System.in);
		String scch ="";
		char ch = ' ';
		System.out.println("Veuillez reesseyer: \n A: s'abonner � un nouveau flux\n B: s'abonner � une cat�gorie\n C: consulter les flux disponibles");
		System.out.println(" D: se d�sabonner d'un flux\n F: consulter vos cat�gories\n I: consulter vos informations(flux)\n Q: Quitter");
		scch = sc.nextLine();
		ch = scch.charAt(0);
		while (ch != 'A' && ch != 'D' && ch != 'C' && ch != 'I' && ch != 'B' && ch != 'F' && ch != 'Q') {
			System.out.println("Veuillez reesseyer: \nA: s'abonner � un nouveau flux\n B: s'abonner � une cat�gorie\n C: consulter les flux disponibles\n D: se d�sabonner d'un flux\n F: consulter vos cat�gories\n I: consulter vos informations(flux)\n Q: Quitter");
			scch = sc.nextLine();
			ch = scch.charAt(0);
		}
		sc.close();
		return ch;
	}
	
	/**
	 * Afficher le menu administrateur
	 * @return Admin choix Action 
	 */
	public default char choixAdmin() {//choix des actions que l'admin peut faire	
		Scanner sc = new Scanner(System.in);
		String scch; 
		char ch = ' ';
		System.out.println("Que voulez-vous faire ? \nA: Ajouter un flux \nS: supprimer un compte utilisateur ou un flux\n I: afficher les infos\n Q: Quitter");
		scch = sc.nextLine();
		ch = scch.charAt(0);
		while (ch != 'A' && ch != 'S' && ch != 'I' && ch != 'Q') {
			System.out.println("Veuillez entrer un caract�re correct\nA: Ajouter un flux \nS: supprimer un compte utilisateur ou un flux\n I: afficher les infos\n Q: Quitter");
			scch = sc.nextLine();
			ch = scch.charAt(0);
		}
		sc.close();
		return ch;
	}
	
	

	/**
	 * Afficher le sous-menu supprimer
	 * Option : flux ou utilisateur
	 * @return Admin choix info � supprimer
	 */
	public default char choixSupp() {//choix des actions que l'admin peut faire
		Scanner sc = new Scanner(System.in);
		String scch;
		char ch = ' '; 
		System.out.println("Que voulez-vous supprimer? U: un utilisateur, F: un flux");
		scch = sc.nextLine();
		ch = scch.charAt(0);
		while (ch != 'U' && ch != 'F') {
			System.out.println("Veuillez entrer un caract�re correct: U ou F");
			scch = sc.nextLine();
			ch = scch.charAt(0);
		}
		sc.close();
		return ch;
	}
	
	/**
	 * Afficher le sous-menu informtions
	 * Option : Liste : flux ou User
	 * @return Amdin choix info � afficher
	 */
	public default char choixInfos() {//choix des actions que l'admin peut faire
		Scanner sc = new Scanner(System.in);
		String scch;
		char ch = ' ';
		System.out.println("Que voulez vous afficher? U pour les utilisateur, F pour les flux");
		scch = sc.nextLine();
		ch = scch.charAt(0);
		while (ch != 'U' && ch != 'F') {
			System.out.println("Veuillez entrer un caract�re correct: U ou F");
			scch = sc.nextLine();
			ch = scch.charAt(0);
		}
		sc.close();
		return ch;
	}
	
	/**
	 * Inscription d'un nouvel utilisateur
	 * Verifie Identifiant/Mail.
	 */
	public default void inscription () throws SQLException {
		
		Scanner sc = new Scanner(System.in);
        boolean mailV = false;
        boolean idV = false; //Pour les v�rifications des mail et id (inscription)
        int j=0; //Compteur mail/id false
        String sccon = ""; //variable pour verifier si user desir se connecter
        char ucon = ' ';  
        String mdpConf="";
        User user = new User();//user � inscrire   
       //Inscription new user
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
        java.sql.Statement stt = cn.createStatement();      		
        System.out.println("Veuillez remplir les informations suivantes vous inscrire");
        System.out.println("Nom:");
		user.setNom(sc.nextLine());
		System.out.println("Pr�nom");
		user.setPrenom(sc.nextLine());
		System.out.println("mail");
		user.setMail(sc.nextLine());
        ResultSet resq; 
        ResultSetMetaData meta;
        
        while (mailV == false && j <= 50) {//Verifier si mail n'est pas d�j� existant
        	resq = stt.executeQuery("SELECT mail FROM users");
        	meta = resq.getMetaData();
        	j++;
	        while(resq.next()){ 
	            for(int i = 1; i <= meta.getColumnCount(); i++) {
	            	String res = resq.getObject(i).toString();
	            	if (res.equals(user.getMail()) == true) {
	            		mailV = false;
	            		System.out.println("Erreur: Adresse mail deja existant. Connectez-vous ou choir une adresse differente");
	            		System.out.print("Se connecter? (O/N)");
	            		sccon = sc.nextLine();
	            		ucon = sccon.charAt(0);
	            		if (ucon == 'O') {//Demander si 'lutilisateur veut se connecter
	            			this.connexion();
	            			sc.close();
	            			return; //Sortie inscription
	            		}
	            		user.setMail(sc.nextLine());
	            	}
	            }
	        }
        }
		System.out.println("Entrez votre nouvel identifiant");
		user.setIdentifiant(sc.nextLine());
        ResultSet resq2;
        j = 0;
        ResultSetMetaData meta2; 
        do { //Verifier si id n'est pas d�j� existant
        	resq2 = stt.executeQuery("SELECT identifiant FROM users");
        	meta2 = resq2.getMetaData();
        	j++;
	        while(resq2.next()){ 
	            for(int i = 1; i <= meta2.getColumnCount(); i++) {
	            	String res2 = resq2.getObject(i).toString();
	            	if (res2.equals(user.getIdentifiant()) == true) {
	            		idV = false;
	            		System.out.println("Erreur : Utilisateur d�j� existant. Connectez-vous ou choisir un identifiant different");
	            		System.out.print("Se connecter? (O/N)");
	            		sccon = sc.nextLine();
	            		ucon = sccon.charAt(0);
	            		if (ucon == 'O') {
	            			this.connexion();
	            			sc.close();
	            			return;//Sortie inscription
	            		}
	            		else {
	            			user.setIdentifiant(sc.nextLine());
	            		}
	            	}
	            }
	        }
        } while (idV == false && j <= 50);
        
		System.out.println("Entrez votre nouveau mot de passe");
		user.setMdp(sc.nextLine());
		while (user.getMdp().length() < 4) {//Caract�re minimum : 4
			System.out.println("Mot de passe trop court: minimum 4 caract�res, r�essayez");
			user.setMdp(sc.nextLine());
		}
		System.out.println("Confirmez votre mot de passe");//Confirmation du mot de passe
		mdpConf = sc.nextLine ();
		while (mdpConf.equals(user.getMdp()) == false) {//mdpconf doit �gal � mdp
			System.out.println ("Confirmation du mot de passe incorecte, r�essayez");
			mdpConf = sc.nextLine ();
		}
        user.inscription();//Inscription avec les donn�es collect�es
        //Fin inscription
        System.out.println("Inscrption r�ussi ! Bienvenue!\nSe connecter ?");
		this.connexion();
        
		sc.close();
        stt.close();
	}
	
	/**
	 * Afficher les informations demand�es � saisir
	 * Saisir Identifiant et Mot de passe
	 * @return L'utilisateur user connect�.
	 */
	public default User connexion () throws SQLException {
		
		Scanner sc = new Scanner(System.in);
        User user = new User();
        System.out.println("Connexion utilisateur..");//Connexion user
        System.out.println("Identifiant:");
        user.setIdentifiant(sc.nextLine());
        System.out.println("Mot de passe:");
        user.setMdp(sc.nextLine());
        
        boolean cn = user.connexion(); //Verification des information d'indentification
        while (cn == false) {
        	System.out.println("Identifiant ou mot de passe saisi (sont)est incorrecte(s), r�essayez");
        	System.out.println("Identifiant: ");
            user.setIdentifiant(sc.nextLine());
            System.out.println("Mot de passe: ");
            user.setMdp(sc.nextLine());
            cn = user.connexion();
        } 
        System.out.println("Connexion reussie, bienvenue " + user.getIdentifiant()+"!"); //Si identification correcte.
        sc.close();
        return user;
	}
	
	/**
	 * Afficher les informations demand�es � saisir
	 * Saisir Identifiant et Mot de passe
	 * @return L'administrateur admin connect�
	 */
	public default Admin connexionA () throws SQLException {
		
		Scanner sc = new Scanner(System.in);
        Admin admin = new Admin();	
        System.out.println("Connexion en tant qu'administrateur..:");//Connexion admin
        System.out.println("Identifiant: ");
        admin.setIdentifiant(sc.nextLine());
        System.out.println("Entrez le mot de passe administrateur");
        admin.setMdp(sc.nextLine());

        boolean con = admin.connexion();
        while (con == false) {//Verification des information d'indentification
        	System.out.println("Identifiant ou mot de passe saisi (sont)est incorrecte, r�essayez");
        	System.out.println("Identifiant administra");
            admin.setIdentifiant(sc.nextLine());
            System.out.println("Entrez le mot de passe administrateur");
            admin.setMdp(sc.nextLine());
            con = admin.connexion();
        }
        System.out.println("Connect� ent tant administrateur. Bienvenue!");//Si identification correcte.
        sc.close();
        return admin;
	}
		
	/**
	 * Afficher la liste des flux
	 * Liste de tout les flux
	 */
	public default void afficherFluxA () throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
		String sql = "SELECT * FROM flux";
	    PreparedStatement data = cn.prepareStatement(sql);
		ResultSet resq = data.executeQuery();
		resq = data.getResultSet();
		ResultSetMetaData meta = data.getMetaData();
        while(resq.next()){//Afficher la liste   
        	System.out.println("Liste des flux\n---------------");
          for(int i = 1; i <= meta.getColumnCount(); i++)
            System.out.print(meta.getColumnName(i).toUpperCase() + " : " + resq.getObject(i).toString() + "\n");
        }
	}
	
	/**
	 * Afficher la liste des utilisateurs
	 * Liste de tout les utilisateurs
	 */
	public default void afficherUsersA () throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
		String sql = "SELECT * FROM users";
	    PreparedStatement data = cn.prepareStatement(sql);
		ResultSet resq = data.executeQuery();
		resq = data.getResultSet();
		ResultSetMetaData meta = data.getMetaData();
		//Affichage de la liste des utilisateurs
        while(resq.next()){    
        	System.out.println("Liste des utilisateurs\n-------------------------");
          for(int i = 1; i <= meta.getColumnCount(); i++)
            System.out.print(meta.getColumnName(i).toUpperCase() + " : " + resq.getObject(i).toString() + "\n");
        }
	}
	
	/**
	 * Afficher les informations demand�es � saisir
	 * Saisir Url,Nom flux, Langue, Localisation
	 * @return Le flux auquel l'utilisateur veut s'abonner
	 */
	public default Flux abonnement () {
		Flux flux = new Flux ();
		String url = " ";
		Scanner sc = new Scanner(System.in);
		//Abonnement � un flux
		try {//Verification de l'url saisi
			System.out.println("Entrez l'url du flux auquel vous voulez vous abonner");
			url= sc.nextLine();
			URL url2 = new URL (url);
			flux.setUrl(url2);
		} catch (MalformedURLException e) {
			System.out.println("Url saisi incorrecte");
			sc.close();
			return null;
		}
		System.out.println("Entrez le nom du flux: Ex: La Une");
		flux.setNom(sc.nextLine());
		System.out.println("Langue du flux:");
		flux.setLangue(sc.nextLine());
		System.out.println("Localisation du flux:");
		flux.setLocalisation(sc.nextLine());
		//Une fois que l'abonnement termin�.
		System.out.println("Abonnement reussi!\n Durr�e de l'abonnement: " + jour +" jours");
		sc.close();
		return flux;
	}
	
	
	/**
	 * Afficher les informations demand�es � saisir
	 * Saisir NomCat�gorie
	 * @param user L'utilisateur qui veut s'abonner � une cat�gorie
	 */
	public default void abonnementC (User user) throws SQLException {
		boolean vCat = false;
		Scanner sc = new Scanner(System.in);
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");  
		//Abonnement � une cat�gorie
		System.out.println("Entrer le nom de la cat�gorie dont vous voulez vous abonner");
		String nomCat = sc.nextLine();
		String sql = "SELECT nomcat FROM abonnementcat WHERE identifiant = ?";
	    PreparedStatement data = cn.prepareStatement(sql);
		data.setString(1, user.getIdentifiant());
		ResultSet resq = data.executeQuery();
		resq = data.getResultSet();
		ResultSetMetaData meta = data.getMetaData();
		//On v�rifie que l'utilisateur n'est pas d�j� abonn�
		while(resq.next()){
            for(int i = 1; i <= meta.getColumnCount(); i++) {
            	String ver = resq.getString(i);
            	if (ver.toUpperCase().equals(nomCat.toUpperCase()) == true) {
            		vCat = true;
            	}
            }
		}
		if (vCat == false) {//Verifier si d�j� abonn�
			user.abonCat(nomCat);
			int nbAbosC =  user.nbAbonCat(nomCat);
			user.ajoutCat(nomCat);
			user.ajoutAbonCat(nomCat, nbAbosC);
			System.out.println("Abonnement � " + nomCat +" reussi!");
		}
		else {//Si d�j� abonn�
			System.out.println("Erreur: vous �tes d�j� abonn�(e) � "+nomCat);
		}	
		sc.close();
	}
	
	/**
	 * Afficher les informations de l'utilisateur
	 * @param user L'utilisateur concern�
	 */
	public default void afficherInfos (User user) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
		String sql = "SELECT * FROM users WHERE identifiant = ?";
	    PreparedStatement data = cn.prepareStatement(sql);
		data.setString(1, user.getIdentifiant());
		ResultSet resq = data.executeQuery();
		resq = data.getResultSet();
		ResultSetMetaData meta = data.getMetaData();
		//Affichage des informations
        while(resq.next()){         
          for(int i = 1; i <= meta.getColumnCount(); i++)
            System.out.print(meta.getColumnName(i).toUpperCase() + " : " + resq.getObject(i).toString() + "\n");
        }
	}
	
	
	/**
	 * Afficher les informations demand�es � saisir
	 * Saisir Url
	 * @param user L'utilisateur souhaitant de d�sabonner
	 * @return Le flux � se d�sabonner
	 */
	public default Flux desabonner (User user) throws SQLException {
		Flux flux = new Flux ();
		String url = " ";
		boolean tUrl = false;
		Scanner sc = new Scanner(System.in);
		//Se d�sabonner d'un flux
		try {//V�rification de l'url
			System.out.println("Saisir l'url du flux auquel se d�sabonner");
			url= sc.nextLine();
			URL url2 = new URL (url);
			flux.setUrl(url2);
		} catch (MalformedURLException e) {
			System.out.println("Erreur: url saisi incorrecte");
			sc.close();
			return null;
		}
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
        String sql = "SELECT url FROM abonnement WHERE identifiant = ?";
        PreparedStatement data = cn.prepareStatement(sql);
		data.setString(1, user.getIdentifiant());
		ResultSet res = data.executeQuery();
		res = data.getResultSet();
		ResultSetMetaData meta = data.getMetaData();
		while(res.next()){//Verification si abonn� � l'url(flux).
            for(int i = 1; i <= meta.getColumnCount(); i++) {
            	String verif = res.getObject(i).toString();
            	if (verif.equals(url) == true) {
            		tUrl = true;
            	}
            }
		}
		if (tUrl == true) {//Si abonn�
			((Abonnement) user).desabonner(flux);
    		System.out.println("D�sabonnement reussi");
		}
		else {//sinon
			System.out.println("Erreur: Vous n'�tes pas abonn� � ce flux");
		}
		sc.close();
		data.close();	
		return flux;
		
	}
	
	/**
	 * Afficher les informations demand�es � saisir
	 * Admin: Saisir Identifiant(user) 
	 * Supprimer si user existe.
	 * Supprimer les informations conernant l'user
	 */
	public default void supprimerUsersA () throws SQLException {
		User user = new User();
		Admin admin = new Admin();
		boolean tId = false;
		Scanner sc = new Scanner(System.in);
		//Supprimer un utilisateur
		System.out.println("Saisir l'identifiant de l'utilisateur � supprimer");
		user.setIdentifiant(sc.nextLine());
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
        String sql = "SELECT identifiant FROM users";
        PreparedStatement data = cn.prepareStatement(sql);
		ResultSet resq = data.executeQuery();
		resq = data.getResultSet();
		ResultSetMetaData meta = data.getMetaData();
		while(resq.next()){//V�rifier si user existe
            for(int i = 1; i <= meta.getColumnCount(); i++) {
            	String verif = resq.getObject(i).toString();
            	if (verif.equals(user.getIdentifiant()) == true) {
            		tId = true;
            	}
            }
		}
		if (tId == true) {//Si exite : supprimer tout les informations concernant l'utilisateur
			admin.suppUser(user); //Suppression de l'utilisateur dans la table users
			admin.suppContraintes(user);
			admin.suppAbonnements(user);
			admin.suppAbonnementsC(user);
    		System.out.println("Reussi: Utilisateur supprim�");
		}
		else {//Si n'existe pas
			System.out.println("Erreur: Cet utilisateur n'existe pas");
		}
		sc.close();
		data.close();	
	}
	
	/**
	 * Afficher les informations demand�es � saisir
	 * Admin: Saisir: Url, Nom,Langue,Localisation
	 * ajoute un nouveau Flux
	 */
	
	public default void AjouterfluxA() throws SQLException{
		Flux flux = new Flux();
		Scanner sc = new Scanner(System.in);
		String url="";
		Date date = new Date();
		//Saisir les infos
		try {//V�rification de l'url
			System.out.println("Saisir l'url du flux auquel se d�sabonner");
			url= sc.nextLine();
			URL url2 = new URL (url);
			flux.setUrl(url2);
		} catch (MalformedURLException e) {
			System.out.println("Erreur: url saisi incorrecte");
			sc.close();
		}
		System.out.println("Nom flux:");
		flux.setNom(sc.nextLine());
		System.out.println("Langue:");
		flux.setLangue(sc.nextLine());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		formatter.format(date);
		flux.setDateAjout(date);
		System.out.println("Localisation:");
		flux.setLocalisation(sc.nextLine());
		flux.setNbAbos(0);
		flux.ajoutFlux(flux);
		sc.close();
	}
	
	/**
	 * Afficher les informations demand�es � saisir
	 * Admin: Saisir: Url
	 * Supprimer Flux ainsi que les abonnement li� � ce flux
	 */
	
	public default void supprimerFluxA () throws SQLException {
		Flux flux = new Flux();
		Admin admin = new Admin();
		boolean tUrl = false;
		Scanner sc = new Scanner(System.in);
		//Supprimer un flux
		System.out.println("Saisir l'url du flux � supprimer");
		String url = sc.nextLine();
		URL url2 = null;
		try {
			url2 = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("Erreur: Url incorrecte");
		}
		flux.setUrl(url2);
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
		//Cr�ation d'un objet PreparedStatement
        String sql = "SELECT url FROM flux";
        PreparedStatement data = cn.prepareStatement(sql);
		ResultSet resq = data.executeQuery();
		resq = data.getResultSet();
		ResultSetMetaData meta = data.getMetaData();
		//On v�rifie que l'url du flux existe dans la base de donn�es
		while(resq.next()){
            for(int i = 1; i <= meta.getColumnCount(); i++) {
            	String verif = resq.getObject(i).toString();
            	if (verif.equals(flux.getUrl().toString()) == true) {
            		tUrl = true;
            	}
            }
		}
		if (tUrl == true) {//Supprimer si Url(flux) existe, ainsi que les abonnemenets associ�s.
			admin.suppFlux(flux);
			admin.suppAbonnementsF(flux);
    		System.out.println("Reussi: flux supprim�");
		}
		//S'il n'existe pas
		else {
			System.out.println("Erreur: Ce flux n'existe pas");
		}
		sc.close();
		data.close();
		
	}
}
