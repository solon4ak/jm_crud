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
@WebServlet(name = "ViewUserServlet", urlPatterns = {"/view"})
public class ViewUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
                        req.getRequestDispatcher("/WEB-INF/jsp/view/user/view.jsp").forward(req, resp);
                    } else {
                        resp.sendRedirect("/list");
                    }
                } catch (DBException ex) {
                    Logger.getLogger(DeleteUserServlet.class.getName())
                            .log(Level.SEVERE, "Exception while retrieving user.", ex);
                }
            } catch (NumberFormatException e) {
                Logger.getLogger(DeleteUserServlet.class.getName())
                        .log(Level.SEVERE, "Wrong id argument.", e);
            }
        }
    }

}
