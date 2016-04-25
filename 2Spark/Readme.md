#Spark

After the files are unzipped, next task is to clean the data. The number of columns varies from 35 to 56. Most of the columns are blank. There are two columns which needs to be modified "OriginCityName","DestCityName".Example "Dallas, Tx" should be converted to Dallas. Remove all the quotes from all the fields. Filtering the first column which are nothing but columns names. 

Start Spark service from Ambari.
Enter into spark shell
```
$spark-shell
scala>
```

Function
```
scala> def cont(x : Array[String]): String = {
   var out = ""
   if(x.length == 56){
   out = x(10)+ x(5) + (System.currentTimeMillis/1000).toString  + ","+ x(0) + "," + x(1) + "," + x(2) + "," + x(3) + "," + x(4) + "," +
   x(5) + "," + x(6) + ","+ x(7) + "," + x(8) + "," + x(9) + "," + x(10) +
   "," + x(11) + "," + x(12) + "," + x(14) + "," + x(15) + "," + x(16) +
   "," + x(17) + "," + x(18) + "," + x(19) + "," + x(20) + "," +
   x(22) + "," + x(23) + "," + x(24) + "," + x(25) + "," + x(26) + "," + x(27) + "," + x(28) +
   "," + x(29) + "," + x(30) + "," +  x(31) + "," + x(32) + "," + x(33) + "," + x(34) + "," + x(35) + "," + x(36) +
    "," + x(37) + "," + x(38) + "," + x(39) + "," + x(40) + "," + x(41) + "," + x(42) + "," + x(43) +
   "," + x(45) + "," + x(46) + "," + x(47) + "," + x(48) + "," + x(49) + "," + x(50) + "," +
   x(51) + "," + x(52) + "," + x(53) + "," + x(54) + "," + x(55);
   }
   out
   }
```

The input file for spark is stores in /user/root/ in hdfs. 

Script
```
val one = sc.textFile("out").
   filter(x => !x.isEmpty).
   filter(x => ! x.startsWith("\"Year\"")).
   map(x=>x.split("[,\"]+")).
   filter(_.length==56).
   map(x=>cont(x)).
   saveAsTextFile("Out_spark");
```



