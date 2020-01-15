package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidNumberTest {

    private ValidNumber validNumber;

    @BeforeEach
    public void setUp(){
        validNumber = new ValidNumber();
    }

    @AfterEach
    public void tearDown(){
        validNumber = null;
    }

    @Test
    public void When_NumberBelowZero_Then_CheckIsFalse(){
        assertEquals(false,validNumber.check(-7));
    }

    @Test
    public void When_NumberBetweenZeroAndNine_Then_CheckIsFalse(){
        assertEquals(true,validNumber.check(6));
    }

    @Test
    public void When_NumberAboveNine_Then_CheckIsFalse(){
        assertEquals(false,validNumber.check(78));
    }

    @Test
    public void When_String_Then_CheckIsFalse(){
        assertEquals(false,validNumber.check("78"));
    }

    @Test
    public void When_Negative_Then_CheckZeroIsTrue(){
        assertEquals(true,validNumber.checkZero(-13));
    }

    @Test
    public void When_String_Then_CheckZeroIsFalse(){
        assertEquals(false,validNumber.checkZero("78"));
    }

    @Test
    public void When_Zero_Then_CheckZeroThrowsException(){
        assertThrows(ArithmeticException.class,()->validNumber.checkZero(0));
    }

    @Test
    public void doubleToIntTest(){
        assertEquals(5,validNumber.doubleToInt(5.5));
    }

    @Test
    public void doubleToIntErrorTest(){
        assertEquals(0,validNumber.doubleToInt("5.5"));
    }


}