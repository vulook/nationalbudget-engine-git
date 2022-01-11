<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        const headerEl = document.getElementById('headerYear');
        headerEl.innerText = 'за ' + localStorage.getItem('year') + ' рік';
    });
</script>
