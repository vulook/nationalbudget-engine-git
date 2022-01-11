<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Завантажити файл</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}static-resource/images/favicon-32x32.png">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}static-resource/css/styles.css">
</head>
<body>
<%@include file="header.jsp" %>

<main class="container">
    <div class="panel">
        <h2 class="mb-2">Завантажити XLS-файл із даними Державного бюджету</h2>

        <form method="POST" action="${pageContext.request.contextPath}/budget-download">

            <!--surround the select box with a "custom-select" DIV element. Remember to set the width:-->

            <div class="flex-row" style="width: 420px;">
                <div class="custom-select" style="width: 200px;">
                    <select name="selectYear" id="selectYear">
                        <option value="0">Оберіть рік:</option>

                        <option value="2021">2021</option>

                    </select>
                </div>

                <script>
                    var x, i, j, selElmnt, a, b, c;
                    /*look for any elements with the class "custom-select":*/
                    x = document.getElementsByClassName("custom-select");
                    for (i = 0; i < x.length; i++) {
                        selElmnt = x[i].getElementsByTagName("select")[0];
                        /*for each element, create a new DIV that will act as the selected item:*/
                        a = document.createElement("DIV");
                        a.setAttribute("class", "select-selected");
                        a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
                        x[i].appendChild(a);
                        /*for each element, create a new DIV that will contain the option list:*/
                        b = document.createElement("DIV");
                        b.setAttribute("class", "select-items select-hide");
                        for (j = 0; j < selElmnt.length; j++) {
                            /*for each option in the original select element,
                            create a new DIV that will act as an option item:*/
                            c = document.createElement("DIV");
                            c.innerHTML = selElmnt.options[j].innerHTML;
                            c
                                .addEventListener(
                                    "click",
                                    function (e) {
                                        /*when an item is clicked, update the original select box,
                                        and the selected item:*/
                                        var y, i, k, s, h;
                                        s = this.parentNode.parentNode
                                            .getElementsByTagName("select")[0];
                                        h = this.parentNode.previousSibling;
                                        for (i = 0; i < s.length; i++) {
                                            if (s.options[i].innerHTML === this.innerHTML) {
                                                s.selectedIndex = i;
                                                h.innerHTML = this.innerHTML;
                                                y = this.parentNode
                                                    .getElementsByClassName("same-as-selected");
                                                for (k = 0; k < y.length; k++) {
                                                    y[k]
                                                        .removeAttribute("class");
                                                }
                                                this.setAttribute("class",
                                                    "same-as-selected");
                                                break;
                                            }
                                        }
                                        h.click();
                                    });
                            b.appendChild(c);
                        }
                        x[i].appendChild(b);
                        a.addEventListener("click", function (e) {
                            /*when the select box is clicked, close any other select boxes,
                            and open/close the current select box:*/
                            e.stopPropagation();
                            closeAllSelect(this);
                            this.nextSibling.classList.toggle("select-hide");
                            this.classList.toggle("select-arrow-active");
                        });
                    }

                    function closeAllSelect(elmnt) {
                        /*a function that will close all select boxes in the document,
                        except the current select box:*/
                        var x, y, i, arrNo = [];
                        x = document.getElementsByClassName("select-items");
                        y = document.getElementsByClassName("select-selected");
                        for (i = 0; i < y.length; i++) {
                            if (elmnt === y[i]) {
                                arrNo.push(i)
                            } else {
                                y[i].classList.remove("select-arrow-active");
                            }
                        }
                        for (i = 0; i < x.length; i++) {
                            if (arrNo.indexOf(i)) {
                                x[i].classList.add("select-hide");
                            }
                        }
                    }

                    /*if the user clicks anywhere outside the select box,
                     then close all select boxes:*/
                    document.addEventListener("click", closeAllSelect);
                </script>

                <p>
                    <input type="submit" value="Завантажити" class="site-btn mb-0"/>
                </p>

            </div>
        </form>
    </div>

</main>
</body>
</html>