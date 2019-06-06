<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>



<#--    <div>-->
<#--        <@l.logout />-->
<#--    </div>-->

<#--    <div>-->
<#--        <a href="/user">Список ролей</a>-->
<#--    </div>-->
    <div>

        <form method="post">

            <input type="text" name="brandAuto" placeholder="марка авто"/>
            <input type="text" name="pts" placeholder="птс"/>
            <input type="text" name="color" placeholder="цвет авто"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Добавить</button>

        </form>


        <form method="get" action="/filter">
            <input type="text" name="filter"/>

<#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
            <button type="submit">Найти по названию авто</button>

        </form>


        <form method="post" action="/filterColor">

            <input type="text" name="color"/>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Найти по цвету авто</button>

        </form>



        <form method="post" action="/date">

            <input type="date" name="date"/>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Найти по дате</button>

        </form>



    </div>

    <div> Список автомобилей по критерию поиска :</div>


    <div class="card-columns" id="car-list" >
    <#list cars as car>
        <div class="card my-3" data-id="${car.id}">
            <b>${car.id}</b>
            <span>${car.brandAuto}</span>
            <i>${car.pts}</i>
            <strong>${car.color}</strong>
            <strong>${car.ownerName}</strong>

        </div>
    <#else>
        No cars
    </#list>
    </div>

</@c.page>