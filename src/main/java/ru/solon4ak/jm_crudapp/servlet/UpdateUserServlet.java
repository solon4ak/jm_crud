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
import ru.solon4ak.jm_crudapp.dbService.DBException;
import ru.solon4ak.jm_crudapp.model.User;
import ru.solon4ak.jm_crudapp.service.UserService;

/**
 *
 * @author solon4ak
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = {"/edit"})
public class UpdateUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");
        String age = req.getParameter("age");
        String idString = req.getParameter("id");

        try {
            long id = Long.parseLong(idString);
            System.out.println("Id long: " + id);
            try {
                User user = userService.getUserById(id);

                if (user != null) {
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setAddress(address);
                    user.setPhoneNumber(phoneNumber);
                    user.setAge(Byte.valueOf(age));

                    req.setAttribute("user", user);
                    req.getRequestDispatcher("/WEB-INF/jsp/view/user/view.jsp").forward(req, resp);
                } else {
                    req.setAttribute("users", (List<User>) userService.getAllUsers());
                    req.getRequestDispatcher("/WEB-INF/jsp/view/user/list.jsp").forward(req, resp);
                }
            } catch (DBException ex) {
                Logger.getLogger(UpdateUserServlet.class.getName())
                        .log(Level.SEVERE, "Exception while updating user.", ex);
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(UpdateUserServlet.class.getName())
                    .log(Level.SEVERE, "Wrong id argument.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idString = req.getParameter("id");
        System.out.println("Id string: " + idString);

        if (!idString.isEmpty() && !"".equals(idString)) {
            try {
                long id = Long.parseLong(idString);
                System.out.println("Id long: " + id);
                try {
                    User user = userService.getUserById(id);

                    if (user != null) {
                        req.setAttribute("user", user);
                        req.getRequestDispatcher("/WEB-INF/jsp/view/user/edit.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("users", (List<User>) userService.getAllUsers());
                        req.getRequestDispatcher("/WEB-INF/jsp/view/user/list.jsp").forward(req, resp);
                    }
                } catch (DBException ex) {
                    Logger.getLogger(UpdateUserServlet.class.getName())
                            .log(Level.SEVERE, "Exception while updating user.", ex);
                }
            } catch (NumberFormatException e) {
                Logger.getLogger(UpdateUserServlet.class.getName())
                        .log(Level.SEVERE, "Wrong id argument.", e);
            }
        }
    }

}
