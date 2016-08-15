package jingtum.deepchain.source;

import java.io.Serializable;

/**
 * Created by mac on 16/7/26.
 */
public class BitcoinTransactionOutput implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2854570630540937753L;

    private long value;
    private byte[] txOutScriptLength;
    private byte[] txOutScript;

    public BitcoinTransactionOutput(long value, byte[] txOutScriptLength, byte[] txOutScript) {
        this.value = value;
        this.txOutScriptLength = txOutScriptLength;
        this.txOutScript = txOutScript;
    }

    public long getValue() {
        return this.value;
    }

    public byte[] getTxOutScriptLength() {
        return this.txOutScriptLength;
    }

    public byte[] getTxOutScript() {
        return this.txOutScript;
    }

}
