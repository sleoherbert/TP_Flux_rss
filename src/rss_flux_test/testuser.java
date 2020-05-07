package rss_flux_test;

import java.sql.SQLException;
import java.util.Date;
import rss_flux_gestion.*;

/**
 * test class user
 * @author LeoHerbert
 *
 */

public class testuser {

	public static void main(String[] args) throws SQLException {
		User user = new User();
		user.setNom("mark");
		user.setPrenom("kel");
		user.setMail("kel.mark@gmail.com");
		user.setIdentifiant("mark123");
		user.setMdp("mark123");
		System.out.println("User : \n" + user);
		//System.out.println("Vous ête inscrit");
		//user.inscription();
			
		System.out.println("Test Connexion avec identifiant correcte");
		boolean connexion = user.connexion();
		System.out.println("Connexion = " + connexion);
		/*System.out.println("Connexion avec identifiant incorrecte");
		Users user2 = new Users();
		user2.setIdentifiant("banane");
		user2.setMdp("coco");
		connexion = user2.connexion();
		System.out.println("Connexion = " + connexion);*/
		
		/*Flux f = new Flux();
		f.setUrl(new URL("https://www.lemonde.fr/football/rss_full.xml"));
		System.out.println("Abonnement de user(mark) au flux f");
		//user.abonnement(user, f, 5);
		 */
		
		/*System.out.println("Désabonnement de u au flux f");
		user.desabonner(f);*/
			
		System.out.println("Abonnement de user à la catégorie \"Sport\"");
		user.abonCat("sport");
			
		int nbAbonC = user.nbAbonCat("sport");
		System.out.println("Il y a " + nbAbonC + " personnes abonnées à Sport");
		
		System.out.println("Ajouter une catégorie astronomie dans la BDD");
		user.ajoutCat("astronomie");
			
		System.out.println("Ajouter cette personne au nombre d'abonnés de la catégorie astronomie");
		user.ajoutAbonCat("astronomie", nbAbonC);
		
		Date date = new Date();
		System.out.println("Ajouter 5 jours à la date " + date);
		Date datemodif = user.ajouterJour(date, 5);
		System.out.println("Date modifié : " + datemodif);
		
		System.out.println("Vérification de la contrainte en étant abonné à un flux");
		boolean verifContrainte = user.verifContrainte();
		System.out.println("VerifContrainte = " + verifContrainte);
			
		
	}

}