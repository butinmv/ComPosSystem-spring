<#import "base.ftl" as base>
<#import "parts/commonjs.ftl" as commonjs>
<#import "parts/logoutModal.ftl" as logoutModal>
<@base.page>
    <!-- Breadcrumbs-->
    <ol class="breadcrumb">
        <li class="breadcrumb-item active">Статистика</li>
    </ol>

    <div class="btn-group" role="group">
        <button id="profit" type="button" class="btn btn-outline-primary active">
            <div id="profith5">Прибыль: 0$</div>
        </button>
        <button id="income" type="button" class="btn btn-outline-primary">
            <div id="incomeh5">Доход: 0$</div>
        </button>
        <button id="checks" type="button" class="btn btn-outline-primary">
            <div id="checksh5">Чеки: 0</div>
        </button>
    </div>

    <div class="btn-group" role="group" aria-label="Basic example" style="float: right;">
        <button id="7days" type="button" class="btn btn-outline-success active">7 дней</button>
        <button id="30days" type="button" class="btn btn-outline-success">30 дней</button>
    </div>

    <div class="card mb-3 mt-3">
        <div class="card-header">
            <i class="fas fa-chart-area"></i>
            Статистика продаж
        </div>
        <div id="chartCanvas" class="card-body" style="height: 300px">
            <canvas id="myAreaChart" width="100%" height="30"></canvas>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-6">
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-chart-bar"></i>
                    Количесвто чеков в каждый день недели
                </div>
                <div class="card-body">
                    <canvas id="checkAmountByDay" width="100%" height="30"></canvas>
                </div>
            </div>
        </div>

        <div class="col-lg-6">
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-chart-bar"></i>
                    Средний чек по дню недели
                </div>
                <div class="card-body">
                    <canvas id="averageCheckByDay" width="100%" height="30"></canvas>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa fa-fire"></i>
                    10 Самых продаваемый продуктов
                </div>
                <div class="card-body">
                    <div id="popularProducts">

                    </div>
                </div>
            </div>
        </div>
    </div>
</@base.page>
<@logoutModal.logoutModal />
<@commonjs.commonjs />

<!-- Page level plugin JavaScript-->
<script src="/static/vendor/chart.js/Chart.min.js"></script>

<!-- Demo scripts for this page-->
<script src="/static/js/charts/chart-area.js"></script>
<script src="/static/js/charts/chart-bar.js"></script>

<script src="/static/js/top10products.js"></script>

