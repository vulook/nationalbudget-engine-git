<%@page import="java.util.List" %>
<%@page import="com.softserve.entity.DownloadDetail" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${message}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/png" sizes="32x32"
              href="${pageContext.request.contextPath}static-resource/images/favicon-32x32.png">
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}static-resource/css/styles.css">
    </head>
</head>
<body>

<div class="wrapper">
    <%@include file="header.jsp" %>
    <main class="container">
        <div class="panel">

            <h2>${message}</h2>

            <table class="bordered_table">
                <thead>
                <tr align="center">
                    <th>Рік</th>
                    <th>Назва файлу</th>
                    <th>Розмір файлу</th>
                    <th>Статус завантаження</th>
                </tr>
                </thead>
                <tbody>

                <%
                    @SuppressWarnings("unchecked") List<DownloadDetail> listDetails = (List<DownloadDetail>) request.getAttribute("FILE_SAVE");
                    for (DownloadDetail listDetail : listDetails) {
                %>
                <tr>
                    <td align="center"><span id="yearDownload"><%=listDetail.getYearDownload() %></span></td>
                    <td align="center"><span id="fileName"><%=listDetail.getFileName() %></span></td>
                    <td align="center"><span id="fileSize"><%=listDetail.getFileSize() %> KB</span></td>
                    <td align="center"><span id="downloadStatus"><%=listDetail.getDownloadStatus() %></span>
                    </td>
                </tr>
                <%
                    }
				%>
                </tbody>
            </table>
        </div>
    </main>
</div>

<script>
    localStorage.setItem('year', <%=listDetails.get(0).getYearDownload() %>);
</script>

<%@include file="footer.jsp" %>

</body>
</html>