<#import "base.ftl" as base>
<#import "parts/commonjs.ftl" as commonjs>
<#import "parts/logoutModal.ftl" as logoutModal>
<@base.page>
    <div class="container-fluid">
    <!-- Breadcrumbs-->
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <a href="/main">Статистика</a>
        </li>
        <li class="breadcrumb-item">
            <a href="/staffs">Персонал</a>
        </li>
        <li class="breadcrumb-item active"><#if editing=true>Редактировать <#else> Добавить</#if> работника
        </li>
    </ol>

    <!-- Page Content -->
    <h1><#if editing=true>Редактировать <#else> Добавить</#if> работника</h1>
    <form method="post">
        <#if isMessage == -1>
            <p><font color="red">${message}</font></p>
        </#if>
        <#if isMessage == 1>
            <p><font color="green">${message}</font></p>
        </#if>
        <div class="form-group">
            <label for="name">Имя</label>
            <input type="text" minlength="2" maxlength="30" name="name" class="form-control"
                   id="name" <#if editing=true> value=${staff.name}</#if> required/>
        </div>
        <div class="form-group">
            <label for="surname">Фамилия</label>
            <input type="text" minlength="2" maxlength="30" name="surname" class="form-control"
                   id="surname" <#if editing=true> value=${staff.surname}</#if> required/>
        </div>
        <div class="form-group">
            <label for="email">Электронная почта</label>
            <input type="email" name="email" class="form-control" id="email" <#if editing=true>
                   value=${staff.email}</#if> required/>
        </div>
        <div class="form-group">
            <label for="password">PIN</label>
            <input type="text" pattern="\d*" maxlength="4" minlength="4" name="password" class="form-control"
                   id="password" <#if editing=true> value=${staff.password}</#if> required/>
        </div>
        <div class="form-group">
            <label for="position_fk">Должность</label>
            <select name="position" class="form-control" id="position" required>
                <#list positions as position>
                    <option value=${position.name}
                    <#if editing=true>
                        <#if position.name == staff.position> selected
                        </#if>
                    </#if>>
                    ${position.name}
                    </option>
                </#list>
            </select>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" id="createProduct" class="btn btn-primary">
            <#if editing=true>Сохранить<#else> Добавить </#if>
        </button>
    </form>
    <!-- /.container-fluid -->
</@base.page>
<@logoutModal.logoutModal />
<@commonjs.commonjs />