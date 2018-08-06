package uk.co.automatictester.wiremock.maven.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parameters {

    private static final String ROOT_DIR_PARAM_PREFIX = "--root-dir=";

    public static List<String> getAllParams(String dir, String nonDirParams) {
        List<String> dirParam = getDirParam(dir);
        List<String> otherParams = getNonDirParams(nonDirParams);
        List<String> allParams = new ArrayList<>();
        allParams.addAll(dirParam);
        allParams.addAll(otherParams);
        return allParams;
    }

    public static List<String> getDirParam(String dir) {
        List<String> dirParam = new ArrayList<>();
        dirParam.add(ROOT_DIR_PARAM_PREFIX + dir);
        return dirParam;
    }

    private static List<String> getNonDirParams(String nonDirParams) {
        return getParamsFrom(nonDirParams);
    }

    private static List<String> getParamsFrom(String paramString) {
        String[] params = paramString.split(" ");
        return Arrays.asList(params);
    }
}