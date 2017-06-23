select
<#--<#if column.count_sales ??>-->
sum(
count_sales
) as count_sales,
<#--</#if>-->
<#--<#if column.count_goods ??>-->
sum(
count_goods
) as count_goods,
<#--</#if>-->
<#--<#if column.count_trades ??>-->
sum(
count_trades
) as count_trades,
<#--</#if>-->
<#include "column/location_sitetype_column.ftl"/>
<#include "column/site_column.ftl"/>
<#include "column/date_column.ftl"/>
from
records_sales_partitioned records,
<#include "from/from.ftl"/>

where 1=1

<#include "where/common_where.ftl"/>
and s_z.type = 7
group by

<#include "groupby/common_groupby.ftl"/>