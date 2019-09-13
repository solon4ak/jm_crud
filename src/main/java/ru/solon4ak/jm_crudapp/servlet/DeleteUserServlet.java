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
@WebServlet(name = "DeleteUserServlet", urlPatterns = {"/delete"})
public class DeleteUserServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idString = req.getParameter("id");
        long id = Long.parseLong(idString);
        try {
            userService.deleteUser(id);
            resp.setStatus(200);
            req.setAttribute("users", (List<User>) userService.getAllUsers());            
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/list.jsp").forward(req, resp);
        } catch (DBException ex) {
            resp.setStatus(400);
            Logger.getLogger(DeleteUserServlet.class.getName())
                    .log(Level.SEVERE, "Exception while deleting user.", ex);
        }
    }

}
