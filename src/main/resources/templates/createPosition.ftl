<#import "base.ftl" as base>
<#import "parts/commonjs.ftl" as commonjs>
<#import "parts/logoutModal.ftl" as logoutModal>
<@base.page>
    <div class="container-fluid">
        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="/">Статистика </a>
            </li>
            <li class="breadcrumb-item">
                <a href="/positions">Должности </a>
            </li>
            <li class="breadcrumb-item active"><#if editing=true>Редактировать <#else> Добавить</#if> должность</li>
        </ol>

        <!-- Page Content -->
        <h1>Добавить должность</h1>
        <form method="post">
            <div class="form-group">
                <#if isMessage == -1>
                <p><font color="red">${message}</font></p>
                </#if>
                <#if isMessage == 1>
                <p><font color="green">${message}</font></p>
                </#if>
                <label for="name">Имя</label>
                <input type="text" name="name" class="form-control" id="name" <#if editing=true>value=${position.name} </#if> required />
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </div>
            <button type="submit" class="btn btn-primary">
                <#if editing=true>Сохранить<#else> Добавить </#if>
            </button>
        </form>
    </div>
</@base.page>
<@logoutModal.logoutModal />
<@commonjs.commonjs />
