<#include "security.ftl">

<div class="card-columns" >
    <#list cars as car>
        <div class="card my-3 ">
<#--            <#if message.filename??>-->
<#--                <img src="/img/${message.filename}" class=" card-img-top">-->
<#--            </#if>-->
            <div class="m-2">
                <span>${car.brandAuto}</span><br/>
                <i>#${car.color}</i>
            </div>
<#--            <div class="card-footer text-muted">-->
<#--                <a href="/user-cars/${car.owned.id}">${car.ownerName}</a>-->
<#--                <#if message.author.id== currentUserId>-->
<#--                    <a class="btn btn-success" href="/user-messages/${message.author.id}?message=${message.id}">Edit</a>-->
<#--                </#if>-->
<#--            </div>-->
        </div>
    <#else>
        No cars
    </#list>
</div>