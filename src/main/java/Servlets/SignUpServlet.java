package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import dataSets.UsersDataSet;
import dbService.DBException;
import dbService.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final DBService dbService;

    public SignUpServlet(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.isBlank() || password.isBlank()) {
            response.getWriter().println("Bad login or password");
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UsersDataSet user = new UsersDataSet(login, password);
        try {
            dbService.addUser(user);
        }
        catch (DBException e) {
            e.printStackTrace();
        }
        //System.out.println(user.toString());

        response.getWriter().println("Registered");
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
