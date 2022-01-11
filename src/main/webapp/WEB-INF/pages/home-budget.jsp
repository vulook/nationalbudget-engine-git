<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Домівка</title>
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}static-resource/images/favicon-32x32.png">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}static-resource/css/styles.css">
</head>
<body>
<%@include file="header.jsp" %>
<main class="container">

    <div class="home-about">
        <div class="mb-2">
            <h2>Аналітична інформаційна система "ВИДАТКИ ДЕРЖАВНОГО
                БЮДЖЕТУ"</h2>
            <p>Дозволяє за лічені хвилини проаналізувати основні параметри
                видаткової частини Державного бюджету.</p>
        </div>


        <div class="panel mb-2">
            <p>
                <b>Для того, щоб розпочати роботу із системою:</b>
            </p>
            <ul>
                <li>у розділі <a href="/budget-download">«Завантажити
                    дані»</a> оберіть рік, за який необхідно провести аналіз;
                </li>
                <li>завантажте актуальну версію додатків до Закону України про
                    Державний бюджет за відповідний період.
                </li>
            </ul>
        </div>

        <div class="flex-row w-75 mb-4">
            <div class="w-50 pr-2">
                <p>виконав:</p>
                <h3 class="mb-0">
                    <b>Андрій Ковальчук</b>
                </h3>
            </div>
            <div class="w-50">
                <p>група:</p>
                <p>
                    <b>Lv-632.JavaCore</b>
                </p>
            </div>
        </div>

        <h3>У проекті реалізовано:</h3>

        <ul>
            <li>проект Maven в Eclipse із використанням архетипу
                maven-archetype-webapp;
            </li>
            <li>налаштування конфігураційних файлів POM.xml і web.xml;</li>
            <li>HTTP сервер Tomcat;</li>
            <li>управління базами даних mySQL;</li>
            <li>динамічні WEB-сторінки з використанням Servlet
                API-інтерфейсу, зокрема, серверні програми (controller) та JSP,
                також - Servlet ErrorHandler;
            </li>
            <li>завантаження файлів за допомогою JSP / Servlet;</li>
            <li>імпорт файлу XLS в базу даних MySQL;</li>
            <li>запити на мові SQL;</li>
            <li>взаємодію WEB-інтерфейсу з базою даних MySQL за допомогою
                JSP / Servlet.
            </li>
        </ul>
    </div>
    <h3></h3>

</main>

<%@include file="footer.jsp" %>
</body>
</html>