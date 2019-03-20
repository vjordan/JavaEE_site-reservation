package imt.mmpj.storage.dao.impl;

import imt.mmpj.storage.dao.ItemDAO;
import imt.mmpj.storage.model.Item;
import imt.mmpj.storage.model.Utilisateur;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Implementation of the ItemDAO.
 */
public class ItemDAOImpl implements ItemDAO {

    private EntityManager em;

    public ItemDAOImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * Get all items.
     *
     * @return all items.
     */
    @Override
    public List findAll() {
        return em.createQuery("from " + Item.class.getName()).getResultList();
    }

    /**
     * Create an item.
     *
     * @param item to create
     */
    @Override
    public void create(Item item) {
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
    }

    /**
     * Find an item with his code.
     *
     * @param code to find
     * @return the item with the right code
     */
    @Override
    public Item find(String code) {
        return em.find(Item.class, code);
    }

    /**
     * Delete an item.
     *
     * @param item to delete
     */
    @Override
    public void delete(Item item) {
        em.getTransaction().begin();
        em.remove(item);
        em.getTransaction().commit();

    }

    /**
     * Close the entity Manager
     */
    @Override
    public void closeEntityManager() {
        em.close();
    }

	@Override
	public void update(Item item) {
		em.getTransaction().begin();
		em.merge(item);
		em.getTransaction().commit();
		
	}

}
