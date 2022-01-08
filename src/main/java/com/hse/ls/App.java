package com.hse.ls;

import com.hse.ls.common.entity.LocalSearch;
import com.hse.ls.common.entity.LocalSearchContext;
import com.hse.ls.common.entity.Problem;
import com.hse.ls.common.entity.Solution;
import com.hse.ls.common.exceptions.DataProcessingException;
import com.hse.ls.common.io.Reader;
import com.hse.ls.common.io.Writer;
import com.hse.ls.guided.GuidedLocalSearch;
import com.hse.ls.iterated.IteratedLocalSearch;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class App {
    private static final String GLS = "gls";
    private static final String ILS = "ils";
    private static final String RLS = "rls";

    public static void main(String[] args) {

        String fileName = "instance1";
        String search = "gls";

        Problem p = Reader.read("instances/"+ fileName);
        LocalSearch localSearch;
        LocalSearchContext context;
        switch (search) {
            case GLS:
                context = new LocalSearchContext(p, 10000);
                localSearch = new GuidedLocalSearch(context);
                break;
            case ILS:
                context = new LocalSearchContext(p, 10000);
                localSearch = new IteratedLocalSearch(context);
                break;
            default:
                throw new DataProcessingException("Algoritmo desconhecido. Deve ser um dos valores a seguir: {gls, ils}.");
        }
        Solution solution = localSearch.solve();

        System.out.println(solution);

        Writer.write("results/"+fileName + ".sol", solution);
    }
}
