<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
    <head>
        <title>Test</title>
        <link href="resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/bootstrap-responsive.min.css" rel="stylesheet">
        <link href="resources/css/style.css" rel="stylesheet">
        </head>
    <body>
        <div class="container">
            <h2>Hello from Brand</h2>

            <h3><a href="<@spring.url '/categories'/>">Category</a></h3>
            <h3><a href="<@spring.url '/products'/>">Product</a><br></h3>
            <button type="button" onclick="loadDoc()">Request data</button>

            <p id="demo"></p>

            </div>
        <script src="resources/js/jquery-2.0.2.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/code.js"></script>
        <script>
            function loadDoc() {
          var xhttp = new XMLHttpRequest();
          xhttp.onreadystatechange = function() {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
              document.getElementById("demo").innerHTML = xhttp.responseText;
            }
          };
          xhttp.open("GET", "/ajaxget", true);
          xhttp.send();
            }
            </script>
        </body>
    </html>