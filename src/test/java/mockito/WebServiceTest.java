package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class WebServiceTest {

    @InjectMocks
    private WebService webService;
    @Mock
    private Callback callback;

    @BeforeEach
    public void setUp(){
        webService = new WebService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkLoginTest(){
        assertTrue(webService.checkLogin("Alberto","1234"));
    }

    @Test
    public void checkLoginErrorTest(){
        assertFalse(webService.checkLogin("AlbertoMarian","1234"));
    }

    @Test
    public void loginSuccessTest(){
        webService.login("Alberto","1234", callback);
        verify(callback).onSuccess("usuario correcto");
    }

    @Test
    public void loginErrorTest(){
        webService.login("AlbertoMarian","1234", callback);
        verify(callback).onFail("Error");
    }

}