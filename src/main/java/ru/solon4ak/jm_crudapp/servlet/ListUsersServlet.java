package ru.solon4ak.jm_crudapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
@WebServlet(name = "ListUsersServlet", urlPatterns = {"/list"})
public class ListUsersServlet extends HttpServlet {

    private static final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> all = new ArrayList<>();
        if (req.getParameter("clear") != null) {
            try {
                userService.ClearData();
            } catch (DBException ex) {
                Logger.getLogger(ListUsersServlet.class.getName())
                        .log(Level.SEVERE, "Exception while deleting all users.", ex);
            }
            req.setAttribute("users", Collections.<User>emptyList());
        } else {
            try {
                all = userService.getAllUsers();
            } catch (DBException ex) {
                Logger.getLogger(ListUsersServlet.class.getName())
                        .log(Level.SEVERE, "Exception while retrieve users", ex);
            }
            req.setAttribute("users", all);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/view/user/list.jsp").forward(req, resp);
    }

}
