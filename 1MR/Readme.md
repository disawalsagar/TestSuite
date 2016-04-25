#MapReduce


First task is to decompress the files into csv formats which would be used for cleaning and further analysis. In Map Reduce job there two most important terms. 
#####InputFormat
Opens the data source and splits the data into bytes of chunks.
#####RecordReader 
Parses the chunks into Key/Value pairs.

#####ZipFileInputFormat and ZipFileRecordReader
ZipFileInputFormat is thin wrapper around FileInputFormat to prevent it from splitting files – ZIP files aren’t a split-able file format. ZipFileRecordReader where it uses the Java built in ZipInputStream to parse out each ZipEntry.

 

