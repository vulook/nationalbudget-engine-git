<%@ page import="com.softserve.entity.NationalBudget" %>
<%@ page import="com.softserve.repository.RepositoryBudgetDB" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta content="charset=UTF-8">
    <title>Огляд видатків</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}static-resource/images/favicon-32x32.png">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}static-resource/css/styles.css">
</head>
<body>
<div class="wrapper">
    <%@include file="header.jsp" %>
    <main class="container">

        <h2>Видатки Державного бюджету</h2>

        <%
            @SuppressWarnings("unchecked") List<NationalBudget> budget = (List<NationalBudget>) request.getAttribute("budget");
            if (!budget.isEmpty()) {
        %>

        <table class="bordered_table">
            <thead>
            <tr>
                <th class="w-70px">No.</th>
                <th>Код бюджетної класифікації</th>
                <th>Код функціональної класифікації</th>
                <th class="w-50">Найменування статті видатків</th>
                <th>Бюджетні видатки <br>(тис.грн.)
                </th>
            </tr>
            </thead>
            <tbody>

            <%
                for (int i = 0; i < budget.size(); i++) {
            %>

            <tr>
                <td class="w-70px"><%=i + 1%>
                </td>
                <td><%=budget.get(i).getCodeB()%>
                </td>
                <td><%=budget.get(i).getCodeF()%>
                </td>
                <td class="w-50 text-left"><a
                        href="${pageContext.request.contextPath}/read-budget?id=<%=budget.get(i).getId()%>"><%=budget.get(i).getNameExpend()%>
                </a></td>
                <td><%=RepositoryBudgetDB.format(budget.get(i).getExpenditures())%>
                </td>
            </tr>

            <% } %>

            </tbody>
        </table>

        <% } %>

    </main>
</div>

<%@include file="footer.jsp" %>
</body>
</html>