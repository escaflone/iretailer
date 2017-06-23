select
<#--<#if column.count_in ??>-->
sum(
case
when sz_d.reversed = 0 then records.count_in
when sz_d.reversed = 1 then records.count_out
else 0
end
) as count_in,
<#--</#if>-->
<#--<#if column.count_out ??>-->
sum(
case
when sz_d.reversed = 0 then records.count_out
when sz_d.reversed = 1 then records.count_in
else 0
end
) as count_out,
<#--</#if>-->
<#include "column/location_sitetype_column.ftl"/>
<#include "column/site_column.ftl"/>
<#include "column/date_column.ftl"/>
from records_inout_partitioned records,
<#include "from/from.ftl"/>

where 1=1

<#include "where/common_where.ftl"/>
and s_z.type = 0
group by

<#include "groupby/common_groupby.ftl"/>