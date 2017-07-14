<#if siteid ??>
s_z.fk_site_id sid,
site.display_name `name`
<#elseif sitezoneid ??>
s_z.id sid,
s_z.display_name `name`
</#if>