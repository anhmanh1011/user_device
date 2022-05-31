package com.kss.userdevicemanagement.utils;

import lombok.experimental.UtilityClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

@UtilityClass
public class ConvertUtils {
    public byte[] convertTobyte(List list) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        /**
         * An ObjectOutputStream writes primitive data types and
         * graphs of Java objects to an OutputStream. The objects can be
         * read (reconstituted) using anObjectInputStream. Persistent storage
         * of objects can be accomplished by using a file for the stream.
         * reference: Java Docs
         */
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
