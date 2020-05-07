package rss_flux_test;

import java.sql.SQLException;

import rss_flux_gestion.*;

/**
 * test des fonction  dans interface
 * @author LeoHerbert
 *
 */

public class testapplication {

	public static void main(String[] args) throws SQLException {
		
		char ch1, chus, chAd, chAc;
		Application app = new Application();
		
		/*System.out.println("lancement de l'application : Menu");
		ch1 = app.bienvenu();
		System.out.println("Votre choix : " + ch1 + "\n");
		System.out.println("Afficher menu pour utilisateur\n");
		chus = app.choixUser();
		System.out.println("\nVous avez saisi : " + chus);*/
		
		System.out.println("Connexion admin");
		app.connexionA();
		
		/*System.out.println("Afficher le menu pour admin\n");
		chAd = app.choixAdmin();
		System.out.println("\nVous avez saisi : " + chAd);*
		
		System.out.println("Afficher sous-menu de suppression\n");
		chAc = app.choixSupp();
		System.out.println("\nVous avez saisi : " + chAc);
		
		System.out.println("Afficher sous-menu des informations\n");
		chAc = app.choixInfos();
		System.out.println("\nVous avez saisi : " + chAc);
		
		System.out.println("Inscription nouvel utilisateur\n");
		app.inscription();
		
		System.out.println("Connexion utilisateur\n");
		app.connexion();
		
		System.out.println("Abonnement d'un utilisateur à un flux\n");
		app.abonnement();
		
		System.out.println("Afficher une liste des flux\n");
		app.afficherFluxA();
		
		System.out.println("Afficher une liste des abonnés \n");
		app.afficherUsersA();*/
		
		User user = new User();
		user.setIdentifiant("lino123");
		System.out.println("Abonnement d'un utilisateur à une catégorie");
		app.abonnementC(user);
		
		System.out.println("Afficher les informations sur l'utilisateur");
		app.afficherInfos(user);
		
		System.out.println("Désabonner à un flux");
		app.desabonner(user);
		
		System.out.println("Supprimer un utilisateur");
		app.supprimerUsersA();
		
		System.out.println("Ajouter un flux\n");
		app.AjouterfluxA();
		
		/*System.out.println("Supprimer un flux");
		app.supprimerFluxA();*/		
	}
}
