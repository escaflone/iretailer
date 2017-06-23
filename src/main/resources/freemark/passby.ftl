select
sum(
count_passby
) as count_passby,
<#include "column/location_sitetype_column.ftl"/>
<#include "column/site_column.ftl"/>
<#include "column/date_column.ftl"/>
from
records_passby_partitioned records,
<#include "from/from.ftl"/>

where 1=1

<#include "where/common_where.ftl"/>
and s_z.type = 2
group by

<#include "groupby/common_groupby.ftl"/>