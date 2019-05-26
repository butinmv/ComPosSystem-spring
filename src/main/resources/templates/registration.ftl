<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ComPos | Регистрация</title>

    <!-- Custom fonts for this template-->
    <link href="/static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="/static/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/static/css/sb-admin.css" rel="stylesheet">

    <link rel="shortcut icon" type="image/png" href="/static/image/favicon.png">
</head>

<body class="bg-dark">
<div class="container">
    <div class="card card-register mx-auto mt-5">
        <div class="card-header">Регистрация компании</div>
        <div class="card-body">
            <form action="/registration" method="post">
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="email" name="username" class="form-control" placeholder="Электронная почта"
                               id="email" required/>
                        <label for="email">Электронная почта</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" minlength="2" maxlength="30" name="nameCompany" class="form-control"
                               placeholder="Название компании" id="namecompany" required/>
                        <label for="namecompany">Название компании</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" pattern="\d*" maxlength="16" minlength="16" name="tin" class="form-control"
                               placeholder="ИНН компании" id="tin" required>
                        <label for="tin">ИНН компании</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-row">
                        <div class="col-md-6">
                            <div class="form-label-group">
                                <input type="password" name="password" id="password" class="form-control"
                                       placeholder="Пароль" required>
                                <label for="password">Пароль</label>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-label-group">
                                <input type="password" name="confirmPassword" id="confirmPassword" class="form-control"
                                       placeholder="Повторите пароль" required>
                                <label for="confirmPassword">Повторите пароль</label>
                            </div>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary btn-block">Зарегестрироваться</button>
                <!-- <a class="d-block small" href="forgot-password.html">Forgot Password?</a> -->
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="/login">Перейти на страницу входа</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="/static/vendor/jquery/jquery.min.js"></script>
<script src="/static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/static/vendor/jquery-easing/jquery.easing.min.js"></script>
</body>

</html>