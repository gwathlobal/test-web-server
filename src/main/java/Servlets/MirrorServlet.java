package servlets;

import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MirrorServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException
    {
        String value = request.getParameter("key");

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("key", (value == null) ? "" : value);
        pageVariables.put("parameters", request.getParameterMap().toString());

        //response.getWriter().println(PageGenerator.instance().getPage("mirror.html", pageVariables));
        response.getWriter().println(value == null ? "" : value);


        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
