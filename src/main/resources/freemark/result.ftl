select
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

<#include "timelineDate.ftl"/>

from timeline
<#if column.count_in ?? || column.count_out ?? >
left join (
    <#include "inout.ftl"/>
) `inout` on `inout`._d = <#include "timelineDate.ftl"/>
</#if>
<#if column.count_passby ??>
left join (
    <#include "passby.ftl"/>
) `passby` on `passby`._d = <#include "timelineDate.ftl"/>
</#if>
<#if column.count_sales ?? || column.count_goods ?? || column.count_trades ??>
left join (
    <#include "sale.ftl"/>
) `sale` on `sale`._d = <#include "timelineDate.ftl"/>
</#if>
where timeline.date_time <= '${ed}'
and timeline.date_time >= '${st}'
and timeline.type = '${groupBy}'
