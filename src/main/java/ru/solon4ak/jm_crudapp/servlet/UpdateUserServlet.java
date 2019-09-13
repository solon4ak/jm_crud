package ru.solon4ak.jm_crudapp.servlet;

import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/edit"})
public class UpdateUserServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));

        try {
            User user = userService.getUserById(id);

            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
            user.setEmail(req.getParameter("email"));
            user.setAddress(req.getParameter("address"));
            user.setPhoneNumber(req.getParameter("phoneNumber"));
            user.setAge(Byte.valueOf(req.getParameter("age")));

            userService.updateUser(user);

            req.setAttribute("user", user);
            resp.setStatus(200);
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/view.jsp").forward(req, resp);
        } catch (DBException ex) {
            resp.setStatus(400);
            Logger.getLogger(UpdateUserServlet.class.getName())
                    .log(Level.SEVERE, "Exception while updating user.", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idString = req.getParameter("id");
        long id = Long.parseLong(idString);

        try {
            User user = userService.getUserById(id);
            req.setAttribute("user", user);
            resp.setStatus(200);
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/edit.jsp").forward(req, resp);
        } catch (DBException ex) {
            resp.setStatus(400);
            Logger.getLogger(UpdateUserServlet.class.getName())
                    .log(Level.SEVERE, "Exception while updating user.", ex);
        }
    }

}
