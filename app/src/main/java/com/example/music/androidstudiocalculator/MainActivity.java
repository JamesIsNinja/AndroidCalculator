package com.example.music.androidstudiocalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {
    // IDs of all the numbers
    private int[] numericButtons = {R.id.buttonZero, R.id.buttonOne, R.id.buttonTwo, R.id.buttonThree, R.id.buttonFour, R.id.buttonFive,
                                    R.id.buttonSix, R.id.buttonSeven, R.id.buttonEight, R.id.buttonNine};
    // IDs of all the operators
    private int[] operatorButtons = {R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide};
    // TextView used to display the output
    private TextView calcArea;
    // checks whether the lastly pressed key is numeric or not
    private boolean lastNumeric;
    // checks that current state is in error or not
    private boolean stateError;
    // If true, do not allow to add another DOT
    private boolean lastDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
