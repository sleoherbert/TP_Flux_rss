package rss_flux_test;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import rss_flux_gestion.*;

/**
 * test class admin
 * @author LeoHerbert
 *
 */

public class testadmin {
	
	public static void main(String[] args) throws SQLException {
		Admin admin = new Admin();
		admin.setIdentifiant("admin");
		admin.setMdp("admin");
		
		Admin ad = new Admin();
		ad.setIdentifiant("admin");
		ad.setMdp("banana");
		
		System.out.println("Identifiant/Mdp Correcte : " + admin.connexion() +"\n"+"Identifiant/Mdp Incorrecte : " + ad.connexion());
		
		User user = new User();
		user.setIdentifiant("testleo");
			
		System.out.println("Supprimer un utilisateur");
		admin.suppUser(user);
		
		System.out.println("Supprimer tous les abonnements de l'utilisateur");
		admin.suppAbonnements(user);
		
		System.out.println("Ajouter une contrainte de 10 flux max à testleo");
		admin.contraintes(user, 10);
		
		System.out.println("Supprimer des contraintes pour l'utilisateur");
		admin.suppContraintes(user);
		
		Flux flux = new Flux();
		String url = "https://www.lemonde.fr/sante/rss_full.xml";
		URL url2 = null;
		try {
			url2 = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		flux.setUrl(url2);
		
		System.out.println("Supprimer des abonnements à ce flux");
		admin.suppAbonnementsF(flux);
		
		System.out.println("Supprimer d'un flux");
		admin.suppFlux(flux);
		
		System.out.println("Suppression des abonnements d'un utilisateur à une catégorie");
		admin.suppAbonnementsC(user);	
		
	}
}
