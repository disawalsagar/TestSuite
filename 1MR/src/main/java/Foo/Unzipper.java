package Foo;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by sagardisawal on 2/17/16.
 */

public class Unzipper {
    public static class Driver {

        public static void main(String[] args) throws Exception {

            // Check args.
            if (args.length != 2) {
                System.out.printf(
                        "Usage: Driver <input dir> <output dir>\n");
                System.exit(-1);
            }

            // Create new job.
            Job job = Job.getInstance();
            job.setJarByClass(Unzipper.class);
            job.setJobName("Unzip");

            //For recursively
            FileInputFormat.setInputDirRecursive(job, true);

            // Specify HDFS input and output paths.
            FileInputFormat.setInputPaths(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));

           // FileSystem.getLocal(conf).delete(outputDir, true);


           job.setInputFormatClass(ZipFileInputFormat.class);
           job.setOutputFormatClass(TextOutputFormat.class);

            // Specify mapper, reducer classes.
            job.setMapperClass(MyMapper.class);
            job.setNumReduceTasks(0);
            //We need reducer for testing 
          // job.setReducerClass(SumReducer.class);

            // Specify key value types.
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            // Submit job from client into cluster.
            boolean success = job.waitForCompletion(true);
            System.exit(success ? 0 : 1);
        }
    }

    public static class MyMapper
            extends Mapper<Text, BytesWritable, Text, IntWritable>
    {
       // private final static IntWritable one = new IntWritable( 1 );
        //private Text word = new Text();

        public void map(Text key, BytesWritable value, Context context )
                throws IOException, InterruptedException
        {
            // the filename is the *full* path within the ZIP file
            
            String filename = key.toString();
           // LOG.info( "map: " + filename );

            // We only want to process .csv files
            if ( filename.endsWith(".csv") == false )
              return;
            System.out.println("****filename " + filename);
            // Prepare the content
            String content = new String( value.getBytes(), "UTF-8" );
           // System.out.println("Content " + content);
           // content = content.replaceAll( "[^A-Za-z \n]", "" ).toLowerCase();

            // Tokenize the content
          /*  StringTokenizer tokenizer = new StringTokenizer( content );
            while ( tokenizer.hasMoreTokens() )
            {
                word.set( tokenizer.nextToken() );
                context.write( word, one );
            }*/
            context.write(new Text(content),null);
        }
    }

    public static class SumReducer
            extends Reducer<Text, NullWritable, Text, NullWritable> {

        public void reduce(Text key, NullWritable values, Context context)
                throws IOException, InterruptedException {

            // Add up values.
          /*  int count = 0;
            for (IntWritable value : values) {
                count += value.get();
            }*/
            context.write(key, null);
        }
    }
}
