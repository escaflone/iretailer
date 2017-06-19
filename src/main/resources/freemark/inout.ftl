select
<#if column.count_in ??>
sum(
case
when sz_d.reversed = 0 then records.count_in
when sz_d.reversed = 1 then records.count_out
else 0
end
) as count_in,
</#if>
<#if column.count_out ??>
sum(
case
when sz_d.reversed = 0 then records.count_out
when sz_d.reversed = 1 then records.count_in
else 0
end
) as count_out,
</#if>
<#include "site_column.ftl"/>
<#include "date_groupby.ftl"/>
from records_inout_partitioned records
, site_zone s_z, r_sitezone_devicezone sz_d, opening_time_tmp op
where records.fk_device_zone_id = sz_d.fk_device_zone_id
and sz_d.fk_site_zone_id = s_z.id
and s_z.fk_site_id = op.fk_site_id
and op.fk_site_id= s_z.fk_site_id
and records.date = op.date
and records.time >= op.start_time
and records.time < op.end_time
<#--and records.date_time <= '${ed}'
and records.date_time >= '${st}'-->
and s_z.type = 0
<#include "site_where.ftl"/>
group by
<#include "site_column.ftl"/>
_d
<#include "limit.ftl"/>
