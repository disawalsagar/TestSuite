package Foo;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * Created by sagardisawal on 2/17/16.
 */
class ZipFileInputFormat
        extends FileInputFormat<Text, BytesWritable>
{
  
    private static boolean isLenient = false;

    /**
     * ZIP files are not splitable
     */
    @Override
    protected boolean isSplitable( JobContext context, Path filename )
    {
        return false;
    }

 
    @Override
    public RecordReader<Text, BytesWritable> createRecordReader( InputSplit split, TaskAttemptContext context )
            throws IOException, InterruptedException
    {
        return new ZipFileRecordReader();
    }

    
    public static void setLenient( boolean lenient )
    {
        isLenient = lenient;
    }

    public static boolean getLenient()
    {
        return isLenient;
    }
}
