package dev.spring.p10walletapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SampleUnitTestClass {

    Calculator calculatorTest ;

    @BeforeEach
    void setup() {
        System.out.println("Inside before each");
        this.calculatorTest = new Calculator();
    }

    @AfterEach
    void afterEach() {
        System.out.println("Inside after each");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("---->>> Inside beforeAll");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("---->>> Inside afterAll");
    }

    @Nested
    class TestingAddMethod {
        @Test
        //@DisplayName(value = "test1")
        public void should_ReturnEquals_When_addTwoNumber(){
            // BDD --> Behaviour Driven Development
            // given
            int firstNumber = 10;
            int secondNumber = 20;
            int expected = 30;

            // when
            int actual = calculatorTest.add(firstNumber, secondNumber);

            // then
            assertEquals(expected, actual);
        }

        @Test
        //@RepeatedTest(10)
        public void should_ReturnNotEquals_When_addTwoNumber(){
            // given
            int firstNumber = 10;
            int secondNumber = 20;
            int expected = 40;

            // when
            int actual = calculatorTest.add(firstNumber, secondNumber);

            // then
            assertNotEquals(expected, actual);
        }
    }

    @Nested
    class TestingMultiplyMethod {
        @ParameterizedTest
        @ValueSource(ints = {-10, 1, 0, 20})
        public void should_ReturnZero_When_multiplyNumberWithZero(int givenSource){
            // given
            int firstNumber = givenSource;
            int secondNumber = 0;

            // when
            int actual = calculatorTest.multiply(firstNumber, secondNumber);

            // then
            assertTrue(actual == 0);
        }

        @ParameterizedTest(name = "1st={0}, 2th={1}")
        @CsvSource(value = {"-10, -1", "-10, -20", "-3, -45"})
        public void should_ReturnTrue_When_multiplyTwoNegativeNumbers(int givenfirstNumber, int  givensecondNumber ){
            // given
            int firstNumber = givenfirstNumber;
            int secondNumber = givensecondNumber;

            // when
            int actual = calculatorTest.multiply(firstNumber, secondNumber);

            // then
            assertTrue(actual > 0);
        }
    }

    @Nested
    class TestingDivideMethod {
        @Test
        public void should_throwException_When_divideNumberWithZero(){
            // given
            int firstNumber = 10;
            int secondNumber = 0;

            // when
            Executable executable = () -> calculatorTest.divide(firstNumber, secondNumber);

            // then
            assertThrows(ArithmeticException.class, executable);
        }
    }


    class Calculator {
        int add(int a, int b) {
            return a+b;
        }

        int multiply(int a, int b) {
            return a * b;
        }

        int divide(int a, int b) {
            return a / b;
        }

    }

}
