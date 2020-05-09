package rss_flux_gestion;

import java.sql.SQLException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;


/**
 * Classe contenant toutes les méthodes concernant les flux ainsi que leurs informations
 * @author LeoHerbert
 *
 */
public class Flux {

	private URL url;
	private String nom;
	private String langue;
	private String localisation;
	private Date dateAjout;
	private int nbAbos;
	
	public Flux () {
		this.nom = ("");
		this.langue = ("");
		this.dateAjout = new Date();
		this.localisation =("");
		this.nbAbos = 0;
		try {
			this.url = (new URL("http://example.com/categorie/sujet/index.html"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public Flux (URL newUrl, String newNom, String newLangue, Date newDateAjout, String newLocalisation, int newNbAbos) {
		this.url = newUrl;
		this.nom = newNom;
		this.langue = newLangue;
		this.dateAjout = newDateAjout;
		this.localisation =newLocalisation;
		this.nbAbos = newNbAbos;
	}
	/**
	 * Récupérer l'url
	 * @return url
	 */
	public URL getUrl() {
		return url;
	}
	
	/**
	 * Ajouter/Modifier url
	 * @param url  Nouvel url
	 */
	public void setUrl(URL url) {
		this.url = url;
	}
	
	/**
	 * Récupérer le nom
	 * @return  nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Ajouter/Modifier le nom
	 * @param nom Le nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Récupérer la langue
	 * @return  langue
	 */
	public String getLangue() {
		return langue;
	}

	/**
	 * Ajouter/Modifier la langue
	 * @param langue La langue
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}
	
	/**
	 * Récupérer la localisation
	 * @return La localisation
	 */
	public String getLocalisation() {
		return localisation;
	}
	
	/**
	 * Ajouter/Modifier la localisation
	 * @param localisation Nouvelle localisation
	 */
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	
	
	/**
	 * Recuperer la date d'ajout
	 * @return dateajout
	 */
	public Date getDataAjout() {
		return dateAjout;
	}
	
	/**
	 * Ajouter/Modifier data
	 * @param dateAjout Date d'ajout du flux
	 */
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	
	
	/**
	 * Récupérer le nombre d'abonnés
	 * @return nombre d'abonnés
	 */
	public int getNbAbos() {
		return nbAbos;
	}
	
	/**
	 * Modification du nombre d'abonnés
	 * @param nbAbos Le nouveau nombre d'abonnés
	 */
	public void setNbAbos(int nbAbos) {
		this.nbAbos = nbAbos;
	}

	/**
	 * Récupération des informations sur le flux
	 * @return  informations du flux (toString)
	 */
	public String toString () {
		return new String ("url : " + getUrl() + "\nnom : " + getNom() + "\nlangue : " + getLangue() + "\nlocalisation : " + getLocalisation() + "\ndateAjout : " + dateAjout + "\nnbAbos : " + getNbAbos());
	}
	
	/**
	 * Ajouter un nouveau flux
	 * @param flux flux à ajouter
	 */
	public void ajoutFlux (Flux flux) throws SQLException {
		 Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");  
		 String url = flux.getUrl().toString();
		 long longDate=flux.dateAjout.getTime();
		 //On insère les données dans la base de données
		 String sql = "INSERT INTO flux VALUES (?, ?, ?, ?, ?, ?)";
		 PreparedStatement data = cn.prepareStatement(sql);
		 data.setString(1, url);
		 data.setString(2, flux.getNom());
		 data.setString(3, flux.getLangue());
		 data.setLong(4, longDate);
		 data.setString(5, flux.getLocalisation());
		 data.setInt(6,  0);
		 
		 data.executeUpdate();
		 data.close();
	}
	
	/**
	 * Recuperer le nombre d'abonnés d'un flux
	 * @return Le nombre d'abonnés
	 */
	public int nbAbos () throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
		String url = this.getUrl().toString();
		String sql = "SELECT COUNT(identifiant) FROM abonnement WHERE url = ?";
	    PreparedStatement data = cn.prepareStatement(sql);
	    data.setString(1, url);
	    ResultSet resq = data.executeQuery();
		resq = data.getResultSet();
		ResultSetMetaData meta = data.getMetaData();
        while(resq.next()){ 
          for(int i = 1; i <= meta.getColumnCount(); i++)
          	this.nbAbos = resq.getInt(1);
        }
        return this.nbAbos;
	}
	
	/**
	 *Ajouter/Modifier du nombre d'abonnés au flux dans la base de données
	 * @param abon Le nouveau nombre d'abonnés
	 */
	public void ajoutAbos (int abon) throws SQLException {
		Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/flux", "root", "");
		String url = this.getUrl().toString();
		String sql = "UPDATE flux SET nbabo = ? WHERE url = ?";
	    PreparedStatement data = cn.prepareStatement(sql);
	    data.setInt(1,  abon);
		data.setString(2, url);
		data.executeUpdate();
		data.close();
	}	
}
