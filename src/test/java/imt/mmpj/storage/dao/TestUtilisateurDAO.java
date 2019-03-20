package imt.mmpj.storage.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.MessageDigest;
import java.util.List;

import javax.persistence.Persistence;
import javax.xml.bind.DatatypeConverter;

import org.junit.BeforeClass;
import org.junit.Test;

import imt.mmpj.storage.dao.impl.UtilisateurDAOImpl;
import imt.mmpj.storage.model.Utilisateur;

public class TestUtilisateurDAO {
	
	private static UtilisateurDAO vUtilisateurDAO;
	
	@BeforeClass
	public static void setUpDAO()
	{
	   vUtilisateurDAO = new UtilisateurDAOImpl(Persistence.createEntityManagerFactory("assoTest").createEntityManager());
	}
	
	/**
	 * Test de la DAO d'utilisateur <br/>
	 * On test le CRUD
	 */
	@Test
	public void testInscription()
	{
		String password="password";
		String myHash=null;
		MessageDigest md;
		try
		{
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
    	    byte[] digest = md.digest();
    	    myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		}
		catch(Exception e)
		{
			
		}
		
		Utilisateur vUtilisateur = new Utilisateur("test@mail.fr", myHash, "Martin", "David", 49120, "Reze", "FRANCE", "141 Rue du genetais");
		
		//Test du create
		try
		{
			vUtilisateurDAO.create(vUtilisateur);
			assertEquals(vUtilisateur.getMail(), vUtilisateurDAO.find("test@mail.fr").getMail());
		}
		catch (Exception e) {
			fail();
		}
		
		//Test de update
		try
		{
			vUtilisateur = vUtilisateurDAO.find("test@mail.fr");
			vUtilisateur.setCodePostal(4400);
			vUtilisateurDAO.update(vUtilisateur);
			assertEquals(vUtilisateur.getCodePostal(), vUtilisateurDAO.find("test@mail.fr").getCodePostal());
		}
		catch (Exception e) {
			fail();
		}
		
		//Suppression en base de l'objet
		vUtilisateurDAO.delete(vUtilisateurDAO.find("test@mail.fr"));
		assertTrue(vUtilisateurDAO.find("test@mail.fr")==null);
	}

}
