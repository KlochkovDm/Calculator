package com.example.calculator;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;

public class Calculator extends AppCompatActivity implements Parcelable {

    private double firstArg;
    private double secondArg;
    private String result;

    protected Calculator(Parcel in) {
        firstArg = in.readDouble();
        secondArg = in.readDouble();
        result = in.readString();;
    }

    public String getInput() {
       return inputStr.toString();}

    public String getResult() {
        return result;}
             

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(firstArg);
        dest.writeDouble(secondArg);
        dest.writeString(result);
    }

    private final StringBuilder inputStr = new StringBuilder();

    private State state;
    private enum State {
        firstArgInput,
        operationSelection,
        secondArgInput,
        resultShow
    }

    private Operation operation;
    private enum Operation {
        addition,
        subtraction,
        division,
        multiplication,
        percentage
    }

    public Calculator() {
        state = State.firstArgInput;
    }

    public void onNumPressed(int buttonId) {

        if (inputStr.length() < 10 && (state == State.firstArgInput || state == State.secondArgInput)) {

            if (buttonId == R.id.key_dot) {
                if (inputStr.length() != 0) {
                    inputStr.append(".");
                }
            }
            if (buttonId == R.id.key_0) {
                if (inputStr.toString().equals("0")) {
                    inputStr.append("0");
                }
            }
            if (buttonId == R.id.key_1) {
                inputStr.append("1");
            }
            if (buttonId == R.id.key_2) {
                inputStr.append("2");
            }
            if (buttonId == R.id.key_3) {
                inputStr.append("3");
            }
            if (buttonId == R.id.key_4) {
                inputStr.append("4");
            }
            if (buttonId == R.id.key_5) {
                inputStr.append("5");
            }
            if (buttonId == R.id.key_6) {
                inputStr.append("6");
            }
            if (buttonId == R.id.key_7) {
                inputStr.append("7");
            }
            if (buttonId == R.id.key_8) {
                inputStr.append("8");
            }
            if (buttonId == R.id.key_9) {
                inputStr.append("9");
            }
        }
    }

    public void onActionPressed (int buttonId) {

        if (buttonId == R.id.key_ac) {
            state = State.firstArgInput;
            inputStr.setLength(0);
            result = null;
        }

        if(inputStr.length() != 0) {
            if (buttonId == R.id.key_addition) {
                state = State.operationSelection;
                result = inputStr.toString();
                firstArg = Double.parseDouble(inputStr.toString());
                operation = Operation.addition;
                inputStr.setLength(0);
                state = State.secondArgInput;
            }
            if (buttonId == R.id.key_subtraction) {
                state = State.operationSelection;
                result = inputStr.toString();
                firstArg = Double.parseDouble(inputStr.toString());
                operation = Operation.subtraction;
                inputStr.setLength(0);
                state = State.secondArgInput;
            }
            if (buttonId == R.id.key_multiplication) {
                state = State.operationSelection;
                result = inputStr.toString();
                firstArg = Double.parseDouble(inputStr.toString());
                operation = Operation.multiplication;
                inputStr.setLength(0);
                state = State.secondArgInput;
            }
            if (buttonId == R.id.key_division) {
                state = State.operationSelection;
                result = inputStr.toString();
                firstArg = Double.parseDouble(inputStr.toString());
                operation = Operation.division;
                inputStr.setLength(0);
                state = State.secondArgInput;
            }
            if (buttonId == R.id.key_result) {
                if (state == State.firstArgInput) {
                    state = State.resultShow;
                    firstArg = Double.parseDouble(inputStr.toString());
                    result = String.format("%.2f", firstArg);
                    inputStr.setLength(0);
                    state = State.firstArgInput;
                } else {
                    state = State.resultShow;
                    secondArg = Double.parseDouble(inputStr.toString());
                    result = String.format("%.2f", calculation(operation, firstArg, secondArg));
                    inputStr.setLength(0);
                    state = State.firstArgInput;
                }
            }
            if (buttonId == R.id.key_del) {
                if (state == State.firstArgInput || state == State.secondArgInput) {
                    inputStr.setLength(inputStr.length() - 1);
                    state = State.secondArgInput;
                }
            }
        }
    }


    public double calculation (Operation o, double firstArg, double secondArg) {
        double result = 0;
        if (o == Operation.addition) {
            result = firstArg + secondArg;
        }
        if (o == Operation.subtraction) {
            result =  firstArg - secondArg;
        }
        if (o == Operation.multiplication) {
            result =  firstArg * secondArg;
        }
        if (o == Operation.division) {
            result =  firstArg / secondArg;
        } return result;
    }
}
