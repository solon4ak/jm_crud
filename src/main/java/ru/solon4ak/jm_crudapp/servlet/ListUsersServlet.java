package ru.solon4ak.jm_crudapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "ListUsersServlet", urlPatterns = {"/list"})
public class ListUsersServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        try {
            req.setAttribute("users", userService.getAllUsers());
            resp.setStatus(200);
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/list.jsp").forward(req, resp);
        } catch (DBException ex) {
            resp.setStatus(400);
            Logger.getLogger(ListUsersServlet.class.getName())
                    .log(Level.SEVERE, "Exception while retrieve users", ex);
        }
    }

}
