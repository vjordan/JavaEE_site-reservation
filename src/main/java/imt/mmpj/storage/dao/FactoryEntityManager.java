package imt.mmpj.storage.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe pour recuperer une factory de facon statique et unique
 * @author David
 *
 */
public class FactoryEntityManager {

	public final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("asso");
	
}
