<#--变量申明区域 start -->
<#if location ??>
    <#assign v_id = "${location}">
<#elseif site_type ??>
    <#assign v_id = "sitetype">
<#elseif siteid ??>
    <#assign v_id = "sid">
<#elseif sitezoneid ??>
    <#assign v_id = "szid">
</#if>
<#--变量申明区域 end -->
select
<#-- 普通指标 start-->
<#if column.count_in ??>
count_in,
</#if>
<#if column.count_out ??>
count_out,
</#if>
<#if column.count_passby ??>
count_passby,
</#if>
<#if column.count_sales ??>
count_sales,
</#if>
<#if column.count_goods ??>
count_goods,
</#if>
<#if column.count_trades ??>
count_trades,
</#if>
<#--普通指标 end-->
<#--计算指标 start-->
<#if column.conversationRate ??>
(count_trades/count_in) conversationRate,
</#if>
<#if column.acv ??>
(count_sales/count_in) acv,
</#if>
<#if column.atv ??>
(count_sales/count_trades) atv,
</#if>
<#if column.upt ??>
(count_goods/count_trades) upt,
</#if>
<#if column.occupancy ??>
(count_in/count_out) occupancy,
</#if>
<#if column.uaa ??>
(count_in/area) uaa,
</#if>
<#--计算指标 end-->
<#--group by 用的 site id , site zone id ，业态，location 获取 start-->
`records`.${v_id},
<#--group by 用的 site id , site zone id ，业态，location 获取 end-->
<#--时间分辨, 到时分秒 ，还是到天-->
<#if groupBy != 'ALL'>
    <#include "column/timelineDate.ftl"/>
</#if>

from timeline
left join (
    <#include "records.ftl"/>
) `records` on `records`._d = <#include "column/timelineDate.ftl"/>

<#--site 或 site zone 表关联 end-->
where timeline.date_time <= '${ed}'
and timeline.date_time >= '${st}'
and timeline.type = '${groupBy}'
group by
${v_id},
<#if groupBy != 'ALL'>
    <#include "column/timelineDate.ftl"/>
</#if>

<#include "limit/limit.ftl"/>