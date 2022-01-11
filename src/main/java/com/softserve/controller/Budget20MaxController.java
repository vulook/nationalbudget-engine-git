package com.softserve.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.repository.RepositoryBudgetDB;

/**
 * Java application analyzes the State Budget of Ukraine
 *
 * @author vulook (https://github.com/vulook).
 */

@SuppressWarnings("serial")
@WebServlet("/budget-20Max")
public class Budget20MaxController extends HttpServlet {

    protected RepositoryBudgetDB repositoryBudget;

    @Override
    public void init() throws ServletException {
        repositoryBudget = RepositoryBudgetDB.getRepositoryBudget();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/budget-20Max.jsp");

        try {
            request.setAttribute("budget", repositoryBudget.get20max());
            request.setAttribute("sExpenditures", repositoryBudget.getSum());
        } catch (SQLException ex) {
            System.err.format("SQLException: %s%n", ex);
        }
        requestDispatcher.forward(request, response);
    }

}
