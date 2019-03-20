package imt.mmpj.web;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
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
import imt.mmpj.storage.model.Utilisateur;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	protected static final int LOGIN_SESSION = 0;
	protected static final int REGISTER_SESSION = 1;
	
	private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class.getName());

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	LOGGER.severe("ljvshclqhvfljvh");
    	
    	if(req.getSession() != null) {
            req.getSession(false).invalidate();
        }
	    HttpSession session = req.getSession(true);

        boolean success = false;
        
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        String lastName = req.getParameter("lastname");
        String firstName = req.getParameter("firstname");
        String address = req.getParameter("address");
        Integer postalCode = null;
        try{
        	postalCode = Integer.parseInt(req.getParameter("postalCode"));
        }catch(Exception e) {}
        String city = req.getParameter("city");
        String country = req.getParameter("country");

        String exception = null;
       
        if(email == null || email.isEmpty()) {
        	exception = "Veuillez saisir un email";
        }else if(password == null || password.isEmpty()) {
        	exception = "Veuillez saisir un mot de passe";
        }else if(password2 == null || password2.isEmpty()) {
        	exception = "Veuillez confirmer votre mot de passe";
        }else if(lastName == null || lastName.isEmpty()) {
        	exception = "Veuillez saisir un prénom";
        }else if(firstName == null || firstName.isEmpty()) {
        	exception = "Veuillez saisir un nom";
        }else if(address == null || address.isEmpty()) {
        	exception = "Veuillez saisir une adresse";
        }else if(postalCode == null) {
        	exception = "Veuillez saisir un code postal";
        }else if(!password.equals(password2)) {
        	exception = "La confirmation du mot de passe est différente";
        }else if(password.length() < 8) {
        	exception = "Votre mot de passe fait moins de 8 caractères";
        }
        else {
        	LOGGER.info("inscription OK");
            success = true;
        }

        if(success) {
        	 MessageDigest md;
			try {
				md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
        	    byte[] digest = md.digest();
        	    String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        	    
        	    Utilisateur user = new Utilisateur(email, myHash, firstName, lastName, postalCode, city, country, address);
                user.setAdmin(false);
        	    UtilisateurDAO perso = new UtilisateurDAOImpl(FactoryEntityManager.emf.createEntityManager());
                perso.create(user);
                perso.closeEntityManager();

                req.setAttribute("success", "Vous avez bien été inscrit");
                req.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(req, resp);
			} catch (NoSuchAlgorithmException e) {
				LOGGER.severe("Erreur au moment du hash" + e);
				success = false;
			}  
        }
        
        if(!success){
        	req.setAttribute("mail", email);
        	req.setAttribute("password", password);
        	req.setAttribute("password2", password2);
        	req.setAttribute("lastname", lastName);
        	req.setAttribute("firstName", firstName);
        	req.setAttribute("address", address);
        	req.setAttribute("postalCode", postalCode);
        	req.setAttribute("city", city);
        	req.setAttribute("country", country);
        	
        	req.setAttribute("exception2", exception);
        	
            req.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(req, resp);
        }
    }

}