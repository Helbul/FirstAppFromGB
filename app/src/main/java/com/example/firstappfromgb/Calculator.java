package com.example.firstappfromgb;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator implements Parcelable {
    private static final int SCALE = 9;
    private static final RoundingMode ROUND = RoundingMode.CEILING;

    private static final String EQUAL = "=";
    private static final String ZERO = "0";
    private static final String EMPTY = "";
    private static final String DOT = ".";

    private String history;
    private String input;
    private BigDecimal firstNumber;
    private BigDecimal secondNumber;
    private BigDecimal result;
    private Buttons previousMathOperation;
    private Buttons currentMathOperation;
    private Buttons currentButton;

    protected Calculator(Parcel in) {
        history = in.readString();
        input = in.readString();
        firstNumber = new BigDecimal(in.readString());
        secondNumber = new BigDecimal(in.readString());
        result = new BigDecimal(in.readString());
        previousMathOperation = Buttons.valueOf(in.readString());
        currentMathOperation = Buttons.valueOf(in.readString());
        currentButton = Buttons.valueOf(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(history);
        dest.writeString(input);
        dest.writeString(firstNumber.toString());
        dest.writeString(secondNumber.toString());
        dest.writeString(result.toString());
        dest.writeString(previousMathOperation.getTitle());
        dest.writeString(currentButton.getTitle());
        dest.writeString(currentButton.getTitle());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    private void initHistory(){
        this.history = EMPTY;
    }

    private void initFirstNumber(){
        this.firstNumber = new BigDecimal("0");
    }

    private void initSecondNumber(){
        this.secondNumber = new BigDecimal("0");
    }

    private void initResult(){
        this.result = new BigDecimal("0");
    }

    private void initInput(){
        this.input = ZERO;
    }

    private void initPreviousMathOperation(){
        this.previousMathOperation = null;
    }
    private void initCurrentMathOperation(){
        this.currentMathOperation = null;
    }
    private void initCurrentButton(){
        this.currentButton = null;
    }

    public Calculator() {
        initCalculator();
    }


    public void enter (Buttons button) {
        switch (button) {
            case BUTTON_CE: {
                initCalculator();
                break;
            }

            case BUTTON_DEL: {
                del();
                break;
            }

            case BUTTON_PLUS:

            case BUTTON_DIVIDE:

            case BUTTON_MULTIPLY:

            case BUTTON_MINUS: {
                if (currentMathOperation == Buttons.BUTTON_EQUAL) {
                    result = new BigDecimal(input);
                    firstNumber = result;
                }
                else if (isNumber(currentButton) && previousMathOperation != null){
                    calculate();
                    previousMathOperation = button;
                    input = result.toString();
                }
                else if (previousMathOperation == null) {
                    previousMathOperation = button;
                    firstNumber = secondNumber;
                }
                currentMathOperation = button;
                currentButton = button;
                addHistory(button);
                break;
            }

            case BUTTON_EQUAL:{
                equal();
                currentButton= button;
                currentMathOperation = button;
                break;
            }

            case BUTTON_DOT:{
                addDot();
                //secondNumber = new BigDecimal(input);
                currentButton = button;
                break;
            }

            case BUTTON0: {
                if (!isNumber(currentButton) || (currentButton == Buttons.BUTTON_EQUAL)) {
                    input = ZERO;
                    secondNumber = new BigDecimal(ZERO);
                    currentButton = button;//???
                    break;
                }
                if (!input.equals("0")){
                    input += button.getTitle();
                    secondNumber = new BigDecimal(input);
                    currentButton= button;
                    break;
                }
            }
            default: {
                //if (input.equals("0") || currentButton == Buttons.BUTTON_PLUS || currentButton == Buttons.BUTTON_MINUS || currentButton == Buttons.BUTTON_MULTIPLY || currentButton == Buttons.BUTTON_DIVIDE || currentButton == Buttons.BUTTON_EQUAL) {
                if (input.equals("0") || !isNumber(currentButton)) {
                    input = button.getTitle();
                    previousMathOperation = currentMathOperation;
                } else {
                    input += button.getTitle();
                }
                secondNumber = new BigDecimal(input);
                currentButton= button;
            }
        }
    }

    private void calculate() {
        switch (previousMathOperation){
            case BUTTON_PLUS: {
                result = firstNumber.add(secondNumber);
                firstNumber = result;
                secondNumber = result;
                break;
            }
            case BUTTON_MINUS: {
                result = firstNumber.subtract(secondNumber);
                firstNumber = result;
                secondNumber = result;
                break;
            }
            case BUTTON_MULTIPLY: {
                result = firstNumber.multiply(secondNumber);
                firstNumber = result;
                secondNumber = result;
                break;
            }
            case BUTTON_DIVIDE: {
                result = firstNumber.divide(secondNumber, SCALE, ROUND);
                result = result.stripTrailingZeros();
                firstNumber = result;
                secondNumber = result;
                break;
            }
            case BUTTON_EQUAL: {
                equal();
                break;
            }
        }
        previousMathOperation = currentMathOperation;
    }

    private boolean isNumber(Buttons button) {
        switch (button) {
            case BUTTON0:
            case BUTTON1:
            case BUTTON2:
            case BUTTON3:
            case BUTTON4:
            case BUTTON5:
            case BUTTON6:
            case BUTTON7:
            case BUTTON8:
            case BUTTON9:
            case BUTTON_DOT:{
                return true;
            }
        }
        return false;
    }

    private void addDot() {
        if (currentButton == Buttons.BUTTON_EQUAL) {
            input = ZERO + DOT;
            return;
        }
        if (!input.contains(DOT)) {
            input = input + DOT;
        }
    }


    private void equal() {
        if (currentButton == Buttons.BUTTON_EQUAL) {
            return;
        }
        history = firstNumber.stripTrailingZeros() + previousMathOperation.getTitle() + secondNumber.stripTrailingZeros() + EQUAL;
        calculate();
        input = result.toString();
        this.firstNumber = new BigDecimal(ZERO);
        this.secondNumber = new BigDecimal(input);
        previousMathOperation = null;
        currentMathOperation = null;
    }



    private void addHistory(Buttons button) {
        if (history.equals(EMPTY)) {
            history = input + button.getTitle();
        } else {
            history = result.stripTrailingZeros() + button.getTitle();
        }
    }

    private void initCalculator() {
        initFirstNumber();
        initSecondNumber();
        initResult();
        initInput();
        initHistory();
        initCurrentButton();
        initCurrentMathOperation();
        initPreviousMathOperation();

    }

    private void del() {
        if (!isNumber(currentButton)) {
            input = input.substring(0, input.length() - 1);
            return;
        }
        if (input.equals(ZERO)) {
            return;
        } else if (input.length() == 1) {
            input = ZERO;
        } else {
            input = input.substring(0, input.length() - 1);
        }

    }

    private BigDecimal correctLargeNumber (BigDecimal number){
        BigDecimal result = new BigDecimal(0);
        if (number.scale() > SCALE) {
            result = number.setScale(SCALE, ROUND);
        }
        return result.stripTrailingZeros();
    }

    public String getInput() {
        return input;
    }

    public String getHistory() {
        return history;
    }


}
