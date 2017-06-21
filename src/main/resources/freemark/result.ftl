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
<#--group by 用的 site id , site zone id 获取 start-->
<#if column.count_in ?? || column.count_out ??>
    <#if siteid ??>
    `inout`.fk_site_id site_id,
    <#elseif sitezoneid ??>
    `inout`.id site_zone_id,
    </#if>
<#elseif column.count_passby ??>
    <#if siteid ??>
    `passby`.fk_site_id site_id,
    <#elseif sitezoneid ??>
    `passby`.id site_zone_id,
    </#if>
<#elseif column.count_sales ?? || column.count_goods ?? || column.count_trades ??>
    <#if siteid ??>
    `sale`.fk_site_id site_id,
    <#elseif sitezoneid ??>
    `sale`.id site_zone_id,
    </#if>
</#if>
<#--group by 用的 site id , site zone id 获取 end-->
<#--时间分辨, 到时分秒 ，还是到天-->
<#include "timelineDate.ftl"/>

from timeline
<#--inout 是否需要-->
<#if column.count_in ?? || column.count_out ?? >
left join (
    <#include "inout.ftl"/>
) `inout` on `inout`._d = <#include "timelineDate.ftl"/>
</#if>
<#--passby 是否需要-->
<#if column.count_passby ??>
left join (
    <#include "passby.ftl"/>
) `passby` on `passby`._d = <#include "timelineDate.ftl"/>
</#if>
<#--sale 是否需要-->
<#if column.count_sales ?? || column.count_goods ?? || column.count_trades ??>
left join (
    <#include "sale.ftl"/>
) `sale` on `sale`._d = <#include "timelineDate.ftl"/>
</#if>
<#--site 或 site zone 表关联 start-->
<#if (column.count_in ?? || column.count_out ??) && column.count_passby ?? && (column.count_sales ?? || column.count_goods ?? || column.count_trades ??)>
    <#if siteid ??>
    and `inout`.fk_site_id = `passby`.fk_site_id and `passby`.fk_site_id = `sale`.fk_site_id
    <#elseif sitezoneid ??>
    and `inout`.id = `passby`.id and `passby`.id = `sale`.id
    </#if>
<#elseif (column.count_in ?? || column.count_out ??) && column.count_passby ??>
    <#if siteid ??>
    and `inout`.fk_site_id = `passby`.fk_site_id
    <#elseif sitezoneid ??>
    and `inout`.id = `passby`.id
    </#if>
<#elseif (column.count_in ?? || column.count_out ??) && (column.count_sales ?? || column.count_goods ?? || column.count_trades ??)>
    <#if siteid ??>
    and `inout`.fk_site_id = `sale`.fk_site_id
    <#elseif sitezoneid ??>
    and `inout`.id = `sale`.id
    </#if>
<#elseif column.count_passby ?? && (column.count_sales ?? || column.count_goods ?? || column.count_trades ??)>
    <#if siteid ??>
    and `passby`.fk_site_id = `sale`.fk_site_id
    <#elseif sitezoneid ??>
    and `passby`.id = `sale`.id
    </#if>
</#if>
<#--site 或 site zone 表关联 end-->
where timeline.date_time <= '${ed}'
and timeline.date_time >= '${st}'
and timeline.type = '${groupBy}'
group by
<#if siteid ??>
site_id,
<#elseif sitezoneid ??>
site_zone_id,
</#if>
<#include "timelineDate.ftl"/>

<#include "limit.ftl"/>