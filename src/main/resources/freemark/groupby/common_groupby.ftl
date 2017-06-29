<#if siteid ?? || sitezoneid ?? >
sid
</#if>

<#if location ??>
,${location}
<#elseif site_type ??>
,sitetype
</#if>
<#if groupBy =  'All'>
<#else >
,_d
</#if>