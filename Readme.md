#Test Suite Documentation

##Objective
Building a test suite involving all the tools of Hadoop ecosystem. Till now the tools have been worked on are MapReduce, Storm, Hive, Hbase, Pig. 

##Data
The Data gathered consist of Airline database downloaded from Amazon’s public open datasets under [Transportation category](https://aws.amazon.com/datasets/transportation-databases/?tag=datasets%23keywords%23economics)

To Access a data set hosted as an Amazon EBS snapshot: Sign up for an AWS account, launch an Amazon EC2 instance, and create an Amazon EBS volume using the Snapshot ID listed in the catalog above. Or, see the Amazon EC2 Getting Started Guide. If you use Nvent credentials for logging into console you won’t find the snapshot it’s would be better if you loggin with personal credentials. Details of how to mount the [EBS volume](http://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-public-data-sets.html)

Size of the complete dataset is 15Gb. It contains Data and statistics from the US Department of Transportation on Aviation, Maritime, Highway, Transit, Rail, Pipeline, Bike/Pedestrian and other modes of transportation. Out of which we are using just the Airline data.  In aviation we using airline_ontime dataset which has size of 3.8Gb. The dataset is in Zipped file format from 1988 to 2008.  The number of  columns varies from 35 to 56.

##Flow of Data
![alt text](https://github.com/disawalsagar/TestSuite/blob/master/images/flow.png "Flow")


Raw data is in Zipped format which is converted to unzipped file format using Map Reduce.
The ouput from MR is feeded to Spark as well as Pig to do cleaning. Spark and Pig do the same job. 
External table 'airline'  created is created on the data derived from spark job.
The ouput of Pig is stored in HBase table 'airline'

##Virtual Machine

Oracle VM VirtualBox Manager
Hortonworks Sandbox with HDP 2.3.2
 
