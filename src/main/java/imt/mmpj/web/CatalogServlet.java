package imt.mmpj.web;

import imt.mmpj.storage.dao.FactoryEntityManager;
import imt.mmpj.storage.dao.ItemDAO;
import imt.mmpj.storage.dao.impl.ItemDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet used to show items of the catalog.
 */
@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {

    /**
     * Route to show items into the catalog.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * Return items of the catalog.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/pages/catalog.jsp");

        ItemDAO itemDAO = new ItemDAOImpl(FactoryEntityManager.emf.createEntityManager());
        List items = itemDAO.findAll();
        itemDAO.closeEntityManager();

        req.setAttribute("items", items);

    	rd.forward(req, resp);
    }

}