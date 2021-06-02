package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView view_input;
    private TextView view_result;
    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        calculator = new Calculator();

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

    }
}
