<#--变量申明区域 start -->
<#if location ??>
    <#assign v_id = "${location}">
<#elseif site_type ??>
    <#assign v_id = "sitetype">
<#elseif split != 0>
<#if siteid ??>
    <#assign v_id = "_s,`name`">
<#elseif sitezoneid ??>
    <#assign v_id = "_s,`name`">
</#if>
<#else>
    <#assign v_id = "'1'">
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
    ,timeline.`date`
from

(
<#list sidlist as s>
(
select t.<#include "column/timelineDate.ftl"/> `date`, ${s} as _s
from timeline t
where t.date_time <= '${ed}'
and t.date_time >= '${st}'
and t.type = '${groupBy}'
) union
</#list>
(select -1 as `date` ,-1 as _s from timeline limit 1)

) timeline
left join (
    <#include "records.ftl"/>
) `records` on (`records`._d =  timeline.`date` and `records`.sid = timeline._s)
where timeline.`date` != -1
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