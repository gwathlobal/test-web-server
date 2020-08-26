package main;

import accounts.AccountService;
import accounts.IAccountService;
import mbeans.AccountServiceController;
import mbeans.AccountServiceControllerMBean;
import servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Main  {

    public static void main(String[] args) throws Exception {

        /*
        AccountService accountService = new AccountService();
        DBService dbService = new DBService();
        dbService.printConnectInfo();
        */

        IAccountService accountService = new AccountService();

        //AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        //SignUpServlet signUpServlet = new SignUpServlet(dbService);
        //SignInServlet signInServlet = new SignInServlet(accountService, dbService);
        //ChatServlet chatServlet = new ChatServlet();
        AdminServlet adminServlet = new AdminServlet(accountService);

        AccountServiceControllerMBean serverStatistics = new AccountServiceController(accountService);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=AccountServerController");
        mbs.registerMBean(serverStatistics, name);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //context.addServlet(new ServletHolder(allRequestsServlet), "/*");
        //context.addServlet(new ServletHolder(signUpServlet), "/signup");
        //context.addServlet(new ServletHolder(signInServlet), "/signin");
        //context.addServlet(new ServletHolder(chatServlet), "/chat");

        context.addServlet(new ServletHolder(adminServlet), "/admin");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }

}
