package com.softserve.repository;

import java.util.ArrayList;
import java.util.List;

import com.softserve.entity.NationalBudget;

/**
 * Java application analyzes the State Budget of Ukraine
 *
 * @author vulook (https://github.com/vulook).
 */

public class BudgetRepository {

    protected static BudgetRepository budgetRepository = null;
    protected List<NationalBudget> budget;

    public static synchronized BudgetRepository getBudgetRepository() {
        if (budgetRepository == null) {
            budgetRepository = new BudgetRepository();
        }
        return budgetRepository;
    }

    protected BudgetRepository() {
        budget = new ArrayList<>();
        NationalBudget stateBudget = new NationalBudget();
        stateBudget.getId();
        stateBudget.getCodeB();
        stateBudget.getCodeF();
        stateBudget.getNameExpend();
        stateBudget.getExpendG();
        stateBudget.getConsumG();
        stateBudget.getDevelG();
        stateBudget.getExpendS();
        stateBudget.getConsumS();
        stateBudget.getDevelS();
        stateBudget.getExpenditures();
        budget.add(stateBudget);
    }

    public boolean create(NationalBudget stateBudget) {
        boolean status = budget.stream()
                .anyMatch(c -> c.getCodeB() == stateBudget.getCodeB());
        if (!status) {
            return budget.add(stateBudget);
        }
        return false;
    }

    public NationalBudget findById(int id) {
        return budget.stream().
                filter(stateBudget -> stateBudget.getId() == id).findFirst()
                .orElse(null);
    }

    public boolean updateId(NationalBudget newNationalBudget) {
        NationalBudget oldNationalBudget = findById(newNationalBudget.getId());
        if (oldNationalBudget != null) {
            boolean status = findAll().stream()
                    .filter(stateBudget -> stateBudget.getId() != oldNationalBudget.getId())
                    .anyMatch(stateBudget -> stateBudget.getCodeB() == newNationalBudget.getCodeB());
            if (!status) {
                return budget.set(budget.indexOf(oldNationalBudget), newNationalBudget) != null;
            }
            return false;
        }
        return false;
    }

    public boolean deleteId(int id) {
        NationalBudget stateBudget = findById(id);
        if (stateBudget != null) {
            return budget.remove(stateBudget);
        }
        return false;
    }

    public List<NationalBudget> findAll() {
        return budget;
    }

    public void deleteAll() {
        budget.clear();
    }

}