package imt.mmpj.storage.dao.impl;

import javax.persistence.EntityManager;

import imt.mmpj.storage.dao.UtilisateurDAO;
import imt.mmpj.storage.model.Utilisateur;

public class UtilisateurDAOImpl implements UtilisateurDAO {

    EntityManager em;

    public UtilisateurDAOImpl(EntityManager aEm) {
        em = aEm;
    }

    @Override
    public void delete(Utilisateur aUtilisateur) {
        em.getTransaction().begin();
        em.remove(find(aUtilisateur.getMail()));
        em.getTransaction().commit();
    }

    @Override
    public void update(Utilisateur aUtilisateur) {
        em.getTransaction().begin();
        em.merge(aUtilisateur);
        em.getTransaction().commit();
    }

    @Override
    public void create(Utilisateur aUtilisateur) {
        em.getTransaction().begin();
        em.persist(aUtilisateur);
        em.getTransaction().commit();
    }

    @Override
    public Utilisateur find(String aMail) {
        Utilisateur vRet = em.find(Utilisateur.class, aMail);
        return vRet;
    }

    @Override
    public void closeEntityManager() {
        em.close();
    }

}
