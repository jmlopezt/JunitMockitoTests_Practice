package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class AddTest {

    @InjectMocks
    private Add add;
    @Mock
    private ValidNumber validNumber;
    @Mock
    private Print print;
    @Spy
    private List<String> spyList = new ArrayList<>();
    @Mock
    private List<String> mockList = new ArrayList<>();

    @Captor
    private ArgumentCaptor<Integer> captor;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTest(){
        when(validNumber.check(3)).thenReturn(true);
        boolean checkNumber = validNumber.check(3);
        assertEquals(true,checkNumber);
    }

    @Test
    public void addMockExceptionTest(){
        when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("Zero is not accepted"));
        Exception exception = null;

        try {
            validNumber.checkZero(0);
        } catch (ArithmeticException e){
            exception = e;
        }
        assertNotNull(exception);
    }

    @Test
    public void addRealMethodTest(){
        when(validNumber.check(3)).thenCallRealMethod();
        assertEquals(true,validNumber.check(3));

        when(validNumber.check("3")).thenCallRealMethod();
        assertEquals(false,validNumber.check("3"));
    }

    @Test
    public void addDoubleToIntThenAnswerTest(){
        Answer<Integer> answer = new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return 50;
            }
        };

        when(validNumber.doubleToInt(50.35)).thenAnswer(answer);
        assertEquals(100,add.addInt(50.35));
    }



   @Test
   public void patternTest(){
        when(validNumber.check(4)).thenReturn(true);
       when(validNumber.check(8)).thenReturn(true);
        int result = add.add(4,8);
        assertEquals(12,result);
   }

    @Test
    public void patternBDDTest(){
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(8)).willReturn(true);
        int result = add.add(4,8);
        assertEquals(12,result);
    }

    @Test
    public void argumentMatcherTest(){
        given(validNumber.check(anyInt())).willReturn(true);
        int result = add.add(4,8);
        assertEquals(12,result);
    }

    @Test
    public void addPrintTest(){
        given(validNumber.check(anyInt())).willReturn(true);
        add.addPrint(4,4);
        //verify(validNumber).check(4);
        //verify(validNumber,times(2)).check(4);
        verify(validNumber,never()).check(88);
        verify(validNumber,atLeast(1)).check(4);
        verify(validNumber,atMost(2)).check(4);

        verify(print).showMessage(8);
        verify(print, never()).showError();
    }

    @Test
    public void captorTest(){
        given(validNumber.check(anyInt())).willReturn(true);
        add.addPrint(4,5);
        verify(print).showMessage(captor.capture());
        assertEquals(captor.getValue().intValue(),9);
    }

    @Test
    public void spyTest(){
        spyList.add("1");
        spyList.add("2");
        verify(spyList).add("1");
        verify(spyList).add("2");
        assertEquals(2,spyList.size());
    }

    @Test
    public void mockTest(){
        mockList.add("1");
        mockList.add("2");
        verify(mockList).add("1");
        verify(mockList).add("2");
        given(mockList.size()).willReturn(2);
        assertEquals(2,mockList.size());
    }

}