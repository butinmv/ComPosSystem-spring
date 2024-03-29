<#import "base.ftl" as base>
<#import "parts/commonjs.ftl" as commonjs>
<#import "parts/logoutModal.ftl" as logoutModal>
<@base.page>
    <div class="container-fluid">
        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/main">Статистика </a>
            </li>
            <li class="breadcrumb-item">
                <a href="/positions">Должности </a>
            </li>
            <li class="breadcrumb-item active"><#if editing=true>Редактировать <#else> Добавить</#if> должность</li>
        </ol>

        <!-- Page Content -->
        <h1><#if editing=true>Редактировать <#else> Добавить</#if> должность</h1>
        <form method="post">
            <#if isMessage == -1>
                <p><font color="red">${message}</font></p>
            </#if>
            <#if isMessage == 1>
                <p><font color="green">${message}</font></p>
            </#if>
            <div class="form-group">
                <label for="name">Название</label>
                <input type="text" name="name" class="form-control" id="name" <#if editing=true>value=${position.name} </#if> required />
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">
                <#if editing=true>Сохранить<#else> Добавить </#if>
            </button>
        </form>
    </div>
</@base.page>
<@logoutModal.logoutModal />
<@commonjs.commonjs />
