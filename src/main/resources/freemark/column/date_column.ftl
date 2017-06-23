<#if groupBy =  'All'>
<#elseif groupBy = '0' >
records.date _d
<#elseif groupBy = '5' >
records._5 _d
<#elseif groupBy = '10' >
records._10 _d
<#elseif groupBy = '15' >
records._15 _d
<#elseif groupBy = '30' >
records._30 _d
<#elseif groupBy = 'dh' >
records._dh _d
<#elseif groupBy = 'w' >
records._w _d
<#elseif groupBy = 'm' >
records._m _d
<#elseif groupBy = 'q' >
records._q _d
<#elseif groupBy = 'y' >
records._y _d
<#else>
records.date _d
</#if>