<#include "security.ftl">

<#import  "login.ftl" as l>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">ПОДКОВА</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>


            <#if isUser>
                <li class="nav-item">
                    <a class="nav-link" href="/identificator">Идентификатор</a>
                </li>


                <li class="nav-item">
                <a class="nav-link" href="/user-cars/${currentUserId}">Мои авто</a>
                </li>


            </#if>

            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">User List</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/main">Список автомобилей</a>
                </li>
            </#if>
            <#--            если пользователь оператор-->
            <#if isOperator>
                <li class="nav-item">
                    <a class="nav-link" href="/operator">ADD CAR</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="/main">Список автомобилей</a>
                </li>
            </#if>

        </ul>

        <div class="navbar-text mr-3"><#if user??>${name}<#else>Please, login</#if></div>
<#--        <div class="navbar-text  mr-3">${name}</div>-->
        <@l.logout/>
    </div>
</nav>