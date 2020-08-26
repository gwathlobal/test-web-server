package servlets;

import accounts.AccountService;
import accounts.IAccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {

    private IAccountService accountService;

    public AdminServlet(IAccountService accountService)
    {
        this.accountService = accountService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException
    {
        response.getWriter().print(accountService.getUsersLimit());
    }
}
