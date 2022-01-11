package com.softserve.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.entity.Badge;
import com.softserve.entity.NationalBudget;

/**
 * Java application analyzes the State Budget of Ukraine
 *
 * @author vulook (https://github.com/vulook).
 */

public class RepositoryBudgetDB {

    protected static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    protected static final String DB_URL = "jdbc:mysql://localhost/NATIONALBUDGET";
    protected static final Logger logger = LoggerFactory.getLogger(RepositoryBudgetDB.class);
    protected static RepositoryBudgetDB repoBudgetDB = null;
    protected static RepositoryBudgetDB repoBadgeDB = null;
    protected static Connection conn = null;
    protected static Statement stmt = null;
    protected static ResultSet rs = null;
    protected static PreparedStatement pstmt = null;
    protected static double sExpenditures = 0;
    protected static double sSumE = 0;
    protected static double sGravity = 0;

    protected RepositoryBudgetDB() {
    };

    public static RepositoryBudgetDB getRepositoryBudget() {
        if (repoBudgetDB == null) {
            repoBudgetDB = new RepositoryBudgetDB();
        }
        return repoBudgetDB;
    }

    public static RepositoryBudgetDB getBadgeRepository() {
        if (repoBadgeDB == null) {
            repoBadgeDB = new RepositoryBudgetDB();
        }
        return repoBadgeDB;
    }

