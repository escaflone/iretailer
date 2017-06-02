select  distinct s_z.fk_site_id, s_z.id ,sz_d.fk_device_zone_id
from records_passby_partitioned re, site_zone s_z,r_sitezone_devicezone sz_d
where re.fk_device_zone_id = sz_d.fk_device_zone_id
and sz_d.fk_site_zone_id = s_z.id

order by s_z.fk_site_id, s_z.id ,sz_d.fk_device_zone_id

//查询有效的site id 同时在 passby 表里有值


select  distinct s_z.fk_site_id, s_z.id ,sz_d.fk_device_zone_id
from records_inout_partitioned re, site_zone s_z,r_sitezone_devicezone sz_d
where re.fk_device_zone_id = sz_d.fk_device_zone_id
and sz_d.fk_site_zone_id = s_z.id
order by s_z.fk_site_id, s_z.id ,sz_d.fk_device_zone_id

//查询有效的 site id 同时在 inout 表里有值


select
case
when DATE_FORMAT(STR_TO_DATE("2016-09-01 24:00:00","%Y-%m-%d %H:%i:%s"),"%H:%i:%s") BETWEEN TIME_FORMAT(start_time,"%H:%i:%s") and TIME_FORMAT(end_time,"%H:%i:%s") then 1
else 0
end
from opening_time_tmp where fk_site_id = 1
limit  10;

//实验 时间转换 和 case when
