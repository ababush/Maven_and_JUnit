import com.endava.utils.UtilProprieties;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

// TODO: What is parameterized tests?
// TODO: Why Jarco isn't working?


public class UtilProprietiesTest {

    @Parameterized.Parameter
    public static UtilProprieties properties;

    @BeforeAll
    static void setupBeforeAll() {
        properties  = new UtilProprieties("C:\\Users\\ababus\\IdeaProjects\\HW_ModularityProject\\ModularityProject\\Utils\\target\\maven-archiver\\pom.properties");
    }

    void mockTestForIsNumberCalled() {
        UtilProprieties prop0 = mock(UtilProprieties.class);
        prop0.getNumericalProprieties();
        verify(prop0).isNumber("");
        verifyNoMoreInteractions(prop0);
    }

     void mockTestForIsNumberFromStringCalled(){
        UtilProprieties prop1 = mock(UtilProprieties.class);
        prop1.getStringProprieties();
        verify(prop1, times(5)).isNumber("");
         verifyNoMoreInteractions(prop1);
    }

     void mockTestForIsNumber() {
        UtilProprieties prop1 = mock(UtilProprieties.class);
        assertTrue(prop1.isNumber(Integer.toString(anyInt())));
        verifyNoMoreInteractions(prop1);
    }

    private final MocksCollector mocksCollector = new MocksCollector();

    @Test
    void mockTest() {
        try {
            mockTestForIsNumberCalled();
            mockTestForIsNumberFromStringCalled();
            mockTestForIsNumber();
            assertEquals( 3, mocksCollector.getMocks().length);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
    }

    @Parameterized.Parameter
    public static Map<String, Integer> expected = new HashMap<>();

    @Test
    public void testShouldReturnOnlyIntProprieties() {
        expected.put("intVal", 10);
        expected.put("password", 789);
        expected.put("intVal3", 4);
        assertEquals(expected, properties.getIntProprieties());
    }

    @Test
    @DisplayName("Test for getNumericalProprieties() method")
    void shouldReturnOnlyIntProprieties() {
        //Given

        Map<String, Integer> expected = new HashMap<>();
        expected.put("intVal", 10);
        expected.put("password", 789);
        expected.put("intVal3", 4);

        // When
        Map<String, Integer> myMap = properties.getIntProprieties();

        //Then
        // Test size with assertj
        assertThat(myMap).hasSize(expected.size());

    }

    @Test
    @DisplayName("Test for getNumericalProprieties() method")
    void shouldReturnOnlyNumericalProprieties() {
        //Given
        Map<String, Float> expected = new HashMap<>();
        expected.put("version", Float.parseFloat("1.0"));
        expected.put("floatVal", Float.parseFloat("0.12345"));

        // When
        Map<String, Float> myMap = properties.getNumericalProprieties();

        //Then
        // Test size with assertj
        assertThat(myMap).hasSize(expected.size());
        // Test equal, ignore order with hamcrest
        assertThat(myMap, is(expected));
    }

    @Test
    @DisplayName("Test for getStringProprieties() method")
    void shouldReturnOnlyStringProprieties() {
        //Given
        Map<String, String> expected = new HashMap<>();
        expected.put("groupId", "com.endava");
        expected.put("name", "MavenAplication");
        expected.put("host", "http://localhost:8888/mydb");
        expected.put("artifactId", "Utils");
        expected.put("login", "root");

        //When
        Map<String, String> myMap = properties.getStringProprieties();

        //Then
        // Test size with assertj
        assertThat(myMap).hasSize(expected.size());
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
