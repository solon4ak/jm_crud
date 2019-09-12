package ru.solon4ak.jm_crudapp.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.solon4ak.jm_crudapp.dbService.DBException;
import ru.solon4ak.jm_crudapp.model.User;
import ru.solon4ak.jm_crudapp.service.UserService;

/**
 *
 * @author solon4ak
 */
@WebServlet(name = "AddUserServlet", urlPatterns = {"/add"})
public class AddUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");
        String age = req.getParameter("age");
        
        User user = new User(firstName, lastName, email, address, phoneNumber, Byte.valueOf(age));
        try {
            int res = userService.addUser(user);
            if (res != 0) {
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/jsp/view/user/view.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("/list");
            }
        } catch (DBException ex) {
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
