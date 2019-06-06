<#import "parts/common.ftl" as c>

<@c.page>


 Список автомобилей



<div >

 <form method="post">

  <input type="text" name="user_id" placeholder="id пользователя"/>
  <input type="text" name="brandAuto" placeholder="марка авто"/>
  <input type="text" name="pts" placeholder="птс"/>
  <input type="text" name="color" placeholder="цвет авто"/>
  <input type="hidden" name="_csrf" value="${_csrf.token}"/>
  <button type="submit">Добавить</button>

 </form>



</div>

</@c.page>