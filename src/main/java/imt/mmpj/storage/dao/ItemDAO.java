package imt.mmpj.storage.dao;

import imt.mmpj.storage.model.Item;
import imt.mmpj.storage.model.Utilisateur;

import java.util.List;

/**
 * Manage items. CRD
 */
public interface ItemDAO {

    /**
     * Get all items.
     *
     * @return all items.
     */
    List findAll();

    /**
     * Create an item.
     *
     * @param item to create
     */
    void create(Item item);

    /**
     * Find an item with his code.
     *
     * @param code to find
     * @return the item with the right code
     */
    Item find(String code);

    /**
     * Delete an item.
     *
     * @param item to delete
     */
    void delete(Item item);

    /**
     * Close the entity Manager
     */
    void closeEntityManager();
    
    void update(Item item);

}
