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
    //boolean for removing the 0 when the user starts using the calc
    private boolean firstUse = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.calcArea = (TextView) findViewById(R.id.calcArea);
        // Find and set OnClickListener to numeric buttons
        setNumberOnClickListener();
        // Find and set OnClickListener to operator buttons, equal button and decimal point button
        setOperatorOnClickListener();
    }

    /**
     * create and set OnClickListener to the number buttons.
     */
    private void setNumberOnClickListener() {
        // Create an OnClickListener
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just append/set the text of a clicked button
                Button button = (Button) v;
                if (stateError) {
                    // If there is an error, replace the error message

                    calcArea.setText(button.getText());
                    stateError = false;
                } else {
                    if (firstUse)
                    {
                        calcArea.setText("");
                        firstUse = false;
                    }
                    // there is a valid expression so append to it
                    calcArea.append(button.getText());
                }
                // Set the lastNumeric boolean/flag to true
                lastNumeric = true;
            }
        };
        // Assign the listener to all the number buttons
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    /**
     * create and set OnClickListener to operators, equal button and decimal point.
     */
    private void setOperatorOnClickListener() {
        // Create a common OnClickListener for operators
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the current state is Error do not add the operator
                // If the last input is a number, add the operator
                if (lastNumeric && !stateError) {
                    Button button = (Button) v;
                    calcArea.append(button.getText());
                    lastNumeric = false;
                    // Reset the dot boolean
                    lastDot = false;
                }
            }
        };
        // Assign the listener to all the operator buttons
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
        // Decimal point
        findViewById(R.id.buttonDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastNumeric && !stateError && !lastDot) {
                    calcArea.append(".");
                    lastNumeric = false;
                    lastDot = true;
                }
            }
        });
        // Clear button
        findViewById(R.id.buttonClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the screen
                calcArea.setText("");
                // Reset all the booleans
                lastNumeric = false;
                stateError = false;
                lastDot = false;
                firstUse = true;
            }
        });
        // Equal button
        findViewById(R.id.buttonEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqual();
            }
        });
    }

    /**
     * Logic to calculate the answers.
     */
    private void onEqual() {
        // If the current state is error, nothing to do.
        // If the last input is a number, calculate
        if (lastNumeric && !stateError) {
            // Read the expression
            String txt = calcArea.getText().toString();
            // Create an Expression (A class from exp4j library)
            Expression expression = new ExpressionBuilder(txt).build();
            try {
                // Calculate the result and display
                double result = expression.evaluate();
                calcArea.setText(Double.toString(result));
                // Result contains a dot
                lastDot = true;
            } catch (ArithmeticException ex) {
                // Display an error message
                calcArea.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }
}
