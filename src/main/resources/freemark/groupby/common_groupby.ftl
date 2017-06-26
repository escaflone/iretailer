<#if siteid ?? || sitezoneid ?? >
sid,
</#if>

<#if location ??>
${location},
<#elseif site_type ??>
sitetype,
</#if>
_d