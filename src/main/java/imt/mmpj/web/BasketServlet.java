package imt.mmpj.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;

import imt.mmpj.storage.dao.FactoryEntityManager;
import imt.mmpj.storage.dao.ItemDAO;
import imt.mmpj.storage.dao.OrderDAO;
import imt.mmpj.storage.dao.impl.ItemDAOImpl;
import imt.mmpj.storage.dao.impl.OrderDAOImpl;
import imt.mmpj.storage.model.Basket;
import imt.mmpj.storage.model.Item;
import imt.mmpj.storage.model.Ordered;
import imt.mmpj.storage.model.Utilisateur;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {
	
	private static final Logger LOGGER = Logger.getLogger(RegisterServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	Basket basket = (Basket)req.getSession().getAttribute("basket");
    	req.setAttribute("basket", basket.getListePanier());
    	
    	req.getRequestDispatcher("/WEB-INF/pages/basket.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	HttpSession session = req.getSession();
    	
    	String commander = req.getParameter("commander");
    	if (commander != null && commander.equals("false")) {	  	
	    	String ref = req.getParameter("id");
	    	String action = req.getParameter("action");
	    	
	    	ItemDAO itemDAO = new ItemDAOImpl(FactoryEntityManager.emf.createEntityManager());
	        Item item = itemDAO.find(ref);
	        itemDAO.closeEntityManager();     
	        Basket basket = (Basket)session.getAttribute("basket");
	    	
	    	if (action.equals("add")) {	
	            basket.addItem(item);   
	    	}
	    	if (action.equals("remove")) {	
	            basket.removeItem(item);   
	    	}
	    	
	    	session.setAttribute("taillePanier", basket.getTaille());
	    	session.setAttribute("basket", basket); 
    	}
    	else {
    		
    		Utilisateur vUtilisateur = (Utilisateur) req.getSession().getAttribute("utilisateur");
    		Date vDate = new Date();
    		String vNumeroCommande = vUtilisateur.getNom()+vDate.getTime();
    		
    		ItemDAO itemDAO = new ItemDAOImpl(FactoryEntityManager.emf.createEntityManager());
    		OrderDAO orderDAO = new OrderDAOImpl(FactoryEntityManager.emf.createEntityManager());
    		
    		int taillePanier = (Integer) session.getAttribute("taillePanier");
    		Map<String,Integer> vMap = new HashMap<>();
    		
    		for(int i = 1; i <= taillePanier; i++) {
    			int vQte = Integer.valueOf(req.getParameter("quantiteVoulue"+Integer.toString(i)));
    			String vCodeItem = req.getParameter("codeVoulue"+Integer.toString(i));
    			vMap.put(vCodeItem, vQte);
    		}
    		
    		for (Map.Entry<String,Integer> entry : vMap.entrySet())
    		{
    			Item vItem = itemDAO.find(entry.getKey());
    			Integer vQuantite = entry.getValue();
    			Ordered vOrder = new Ordered(vNumeroCommande, vItem, vUtilisateur, vQuantite);
    			
    			orderDAO.create(vOrder);
    			vItem.setQuantity(vItem.getQuantity()-vQuantite);
    			itemDAO.update(vItem);
    		}
    		itemDAO.closeEntityManager();
    		orderDAO.closeEntityManager();
    		
    		session.setAttribute("taillePanier", 0);
    		session.setAttribute("basket", new Basket());
            req.setAttribute("success", "Commande validée !");
            req.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(req, resp);
    	}
    }

}