<%@ page import="com.softserve.entity.NationalBudget" %>
<%@ page import="com.softserve.repository.RepositoryBudgetDB" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Редагувати Державний бюджет України</title>
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}static-resource/images/favicon-32x32.png">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}static-resource/css/styles.css">
</head>
<body>
<div class="wrapper">
    <%@include file="header.jsp" %>

    <main class="container">
        <h2>Редагувати елементи Державного бюджету</h2>

        <div class="text-right">
            <a href="${pageContext.request.contextPath}/create-budget"
               class="site-btn">+ Нова стаття</a>
        </div>

        <%
            @SuppressWarnings("unchecked") List<NationalBudget> budget = (List<NationalBudget>) request.getAttribute("budget");
            if (!budget.isEmpty()) {
        %>

        <table class="bordered_table">
            <thead>
            <tr>
                <th class="w-70px">No.</th>
                <th>Код бюджетної класифікації</th>
                <th class="w-50">Найменування статті видатків</th>
                <th>Бюджетні видатки <br>(тис.грн.)
                </th>
                <th colspan="2">Операції</th>
            </tr>
            </thead>

            <%
                for (int i = 0; i < budget.size(); i++) {
            %>

            <tbody>
            <tr>
                <td class="w-70px"><%=i + 1%></td>
                <td><%=budget.get(i).getCodeB()%></td>
                <td class="w-50 text-left"><%=budget.get(i).getNameExpend()%></td>
                <td><%=RepositoryBudgetDB.format(budget.get(i).getExpenditures())%></td>
                <td><a href="${pageContext.request.contextPath}/update-budget?id=<%=budget.get(i).getId()%>">Edit
                </a></td>
                <td><a href="${pageContext.request.contextPath}/delete-budget?id=<%=budget.get(i).getId()%>">Delete
                </a></td>
            </tr>
            </tbody>

            <% } %>

        </table>

        <% } %>

    </main>
</div>

<%@include file="footer.jsp" %>
</body>
</html>