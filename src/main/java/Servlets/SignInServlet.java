package servlets;

import accounts.AccountService;
import dataSets.UsersDataSet;
import dbService.DBException;
import dbService.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final AccountService accountService;
    private final DBService dbService;

    public SignInServlet(AccountService accountService, DBService dbService) {
        this.accountService = accountService;
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

        UsersDataSet user = null;
        try {
            user = dbService.getUserByLogin(login);
        }
        catch (DBException e) {
            e.printStackTrace();
        }

        if (user == null || !password.equals(user.getPassword())) {
            response.getWriter().println("Unauthorized");
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        accountService.addSession(request.getSession().getId(), user);
        //System.out.println(user.toString());

        response.getWriter().println(String.format("Authorized: %s", login));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
