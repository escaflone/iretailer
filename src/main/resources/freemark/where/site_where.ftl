<#if siteid ??>
and s_z.fk_site_id in (${siteid})
<#elseif sitezoneid ??>
and s_z.id in (${sitezoneid})
</#if>