<%@ page import="com.softserve.entity.NationalBudget" %>
<%@ page import="com.softserve.repository.RepositoryBudgetDB" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>20-ка видатків</title>
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}static-resource/images/favicon-32x32.png">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}static-resource/css/styles.css">
</head>
<body>
<%@include file="header.jsp" %>
<main class="container">

    <h2>20-ка найбільших видатків Державного бюджету</h2>

    <%
        @SuppressWarnings("unchecked") List<NationalBudget> budget = (List<NationalBudget>) request.getAttribute("budget");
        double sExpenditures = (Double) request.getAttribute("sExpenditures");
        if (!budget.isEmpty()) {
    %>

    <table class="bordered_table">
        <thead>
        <tr>
            <th class="w-70px">No.</th>
            <th>Код бюджетної <br> класифікації
            </th>
            <th class="w-50">Стаття видатків</th>
            <th>Бюджетні видатки <br>(тис.грн.)
            </th>
        </tr>
        </thead>
        <tbody>

        <%
            for (int i = 0; i < budget.size(); i++) {
        %>

        <tr>
            <td class="w-70px"><%=i + 1%></td>
            <td><%=budget.get(i).getCodeB()%></td>
            <td class="w-50 text-left">
                <a href="${pageContext.request.contextPath}/read-budget?id=<%=budget.get(i).getId()%>"><%=budget.get(i).getNameExpend()%>
                </a></td>
            <td><%=RepositoryBudgetDB.format(budget.get(i).getExpenditures())%></td>
        </tr>

        <% } %>

        <tr>
            <td colspan="3" class="w-75 text-center"><b>ВСЬОГО :</b></td>
			<td><b><%=RepositoryBudgetDB.format(sExpenditures)%></b></td>
        </tr>

        </tbody>
    </table>

    <% } %>

</main>

<%@include file="footer.jsp" %>
</body>
</html>