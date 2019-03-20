package imt.mmpj.storage.dao;

import java.util.List;

import imt.mmpj.storage.model.Ordered;
import imt.mmpj.storage.model.Utilisateur;

public interface OrderDAO {

	public List<Ordered> findAllByUser(Utilisateur aUtilisateur);
	
	public void create(Ordered aOrder);
	
	public void closeEntityManager();
	
}
