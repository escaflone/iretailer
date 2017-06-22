<#--变量申明区域 start -->
<#if column.count_in ?? || column.count_out ??>
    <#assign v_table = "`inout`">
<#elseif column.count_passby ??>
    <#assign v_table = "`passby`">
<#elseif column.count_sales ?? || column.count_goods ?? || column.count_trades ??>
    <#assign v_table = "`sale`">
</#if>
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
    ${v_table}.${v_id},
<#--group by 用的 site id , site zone id ，业态，location 获取 end-->
<#--时间分辨, 到时分秒 ，还是到天-->
<#if groupBy != 'ALL'>
    <#include "timelineDate.ftl"/>
</#if>

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
<#--location 或 site type 列是否需要 start-->
<#if location ??>
    inner join site on site.id =
    <#if (column.count_in ?? || column.count_out ??)>
        `inout`.fk_site_id
    <#elseif column.count_passby ??>
        `passby`.fk_site_id
    <#elseif (column.count_sales ?? || column.count_goods ?? || column.count_trades ??)>
        `sale`.fk_site_id
    </#if>
    inner join location_tmp on site.fk_location_id = location_tmp.id
<#elseif site_type ??>
    inner join site_tag on site_tag.fk_site_id =
    <#if (column.count_in ?? || column.count_out ??)>
    `inout`.fk_site_id
    <#elseif column.count_passby ??>
    `passby`.fk_site_id
    <#elseif (column.count_sales ?? || column.count_goods ?? || column.count_trades ??)>
    `sale`.fk_site_id
    </#if>
</#if>
<#--location 或 site type 列是否需要 end-->
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
<#if site_type ??>
and site_tag.`type` = '${site_type}'
</#if>
<#--site 或 site zone 表关联 end-->
where timeline.date_time <= '${ed}'
and timeline.date_time >= '${st}'
and timeline.type = '${groupBy}'
group by
${v_id},
<#if groupBy != 'ALL'>
    <#include "timelineDate.ftl"/>
</#if>

<#include "limit.ftl"/>