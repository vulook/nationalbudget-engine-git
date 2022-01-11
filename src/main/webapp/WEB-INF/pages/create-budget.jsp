<%@ page import="com.softserve.entity.NationalBudget" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Редагування ДБ</title>
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}static-resource/images/favicon-32x32.png">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}static-resource/css/styles.css">
</head>
<body>
<div class="wrapper">
    <%@include file="header.jsp" %>

    <main class="container">
        <h2>Додати нову бюджетну статтю</h2>
        <form action="" method="post">

            <table class="bordered_table">
                <tr>
                    <td class="text-left"><label for="codeB">Код бюджетної класифікації:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="codeB" id="codeB"></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="codeF">Код функціональної класифікації:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="codeF" id="codeF"></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="nameExpend">Найменування статті видатків:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="nameExpend"
                                                 id="nameExpend"></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="expenditures">Бюджетні видатки (тис.грн.):</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="expenditures"
                                                 id="expenditures"></td>
                </tr>
                <tr>
                    <td class="text-left"><label>в тому числі:</label></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="expendG">Видатки Загального фонду:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="expendG" id="expendG">
                    </td>
                </tr>
                <tr>
                    <td class="text-left"><label for="consumG">=>видатки споживання:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="consumG" id="consumG">
                    </td>
                </tr>
                <tr>
                    <td class="text-left"><label for="develG">=>видатки розвитку:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="develG" id="develG"></td>
                </tr>
                <tr>
                    <td class="text-left"><label for="expendS">Видатки Спеціального фонду:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="expendS" id="expendS">
                    </td>
                </tr>
                <tr>
                    <td class="text-left"><label for="consumS">=>видатки споживання:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="consumS" id="consumS">
                    </td>
                </tr>
                <tr>
                    <td class="text-left"><label for="develS">=>видатки розвитку:</label></td>
                    <td class="text-left"><input type="text" style="font-weight: bold;" name="develS" id="develS"></td>
                </tr>
                <tr>
            </table>
            <p class="text-right mt-3">
                <input type="submit" style="font-weight: bold;" value="Додати" class="site-btn">
            </p>
        </form>
    </main>

</div>
<%@include file="footer.jsp" %>
</body>
</html>
