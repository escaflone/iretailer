select
<#if column.count_sales ??>
sum(
count_sales
) as count_sales,
</#if>
<#if column.count_goods ??>
sum(
count_goods
) as count_goods,
</#if>
<#if column.count_trades ??>
sum(
count_trades
) as count_trades,
</#if>
<#include "site_column.ftl"/>
<#include "date_groupby.ftl"/>
from
records_sales_partitioned records,
site_zone s_z, r_sitezone_devicezone sz_d, opening_time_tmp op
where  1=1
and records.fk_device_zone_id = sz_d.fk_device_zone_id
and sz_d.fk_site_zone_id = s_z.id
and s_z.fk_site_id = op.fk_site_id
and op.fk_site_id= s_z.fk_site_id
and records.date = op.date
and records.time >= op.start_time
and records.time <op.end_time
and records.date_time <= '${ed}'
and records.date_time >= '${st}'
<#-- and s_z.type = 0-->
<#include "site_where.ftl"/>
group by
<#include "site_column.ftl"/>
<#include "date_groupby.ftl"/>
<#include "limit.ftl"/>