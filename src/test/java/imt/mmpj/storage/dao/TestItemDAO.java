package imt.mmpj.storage.dao;

import imt.mmpj.storage.dao.impl.ItemDAOImpl;
import imt.mmpj.storage.dao.impl.UtilisateurDAOImpl;
import imt.mmpj.storage.model.Item;
import imt.mmpj.storage.model.Utilisateur;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.Persistence;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

import static org.junit.Assert.*;

/**
 * ItemDAO tests.
 */
public class TestItemDAO {

    private static ItemDAO itemDAO;

    /**
     * Create itemDAO if doesn't exist.
     */
    @BeforeClass
    public static void setUpDAO() {
        if (itemDAO == null) {
            itemDAO = new ItemDAOImpl(Persistence.createEntityManagerFactory("assoTest").createEntityManager());
        }
    }

    /**
     * Test if we can add item to catalog.
     */
    @Test
    public void testAddToCatalog() {
        Item item = new Item("code", "name", 1, 1, "");

        itemDAO.create(item);

        assertTrue(itemDAO.find("code") != null);
        assertTrue(itemDAO.findAll().size() == 1);

        itemDAO.delete(item);
    }

    /**
     * Test if we can delete item.
     */
    @Test
    public void testDeleteItem() {
        Item item = new Item("code", "name", 1, 1, "");

        itemDAO.create(item);

        assertTrue(itemDAO.find("code") != null);
        assertTrue(itemDAO.findAll().size() == 1);

        itemDAO.delete(item);

        assertTrue(itemDAO.find("code") == null);
        assertTrue(itemDAO.findAll().size() == 0);
    }

}
