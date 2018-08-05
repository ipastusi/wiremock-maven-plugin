package uk.co.automatictester.wiremock.maven.plugin;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ParametersTest {

    @Test(dataProvider = "allParams")
    public void testGetAllParams(Object dir, Object params, Object expectedParams) {
        String[] allParams = Parameters.getAllParams((String) dir, (String) params).toArray(new String[]{});
        assertThat(allParams, equalTo(expectedParams));
    }

    @Test(dataProvider = "dirParam")
    public void testGetDirParam(Object dir, Object expectedParams) {
        String[] allParams = Parameters.getDirParam((String) dir).toArray(new String[]{});
        assertThat(allParams, equalTo(expectedParams));
    }

    @DataProvider(name = "allParams")
    public Object[][] allParamsTestData() {
        return new Object[][]{
                {"target/classes", "--port=8081", new String[]{"--root-dir=target/classes", "--port=8081"}},
                {"target/classes", "--port=8081 --extensions=uk.co.automatictester.wiremock.maven.plugin.SampleResponseTransformer -v", new String[]{"--root-dir=target/classes", "--port=8081", "--extensions=uk.co.automatictester.wiremock.maven.plugin.SampleResponseTransformer", "-v"}},
                {"dir with spaces", "--port=8081", new String[]{"--root-dir=dir with spaces", "--port=8081"}}
        };
    }

    @DataProvider(name = "dirParam")
    public Object[][] dirParamTestData() {
        return new Object[][]{
                {"target/classes", new String[]{"--root-dir=target/classes"}},
                {"dir with spaces", new String[]{"--root-dir=dir with spaces"}}
        };
    }
}
