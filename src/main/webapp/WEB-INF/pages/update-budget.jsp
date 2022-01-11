<%@ page import="com.softserve.entity.NationalBudget" %>
<%@ page import="com.softserve.repository.RepositoryBudgetDB" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <meta charset="utf-8">
    <title>Редагувати ДБ</title>
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}static-resource/images/favicon-32x32.png">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}static-resource/css/styles.css">
</head>
<body>
<div class="wrapper">
    <%@include file="header.jsp" %>

    <%
        NationalBudget stateBudget = (NationalBudget) request.getAttribute("stateBudget");
    %>

    <main class="container">
        <h2>Змінити параметри бюджетної статті</h2>

        <form action="" method="post">
            <table class="bordered_table">
                <tr>
                    <td class="text-left"><label for="id">Id: </label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="id" id="id"
                                                 disabled value="<%=stateBudget.getId()%>"></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="codeB">Код бюджетної класифікації:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="codeB"
                                                 id="codeB" value="<%=stateBudget.getCodeB()%>"></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="codeF">Код функціональної класифікації:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="codeF"
                                                 id="codeF" value="<%=stateBudget.getCodeF()%>"></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="nameExpend">Найменування статті видатків:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="nameExpend"
                                                 id="nameExpend" value="<%=stateBudget.getNameExpend()%>"></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="expenditures">Бюджетні видатки (тис.грн.):</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="expenditures"
                                                 id="expenditures"
                                                 value="<%=RepositoryBudgetDB.format(stateBudget.getExpenditures())%>">
                    </td>
                </tr>
                <tr>
                    <td class="text-left"><label>в тому числі:</label></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="expendG">Видатки Загального фонду:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="expendG"
                                                 id="expendG"
                                                 value="<%=RepositoryBudgetDB.format(stateBudget.getExpendG())%>"></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="consumG">=>видатки споживання:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="consumG"
                                                 id="consumG"
                                                 value="<%=RepositoryBudgetDB.format(stateBudget.getConsumG())%>"></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="develG">=>видатки розвитку:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="develG"
                                                 id="develG"
                                                 value="<%=RepositoryBudgetDB.format(stateBudget.getDevelG())%>"></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="expendS">Видатки Спеціального фонду:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="expendS"
                                                 id="expendS"
                                                 value="<%=RepositoryBudgetDB.format(stateBudget.getExpendS())%>"></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="consumS">=>видатки споживання:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="consumS"
                                                 id="consumS"
                                                 value="<%=RepositoryBudgetDB.format(stateBudget.getConsumS())%>"></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="develS">=>видатки розвитку:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="develS"
                                                 id="develS"
                                                 value="<%=RepositoryBudgetDB.format(stateBudget.getDevelS())%>"></td>
                </tr>
            </table>
            <br>
            <p class="container text-right">
                <input type="submit" style="font-weight: bold;" class="site-btn" value="Зберегти">
            </p>
        </form>
    </main>

</div>
<%@include file="footer.jsp" %>
</body>
</html>
