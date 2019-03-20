package imt.mmpj.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="ORDERED" , schema ="PUBLIC")
public class Ordered {
	
	public Ordered()
	{
		
	}
	
	public Ordered (String aNumeroCommande,Item aItem,Utilisateur aUtilisateur,int aQte)
	{
		numeroCommande = aNumeroCommande;
		utilisateur = aUtilisateur;
		item = aItem;
		quantite = aQte;
	}

	@Id
	@Column (name="ID_ORDER")
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int idOrder;
	
	@Column (name="NUMERO_COMMANDE")
	private String numeroCommande;
	
	@ManyToOne
	@JoinColumn (name="ITEM_ID")
	private Item item;
	
	@ManyToOne
	@JoinColumn (name="UTILISATEUR_ID")
	private Utilisateur utilisateur;
	
	@Column (name="QUANTITE")
	private int quantite;

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getNumeroCommande() {
		return numeroCommande;
	}

	public void setNumeroCommande(String numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public double getTotal()
	{
		return ((double)quantite)*item.getCost();
	}
}
