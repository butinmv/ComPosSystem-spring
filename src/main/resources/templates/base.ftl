<#macro page>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>ComPos | ${title} </title>

        <!-- Custom fonts for this template-->
        <link href="/static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Page level plugin CSS-->
        <link href="/static/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="/static/css/sb-admin.css" rel="stylesheet">
        <link rel="shortcut icon" type="image/png" href="/static/image/favicon.png">
    </head>

    <body id="page-top">

    <!-- NAVBAR -->
    <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

        <a class="navbar-brand mr-1" href="/main">ComPos</a>

        <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
            <i class="fas fa-bars"></i>
        </button>

        <!-- Navbar -->
        <ul class="navbar-nav ml-auto ml-md-0">
            <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-user-circle fa-fw"></i>
                </a>
                <div class="dropdown-menu dropdown-menu-left" aria-labelledby="userDropdown">
                    <a method="post" class="dropdown-item" href="/settings">Настройки компании</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Выйти</a>
                </div>

            </li>
        </ul>
        <div class="btn-group ml-auto mr-1" role="group">
            <button type="button" class="btn btn-outline-success btn-sm active">RU</button>
            <button type="button" class="btn btn-outline-success btn-sm">ENG</button>
        </div>
    </nav>

    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="sidebar navbar-nav">
            <li class="nav-item <#if title=="Статистика">active</#if>">
                <a class="nav-link" href="/main">
                    <i class="fas fa-chart-line"></i>
                    <span>Статистика</span>
                </a>
            </li>
            <li class="nav-item #if(title==" Персонал
            "){active}">
            <a class="nav-link" href="/staffs">
                <i class="fa fa-address-card"></i>
                <span>Персонал</span>
            </a>
            </li>
            <li class="nav-item #if(title==" Должности
            "){active}">
            <a class="nav-link" href="/positions">
                <i class="fa fa-users"></i>
                <span>Должности</span>
            </a>
            </li>
            <li class="nav-item #if(title==" Продукты
            "){active}">
            <a class="nav-link" href="/products">
                <i class="fa fa-shopping-cart"></i>
                <span>Продукты</span></a>
            </li>
            <li class="nav-item #if(title==" Категории
            "){active}">
            <a class="nav-link" href="/categories">
                <i class="fa fa-list-ol"></i>
                <span>Категории</span></a>
            </li>
            <li class="nav-item #if(title==" Чеки
            "){active}">
            <a class="nav-link" href="/checks">
                <i class="fa fa-credit-card"></i>
                <span>Чеки</span></a>
            </li>
            <li class="nav-item #if(title==" Счета
            "){active}">
            <a class="nav-link" href="/bills">
                <i class="fa fa-money"></i>
                <span>Счета</span></a>
            </li>
        </ul>

        <div id="content-wrapper">
            <div class="container-fluid">
                <#nested>
            </div>
            <!-- Sticky Footer -->
            <footer class="sticky-footer">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright © ComPos 2019</span>
                    </div>
                </div>
            </footer>
        </div>
        <!-- /#wrapper -->
    </body>
    </html>
</#macro>