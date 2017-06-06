<#if groupBy =  'All'>
records.date_time
<#elseif groupBy = '0' >
records.date
<#elseif groupBy = '5' >
records._5
<#elseif groupBy = '10' >
records._10
<#elseif groupBy = '15' >
records._15
<#elseif groupBy = '30' >
records._30
<#elseif groupBy = 'dh' >
records._dh
<#elseif groupBy = 'w' >
records._w
<#elseif groupBy = 'm' >
records._m
<#elseif groupBy = 'q' >
records._q
<#elseif groupBy = 'y' >
records._y
<#else>
records.date
</#if>