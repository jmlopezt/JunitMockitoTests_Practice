package Junit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureCalculatorTest {
    private TemperatureCalculator temperatureCalculator;

    @BeforeEach
    void setUp() {
        temperatureCalculator = new TemperatureCalculator((float) 0);
    }

    @AfterEach
    void tearDown() {
        temperatureCalculator = null;
    }

    @Test
    @Disabled
    void toFahrenheit() {
        assertEquals(131, temperatureCalculator.toFahrenheit());
    }

    @ParameterizedTest(name = "{index} => a={0}, fah={2}")
    @MethodSource("addProviderData")
    public void addParameterizedTest(float a, float fah) {
        temperatureCalculator.setTemperatureCelsius(a);
        assertEquals(fah, temperatureCalculator.toFahrenheit());
    }

    private static Stream<Arguments> addProviderData() {
        return Stream.of(
                Arguments.of(55, 131),
                Arguments.of(100, 212),
                Arguments.of(45, 113),
                Arguments.of(2, (float)35.6)
        );

    }
}