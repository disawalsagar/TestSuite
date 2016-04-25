#HBase

###Overview of Bulkloading. 
HBase includes several methods of loading data into tables. The most straightforward method is to either use the TableOutputFormat class from a MapReduce job, or use the normal client APIs; however, these are not always the most efficient methods.

The bulk load feature uses a MapReduce job to output table data in HBase’s internal data format, and then directly loads the generated StoreFiles into a running cluster. Using bulk load will use less CPU and network resources than simply using the HBase API. 


###HBase shell 
```
Sudo –u hbase hbase shell … If we start hbase with any other user we wont be able to create table. 
```
###Create table  
```
Create ‘airplane’, ’d’

```
###List the contents of the table
```
Scan ‘airline’
```

###Script to load data using bulkload
```
hbase org.apache.hadoop.hbase.mapreduce.ImportTsv -Dimporttsv.separator="," -Dimporttsv.columns=HBASE_ROW_KEY,  d:`id`,d:`year`,d:`quarter`,d:`month` ,d:`dayofmonth`,d:`dayofweek` ,d:`flightdate` ,d:`uniquecarrier`,d:`airlineid` ,d:`carrier` ,d:`tailnum` ,d:`flightnum` ,d:`origin`,d:`origincityname`, d:`originstate` ,d:`originstatefips` ,d:`originstatename` ,d:`originwac` ,d:`dest` ,d:`destcityname` ,d:`deststate` ,d:`deststatefips` ,d:`deststatename` ,d:`destwac` ,d:`crsdeptime` ,d:`deptime` ,d:`depdelay` ,d:`depdelayminutes`,d:`depdel15` ,d:`departuredelaygroups` ,d:`deptimeblk` ,d:`taxiout` ,d:`wheelsoff` ,d:`wheelson` ,d:`taxiin` ,d:`crsarrtime`,d:`arrtime` ,d:`arrdelay` ,d:`arrdelayminutes` ,d:`arrdel15` ,d:`arrivaldelaygroups` ,d:`arrtimeblk` ,d:`cancelled` ,d:`cancellationcode` ,d:`diverted` ,d:`crselapsedtime` ,d:`actualelapsedtime` ,d:`airtime` ,d:`flights` ,d:`distance` ,d:`distancegroup`,d:`carrierdelay` , d:`weatherdelay` , d:`nasdelay` , d:`securitydelay` ,d:`lateaircraftdelay` 
airline  /user/root/Out_Pig
```


