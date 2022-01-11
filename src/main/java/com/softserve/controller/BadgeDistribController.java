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
 * Java application that analyzes the National budget for the corresponding year
 *
 * @author vulook (https://github.com/vulook).
 */

@SuppressWarnings("serial")
@WebServlet("/badge-distrib")
public class BadgeDistribController extends HttpServlet {

    protected RepositoryBudgetDB repositoryBadge;

    @Override
    public void init() throws ServletException {
        repositoryBadge = RepositoryBudgetDB.getBadgeRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/badge-distrib.jsp");

        try {
            request.setAttribute("badge", repositoryBadge.getAllBadgeShort());
        } catch (SQLException ex) {
            System.err.format("SQLException: %s%n", ex);
        }
        requestDispatcher.forward(request, response);
    }

}
