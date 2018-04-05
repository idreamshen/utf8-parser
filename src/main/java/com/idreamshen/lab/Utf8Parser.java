package com.idreamshen.lab;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utf8Parser {

    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream("/tmp/test");

        while (inputStream.available() != 0) {
            byte b = (byte) inputStream.read();

            if ((b >>> 7) == 0) {
                print(new byte[] {b});
                continue;
            }

            byte tmp = b;
            int num = 0;

            while ((byte)(tmp << 1) < 0) {
                num++;
                tmp = (byte)(tmp << 1);
            }

            byte[] bytes = new byte[num];
            inputStream.read(bytes);
            byte[] tmpBytes = new byte[num + 1];
            tmpBytes[0] = b;
            for (int i = 0; i < bytes.length; i++) {
                tmpBytes[i + 1] = bytes[i];
            }

            print(tmpBytes);

        }
    }

    private static void print(byte[] bytes) {
        String data = new String(bytes);
        List<String> binaryStringArray = new ArrayList<String>();
        for (byte b : bytes) {
            binaryStringArray.add(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }
        System.out.println(String.format("二进制：%s，值：%s", String.join(" ", binaryStringArray), data));
    }

}
