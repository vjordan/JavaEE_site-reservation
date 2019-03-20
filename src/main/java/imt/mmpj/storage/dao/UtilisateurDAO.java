package imt.mmpj.storage.dao;

import imt.mmpj.storage.model.Utilisateur;

public interface UtilisateurDAO {

	public void delete(Utilisateur aUtilisateur);
	
	public void update(Utilisateur aUtilisateur);
	
	void create(Utilisateur aUtilisateur);
	
	public Utilisateur find(String aMail);
	
	public void closeEntityManager();
	
}
