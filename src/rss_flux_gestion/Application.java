package rss_flux_gestion;

import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndLinkImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Application implements Interface {

	/**
	 * Class Application: class pricipal du programe
	 * Implément les fonction de l'interface.
	 *@author LeoHerbert
	 */
	    public static void main(String[] args) throws Exception {
	    	//lecture de saisi clavier
	    	Scanner sc = new Scanner(System.in);        
	        char cnUserIns = ' ';//Variable pour les choix de type de connexion (user/admin)
	        char choix = ' ';//Variable pour les choix des actions user/admin
	        boolean vUrl = true;
	        sc.close();
	        
	        User user = new User();
	        Admin admin = new Admin();
	        Flux flux = new Flux();
	        Application app = new Application();
	        
	        //Vérification des dates d'expiration des abonnements afin de les supprimer
	        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
	        String sql = "SELECT datefin FROM abonnement";
	        PreparedStatement data = cn.prepareStatement(sql);
			ResultSet resq = data.executeQuery();
			resq = data.getResultSet();
			ResultSetMetaData meta = data.getMetaData();
			Date aujourdhui = new Date();
			long lAujourdhui = aujourdhui.getTime();
	        sql = "DELETE FROM abonnement WHERE datefin <= ?";
	        data = cn.prepareStatement(sql);
	        data.setLong(1, lAujourdhui);
	        data.executeUpdate(); 
	        cnUserIns = app.bienvenu();
	        			
			if (cnUserIns == 'I') {//Nouvelle inscription
				app.inscription();
			}
			//Coté utilisateur
			if (cnUserIns == 'C') {//se connecter
				user = app.connexion();
				do { //Tant que l'utilisateur ne choisit pas de quitter
					choix = app.choixUser();
					if (choix == 'A') {//s'abonner 
						if ( user.verifContrainte() == true) {//Verifier si la contrainte n'est pas dépassé
							do { //Tant que le flux est null
								flux = app.abonnement ();
								//Verification de l'absence de l'url dans la BDD
								String sql2 = "SELECT url FROM flux";
								PreparedStatement data2 = cn.prepareStatement(sql2);
								ResultSet resq2= data2.executeQuery();
								resq2 = data2.getResultSet();
						        ResultSetMetaData meta2;
						        meta2 = resq2.getMetaData();
							    while(resq2.next()){ 
							    	for(int j = 1; j <= meta2.getColumnCount(); j++) {
							            String res2 = resq2.getObject(j).toString();
							            String sUrl = flux.getUrl().toString();
							            if (res2.equals(sUrl) == true) {
							            	vUrl = false;
							            }
							    	}
							    }
							    if (vUrl == true) {//Ajouter l'url en cas d'absance
							    	flux.ajoutFlux(flux);
							    }
							    vUrl = true;
							    sql = "SELECT url FROM abonnement WHERE identifiant = ?";
							    data = cn.prepareStatement(sql);
								data.setString(1, user.getIdentifiant());
								resq = data.executeQuery();
								resq = data.getResultSet();
								meta = data.getMetaData();
								while(resq.next()){//Verifier si user est abonn" au flux
						            for(int j = 1; j <= meta.getColumnCount(); j++) {
						            	String verif = resq.getObject(j).toString();
						            	String sUrl = flux.getUrl().toString();
						            	if (verif.equals(sUrl) == true) {
						            		vUrl = false;
						            	}
						            }
								}
								if (vUrl == true) {//Sinon , ajouter
									user.abonnement(user, flux, Interface.jour);
								}
								int abos = flux.nbAbos();
								flux.ajoutAbos(abos);
							}while (flux == null);
						}
						else {//Si contrainte depassé
							System.out.println("Nombre de flux max atteint: Impossible de vous abonné.\nVeillez vous désabonner (au)x quels vous êtes abonné");
						}
					}
					
					if (choix == 'B') {//Abonnement à une catégorie
						app.abonnementC(user);
					}
					
					if (choix == 'C') {//Consultation des flux
						sql = "SELECT url FROM abonnement WHERE identifiant = ?";
					    data = cn.prepareStatement(sql);
						data.setString(1, user.getIdentifiant());
						resq = data.executeQuery();
						resq = data.getResultSet();
						meta = data.getMetaData();
						while(resq.next()){//Afficher les flux
				            for(int j = 1; j <= meta.getColumnCount(); j++) {
				            	String url = resq.getObject(j).toString();
				            	URL feedUrl = new URL(url);
								SyndFeedInput input = new SyndFeedInput();
								SyndFeed feed = null;
								try {//Verification internet
									feed = input.build(new XmlReader(feedUrl));
									System.out.println("Titre: " + feed.getTitle());
								    for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
								    	System.out.println("Titre: " + entry.getTitle());
								        System.out.println("Description: " + entry.getDescription().getValue());							         
								        for (SyndCategoryImpl category : (List<SyndCategoryImpl>) entry.getCategories()) {
								            System.out.println("Catégorie: " + category.getName());
								        }
								        System.out.println("\n------------------------------------------------------\n");
								    }
								    //Fin diffusion de flux
									System.out.println("Fin de diffusion des flux!");
								}
								catch (UnknownHostException e) {
									System.out.println("Problème connexion internet. Veillez réessayez");
								}
				            }
						}
					}
					
					if (choix == 'D') {//Se désabonner
						do {//tant que flux == null ; flux inexistant;
							flux = app.desabonner(user);
						}while (flux == null);
					}
					
					if (choix == 'F') {//Consultation des flux
						sql = "SELECT url FROM flux"; //recuperer tout les flux
					    data = cn.prepareStatement(sql);
						resq = data.executeQuery();
						resq = data.getResultSet();
						meta = data.getMetaData();
						while(resq.next()){
				            for(int j = 1; j <= meta.getColumnCount(); j++) {
				            	String url = resq.getObject(j).toString();
				            	URL feedUrl = new URL(url);
								SyndFeedInput input = new SyndFeedInput();
								SyndFeed feed = null;
								sql = "SELECT nomcat FROM abonnementcat WHERE identifiant = ?"; //Recuperer catégorie auxquelles user est abonné
								data = cn.prepareStatement(sql);
								data.setString(1, user.getIdentifiant());
								resq.close();
								resq = data.executeQuery();
								resq = data.getResultSet();
								meta = data.getMetaData();
								while(resq.next()){
						            for(int i3 = 1; i3 <= meta.getColumnCount(); i3++) {
						            	String cat = resq.getObject(j).toString();
										try {//Verification internet
											feed = input.build(new XmlReader(feedUrl));//afficher les flux des catégories auquelles user est abonné
											 for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
												 for (SyndCategoryImpl category : (List<SyndCategoryImpl>) entry.getCategories()) {
													 if (cat.toUpperCase().equals(category.getName().toUpperCase())) {
														 System.out.println("Titre: " + entry.getTitle());
														 System.out.println("Description: " + entry.getDescription().getValue());
														 System.out.println("\n------------------------------------------------------\n");
													 }
										        }
											}
											System.out.println("Fin de diffusion des flux!"); //Fin de diffusion
										}
										catch (UnknownHostException e) {
										System.out.println("Problème connexion internet. Veillez réessayez");
										}
						            }
								}
				            }
						}
					}
					
					if (choix == 'I') {//Use infos
						app.afficherInfos(user);
					}
					
					if (choix == 'Q') {//Quiter
						return;
					}
					
				}while (choix != 'Q');
			} //Fin du cas : Utilisateur connecter
			
			//Coté admin
			if (cnUserIns == 'A') {
				admin = app.connexionA();
				do { //Tant que l'administrateur ne choisit pas de quitter l'application
					choix = app.choixAdmin();
					if (choix == 'A') {//Ajouter un flux
						app.AjouterfluxA();
					}
					if (choix == 'S') {//Options supprimer
						choix = app.choixSupp();
						if (choix == 'U') {//supprimer user
							app.supprimerUsersA();
						}
						if (choix == 'F') {//supprimer un flux
							app.supprimerFluxA();
						}
					}
				
					if (choix == 'I') {//Consultation d'informations
						choix = app.choixInfos();
						if(choix == 'U') {//Info user
							app.afficherUsersA();
						}
						if(choix == 'F') {//Info flux
							app.afficherFluxA();
						}
					}
					if (choix == 'Q') {//Quiter
						return;
					}
				}while (choix != 'Q');
			} //Fin de la connexion administrateur
	    }
}