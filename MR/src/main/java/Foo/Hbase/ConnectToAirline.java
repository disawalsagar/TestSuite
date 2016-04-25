package Foo.Hbase;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.*;

import java.io.*;

@SuppressWarnings( "deprecation" )
public class ConnectToAirline {

    public static void main(String[] args) throws IOException {

        Configuration conf = new Configuration();
        changeConfigForRemote(conf);

        // Create table.
       // createTable(conf, "orders", "d1","d2");

        // Put.
        HConnection conn = HConnectionManager.createConnection(conf);
        String tableName = "orders";
        HTableInterface table = conn.getTable(tableName);
        put(table, "row1", "type", "d1", "Coffee");
        put(table, "row1", "type", "d1", "Coffee");
        put(table, "row1", "type", "d1", "Coffee");
        put(table, "row1", "type", "d1", "Coffee");
        put(table, "row1", "type", "d1", "Coffee");
        table.close();

        // Get.
        table = conn.getTable("orders");
        Get get = new Get(Bytes.toBytes("row1"));
        Result result = table.get(get);
        String columnValue = Bytes.toString(result.getValue(Bytes.toBytes("d1"), Bytes.toBytes("type")));
        System.out.println("columnValue = " + columnValue);
        table.close();

        // Scan.
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        for (Result scanResult : scanner) {
            String rowKey = Bytes.toString(scanResult.getRow());
            String value = Bytes.toString(scanResult.getValue(Bytes.toBytes("d1"), Bytes.toBytes("type")));
            System.out.println("rowKey = " + rowKey);
            System.out.println("value = " + value);
        }
        scanner.close();

        // Scan specific range.
        scan = new Scan();
        scan.setStartRow(Bytes.toBytes("row1"));
        scan.setStopRow(Bytes.toBytes("row10"));

        scan.addColumn(Bytes.toBytes("d1"), Bytes.toBytes("type"));
        scan.setCaching(500);
        scanner = table.getScanner(scan);
        for (Result scanResult : scanner) {
            String rowKey = Bytes.toString(scanResult.getRow());
            String value = Bytes.toString(scanResult.getValue(Bytes.toBytes("d1"), Bytes.toBytes("type")));
            System.out.println("rowKey = " + rowKey);
            System.out.println("value = " + value);
        }
        scanner.close();




        conn.close();

    }

    private static void put(HTableInterface table, String rowKey, String column, String columnFamily, String value) throws IOException {
        Put p = new Put(Bytes.toBytes(rowKey));
        p.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
        table.put(p);
    }

    private static void changeConfigForRemote(Configuration conf) {
        conf.set("hbase.master","172.16.71.128:16010");
        conf.set("hbase.root", "hdfs://172.16.71.128:8020");
        conf.set("hbase.zookeeper.quorum","172.16.71.128");
        conf.set("hbase.zookeeper.property.clientPort","2181");
        conf.set("zookeeper.znode.parent","/hbase-unsecure");
        conf.set("hbase.master.port","16000");
        conf.set("hbase.regionserver.port","16020");
    }

    private static void createTable(
            Configuration conf, String tableName, String... columnFamilies) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(conf);
        HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(tableName));
        for (String columnFamily : columnFamilies) {
            descriptor.addFamily(new HColumnDescriptor(Bytes.toBytes(columnFamily)));
        }
        if (!admin.tableExists(tableName))
            admin.createTable(descriptor);
        admin.close();
    }

    private static void deleteTable(Configuration conf, String tableName)
            throws IOException {
        HBaseAdmin admin = new HBaseAdmin(conf);
        admin.disableTable(tableName);
        Boolean isDisabled = admin.isTableDisabled(tableName);
        if (true == isDisabled) {
            admin.deleteTable(tableName);
        }
        else {
            throw new RuntimeException("Disable failed");
        }
        admin.close();
    }
}