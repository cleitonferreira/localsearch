package com.hse.ls.common.io;

import com.hse.ls.common.entity.Solution;
import com.hse.ls.common.exceptions.DataProcessingException;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class Writer {

    public static void write(String fileName, Solution s) {
        try (FileOutputStream outputStream = new FileOutputStream(fileName);
             OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, Charset.defaultCharset());
             BufferedWriter writer = new BufferedWriter(streamWriter)) {
            writer.write(StringUtils.join(s.locations, ' '));
        } catch (IOException ex) {
            throw new DataProcessingException(ex);
        }
    }
}
