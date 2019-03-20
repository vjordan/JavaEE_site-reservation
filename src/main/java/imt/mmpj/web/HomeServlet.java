package imt.mmpj.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/home", "/"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//Si on est dans le cas d'une déconnexion on supprime la variable de session indiquant que l'on est connecte
    	if(req.getParameter("deconnexion")!= null && req.getParameter("deconnexion").equals("true"))
    		req.getSession().setAttribute("utilisateur", null);
    	
        process(req, resp);
    }

    
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	process(req, resp);
	}


	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/pages/home.jsp");
    	rd.forward(req, resp);
    }

}