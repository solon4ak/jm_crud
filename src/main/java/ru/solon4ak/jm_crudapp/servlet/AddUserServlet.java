package ru.solon4ak.jm_crudapp.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.solon4ak.jm_crudapp.util.DBException;
import ru.solon4ak.jm_crudapp.model.User;
import ru.solon4ak.jm_crudapp.service.UserService;
import ru.solon4ak.jm_crudapp.service.UserServiceImpl;

/**
 *
 * @author solon4ak
 */
@WebServlet(name = "AddUserServlet", urlPatterns = {"/add"})
public class AddUserServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = new User(
                req.getParameter("firstName"), 
                req.getParameter("lastName"), 
                req.getParameter("email"), 
                req.getParameter("address"), 
                req.getParameter("phoneNumber"), 
                Byte.valueOf(req.getParameter("age")));
        
        try {
            userService.addUser(user);
            req.setAttribute("user", user);
            resp.setStatus(200);
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/view.jsp").forward(req, resp);
        } catch (DBException ex) {
            resp.setStatus(400);
            Logger.getLogger(AddUserServlet.class.getName())
                    .log(Level.SEVERE, "Exception while adding user.", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/view/user/add.jsp").forward(req, resp);
    }

}
