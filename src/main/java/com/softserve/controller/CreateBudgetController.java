package com.softserve.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.entity.NationalBudget;
import com.softserve.repository.RepositoryBudgetDB;

/**
 * Java application analyzes the State Budget of Ukraine
 *
 * @author vulook (https://github.com/vulook).
 */

@SuppressWarnings("serial")
@WebServlet(value = "/create-budget")
public class CreateBudgetController extends HttpServlet {

    protected RepositoryBudgetDB repositoryBudget;

    @Override
    public void init() throws ServletException {
        repositoryBudget = RepositoryBudgetDB.getRepositoryBudget();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/create-budget.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        NumberFormat nf = NumberFormat.getInstance();
        double expenditures = 0.00;
        double expendG = 0.00;
        double consumG = 0.00;
        double develG = 0.00;
        double expendS = 0.00;
        double consumS = 0.00;
        double develS = 0.00;

        int codeB = Integer.parseInt(request.getParameter("codeB"));
        int codeF = Integer.parseInt(request.getParameter("codeF"));
        String nameExpend = request.getParameter("nameExpend");

        try {
            expenditures = nf.parse(request.getParameter("expenditures")).doubleValue();
            expendG = nf.parse(request.getParameter("expendG")).doubleValue();
            consumG = nf.parse(request.getParameter("consumG")).doubleValue();
            develG = nf.parse(request.getParameter("develG")).doubleValue();
            expendS = nf.parse(request.getParameter("expendS")).doubleValue();
            consumS = nf.parse(request.getParameter("consumS")).doubleValue();
            develS = nf.parse(request.getParameter("develS")).doubleValue();

        } catch (ParseException ex) {
            System.err.format("Parse Exception: %s%n", ex);
        }

        NationalBudget stateBudget = new NationalBudget(codeB, codeF, nameExpend, expendG, consumG, develG, expendS,
                consumS, develS, expenditures);
        try {
            repositoryBudget.create(stateBudget);
        } catch (SQLException ex) {
            System.err.format("SQLException: %s%n", ex);
        }
        response.sendRedirect("/budget-edit");
    }

}
