#Problems

####Virtual Machine disk issue 

If Ambari throws disk space error clear /tmp of hdfs with -skipTrash flag 
To add disk space to VM follow the steps 

Step1
```
yum -y install xfsprogs

mkdir /data
chmod 777 /data
mkfs -t xfs /dev/sdb
FSTAB=`cat /etc/fstab | grep "/data"`
if [ -z "$FSTAB" ]; then
 echo "/dev/sdb /data xfs     defaults        0 0" >> /etc/fstab
fi
mount /dev/sdb /data

```
/dev/sdc and you might want to mount it on /data2 for instance. you get the idea

Step2
```
In VM setting
```
1. new disk
2. vdi or vmdk 
3. dynamic
4. OK
5. Run the script
6. Log in to Ambari
7. Under Data node add ,/data/hadoop/hdfs/data
8. push config - i.e. restart
9. YARN
advanced
Under the two boxes with "/hadoop" add out new disk in there.
for the first one:
,/data/hadoop/yarn/local
second
,/data/hadoop/yarn/log
save and restart
proceed
10. TO check hdfs disk space usage:
hdfs dfs -du -s /*
hdfs dfs -du -h /user/folder
````
df -h to know the  disk space

##Hbase

If Hbase has issue with region server like not finding it or not able to restart 
try cleaning files from zookeeper by

```
1. sh /usr/hdp/2.3.2.0-2950//zookeeper/bin/zkCli.sh
2. rmr /hbase-unsecure

```
##Hbase  
To clean corrupt data from region server HBase

```
su - hdfs 
hdfs fsck /apps/hbase/data -files -locations | grep -v OK
hdfs fsck /apps/hbase/data -move -files -locations | grep -v OK
hdfs fsck /apps/hbase/data -delete -files -locations | grep -v OK

```

