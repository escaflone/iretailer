<#if siteid ?? || sitezoneid ?? >
sid,`name`
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