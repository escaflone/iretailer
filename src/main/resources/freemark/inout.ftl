select
<#if column.count_in ??>
sum(
case
when sz_d.reversed = 0 then re.count_in
when sz_d.reversed = 1 then re.count_out
else 0
end
) as count_in,
</#if>
<#if column.count_out ??>
sum(
case
when sz_d.reversed = 0 then re.count_out
when sz_d.reversed = 1 then re.count_in
else 0
end
) as count_out,
</#if>
re.date, s_z.id
from records_inout_partitioned re
, site_zone s_z, r_sitezone_devicezone sz_d, opening_time_tmp op

where re.fk_device_zone_id = sz_d.fk_device_zone_id
and sz_d.fk_site_zone_id = s_z.id
and s_z.fk_site_id = op.fk_site_id
and op.fk_site_id= s_z.fk_site_id
and re.date = op.date
and re.time >= op.start_time
and re.time <op.end_time
and re.date_time <= '${ed}'
and re.date_time >= '${st}'
and s_z.type = 0
and s_z.fk_site_id in (${id})
group by re.date,s_z.id