select sum(count_in),sum(count_out),sum(count_passby),sum(count_sales),sum(count_goods),sum(count_trades),
<#if location ??>
${location},
<#elseif site_type ??>
sitetype,
</#if>
sid,_d
from (
select <#include "all_columns.ftl"/> from (<#include "../inout.ftl"/>) t1
left join (<#include "../passby.ftl"/>) t2 on t1.sid = t2.sid and t1._d = t2._d
left join (<#include "../sale.ftl"/>) t3 on t2.sid = t3.sid and t3._d = t2._d

union

select <#include "all_columns.ftl"/> from (<#include "../inout.ftl"/>) t1
right join (<#include "../passby.ftl"/>) t2 on t1.sid = t2.sid and t1._d = t2._d
left join (<#include "../sale.ftl"/>) t3 on t2.sid = t3.sid and t3._d = t2._d

union

select <#include "all_columns.ftl"/> from (<#include "../inout.ftl"/>) t1
right join (<#include "../passby.ftl"/>) t2 on t1.sid = t2.sid and t1._d = t2._d
right join (<#include "../sale.ftl"/>) t3 on t2.sid = t3.sid and t3._d = t2._d
) as b
group by sid,_d