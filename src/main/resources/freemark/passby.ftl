select sum(
case
when re.time BETWEEN op.start_time and op.end_time then re.count_passby
else 0
end
) as cpass , date_time, s_z.fk_site_id
from records_passby_partitioned re, site_zone s_z, r_sitezone_devicezone sz_d, opening_time_tmp op
where re.fk_device_zone_id = sz_d.fk_device_zone_id
and sz_d.fk_site_zone_id = s_z.id
and s_z.fk_site_id in (38,37)
and re.date = op.date
group by re.date_time , s_z.fk_site_id
HAVING cpass > 0