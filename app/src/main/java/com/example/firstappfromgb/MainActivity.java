package com.example.firstappfromgb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Calculator calculator;
    TextView result;
    TextView history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        calculator = new Calculator();
        result = findViewById(R.id.result);
        history = findViewById(R.id.history);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button0: {
                        calculator.enter(Buttons.BUTTON0);
                        break;
                    }
                    case R.id.button1: {
                        calculator.enter(Buttons.BUTTON1);
                        break;
                    }
                    case R.id.button2: {
                        calculator.enter(Buttons.BUTTON2);
                        break;
                    }
                    case R.id.button3: {
                        calculator.enter(Buttons.BUTTON3);
                        break;
                    }
                    case R.id.button4: {
                        calculator.enter(Buttons.BUTTON4);
                        break;
                    }
                    case R.id.button5: {
                        calculator.enter(Buttons.BUTTON5);
                        break;
                    }
                    case R.id.button6: {
                        calculator.enter(Buttons.BUTTON6);
                        break;
                    }
                    case R.id.button7: {
                        calculator.enter(Buttons.BUTTON7);
                        break;
                    }
                    case R.id.button8: {
                        calculator.enter(Buttons.BUTTON8);
                        break;
                    }
                    case R.id.button9: {
                        calculator.enter(Buttons.BUTTON9);
                        break;
                    }
                    case R.id.del: {
                        calculator.enter(Buttons.BUTTON_DEL);
                        break;
                    }
                    case R.id.CE: {
                        calculator.enter(Buttons.BUTTON_CE);
                        break;
                    }
                    case R.id.dot: {
                        calculator.enter(Buttons.BUTTON_DOT);
                        break;
                    }
                    case R.id.equals: {
                        calculator.enter(Buttons.BUTTON_EQUAL);
                        break;
                    }
                    case R.id.plus: {
                        calculator.enter(Buttons.BUTTON_PLUS);
                        break;
                    }
                    case R.id.minus: {
                        calculator.enter(Buttons.BUTTON_MINUS);
                        break;
                    }
                    case R.id.division: {
                        calculator.enter(Buttons.BUTTON_DIVIDE);
                        break;
                    }
                    case R.id.multiply: {
                        calculator.enter(Buttons.BUTTON_MULTIPLY);
                        break;
                    }
                }
                result.setText(calculator.getInput());
                history.setText(calculator.getHistory());
            }
        };
        findViewById(R.id.button0).setOnClickListener(clickListener);
        findViewById(R.id.button1).setOnClickListener(clickListener);
        findViewById(R.id.button2).setOnClickListener(clickListener);
        findViewById(R.id.button3).setOnClickListener(clickListener);
        findViewById(R.id.button4).setOnClickListener(clickListener);
        findViewById(R.id.button5).setOnClickListener(clickListener);
        findViewById(R.id.button6).setOnClickListener(clickListener);
        findViewById(R.id.button7).setOnClickListener(clickListener);
        findViewById(R.id.button8).setOnClickListener(clickListener);
        findViewById(R.id.button9).setOnClickListener(clickListener);
        findViewById(R.id.division).setOnClickListener(clickListener);
        findViewById(R.id.multiply).setOnClickListener(clickListener);
        findViewById(R.id.plus).setOnClickListener(clickListener);
        findViewById(R.id.minus).setOnClickListener(clickListener);
        findViewById(R.id.CE).setOnClickListener(clickListener);
        findViewById(R.id.del).setOnClickListener(clickListener);
        findViewById(R.id.equals).setOnClickListener(clickListener);
        findViewById(R.id.changeSign).setOnClickListener(clickListener);
        findViewById(R.id.dot).setOnClickListener(clickListener);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("KEY", calculator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = savedInstanceState.getParcelable("KEY");
        result.setText(calculator.getInput());
        history.setText(calculator.getHistory());
    }
}