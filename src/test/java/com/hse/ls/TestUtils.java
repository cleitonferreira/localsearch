package com.hse.ls;

import com.hse.ls.common.entity.LocalSearch;
import com.hse.ls.common.entity.Problem;
import com.hse.ls.common.entity.Solution;
import com.hse.ls.common.io.Reader;
import org.junit.Assert;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class TestUtils {

    protected String getFile(String problemName) {
        return this.getClass().getClassLoader().getResource(problemName).getPath();
    }

    protected Solution testOnFile(LocalSearch ls) {
        Solution s = ls.solve();
        Set<Integer> set = IntStream.of(s.locations).boxed().collect(Collectors.toCollection(HashSet::new));
        Assert.assertEquals(set.size(), s.locations.length);

        System.out.println(s.toString());
        System.out.println("Custo: " + s.cost);

        return s;
    }
}
