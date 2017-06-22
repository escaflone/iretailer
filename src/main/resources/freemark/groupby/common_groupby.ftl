<#if siteid ??>
sid,
<#elseif sitezoneid ??>
szid,
</#if>

<#if location ??>
${location},
<#elseif site_type ??>
sitetype,
</#if>
_d