<#--基本列-->
count_in,count_out,count_passby,count_sales,count_goods,count_trades,
<#--location 或 site type-->
<#if location ??>
case when t1.${location} is not null then t1.${location}
when t2.${location} is not null then t2.${location}
when t3.${location} is not null then t3.${location}
else -99
end as ${location}
,
<#elseif site_type ??>
case when t1.sitetype is not null then t1.sitetype
when t2.sitetype is not null then t2.sitetype
when t3.sitetype is not null then t3.sitetype
else -99
end as sitetype
,
</#if>
<#--sid szid-->
case when t1.sid is not null then t1.sid
when t2.sid is not null then t2.sid
when t3.sid is not null then t3.sid
else -99
end as sid
,
<#--时间-->
case when t1._d is not null then t1._d
when t2._d is not null then t2._d
when t3._d is not null then t3._d
else -99
end as _d