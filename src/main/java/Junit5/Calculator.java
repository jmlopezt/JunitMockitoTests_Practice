package Junit5;

public class Calculator {

    private double result;
    private int intResult;

    public Calculator(){
        result = 0.0;
    }
    public Calculator(double result) {
        this.result = result;
    }

    public double add(double n1, double n2){
        result = n1 + n2;
        return result;
    }
    public double add(int n1, int n2){
        result = n1 + n2;
        return result;
    }

    public double addVectorMembers(int ... n1){
        for (int i=0; i< n1.length; i++){
            result += n1[i];
        }
        return result;
    }

    public double addVectorMembers(double ... n1){
        for (int i=1; i< n1.length; i++){
            result = result + n1[i];
        }
        return result;
    }

    public double subtract(int n1, int n2){
        return result = n1-n2;
    }

    public double subtract(double n1, double n2){
        return result = n1 - n2;
    }

    public double mult(double n1, double n2){
        result = n1*n2;
        return result;
    }

    public double mult(int n1, int n2){
        result = n1*n2;
        return result;
    }

    public double div(double n1, double n2){// int numbers casts to double
        result = n1/n2;
        return result;
    }

    public int divByZero(int n1, int n2){
        if (n2 == 0){
            throw  new ArithmeticException("No se puede dividir pro 0 un int");
        }else{
            intResult = n1/n2;
        }
        return intResult;
    }


    public Double getResult() {
        return result;
    }
}
