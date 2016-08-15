package jingtum.deepchain.source;

/**
 * Created by nichunen on 16/8/9.
 */

import exception.ConfigurationException;
import exception.BitcoinBlockReadException;

import java.io.IOException;


import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.SplittableCompressionCodec;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobConfigurable;
import org.apache.hadoop.mapred.JobContext;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class BitcoinTransactionFileInputFormat extends FileInputFormat<BytesWritable, BitcoinTransaction> implements JobConfigurable {

    private static final Log LOG = LogFactory.getLog(BitcoinTransactionFileInputFormat.class.getName());
    private CompressionCodecFactory compressionCodecs = null;


    public RecordReader<BytesWritable, BitcoinTransaction> getRecordReader(InputSplit split, JobConf job, Reporter reporter) throws IOException {
        /** Create reader **/
        try {
            return new BitcoinTransactionRecordReader((FileSplit) split, job, reporter);
        } catch (ConfigurationException e) {
            // log
            LOG.error(e);
        } catch (BitcoinBlockReadException e) {
            // log
            LOG.error(e);
        }
        return null;
    }


    public void configure(JobConf conf) {
        compressionCodecs = new CompressionCodecFactory(conf);
    }

    /**
     * This method is experimental and derived from TextInputFormat. It is not necessary and not recommended to compress the blockchain files. Instead it is recommended to extract relevant data from the blockchain files once and store them in a format suitable for analytics (including compression), such as ORC or Parquet.
     */

    protected boolean isSplitable(FileSystem fs, Path file) {
        final CompressionCodec codec = compressionCodecs.getCodec(file);
        if (null == codec) {
            return true;
        }
        return codec instanceof SplittableCompressionCodec;

    }


}
