package uk.co.automatictester.wiremock.maven.plugin;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ParametersTest {

    @Test(dataProvider = "params")
    public void testGetAllParams(Object dir, Object params, Object expected) {
        assertThat(Parameters.getAllParams((String) dir, (String) params), equalTo(expected));
    }

    @DataProvider(name = "params")
    public Object[][] testData() {
        return new Object[][]{
                {"target/classes", "--port=8081", new String[]{"--root-dir=target/classes", "--port=8081"}},
                {"target/classes", "--port=8081 -v", new String[]{"--root-dir=target/classes", "--port=8081", "-v"}},
                {"dir with spaces", "--port=8081", new String[]{"--root-dir=dir with spaces", "--port=8081"}},
                {"target/classes", null, new String[]{"--root-dir=target/classes"}}
        };
    }
}
