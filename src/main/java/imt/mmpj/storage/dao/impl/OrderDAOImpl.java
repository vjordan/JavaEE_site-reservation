package imt.mmpj.storage.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import imt.mmpj.storage.dao.OrderDAO;
import imt.mmpj.storage.model.Ordered;
import imt.mmpj.storage.model.Utilisateur;

public class OrderDAOImpl implements OrderDAO{
	
	EntityManager em;
	
	public  OrderDAOImpl(EntityManager aEm) {
		em=aEm;
	}

	@Override
	public List<Ordered> findAllByUser(Utilisateur aUtilisateur) {
		return em.createQuery("FROM "+Ordered.class.getName()+" WHERE UTILISATEUR_ID ="+"\'"+aUtilisateur.getMail()+"\'").getResultList();
	}

	@Override
	public void create(Ordered aOrder) {
		em.getTransaction().begin();
		em.persist(aOrder);
		em.getTransaction().commit();		
	}

	@Override
	public void closeEntityManager() {
		em.close();
	}

}
