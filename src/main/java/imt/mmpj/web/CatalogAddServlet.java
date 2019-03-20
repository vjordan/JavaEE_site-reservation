package imt.mmpj.web;

import imt.mmpj.storage.dao.FactoryEntityManager;
import imt.mmpj.storage.dao.ItemDAO;
import imt.mmpj.storage.dao.UtilisateurDAO;
import imt.mmpj.storage.dao.impl.ItemDAOImpl;
import imt.mmpj.storage.dao.impl.UtilisateurDAOImpl;
import imt.mmpj.storage.model.Item;
import imt.mmpj.storage.model.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet used to add an item to the catalog.
 */
@WebServlet("/catalogAdd")
public class CatalogAddServlet extends HttpServlet {

    /**
     * Route to show the form.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //check if the user is authorized to show form.
        if (middleware(req, resp)) return;

        req.setAttribute("imageurl", "");
        req.setAttribute("code", "");
        req.setAttribute("name", "");
        req.setAttribute("price", 0);
        req.setAttribute("quantity", 0);
        req.getRequestDispatcher("/WEB-INF/pages/catalogAdd.jsp").forward(req, resp);
    }

    /**
     * Route to add item to the database.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //check if the user is authorized to add item.
        if (middleware(req, resp)) return;

        try {
            process(req, resp);
        } catch (Exception e) {
            req.setAttribute("exception", e.getMessage());
            req.setAttribute("imageurl", req.getParameter("imageurl"));
            req.setAttribute("code", req.getParameter("code"));
            req.setAttribute("name", req.getParameter("name"));
            req.setAttribute("price", req.getParameter("price"));
            req.setAttribute("quantity", req.getParameter("quantity"));
            req.getRequestDispatcher("/WEB-INF/pages/catalogAdd.jsp").forward(req, resp);
        }
    }

    /**
     * Check if the user is authorized to add item.
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private boolean middleware(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //check if the user is connected
        if(req.getSession().getAttribute("utilisateur") == null) {
            req.getRequestDispatcher("/WEB-INF/pages/errors/401.jsp").forward(req, resp);
            return true;
        }

        //check is the user is admin
        Utilisateur utilisateur = (Utilisateur) req.getSession().getAttribute("utilisateur");
        if(!utilisateur.getAdmin()) {
            req.getRequestDispatcher("/WEB-INF/pages/errors/403.jsp").forward(req, resp);
            return true;
        }
        return false;
    }

    /**
     * Add item into the database.
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    private void process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        //Validate form
        if (req.getParameter("code").isEmpty()) {
            throw new Exception("Le champ code est vide");
        }
        if (req.getParameter("name").isEmpty()) {
            throw new Exception("Le champ nom est vide");
        }
        if (req.getParameter("price").isEmpty()) {
            throw new Exception("Le champ prix est vide");
        }
        if (req.getParameter("quantity").isEmpty()) {
            throw new Exception("Le champ quantité est vide");
        }

        String imageurl = req.getParameter("imageurl");
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        ItemDAO itemDAO = new ItemDAOImpl(FactoryEntityManager.emf.createEntityManager());

        //check if the code doesn't exist
        if (itemDAO.find(code) != null) {
            throw new Exception("Le code " + "\"" + code + "\"" + " est déjà utilisé, merci d'en prendre un autre.");
        }

        //create item
        Item item = new Item(code, name, price, quantity, imageurl);
        itemDAO.create(item);
        itemDAO.closeEntityManager();

        req.setAttribute("success", "Produit ajouté");
        req.getRequestDispatcher("/WEB-INF/pages/catalogAdd.jsp").forward(req, resp);
    }
}