package rss_flux_gestion;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Users {
	protected String nom;
	protected String prenom;
	protected String mail;
	protected String identifiant;
	protected String mdp;
	

	public Users () {
		setNom("");
		setPrenom("");
		setMail("");
		setIdentifiant("");
		setMdp("");
	}
	
	/**
	 * R�cup�ration des donn�es de l'utilisateur
	 * @return Information global sur l'utilisateur
	 */
	public String toString () {
		return new String ("nom : " + getNom() + "\nprenom : " + getPrenom() + "\nmail : " + getMail() + "\nidentifiant : " + getIdentifiant() + "\nmot de passe : " + getMdp());
	}
	
	/**
	 * R�cup�ration du nom
	 * @return nom user
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Modification du nom
	 * @param add/set a new nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * R�cup�ration du pr�nom
	 * @return Le pr�nom
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Modification du pr�nom
	 * @param prenom Le nouveau pr�nom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * R�cup�ration du mail
	 * @return Le mail
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * Modification du mail
	 * @param mail Le nouveau mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * R�cup�ration de l'identifiant
	 * @return L'identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}
	
	/**
	 * Modification de l'identifiant
	 * @param identifiant Le nouvel identifiant
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	/**
	 * R�cup�ration du mot de passe
	 * @return Le mot de passe
	 */
	public String getMdp() {
		return mdp;
	}
	
	/**
	 * Modification du mot de passe
	 * @param  Add/set mdp
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public void inscription() {}
	
	
	public void abonnement() {}
	
	
	
	
}
