package jingtum.deepchain.source;

/**
 * Created by nichunen on 16/8/9.
 */

import java.util.Arrays;
import java.util.Date;


public class BitcoinScriptPatternParser {

    /**
     * Get the payment destination from an scriptPubKey (output script of a transaction). This is based on standard scripts accepted by the Bitcoin network (https://en.bitcoin.it/wiki/Script).
     *
     * @param scriptPubKey output script of a transaction
     * @return a string in the following format in case of (1) a standard transfer to a Bitcoin address : "bitcoinaddress_ADRESS" where ADDRESS is the Bitcoin address, (2) an (obsolete) transfer to a public key: "bitcoinpupkey_PUBKEY" where PUBKEY is the public key, (3) in case of output that cannot be spent: "unspendable", (4) in case anyone can spend: "anyone", (5) in case of transaction puzzle: "puzzle_HASH256" where HASH256 is the puzzle (6) in all other cases null (different type of Bitcoin transaction)
     **/

    public static String getPaymentDestination(byte[] scriptPubKey) {
        if (scriptPubKey == null) return null;
        // test if anyone can spend output
        if (scriptPubKey.length == 0) return "anyone"; // need to check also ScriptSig for OP_TRUE
        // test if standard transaction to Bitcoin address
        if ((scriptPubKey.length == 25)) {
            // test start
            if (((scriptPubKey[0] & 0xFF) == 0x76) && ((scriptPubKey[1] & 0xFF) == 0xA9) && ((scriptPubKey[2] & 0xFF) == 0x14)) {
                // test end
                if (((scriptPubKey[23] & 0xFF) == 0x88) && ((scriptPubKey[24] & 0xFF) == 0xAC)) {
                    byte[] bitcoinAddress = Arrays.copyOfRange(scriptPubKey, 3, 23);
                    return "bitcoinaddress_" + BitcoinUtil.convertByteArrayToHexString(bitcoinAddress);
                }
            }
        }
        // test if obsolete transaction to public key
        if ((scriptPubKey.length > 0) && ((scriptPubKey[scriptPubKey.length - 1] & 0xFF) == 0xAC)) {
            byte[] publicKey = Arrays.copyOfRange(scriptPubKey, 0, scriptPubKey.length - 1);
            return "bitcoinpubkey_" + BitcoinUtil.convertByteArrayToHexString(publicKey);
        }
        // test if puzzle
        if ((scriptPubKey.length > 0) && ((scriptPubKey[0] & 0xFF) == 0xAA) && ((scriptPubKey[scriptPubKey.length - 1] & 0xFF) == 0x87)) {
            byte[] puzzle = Arrays.copyOfRange(scriptPubKey, 1, scriptPubKey.length - 2);
            return "puzzle_" + BitcoinUtil.convertByteArrayToHexString(puzzle);
        }
        // test if unspendable
        if ((scriptPubKey.length > 0) && ((scriptPubKey[0] & 0xFF) == 0x6a)) return "unspendable";
        return null;
    }

    /**
     * Converts a Bitcoin script in byte format to a (human) readable String. Not implemented.
     *
     * @param script script in a byte array
     * @return String with a human readable script or null in case of invalid/non-parseable script (e.g. unknown opcodes etc.)
     */
    public static String convertByteScriptToReadableString(byte[] script) {
        return "";
    }

}

