#Hive
Once the data is clean. Hive external table is made over it. Advantage of external table is you can delete the table but data won't be deleted.

To start hive just type hive

Create table 

```
CREATE EXTERNAL TABLE `airline`(
 `id`  string,
   `year` int,
   `quarter` int,
   `month` int,
   `dayofmonth` int,
   `dayofweek` int,
   `flightdate` date,
   `uniquecarrier` string,
   `airlineid` string,
   `carrier` string,
   `tailnum` string,
   `flightnum` string,
   `origin` string,
   `origincityname` string,
   `originstate` string,
   `originstatefips` string,
   `originstatename` string,
   `originwac` string,
   `dest` string,
   `destcityname` string,
   `deststate` string,
   `deststatefips` string,
   `deststatename` string,
   `destwac` string,
   `crsdeptime` string,
   `deptime` string,
   `depdelay` string,
   `depdelayminutes` string,
   `depdel15` string,
   `departuredelaygroups` string,
   `deptimeblk` string,
   `taxiout` string,
   `wheelsoff` string,
   `wheelson` string,
   `taxiin` string,
   `crsarrtime` string,
   `arrtime` string,
   `arrdelay` string,
   `arrdelayminutes` string,
   `arrdel15` string,
   `arrivaldelaygroups` string,
   `arrtimeblk` string,
   `cancelled` string,
   `cancellationcode` string,
   `diverted` string,
   `crselapsedtime` string,
   `actualelapsedtime` string,
   `airtime` string,
   `flights` string,
   `distance` string,
   `distancegroup` string,
   `carrierdelay` string,
   `weatherdelay` string,
   `nasdelay` string,
   `securitydelay` string,
   `lateaircraftdelay` string)
 ROW FORMAT DELIMITED
   FIELDS TERMINATED BY ','
 STORED AS INPUTFORMAT
   'org.apache.hadoop.mapred.TextInputFormat'
 OUTPUTFORMAT
   'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat'
 LOCATION
   'hdfs://sandbox.hortonworks.com:8020/user/root/Out_spark'
```
Now it is free to run queries on it
