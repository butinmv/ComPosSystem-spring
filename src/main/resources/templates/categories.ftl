<#import "base.ftl" as base>
<#import "parts/commonjs.ftl" as commonjs>
<#import "parts/logoutModal.ftl" as logoutModal>
<@base.page>
    <#if isMessage=-1>
        <!— Модальное окно —>
        <div class="modal fade show" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             style="display: block" aria-modal="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">Message</h4>
                    </div>
                    <div class="modal-body">
                        ${message}
                    </div>
                    <div class="modal-footer">
                        <button id="closeModal" type="button" class="btn btn-primary" data-dismiss="modal"
                                onclick="$('.modal').hide()">Close
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </#if>

    <div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/main">Статистика</a>
            </li>
            <li class="breadcrumb-item active">Категории</li>
        </ol>

        <!-- Page Content -->
        <div class="row">
            <div class="col-sm-6 col-lg-6 col-md-6">
                <h2>Категории</h2>
            </div>
            <div class="col-sm-6 col-lg-6 col-md-6 align-self-center">
                <a href="/category/create" role="button" class="btn btn-success btn-sm" id="createCategory"
                   style="float: right;"><i class="fa fa-plus"></i> Добавить категорию</a>
            </div>
        </div>
        <input type="text" id="searchInput" onkeyup="searchCategory()" placeholder="Поиск категории по названию..."
               class="form-control" style="margin-bottom: 20px; margin-top: 10px"/>
        <table class="table table-borderred table-hover" id="categoriesTable">
            <thread class="thread-light">
                <tr>
                    <th>Название</th>
                    <th>Дейсвие</th>
                </tr>
            </thread>
            <tbody>
            <#list categories as category>
            <tr>
                <td>${category.name}</td>
                <td>
                    <form method="post" action="/category/${category.id}/delete">
                        <a href="/category/${category.id}/edit" role="button"
                           class="btn btn-outline-info btn-sm">Ред.</a>&nbsp
                        <input class="btn btn-outline-danger btn-sm" type="submit" value="Удалить"/>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    </form>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@base.page>
<@logoutModal.logoutModal />
<@commonjs.commonjs />

<script src="/static/js/searchCategory.js"></script>
