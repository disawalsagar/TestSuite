#Pig

Similar to Spark job, the files are unzipped, next task is to clean the data. The number of columns varies from 35 to 56. Most of the columns are blank. There are two columns which needs to be modified "OriginCityName","DestCityName".Example "Dallas, Tx" should be converted to Dallas. Remove all the quotes from all the fields. Filtering the first column which are nothing but columns names.

With Pig it is very easy to clean than spark 

Pig Script

```
one = load 'out' using PigStorage(',');
zero = FOREACH one GENERATE $0..;
two = FILTER zero BY $0 !='("Year"';
B = FOREACH two GENERATE COUNT(TOBAG(*)),$0..;
C = FILTER B BY (int)$0>0;
D = FOREACH C GENERATE CONCAT($6,CONCAT($8,$18)),$1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$15,$16,$17,$18,$19,$20,$22..$56;
STORE D INTO '/user/root/Out_pig' using PigStorage(',');
```



