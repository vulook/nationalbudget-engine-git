package com.softserve.controller;

import java.io.IOException;
import java.sql.SQLException;

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
@WebServlet(value = "/read-budget")
public class ReadBudgetController extends HttpServlet {

    protected RepositoryBudgetDB repositoryBudget;

    @Override
    public void init() throws ServletException {
        repositoryBudget = RepositoryBudgetDB.getRepositoryBudget();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int recordId = Integer.parseInt(request.getParameter("id"));
        NationalBudget stateBudget = null;

        try {
            stateBudget = repositoryBudget.findById(recordId);
        } catch (SQLException ex) {
            System.err.format("SQLException: %s%n", ex);
        }
        request.setAttribute("stateBudget", stateBudget);
        request.getRequestDispatcher("/WEB-INF/pages/read-budget.jsp").forward(request, response);
    }

}
