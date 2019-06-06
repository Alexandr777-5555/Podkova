<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

    СОЗДАНИЕ НОВОГО ПОЛЬЗОВАТЕЛЯ

    ${message?if_exists}

    <@l.login "/registration"/>

</@c.page>