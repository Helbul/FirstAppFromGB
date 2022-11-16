package com.example.firstappfromgb;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String KEY = "KEY";

    Calculator calculator;
    TextView result;
    TextView history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeStorage storage = ThemeStorage.getInstance(getApplicationContext());

        ActivityResultLauncher<Intent>launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Theme chosenTheme = (Theme) data.getSerializableExtra(ThemeSelectionActivity.CURRENT_THEME);
                    storage.saveTheme(chosenTheme);
                    recreate();
                }
            }
        });

        Theme savedTheme = ThemeStorage.getInstance(getApplicationContext()).getTheme();
        setTheme(savedTheme.getTheme());

        if (savedTheme.isModeNight() == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }


        setContentView(R.layout.calculator);

        Button themeSettingsLight = findViewById(R.id.changeThemeLight);
        Button themeSettingsDark = findViewById(R.id.changeThemeDark);
        if (savedTheme.isModeNight() == false) {
            themeSettingsLight.setVisibility(View.GONE);
            themeSettingsDark.setVisibility(View.VISIBLE);
        } else {
            themeSettingsDark.setVisibility(View.GONE);
            themeSettingsLight.setVisibility(View.VISIBLE);
        }

        View.OnClickListener settingsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThemeSelectionActivity.class);
                intent.putExtra(ThemeSelectionActivity.SELECTED_THEME, savedTheme);
                launcher.launch(intent);
                //startActivity(intent);
            }
        };

        themeSettingsLight.setOnClickListener(settingsClickListener);
        themeSettingsDark.setOnClickListener(settingsClickListener);


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
        outState.putParcelable(KEY, calculator);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = savedInstanceState.getParcelable(KEY);
        result.setText(calculator.getInput());
        history.setText(calculator.getHistory());
    }
}