<#macro layout h1>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Raspberry Akka Portal</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="/webjars/jquery/2.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container-fluid">
    <h1>${h1}</h1>
    <#nested>
</div>

</body>
</html>
</#macro>