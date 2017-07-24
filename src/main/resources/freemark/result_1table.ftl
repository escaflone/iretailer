<#--变量申明区域 start -->
<#if location ??>
    <#assign v_id = "${location}">
<#elseif site_type ??>
    <#assign v_id = "sitetype">
<#elseif split != 0>
    <#if groupBy != 'All'>
        <#assign v_id = "tl.sid,tl.`name`">
    <#else>
        <#assign v_id = "sid,`name`">
    </#if>
<#else>
    <#assign v_id = "'1'">
</#if>
<#if siteid ??>
    <#assign siteOrZoneTable = "site">
<#elseif sitezoneid ??>
    <#assign siteOrZoneTable = "site_zone">
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
<#if split = 1>
${v_id}
<#else>
${v_id}
</#if>

<#--时间分辨, 到时分秒 ，还是到天-->
<#if groupBy != 'All'>
    ,tl.`date`
from

(
<#list sidlist as s>

(
select t.<#include "column/timelineDate.ftl"/> `date`, ${s} as sid , ${siteOrZoneTable}.display_name as `name`
from timeline t,${siteOrZoneTable}
where t.date_time <= '${ed}'
and t.date_time >= '${st}'
and t.type = '${groupBy}'
and ${siteOrZoneTable}.id = ${s}
) union
</#list>
(select -1 as `date` ,-1 as sid  ,-1 as `name` from timeline limit 1)

) tl
left join (
    <#include "records.ftl"/>
) `records` on (`records`._d =  tl.`date` and `records`.sid = tl.sid)
where tl.`date` != -1
<#--site 或 site zone 表关联 end-->
<#--where-->
<#--timeline.date_time <= '${ed}'-->
<#--and timeline.date_time >= '${st}'-->
<#--and timeline.type = '${groupBy}'-->
group by
${v_id},
`date`
order by
`date`
<#else>
from (<#include "records.ftl"/>) `records`
group by
${v_id}
</#if>

<#include "limit/limit.ftl"/>