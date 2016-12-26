package com.github.gotos.rpgreader.tests;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.github.gotos.rpgreader.LcfHelper;

/**
 * Tests conversions
 * 
 * @author alina
 *
 */
public class TestConversion {

    /**
     * Tests conversion to varint
     */
    @Test
    public void intToVarint() {

        assertArrayEquals(new byte[] {0}, LcfHelper.intToVarint(0));
        assertArrayEquals(new byte[] {1}, LcfHelper.intToVarint(1));
        assertArrayEquals(new byte[] {127}, LcfHelper.intToVarint(127));
        assertArrayEquals(new byte[] {-127, 0}, LcfHelper.intToVarint(128));
        assertArrayEquals(new byte[] {-126, 0}, LcfHelper.intToVarint(256));
        assertArrayEquals(new byte[] {-124, 0}, LcfHelper.intToVarint(512));
        assertArrayEquals(new byte[] {-127, -128, 0}, LcfHelper.intToVarint(1024 * 16));
        assertArrayEquals(new byte[] {-127, -128, -128, 0}, LcfHelper.intToVarint(1024 * 1024 * 2));
        assertArrayEquals(new byte[] {-127, -128, -128, -128, 0}, LcfHelper.intToVarint(1024 * 1024 * 256));
        assertArrayEquals(new byte[] {-121, -1, -1, -1, 127}, LcfHelper.intToVarint(Integer.MAX_VALUE));

        /*assertArrayEquals(new byte[] {0}, DataReader.intToRPGint(0));
        assertArrayEquals(new byte[] {1}, DataReader.intToRPGint(1));
        assertArrayEquals(new byte[] {127}, DataReader.intToRPGint(127));
        assertArrayEquals(new byte[] {-127, 0}, DataReader.intToRPGint(128));
        assertArrayEquals(new byte[] {-126, 0}, DataReader.intToRPGint(256));
        assertArrayEquals(new byte[] {-124, 0}, DataReader.intToRPGint(512));
        assertArrayEquals(new byte[] {-127, -128, 0}, DataReader.intToRPGint(1024 * 16));
        assertArrayEquals(new byte[] {-127, -128, -128, 0}, DataReader.intToRPGint(1024 * 1024 * 2));
        assertArrayEquals(new byte[] {-127, -128, -128, -128, 0}, DataReader.intToRPGint(1024 * 1024 * 256));
        assertArrayEquals(new byte[] {-121, -1, -1, -1, 127}, DataReader.intToRPGint(Integer.MAX_VALUE));
        assertArrayEquals(new byte[] {-120, -128, -128, -128, 0}, DataReader.intToRPGint(Integer.MAX_VALUE + 1));*/
    }

}
