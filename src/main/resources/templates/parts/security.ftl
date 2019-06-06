<#assign
know = Session.SPRING_SECURITY_CONTEXT??

>
<#--  если сессия существует -->
<#if know>

    <#assign

    <#--  user   это значение содержит пользователя в нашей БД-->
    user= Session.SPRING_SECURITY_CONTEXT.authentication.principal
    <#--    проверяем пользователя если админ -->
    name=user.getUsername()
    isAdmin = user.isAdmin()
    isOperator = user.isOperator()
    isUser=user.isUser()
    currentUserId = user.getId()

    >


<#--     иначе выполняем заглушку-->
<#else>
    <#assign
    name = "unkown"
    isAdmin = false
    isOperator= false
    isUser=false
    currentUserId = -1
    >

</#if>

