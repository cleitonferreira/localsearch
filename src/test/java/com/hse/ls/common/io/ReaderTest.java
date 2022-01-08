package com.hse.ls.common.io;

import com.hse.ls.TestUtils;
import com.hse.ls.common.entity.Problem;
import org.junit.Assert;
import org.junit.Test;


public class ReaderTest extends TestUtils {
    @Test
    public void readShouldReturnCorrectProblemEntity() {
        Problem p = Reader.read(getFile("instance1"));

        Assert.assertEquals(20, p.dim);
        Assert.assertEquals(p.distance.length, p.dim);
        Assert.assertEquals(p.flow.length, p.dim);
    }
}
