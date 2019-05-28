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
                <a href="/products">Продукты</a>
            </li>
            <li class="breadcrumb-item active"><#if editing=true>Редактировать <#else> Добавить</#if> продукт
            </li>
        </ol>

        <!-- Page Content -->
        <h1><#if editing=true>Редактировать <#else> Добавить</#if> продукт</h1>
        <form method="post">
            <#if isMessage == -1>
                <p><font color="red">${message}</font></p>
            </#if>
            <#if isMessage == 1>
                <p><font color="green">${message}</font></p>
            </#if>
            <div class="form-group">
                <label for="name">Название</label>
                <input type="text" name="name" maxlength="30" minlength="2" class="form-control"
                       id="name" <#if editing=true> value=${product.name}</#if> required/>
            </div>
            <div class="form-group">
                <label for="barcode">Шрихкод</label>
                <input type="text" name="barcode" maxlength="13" minlength="13" class="form-control" id="barcode"
                        <#if editing=true> value=${product.barcode}</#if> required/>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-4">
                    <div class="form-group">
                        <label for="wholesalePrice"> <#if editing=true>Оптовая цена (обновляется при поставках) <#else> Оптовая цена (обновится при первой поставке) </#if> </label>
                        <input type="number" name="wholePrice" class="form-control"
                               id="wholesalePrice" readonly  <#if editing=true> value=${product.wholePrice} <#else> value="0.00" </#if> required/>
                        <label for="wholesalePrice">₽</label>
                    </div>
                </div>
                <div class="col-lg-1 col-md-1 col-sm-1">
                    <h1>+</h1>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3">
                    <div class="form-group">
                        <label for="markup">Наценка</label>
                        <input type="number" name="markup" min="0" max="1000" class="form-control" id="markup"
                                <#if editing=true> value=${product.markup}</#if> required/>
                        <label for="wholesalepPrice">%</label>
                    </div>
                </div>
                <div class="col-lg-1 col-md-1 col-sm-1">
                    <h1>=</h1>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3">
                    <div class="form-group">
                        <label for="retailPrice">Розничная цена</label>
                        <input type="number" name="retailPrice" class="form-control" id="retailPrice" readonly/>
                        <label for="wholesalepPrice">₽</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="category_fk">Категория</label>
                <select name="category" class="form-control" id="category" required>
                    <#list categories as category>
                        <option value=${category.name}
                                <#if editing=true>
                                <#if category.name == product.category> selected
                                </#if>
                                </#if>>
                            ${category.name}
                        </option>
                    </#list>
                </select>
            </div>
<#--            <div class="form-group">-->
<#--                <label for="measure_fk">Единица измерения</label>-->
<#--                <select name="measure_fk" class="form-control" id="measure_fk" required>-->
<#--                    #for(measure in measures) {-->
<#--                    <option value="#(measure.id)"-->
<#--                            #if(editing) {#if(measure.id== product.measure_fk) {selected}}>-->
<#--                        #(measure.name)-->
<#--                    </option>-->
<#--                    }-->
<#--                </select>-->
<#--            </div>-->
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" id="createProduct" class="btn btn-primary">
                <#if editing=true>Сохранить<#else> Добавить </#if>
            </button>
        </form>
    </div>
    <!-- /.container-fluid -->

</@base.page>
<@logoutModal.logoutModal />
<@commonjs.commonjs />

<script src="/static/js/calculateRetailPrice.js"></script>