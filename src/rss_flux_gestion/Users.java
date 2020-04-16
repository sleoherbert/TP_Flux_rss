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
	 * Récupération des données de l'utilisateur
	 * @return Information global sur l'utilisateur
	 */
	public String toString () {
		return new String ("nom : " + getNom() + "\nprenom : " + getPrenom() + "\nmail : " + getMail() + "\nidentifiant : " + getIdentifiant() + "\nmot de passe : " + getMdp());
	}
	
	/**
	 * Récupération du nom
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
	 * Récupération du prénom
	 * @return Le prénom
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Modification du prénom
	 * @param prenom Le nouveau prénom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * Récupération du mail
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
	 * Récupération de l'identifiant
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
	 * Récupération du mot de passe
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