    public static void connectToDB() {
        System.out.print("Connecting to database..");
        try (FileInputStream f = new FileInputStream(DBPROP)) {
            Properties pros = new Properties();
            pros.load(f);
            final String USER = pros.getProperty("user");
            final String PASS = pros.getProperty("password");
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("\nConnected to database " + conn.getCatalog() + " successfully.");
        } catch (SQLException | ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void variableNameLike() throws SQLException {
        stmt = conn.createStatement();
        String sql = "SET NAMES 'utf8mb4' COLLATE 'utf8mb4_unicode_ci'";
        stmt.executeUpdate(sql);
    }

    public static void dropTable() throws SQLException {
        String sql = "DROP TABLE IF EXISTS BUDGETDB";
        System.out.println("Table BUDGETDB was be deleted");
        stmt.executeUpdate(sql);
    }

    public static void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS BUDGETDB " + "(id INTEGER NOT NULL AUTO_INCREMENT, "
                + " codeB INTEGER, " + " codeF INTEGER, " + " nameExpend VARCHAR(1020), " + " expendG DOUBLE, "
                + " consumG DOUBLE, " + " develG DOUBLE, " + " expendS DOUBLE, " + " consumS DOUBLE, "
                + " develS DOUBLE, " + " expenditures DOUBLE, " + " PRIMARY KEY (id))";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        System.out.println("Table BUDGETDB was be created");
    }

    public static void updateTable(List<NationalBudget> budget) throws SQLException {
        int countRows = 0;
        System.out.println("Update BUDGETDB to the table...");
        String sql = "INSERT INTO BUDGETDB(codeB, codeF, nameExpend, expendG, consumG, develG, expendS, consumS, develS, expenditures) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);

        for (NationalBudget group : budget) {
            pstmt.setInt(1, group.getCodeB());
            pstmt.setInt(2, group.getCodeF());
            pstmt.setString(3, group.getNameExpend());
            pstmt.setDouble(4, group.getExpendG());
            pstmt.setDouble(5, group.getConsumG());
            pstmt.setDouble(6, group.getDevelG());
            pstmt.setDouble(7, group.getExpendS());
            pstmt.setDouble(8, group.getConsumS());
            pstmt.setDouble(9, group.getDevelS());
            pstmt.setDouble(10, group.getExpenditures());
            pstmt.addBatch();
            pstmt.executeUpdate();
            countRows++;
        }
        System.out.println("Data extraction completed successfully! Import rows: " + countRows);
    }

    public void create(int codeB, int codeF, String nameExpend, double expendG, double consumG, double develG,
                       double expendS, double consumS, double develS, double expenditures) throws SQLException {
        System.out.println("Adding a record to a table ...");
        stmt = conn.createStatement();
        String sql = "INSERT INTO BUDGETDB(codeB, codeF, nameExpend, expendG, consumG, develG, expendS, consumS, develS, expenditures) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, codeB);
        pstmt.setInt(2, codeF);
        pstmt.setString(3, nameExpend);
        pstmt.setDouble(4, expendG);
        pstmt.setDouble(5, consumG);
        pstmt.setDouble(6, develG);
        pstmt.setDouble(7, expendS);
        pstmt.setDouble(8, consumS);
        pstmt.setDouble(9, develS);
        pstmt.setDouble(10, expenditures);
        pstmt.executeUpdate();
    }

    public void create(NationalBudget stateBudget) throws SQLException {
        create(stateBudget.getCodeB(), stateBudget.getCodeF(), stateBudget.getNameExpend(), stateBudget.getExpendG(),
                stateBudget.getConsumG(), stateBudget.getDevelG(), stateBudget.getExpendS(), stateBudget.getConsumS(),
                stateBudget.getDevelS(), stateBudget.getExpenditures());
    }

    public List<NationalBudget> getAllBudget() throws SQLException {
        stmt = conn.createStatement();
        String sql = "SELECT * FROM BUDGETDB";
        rs = stmt.executeQuery(sql);
        return convertResultSetToBudget(rs);
    }

    public List<NationalBudget> getAllDisposer() throws SQLException {
        stmt = conn.createStatement();
        String sql = "SELECT * FROM BUDGETDB WHERE codeB like '%0000' ORDER BY codeB";
        rs = stmt.executeQuery(sql);
        return convertResultSetToBudget(rs);
    }

    public List<NationalBudget> get20max() throws SQLException {
        stmt = conn.createStatement();
        String sql = "SELECT * FROM BUDGETDB WHERE codeF>0  ORDER BY expenditures DESC LIMIT 20";
        rs = stmt.executeQuery(sql);
        return convertResultSetToBudget(rs);
    }

    public NationalBudget findById(int id) throws SQLException {
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = "SELECT id, codeB, codeF, nameExpend, expendG, consumG, develG, expendS, consumS, develS, expenditures "
                + "FROM BUDGETDB where id = " + id;
        rs = stmt.executeQuery(sql);
        return convertResultSetToStateBudget(rs);
    }

    public boolean updateId(NationalBudget stateBudget) throws SQLException {
        stmt = conn.createStatement();
        String sql = "UPDATE BUDGETDB SET BUDGETDB.codeB = '" + stateBudget.getCodeB() + "', BUDGETDB.codeF = '"
                + stateBudget.getCodeF() + "', BUDGETDB.nameExpend = '" + stateBudget.getNameExpend()
                + "', BUDGETDB.expendG = '" + stateBudget.getExpendG() + "', BUDGETDB.consumG = '"
                + stateBudget.getConsumG() + "', BUDGETDB.develG = '" + stateBudget.getDevelG()
                + "', BUDGETDB.expendS = '" + stateBudget.getExpendS() + "', BUDGETDB.consumS = '"
                + stateBudget.getConsumS() + "', BUDGETDB.develS = '" + stateBudget.getDevelS()
                + "', BUDGETDB.expenditures = " + stateBudget.getExpenditures() + " WHERE id = "
                + stateBudget.getId();
        return stmt.execute(sql);
    }

    public boolean deleteId(int id) throws SQLException {
        stmt = conn.createStatement();
        String sql = "DELETE FROM BUDGETDB where id = " + id;
        return stmt.execute(sql);
    }

    protected void printResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt("id");
            int codeB = rs.getInt("codeB");
            int codeF = rs.getInt("codeF");
            String nameExpend = rs.getString("nameExpend");
            double expenditures = rs.getDouble("expenditures");

            System.out.printf("| %-3s | %-7d |  %-4d |   %-10.2f | %-90s | \n", id, codeB, codeF, expenditures, nameExpend);
        }
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------");
        rs.beforeFirst();
    }

    protected List<NationalBudget> convertResultSetToBudget(ResultSet rs) throws SQLException {
        sExpenditures = 0;
        List<NationalBudget> budget = new ArrayList<>();
        NationalBudget stateBudget;
        while (rs.next()) {
            stateBudget = new NationalBudget();
            stateBudget.setId(rs.getInt("id"));
            stateBudget.setCodeB(rs.getInt("codeB"));
            stateBudget.setCodeF(rs.getInt("codeF"));
            stateBudget.setNameExpend(rs.getString("nameExpend"));
            stateBudget.setExpenditures(rs.getDouble("expenditures"));
            stateBudget.setExpendG(rs.getDouble("expendG"));
            stateBudget.setConsumG(rs.getDouble("consumG"));
            stateBudget.setDevelG(rs.getDouble("develG"));
            stateBudget.setExpendS(rs.getDouble("expendS"));
            stateBudget.setConsumS(rs.getDouble("consumS"));
            stateBudget.setDevelS(rs.getDouble("develS"));
            System.out.printf("| %-3s | %-7d |  %-4d |   %-10.2f | %-90s | \n", stateBudget.getId(),
                    stateBudget.getCodeB(), stateBudget.getCodeF(), stateBudget.getExpenditures(), stateBudget.getNameExpend());
            budget.add(stateBudget);
        }
        sExpenditures = (budget.stream().mapToDouble(NationalBudget::getExpenditures).sum());
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------");
        return budget;
    }

