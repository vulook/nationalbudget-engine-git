package com.softserve.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;

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
@WebServlet("/update-budget")
public class UpdateBudgetController extends HttpServlet {

    protected RepositoryBudgetDB repositoryBudget;
    protected NationalBudget stateBudget;

    @Override
    public void init() throws ServletException {
        repositoryBudget = RepositoryBudgetDB.getRepositoryBudget();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int recordId = Integer.parseInt(request.getParameter("id"));

        try {
            stateBudget = repositoryBudget.findById(recordId);
        } catch (SQLException ex) {
            System.err.format("SQLException: %s%n", ex);
        }
        request.setAttribute("stateBudget", stateBudget);
        request.getRequestDispatcher("/WEB-INF/pages/update-budget.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        NumberFormat nf = NumberFormat.getInstance();

        stateBudget.setCodeB(Integer.parseInt(request.getParameter("codeB")));
        stateBudget.setCodeF(Integer.parseInt(request.getParameter("codeF")));
        stateBudget.setNameExpend(request.getParameter("nameExpend"));

        try {
            stateBudget.setExpenditures(nf.parse(request.getParameter("expenditures")).doubleValue());
            stateBudget.setExpendG(nf.parse(request.getParameter("expendG")).doubleValue());
            stateBudget.setConsumG(nf.parse(request.getParameter("consumG")).doubleValue());
            stateBudget.setDevelG(nf.parse(request.getParameter("develG")).doubleValue());
            stateBudget.setExpendS(nf.parse(request.getParameter("expendS")).doubleValue());
            stateBudget.setConsumS(nf.parse(request.getParameter("consumS")).doubleValue());
            stateBudget.setDevelS(nf.parse(request.getParameter("develS")).doubleValue());

        } catch (ParseException ex) {
            System.err.format("Parse Exception: %s%n", ex);
        }

        try {
            repositoryBudget.updateId(stateBudget);
        } catch (SQLException ex) {
            System.err.format("SQL Exception: %s%n", ex);
        }
        response.sendRedirect("/budget-edit");
    }

}
