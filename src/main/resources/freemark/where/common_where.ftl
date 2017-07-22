and records.fk_device_zone_id = sz_d.fk_device_zone_id
and sz_d.fk_site_zone_id = s_z.id
and s_z.fk_site_id = op.fk_site_id
and op.fk_site_id= s_z.fk_site_id
and records.date = op.date
and records.time >= op.start_time
and records.time < op.end_time
and site.id = s_z.fk_site_id
<#if location ??>
and site.fk_location_id = location_tmp.id
<#elseif site_type ??>
and site_tag.fk_site_id = s_z.fk_site_id
and site_tag.`type` =  '${site_type}'
</#if>
and records.date_time <= '${ed}'
and records.date_time >= '${st}'

<#if siteid ??>
and s_z.fk_site_id in (${siteid})
and s_z.type = 0
<#elseif sitezoneid ??>
and s_z.id in (${sitezoneid})
</#if>