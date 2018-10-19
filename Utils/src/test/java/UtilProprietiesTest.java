import com.endava.utils.UtilProprieties;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UtilProprietiesTest {

    static UtilProprieties properties;

    @BeforeAll
    static void setupBeforeAll() {
        properties  = new UtilProprieties("C:\\Users\\ababus\\IdeaProjects\\HW_ModularityProject\\ModularityProject\\MainModule\\target\\maven-archiver\\pom.properties");
    }

    @Test
    @DisplayName("Test for getNumericalProprieties() method")
    void shouldReturnOnlyNumericalProprieties() {
        Map<String, Float> myMap = properties.getNumericalProprieties();

        Map<String, Float> expected = new HashMap<>();
        expected.put("version", Float.parseFloat("1.0"));
        expected.put("password", Float.parseFloat("789.0"));

        // Test size with assertj
        assertThat(myMap).hasSize(2);
        // Test equal, ignore order with hamcrest
        assertThat(myMap, is(expected));
    }

    @Test
    @DisplayName("Test for getStringProprieties() method")
    void shouldReturnOnlyStringProprieties() {
        Map<String, String> myMap = properties.getStringProprieties();

        Map<String, String> expected = new HashMap<>();
        expected.put("groupId", "com.endava");
        expected.put("name", "MavenAplication");
        expected.put("host", "http://localhost:8888/mydb");
        expected.put("artifactId", "MainModule");
        expected.put("login", "root");

        // Test size with assertj
        assertThat(myMap).hasSize(5);
        // Test equal, ignore order with hamcrest
        assertThat(myMap, is(expected));
    }

    @Test
    void shouldFail() {
        Assertions.assertThrows(NullPointerException.class,
                ()-> new UtilProprieties(""));

    }

    @AfterAll
    static void setupAfterAll() {
       properties = null;
       System.gc();
    }


}
