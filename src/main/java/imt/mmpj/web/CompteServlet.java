package imt.mmpj.web;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import imt.mmpj.storage.dao.FactoryEntityManager;
import imt.mmpj.storage.dao.UtilisateurDAO;
import imt.mmpj.storage.dao.impl.UtilisateurDAOImpl;
import imt.mmpj.storage.model.Utilisateur;

@WebServlet("/compte")
public class CompteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Passe l'utilisateur de variable de session a attribut de la requete pour être traité par la jsp
		req.setAttribute("utilisateur", req.getSession().getAttribute("utilisateur"));
		req.getRequestDispatcher("WEB-INF/pages/compte.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			process(req, resp);
		} catch (Exception e) {
			//Gestion des exceptions
			req.setAttribute("exception", e.getMessage());
			req.getRequestDispatcher("/WEB-INF/pages/compte.jsp").forward(req, resp);
		}
		
	}
	
	//Traite le formulaire de modifications des informations personnelles
	public void process(HttpServletRequest req,HttpServletResponse resp) throws Exception
	{
		if(req.getParameter("cp").isEmpty())
		{
			throw new Exception("Le champ code postal est vide");
		}
		
		//Recupere les champs du formulaire
		Utilisateur vUtilisateur = (Utilisateur) req.getSession().getAttribute("utilisateur");

		String vPassword = req.getParameter("password");
		String vPasswordConfirm = req.getParameter("passwordConfirm");
		String vAdresse = req.getParameter("adresse");
		Integer vCp = Integer.valueOf(req.getParameter("cp"));
		String vVille = req.getParameter("ville");
		String vPays = req.getParameter("pays");
		
		String hashPassword;
		String hashPasswordConfirm;
		
		//Hashage du mdp
		try
		{
			//Permet de hasher le mot de passe
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(vPassword.getBytes());
			byte[] digest = md.digest();
			hashPassword = DatatypeConverter.printHexBinary(digest).toUpperCase();
			md.update(vPasswordConfirm.getBytes());
			digest = md.digest();
			hashPasswordConfirm = DatatypeConverter.printHexBinary(digest).toUpperCase();  
		}
		catch(Exception e)
		{
			throw new Exception("Une erreur s est produite lors de l authentification, contactez un administrateur si l erreur se reproduit.");
		}
		
		//Vérifie queles champs ne soit pas vide et que les mot de passe sont egaux
		if(vPassword.isEmpty() || !hashPassword.equals(hashPasswordConfirm))
		{
			throw new Exception("Le mot de passe de confirmation ne correspond pas au mot de passe");
		}
		if(vAdresse.isEmpty())
		{
			throw new Exception("Le champ adresse est vide");
		}
		if(vVille.isEmpty())
		{
			throw new Exception("Le champ ville est vide");
		}
		if(vPays.isEmpty())
		{
			throw new Exception("Le champ pays est vide");
		}
		
		//Recupère la dao utilisateur
		UtilisateurDAO vUtilisateurDAO = new UtilisateurDAOImpl(FactoryEntityManager.emf.createEntityManager());
		
		//Modification de l'utilisateur
		vUtilisateur.setAdresse(vAdresse);
		vUtilisateur.setVille(vVille);
		vUtilisateur.setCodePostal(vCp);
		vUtilisateur.setMotDePasse(hashPassword);
		vUtilisateur.setPays(vPays);
		
		//Modification de l'utilisateur en base
		vUtilisateurDAO.update(vUtilisateur);
		//Remet en session l'utilisateur frais
		req.getSession().setAttribute("utilisateur", vUtilisateurDAO.find(vUtilisateur.getMail()));
		vUtilisateurDAO.closeEntityManager();
		
		//Ajout d'un message de confirmation de la modification
		req.setAttribute("success", "Informations du compte modifi�es avec succ�s");
		req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
		
		
	}
}
