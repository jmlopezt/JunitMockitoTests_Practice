package mockito;

public class WebService {


    public void login(String user, String password, Callback callback){
        if (checkLogin(user, password)){
            callback.onSuccess("usuario correcto");
        }else{
            callback.onFail("Error");
        }
    }

    public boolean checkLogin(String user, String password){
        if (user.equals("Alberto") && password.equals("1234")){
            return true;
        } else {
            return false;
        }

    }
}
