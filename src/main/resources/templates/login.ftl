
<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

    Страница входа пользователя
    <@l.login "/login"/>

    <a href="/registration"> Add new User </a>

</@c.page>