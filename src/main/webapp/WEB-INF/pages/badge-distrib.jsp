<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Функціональна класифікація</title>
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}static-resource/images/favicon-32x32.png">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}static-resource/css/styles.css">
</head>
<body>
<%@include file="header.jsp" %>

<main class="container">
    <h2>Розподіл видатків Державного бюджету згідно функціональної класифікації</h2>

    <%
        @SuppressWarnings("unchecked") List<Badge> badge = (List<Badge>) request.getAttribute("badge");
        double sSumE = (Double) request.getAttribute("sSumE");
        double sGravity = (Double) request.getAttribute("sGravity");
    %>

    <div class="w-75">
        <table class="bordered_table">
            <thead>
            <tr>
                <th class="w-70px">No.</th>
                <th class="w-50">Найменування функціональної класифікації</th>
                <th>Бюджетні видатки <br>(тис.грн.)</th>
                <th>Питома вага, <br>(%)</th>
            </tr>
            </thead>
            <tbody>

            <%
                for (int i = 0; i < badge.size(); i++) {
            %>

            <tr>
                <td class="w-70px"><%=i + 1%></td>
                <td class="w-75 text-left"><%=badge.get(i).getNameF()%></td>
                <td><%=RepositoryBudgetDB.format(badge.get(i).getSumE())%></td>
                <td><%=RepositoryBudgetDB.formatP(badge.get(i).getGravity())%></td>
            </tr>

            <%
                }
            %>

            <tr>
                <td class="w-70px"></td>
                <td class="w-75 text-left"><b>ВСЬОГО :</b></td>
                <td><b><%=RepositoryBudgetDB.format(sSumE)%></b></td>
                <td><b><%=RepositoryBudgetDB.formatP(sGravity)%></b></td>
            </tr>

            </tbody>
        </table>
    </div>

    <% } %>

</main>
<%@include file="footer.jsp" %>
</body>
</html>