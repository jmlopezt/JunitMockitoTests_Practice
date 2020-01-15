package Junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator;
    private static Calculator staticCalculator;

    @BeforeAll
    public static void beforeAllTests(){
        staticCalculator = new Calculator();
    }

    @BeforeEach
    public void setUp(){
        calculator = new Calculator();
    }
    @AfterEach
    public void tearDown(){
        calculator = null;
    }

    @Test
    public void calculatorNotNullTest(){
        assertNotNull(calculator,"Calculator must be Not Null");
    }

    @Test
    public void addIntAssertTest() {
        assertEquals(30,calculator.add(10,20));
    }

    @Test
    public void addVectorMembersTest(){
        int[] numbers = {1, 25, 2, 2};
        assertEquals(30,calculator.addVectorMembers(numbers));
    }

    @Test
    public void divTest(){
        assertEquals(50,calculator.div(100,2));
        assertEquals(50,calculator.div(100,2.05),2);
    }

    @Test
    public void divByZeroTest(){
        assertEquals(Double.POSITIVE_INFINITY,calculator.div(100,0));
    }

    @Test
    public void divIntByZeroTest(){
        assertThrows(ArithmeticException.class,()->calculator.divByZero(3,0),"No se puede dividir por cero");
    }


    @AfterAll
    public static void afterAllTests(){
        staticCalculator = null;
    }


    @Nested
    class DivTest{
        @Test
        public void divPositiveTest(){
            assertEquals(50,staticCalculator.div(100,2));
        }
        @Test
        public void divNegativeTest(){
            assertEquals(-50,staticCalculator.div(100,-2));
        }
        @Test
        public void divByZeroTest(){
            assertEquals(Double.POSITIVE_INFINITY,calculator.div(78,0));
        }
        @Test
        public void divInfTest(){
            assertEquals(0,calculator.div(78,Double.POSITIVE_INFINITY));
        }
    }


    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @MethodSource("addProviderData")
    public void addParameterizedTest(int a, int b, int sum){
        assertEquals(sum,calculator.add(a,b));
    }

    private static Stream<Arguments> addProviderData(){
        return Stream.of(
                Arguments.of(6,2,8),
                Arguments.of(-6,2,-4),
                Arguments.of(-6,-2,-8),
                Arguments.of(6,-2,4)
        );
    }
}