    public double getSum() {
        return sExpenditures;
    }

    protected NationalBudget convertResultSetToStateBudget(ResultSet rs) throws SQLException {
        NationalBudget stateBudget = new NationalBudget();
        System.out.println("From convert ... ");
        printResultSet(rs);
        if (rs.next()) {
            stateBudget.setId(rs.getInt("id"));
            stateBudget.setCodeB(rs.getInt("codeB"));
            stateBudget.setCodeF(rs.getInt("codeF"));
            stateBudget.setNameExpend(rs.getString("nameExpend"));
            stateBudget.setExpenditures(rs.getDouble("expenditures"));
            stateBudget.setExpendG(rs.getDouble("expendG"));
            stateBudget.setConsumG(rs.getDouble("consumG"));
            stateBudget.setDevelG(rs.getDouble("develG"));
            stateBudget.setExpendS(rs.getDouble("expendS"));
            stateBudget.setConsumS(rs.getDouble("consumS"));
            stateBudget.setDevelS(rs.getDouble("develS"));
        }
        System.out.printf("| %-3s | %-7d |  %-4d |   %-10.2f | %-90s | \n", stateBudget.getId(), stateBudget.getCodeB(),
                stateBudget.getCodeF(), stateBudget.getExpenditures(), stateBudget.getNameExpend());
        System.out.println(
                "-----------------------------------------------------------------------------------------------------");
        return stateBudget;
    }

    public static void createTableBadgeShortPercentage() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS BADGE_SHORT_PERCENTAGE " + "(id INTEGER NOT NULL AUTO_INCREMENT, "
                + " codeF INTEGER, " + " nameF VARCHAR(220), " + " sumE DOUBLE, " + " gravity DOUBLE, " + " PRIMARY KEY (id))";
        stmt = conn.createStatement();
        stmt.executeUpdate(sql);
        System.out.println("Table BADGE_SHORT_PERCENTAGE was be created");
    }

    public List<Badge> getAllBadgeShort() throws SQLException {
        stmt = conn.createStatement();
        String sql = "SELECT * FROM BADGE_SHORT_PERCENTAGE";
        rs = stmt.executeQuery(sql);
        return convertResultSetToBadge(rs);
    }

    protected static List<Badge> convertResultSetToBadge(ResultSet rs) throws SQLException {
        sSumE = 0;
        sGravity = 0;
        List<Badge> badge = new ArrayList<>();
        Badge badgeB;
        while (rs.next()) {
            badgeB = new Badge();
            badgeB.setId(rs.getInt("id"));
            badgeB.setCodeF(rs.getInt("codeF"));
            badgeB.setNameF(rs.getString("nameF"));
            badgeB.setSumE(rs.getDouble("sumE"));
            badgeB.setGravity(rs.getDouble("gravity"));
            System.out.printf("| %-3s | %-4d | %-80s |   %-10.2f | %-5.3f | \n", badgeB.getId(), badgeB.getCodeF(),
                    badgeB.getNameF(), badgeB.getSumE(), badgeB.getGravity());
            badge.add(badgeB);
        }
        sSumE = (badge.stream().mapToDouble(Badge::getSumE).sum());
        sGravity = (badge.stream().mapToDouble(Badge::getGravity).sum());
        System.out.println(
                "---------------------------------------------------------------------------------------------------------");
        return badge;
    }

    static {
        long start = System.currentTimeMillis();
        try {

            connectToDB();
            variableNameLike();
            dropTable();
            createTable();
            updateTable(ReadExcelBudget.budgetList());

        } catch (SQLException se) {
            System.err.format("SQL Exception: %s%n", se);
        }
        long end = System.currentTimeMillis();
        System.out.printf("Import done in %d ms", (end - start));
        System.out.println(" - Success!");
    }

}