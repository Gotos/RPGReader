package com.github.gotos.rpgreader;

/**
 * This class provides helper methodes used throughout the rpgreader
 * 
 * @author alina
 */
public final class LcfHelper {
    
    private LcfHelper() { }
    
    /**
     * Returns the given integer as a byte-array varint
     * 
     * @param num the integer which should be converted to a varint
     * @return the given int as an byte-array varint
     */
    public static byte[] intToVarint(int num) {
        if (num == 0) {
            return new byte[] {0};
        }
        if (num < 0) {
            throw new IllegalArgumentException("Negative numbers can not be converted to varint");
        }
        
        int bytes = (int) Math.ceil(Math.floor(((Math.log(num) / Math.log(2))) + 1) / 7);
        byte[] out = new byte[bytes];
        
        int i = bytes - 1;
        while (num != 0) {
            out[i--] = (byte) ((num & 0x7F) | 0xFFFFFF80);
            num >>>= 7;
        }
        
        out[bytes - 1] = (byte) (out[bytes - 1] & 0x7F);
        
        return out;
    }
}
