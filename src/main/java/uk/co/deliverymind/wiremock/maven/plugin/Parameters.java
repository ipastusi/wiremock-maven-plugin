package uk.co.deliverymind.wiremock.maven.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parameters {

    static String[] getAllParams(String dir, String params) {
        List<String> allParams = new ArrayList<>();
        allParams.add("--root-dir=" + dir);

        if (params != null) {
            List<String> paramsAsList = Arrays.asList(params.split(" "));
            allParams.addAll(paramsAsList);
        }

        int paramCount = allParams.size();
        String[] arraySize = new String[paramCount];
        String[] allParamsAsArray = allParams.toArray(arraySize);

        return allParamsAsArray;
    }
}
