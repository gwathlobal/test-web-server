package main;

import accounts.AccountService;
import dbService.DBService;
import servlets.AllRequestsServlet;
import servlets.ChatServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.net.http.WebSocket;

public class Main  {

    public static void main(String[] args) throws Exception {

        /*
        AccountService accountService = new AccountService();
        DBService dbService = new DBService();
        dbService.printConnectInfo();
        */

        //AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        //SignUpServlet signUpServlet = new SignUpServlet(dbService);
        //SignInServlet signInServlet = new SignInServlet(accountService, dbService);
        ChatServlet chatServlet = new ChatServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //context.addServlet(new ServletHolder(allRequestsServlet), "/*");
        //context.addServlet(new ServletHolder(signUpServlet), "/signup");
        //context.addServlet(new ServletHolder(signInServlet), "/signin");
        context.addServlet(new ServletHolder(chatServlet), "/chat");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }

}
