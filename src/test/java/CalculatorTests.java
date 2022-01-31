import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTests {
    Calculator calc;
    @BeforeEach
    public void start() {
        calc = new Calculator();
        System.out.println("Test started");
    }
    @AfterEach
    public  void finish(){
        System.out.println("Test finished");
    }
    @ParameterizedTest
    @MethodSource("methodForPlus")
    public void testPlus(int a, int b, int expected){
        int result = calc.plus.apply(a,b);
        Assertions.assertEquals(expected, result);

    }

    @ParameterizedTest
    @MethodSource("methodForMinus")
    public void testMinus(int a, int b, int expected){
        int result = calc.minus.apply(a,b);
        Assertions.assertEquals(expected, result);

    }
    @ParameterizedTest
    @MethodSource("methodForDivide")
    public void testDivide (int a, int b){

        assertDoesNotThrow(
                ()->calc.divide.apply(a, b));

    }
    @ParameterizedTest
    @MethodSource("methodForIsPositive")
    public void testIsPositive (int a){
        boolean result = calc.isPositive.test(a);
        assertTrue(result);
    }


    private  static Stream<Arguments> methodForPlus (){
        return Stream.of(Arguments.of(2,3,6), Arguments.of(6,5,11));
    }
    private  static Stream<Arguments> methodForMinus (){
        return Stream.of(Arguments.of(6,5,1), Arguments.of(10,5,5));
    }

    private  static Stream<Arguments> methodForDivide (){
        return Stream.of(Arguments.of(6,0), Arguments.of(10,1));
    }
    private  static Stream<Arguments> methodForIsPositive (){
        return Stream.of(Arguments.of(6), Arguments.of(-10));
    }

}
