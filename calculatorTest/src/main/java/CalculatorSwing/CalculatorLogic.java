package CalculatorSwing;

public class CalculatorLogic {
    static private final char NO_OPERATOR = ' ';
    private char operator = NO_OPERATOR;

    double firstNum;

    public void setFirstNum(double firstNum) {
        this.firstNum = firstNum;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public CalculatorLogic() {
    }

    public double getResult(double second) {
        switch (operator) {
            case '+':
                return firstNum + second;
            case '-':
                return firstNum - second;
            case '*':
                return firstNum * second;
            case '/':
                return firstNum / second;
        }
        return 0;
    }

}







