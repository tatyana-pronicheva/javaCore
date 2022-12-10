package gb.demo.homework;

import jakarta.servlet.http.*;

import java.io.IOException;


public class HelloWorldServlet extends HttpServlet {
    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) throws IOException {

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("Hello world!");
        resp.getWriter().flush();
        resp.getWriter().close();
    }

}
