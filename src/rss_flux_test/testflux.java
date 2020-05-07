package rss_flux_test;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;

import rss_flux_gestion.*;

/**
 * test class flux
 * @author LeoHerbert
 *
 */

public class testflux {
	public static void main(String[] args) throws SQLException {
		Date dateAjout = new Date();
		String nomF = "rss", langue = "français", localisation = "Nlle Caléfonie";
        URL url = null;
        try {
			url = new URL("http://example.com/categorie/sujet/index.htm");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Flux flux = new Flux();
		System.out.println("Flux pre-set \n" + flux);
		Flux f = new Flux(url, nomF, langue, dateAjout, localisation, 20);
		System.out.println("Avec donnée en paramètre \n" + f);
		System.out.println("Ajouter le flux");
		//f.ajoutFlux(f);
		int nbAbos = f.nbAbos();
		
		System.out.println("Il y a " + nbAbos + " personnes abonnées à ce flux");
		System.out.println("Ajouter un aux d'abonnés du flux");
		f.ajoutAbos(nbAbos);
	}
}
