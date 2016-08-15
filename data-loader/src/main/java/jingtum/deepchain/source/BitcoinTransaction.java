package jingtum.deepchain.source;

/**
 * Created by nichunen on 16/7/26.
 */

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

import java.util.List;
import java.util.ArrayList;


public class BitcoinTransaction implements Writable {


    private int version;
    private byte[] inCounter;
    private byte[] outCounter;
    private List<BitcoinTransactionInput> listOfInputs;
    private List<BitcoinTransactionOutput> listOfOutputs;
    private int lockTime;

    public BitcoinTransaction() {
        this.version = 0;
        this.inCounter = new byte[0];
        this.outCounter = new byte[0];
        this.listOfInputs = new ArrayList<BitcoinTransactionInput>();
        this.listOfOutputs = new ArrayList<BitcoinTransactionOutput>();
        this.lockTime = 0;
    }

    public BitcoinTransaction(int version, byte[] inCounter, List<BitcoinTransactionInput> listOfInputs, byte[] outCounter, List<BitcoinTransactionOutput> listOfOutputs, int lockTime) {
        this.version = version;
        this.inCounter = inCounter;
        this.listOfInputs = listOfInputs;
        this.outCounter = outCounter;
        this.listOfOutputs = listOfOutputs;
        this.lockTime = lockTime;
    }

    public int getVersion() {
        return this.version;
    }

    public byte[] getInCounter() {
        return this.inCounter;
    }

    public List<BitcoinTransactionInput> getListOfInputs() {
        return this.listOfInputs;
    }

    public byte[] getOutCounter() {
        return this.outCounter;
    }

    public List<BitcoinTransactionOutput> getListOfOutputs() {
        return this.listOfOutputs;
    }

    public int getLockTime() {
        return this.lockTime;
    }

    public void set(BitcoinTransaction newTransaction) {
        this.version = newTransaction.getVersion();
        this.inCounter = newTransaction.getInCounter();
        this.listOfInputs = newTransaction.getListOfInputs();
        this.outCounter = newTransaction.getOutCounter();
        this.listOfOutputs = newTransaction.getListOfOutputs();
        this.lockTime = newTransaction.getLockTime();

    }

    /**
     * Writable
     **/

    public void write(DataOutput dataOutput) throws IOException {
        throw new UnsupportedOperationException("write unsupported");
    }

    public void readFields(DataInput dataInput) throws IOException {
        throw new UnsupportedOperationException("readFields unsupported");
    }

}
