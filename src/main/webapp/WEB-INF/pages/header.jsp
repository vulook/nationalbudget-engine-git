<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.format.FormatStyle" %>
<%@ page import="java.time.format.TextStyle" %>

<%!
    String getFormattedDate() {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(new Locale("uk")).format(LocalDate.now());
    }
%>

<header class="header">
    <div class="container">
        <div class="flex-row">
            <h3 class="mb-0">
                Аналіз видатків Державного бюджету <b id="headerYear"></b>
            </h3>
            <p class="text-right">
                Сьогодні:
                <%= getFormattedDate() %>
            </p>
        </div>
    </div>

    <div class="header_body">
        <div class="header_body_nav">
            <a href="/home-budget"><span class="icon icon-home"></span> <span>Домівка</span></a>
            <a href="/budget-download"><span class="icon icon-import"></span> <span>Завантажити дані</span></a>
            <a href="/budget-analis"><span class="icon icon-analytic"></span> <span>Огляд видатків</span></a>
            <a href="/budget-disposer"><span class="icon icon-list"></span> <span>Розпорядники</span></a>
            <a href="/budget-20Max"><span class="icon icon-max"></span> <span>20-ка видатків</span></a>
            <a href="/badge-distrib"><span class="icon icon-peoples"></span> <span>Розподіл</span></a>
            <a href="/budget-edit"><span class="icon icon-edit"></span> <span>Редагувати</span></a>
        </div>
    </div>
</header>
