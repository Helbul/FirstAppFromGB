package com.example.firstappfromgb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThemeSelectionActivity extends AppCompatActivity {
    public static final String SELECTED_THEME = "SELECTED_THEME";
    public static final String CURRENT_THEME = "CURRENT_THEME";
    private Theme currentTheme;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button buttonApply;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_theme_selection);
        radioGroup = findViewById(R.id.radioGroup);

        currentTheme = (Theme) getIntent().getSerializableExtra(SELECTED_THEME);
        setTheme(currentTheme);


        buttonApply = findViewById(R.id.applyButton);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTheme = checkButton();
                Intent resultIntent = new Intent();
                resultIntent.putExtra(CURRENT_THEME, currentTheme);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });


    }

    public void setTheme (Theme theme) {
        if (theme == Theme.ONE) {
            radioButton = findViewById(R.id.radioButtonLight);
        } else {
            radioButton = findViewById(R.id.radioButtonDark);
        }
        radioButton.setChecked(true);
    }

    public Theme checkButton (){
        int radioButtonId = radioGroup.getCheckedRadioButtonId();

        if (radioButtonId == R.id.radioButtonLight) {
            return  Theme.ONE;
        } else {
            return Theme.TWO;
        }

    }
}
