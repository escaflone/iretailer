select
sum(
case
when sz_d.reversed = 0 then records.count_in
when sz_d.reversed = 1 then records.count_out
else 0
end
) as count_in,

sum(
case
when sz_d.reversed = 0 then records.count_out
when sz_d.reversed = 1 then records.count_in
else 0
end
) as count_out,

sum(
count_passby
) as count_passby,

sum(
count_sales
) as count_sales,

sum(
count_goods
) as count_goods,

sum(
count_trades
) as count_trades,

<#include "column/location_sitetype_column.ftl"/>
<#include "column/site_column.ftl"/>
<#include "column/date_column.ftl"/>
from records_partitioned records,
<#include "from/from.ftl"/>

where 1=1

<#include "where/common_where.ftl"/>


group by

<#include "groupby/common_groupby.ftl"/>