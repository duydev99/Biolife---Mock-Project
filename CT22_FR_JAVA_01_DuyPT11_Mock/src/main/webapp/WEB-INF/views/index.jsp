<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Biolife - Organic Food</title>
    <link href="https://fonts.googleapis.com/css?family=Cairo:400,600,700&amp;display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:600&amp;display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400i,700i" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu&amp;display=swap" rel="stylesheet">
    <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.png"/>
    <link rel="stylesheet" type="text/css" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/animate.min.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/nice-select.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/slick.min.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/main-color.css">

</head>
<body class="biolife-body">

    <!-- Preloader -->
    <div id="biof-loading">
        <div class="biof-loading-center">
            <div class="biof-loading-center-absolute">
                <div class="dot dot-one"></div>
                <div class="dot dot-two"></div>
                <div class="dot dot-three"></div>
            </div>
        </div>
    </div>

    <!-- HEADER -->
    <header id="header" class="header-area style-01 layout-03">
        <jsp:include page="client/header.jsp" />
    </header>

	<!-- BODY -->
    <jsp:include page="client/${body}" />

    <!-- FOOTER -->
    <jsp:include page="client/footer.jsp" />
  

    <script src="/assets/js/jquery-3.4.1.min.js"></script>

    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/jquery.countdown.min.js"></script>
    <script src="/assets/js/jquery.nice-select.min.js"></script>
    <script src="/assets/js/jquery.nicescroll.min.js"></script>
    <script src="/assets/js/slick.min.js"></script>
    <script src="/assets/js/biolife.framework.js"></script>
    <script src="/assets/js/functions.js"></script>
    <script src="/js-form/product-client.js"></script>
</body>

</html>