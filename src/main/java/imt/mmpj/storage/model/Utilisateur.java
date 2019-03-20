package imt.mmpj.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="UTILISATEUR" , schema="PUBLIC")
public class Utilisateur {

	@Id
	@Column (name="MAIL")
	private String mail;
	
	@Column (name="MDP")
	private String motDePasse;

	@Column (name="NOM")
	private String nom;
	
	@Column (name="PRENOM")
	private String prenom;
	
	@Column (name="CODEPOSTAL")
	private int codePostal;
	
	@Column (name="VILLE")
	private String ville;
	
	@Column (name="PAYS")
	private String pays;
	
	@Column (name="ADRESSE")
	private String adresse;
	
	@Column (name="ISADMIN")
	private boolean isAdmin;


	public Utilisateur()
	{
	}
	
	public Utilisateur(String mail, String mdp, String firstName, String lastName, int postalCode, String city, String country,String adress) {
		this.mail = mail;
		this.motDePasse = mdp;
		this.nom = firstName;
		this.prenom = lastName;
		this.codePostal = postalCode;
		this.ville = city;
		this.pays = country;
		this.adresse = adress;
	}
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}	
	
	public boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
