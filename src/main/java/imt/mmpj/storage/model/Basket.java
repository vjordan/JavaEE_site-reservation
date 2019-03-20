package imt.mmpj.storage.model;

import java.util.HashSet;
import java.util.Set;

public class Basket {

	Set<Item> listePanier= new HashSet<Item>();
	//int taillePanier;

	public Set<Item> getListePanier() {
		return listePanier;
	}

	public void setListePanier(HashSet<Item> listePanier) {
		this.listePanier = listePanier;
	}
	
	public void addItem(Item item) {
		this.listePanier.add(item);
	}
	
	public void removeItem(Item item) {
		this.listePanier.remove(item);
	}
	
	public int getTaille()
	{
		return listePanier.size();
	}
}
