package com.softserve.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Java application analyzes the State Budget of Ukraine
 *
 * @author vulook (https://github.com/vulook).
 */

@SuppressWarnings("serial")
public class ErrorHandlerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }

        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();
        String title = "Error Handling";
        String docType = "<!DOCTYPE html>";
        writer.println(docType + "<html>" + "<head>" + "<title>" + title + "</title>" + "</head>" + "<body>");
        writer.println("<h1>Отакої, щось пішло не так! Інформація про помилку:</h1>");

        if (throwable != null && statusCode != null) {
            writer.println("<h3 style=\"color:red\">" + "The status code: " + statusCode + "." + "</h3>");
            writer.println("Requested URI: " + requestUri + "." + "</br></br>");
            writer.println("Servlet Name: " + servletName + "." + "</br></br>");
            writer.println("Exception Type: " + throwable.getClass().getName() + "." + "</br></br>");
            writer.println("The exception message: " + throwable.getMessage() + ".");
        }

        writer.println("<br><br>");
        writer.println("<strong>");
        writer.println("<a href=\"/\">Повернутися на головну сторінку</a>");
        writer.println("</strong>");
        writer.println("</body>");
        writer.println("</html>");
        writer.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
