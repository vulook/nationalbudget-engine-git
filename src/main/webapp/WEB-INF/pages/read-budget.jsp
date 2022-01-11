<%@ page import="com.softserve.entity.NationalBudget" %>
<%@ page import="com.softserve.repository.RepositoryBudgetDB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Аналітика</title>
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}static-resource/images/favicon-32x32.png">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}static-resource/css/styles.css">
</head>
<body>
<div class="wrapper">
    <%@include file="header.jsp" %>

    <main class="container">
        <h2>Картка бюджетної статті</h2>

        <%
            NationalBudget stateBudget = (NationalBudget) request.getAttribute("stateBudget");
        %>

        <table class="bordered_table ">
            <tr>
                <td class="text-left">Id:</td>
                <td class="text-left"><strong><%=stateBudget.getId()%>
                </strong></td>
            </tr>
            <tr>
                <td class="text-left">Код бюджетної класифікації:</td>
                <td class="text-left"><strong><%=stateBudget.getCodeB()%>
                </strong></td>
            </tr>
            <tr>
                <td class="text-left">Код функціональної класифікації:</td>
                <td class="text-left"><strong><%=stateBudget.getCodeF()%>
                </strong></td>
            </tr>
            <tr>
                <td class="text-left">Найменування статті видатків:</td>
                <td class="text-left"><strong><%=stateBudget.getNameExpend()%>
                </strong></td>
            </tr>
            <tr>
                <td class="text-left">Бюджетні видатки (тис.грн.):</td>
                <td class="text-left"><strong><%=RepositoryBudgetDB.format(stateBudget.getExpenditures())%>
                </strong></td>
            </tr>
            <tr>
                <td class="text-left">в тому числі:</td>
            </tr>
            <tr>
                <td class="text-left">Видатки Загального фонду:</td>
                <td class="text-left"><strong><%=RepositoryBudgetDB.format(stateBudget.getExpendG())%>
                </strong></td>
            </tr>
            <tr>
                <td class="text-left">=>видатки споживання:</td>
                <td class="text-left"><strong><%=RepositoryBudgetDB.format(stateBudget.getConsumG())%>
                </strong></td>
            </tr>
            <tr>
                <td class="text-left">=>видатки розвитку:</td>
                <td class="text-left"><strong><%=RepositoryBudgetDB.format(stateBudget.getDevelG())%>
                </strong></td>
            </tr>
            <tr>
                <td class="text-left">Видатки Спеціального фонду:</td>
                <td class="text-left"><strong><%=RepositoryBudgetDB.format(stateBudget.getExpendS())%>
                </strong></td>
            </tr>
            <tr>
                <td class="text-left">=>видатки споживання:</td>
                <td class="text-left"><strong><%=RepositoryBudgetDB.format(stateBudget.getConsumS())%>
                </strong></td>
            </tr>
            <tr>
                <td class="text-left">=>видатки розвитку:</td>
                <td class="text-left"><strong><%=RepositoryBudgetDB.format(stateBudget.getDevelS())%>
                </strong></td>
            <tr>
        </table>
    </main>

</div>
<%@include file="footer.jsp" %>
</body>
</html>
