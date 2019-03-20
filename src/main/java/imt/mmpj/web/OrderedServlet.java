package imt.mmpj.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import imt.mmpj.storage.dao.FactoryEntityManager;
import imt.mmpj.storage.dao.OrderDAO;
import imt.mmpj.storage.dao.impl.OrderDAOImpl;
import imt.mmpj.storage.model.Ordered;
import imt.mmpj.storage.model.Utilisateur;

@WebServlet("/ordered")
public class OrderedServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OrderDAO vOrderDAO = new OrderDAOImpl(FactoryEntityManager.emf.createEntityManager());
		
		List<Ordered> vListeOrdered = vOrderDAO.findAllByUser(((Utilisateur)req.getSession().getAttribute("utilisateur")));
		vOrderDAO.closeEntityManager();
		
		List<List<Ordered>> vListeParCommande = getListeParCommande(vListeOrdered);
		
		req.setAttribute("listeListeOrder", vListeParCommande);
		req.getRequestDispatcher("/WEB-INF/pages/ordered.jsp").forward(req, resp);

	}
	
	
	
	
	private List<List<Ordered>> getListeParCommande(List<Ordered> aListeOrdered)
	{
		List<List<Ordered>> vListeParOrder = new ArrayList<>();
		for(Ordered vOrder : aListeOrdered)
		{
			boolean vExist = false;
			int vCp = -1;
			int vIt = 0;
			for(List<Ordered> vListeSimple : vListeParOrder)
			{ 
				if(vListeSimple.get(0).getNumeroCommande()==vOrder.getNumeroCommande())
				{
					vExist = true;
					vCp = vIt;		
				}
				vIt++;
			}
			
			if(vExist)
			{
				vListeParOrder.get(vCp).add(vOrder);
			}
			else
			{
				List<Ordered> vNewListe = new ArrayList<>();
				vNewListe.add(vOrder);
				vListeParOrder.add(vNewListe);
			}
		}
		
		return vListeParOrder;
	}

}
