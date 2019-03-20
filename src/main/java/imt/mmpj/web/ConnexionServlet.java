package imt.mmpj.web;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import imt.mmpj.storage.dao.FactoryEntityManager;
import imt.mmpj.storage.dao.UtilisateurDAO;
import imt.mmpj.storage.dao.impl.UtilisateurDAOImpl;
import imt.mmpj.storage.model.Basket;
import imt.mmpj.storage.model.Utilisateur;

@WebServlet("/login")
public class ConnexionServlet extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.sendRedirect("home");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try 
		{
			checkForm(req);
			process(req, resp);
		} catch (Exception e) {
			req.setAttribute("exception", e.getMessage());
			req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
		}
		
	}	
	
	/**
	 * Traite le formulaire une fois que l'on est sur que le formulaire est correctement rempli
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	public void process(HttpServletRequest req,HttpServletResponse resp) throws Exception
	{   
		//Recupère les champs du formulaire email et mdp
		String vEmail = (String) req.getParameter("email");
		String vPassword = (String) req.getParameter("mdp");
		
		//Instantiatio de la DAO
		UtilisateurDAO vUtilisateurDAO = new UtilisateurDAOImpl(FactoryEntityManager.emf.createEntityManager());
		//Recupère un utilisateur depuis son email
		Utilisateur vUtilisateur = vUtilisateurDAO.find(vEmail);
		//Ferme l'entitymanager qui est dans la dao
		vUtilisateurDAO.closeEntityManager();
		
		String myHash=null;
		
		try
		{
			//Permet de hasher le mot de passe
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(vPassword.getBytes());
		    byte[] digest = md.digest();
		     myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		}
		catch(Exception e)
		{
			throw new Exception("Une erreur s est produite lors de l authentification, contactez un administrateur si l erreur se reproduit.");
		}
		if(vUtilisateur==null)
		{
			throw new Exception("L adresse email n est pas enregistr�e, recommencez ou inscrivez-vous");
		}
		if(!vUtilisateur.getMotDePasse().equals(myHash))
		{
			throw new Exception("Le mot de passe est invalide, veuillez r�essayer");
		}
		
		//Une fois l'utilisateur vérifié , on l'enregistre dans une variable de session
		HttpSession vSession = req.getSession(true);
		vSession.setAttribute("utilisateur", vUtilisateur);
		vSession.setAttribute("basket", new Basket());
		vSession.setAttribute("taillePanier", 0);
		resp.sendRedirect("home");
		
	}
	
	/**
	 * Verifie que les champs des formulaires soient bien remplies<br/>
	 * N'accepte pas les emails et mot de passe vides<br/>
	 * L'email doit être de la forme abcd@abcd.abcd<br/>
	 * @param req
	 * @throws Exception
	 */
	public void checkForm(HttpServletRequest req) throws Exception
	{
		String vEmail = (String) req.getParameter("email");
		String vPassword = (String) req.getParameter("mdp");
		
		if(vEmail==null)
		{
			throw new Exception("Le champ email est vide, veuillez renseigner une adresse email");
		}
		else
		{
		    String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(vEmail);
		    if (!matcher.matches()) {
		        throw new Exception("L adresse email n est pas valide , veuillez la modifier ");
		    }
		}
		
		if(vPassword==null)
		{
			throw new Exception("Le champ mot de passe est vide, veuillez renseigner un mot de passe");
		}
	}

}

