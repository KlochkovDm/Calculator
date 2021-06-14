package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {

    private static final String CALCULATOR_STATE = "CALCULATOR_STATE";

    private TextView view_input;
    private TextView view_result;
    private Calculator calculator;

    private ThemeStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = new ThemeStorage(this);
        setTheme(storage.getTheme().getResource());

        setContentView(R.layout.activity_main);

        calculator = savedInstanceState == null ? new Calculator() : savedInstanceState.getParcelable(CALCULATOR_STATE);

        int[] numberIds = new int[] {
                R.id.key_0,
                R.id.key_1,
                R.id.key_2,
                R.id.key_3,
                R.id.key_4,
                R.id.key_5,
                R.id.key_6,
                R.id.key_7,
                R.id.key_8,
                R.id.key_9,
                R.id.key_dot,
        };

        int[] actionsIds = new int[] {
                R.id.key_addition,
                R.id.key_subtraction,
                R.id.key_multiplication,
                R.id.key_division,
                R.id.key_result,
                R.id.key_ac,
                R.id.key_del
        };

        view_input = findViewById(R.id.view_input);
        view_result = findViewById(R.id.view_result);
        updateTexts();

        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onNumPressed(view.getId());
                view_input.setText(calculator.getInput());
            }
        };

        View.OnClickListener actionButtonOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onActionPressed(view.getId());
                view_input.setText(calculator.getInput());
                view_result.setText(calculator.getResult());
            }
        };

        for (int numberId : numberIds) {
            findViewById(numberId).setOnClickListener(numberButtonClickListener);
        }

        for (int actionsId : actionsIds) {
            findViewById(actionsId).setOnClickListener(actionButtonOnclickListener);
        }

        findViewById(R.id.key_switch_theme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(storage.getTheme().getKey().equals(AppTheme.DARK.getKey())){;
                    storage.setTheme(AppTheme.LIGHT);
                } else {
                    storage.setTheme(AppTheme.DARK);
                };
                recreate();
            }
        });
    }

//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        calculator = savedInstanceState.getParcelable(CALCULATOR_STATE);
//        super.onRestoreInstanceState(savedInstanceState);
//    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CALCULATOR_STATE,calculator);
        super.onSaveInstanceState(outState);
    }

//    private void updateTexts() {
//        if (calculator.getResult() != null) {
//            view_result.setText(calculator.getResult());
//        }
//        if (calculator.getInput() != null) {
//            view_input.setText(calculator.getInput());
//        }
//    }

    private void updateTexts() {
        setTextToTextView(calculator.getResult(), view_result);
        setTextToTextView(calculator.getInput(), view_input);
    }

    private void setTextToTextView(String text, TextView textView) {
        if (text != null) {
            textView.setText(text);
        }
    }

}
