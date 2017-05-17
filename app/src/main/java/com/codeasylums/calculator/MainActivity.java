package com.codeasylums.calculator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  Button[] button = new Button[17];
  TextView ansText, ansText2;
  float a = -1, b;
  char c;


  @Override

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    button[0] = (Button) findViewById(R.id.numberButton1);
    button[1] = (Button) findViewById(R.id.numberButton2);
    button[2] = (Button) findViewById(R.id.numberButton3);
    button[3] = (Button) findViewById(R.id.numberButton4);
    button[4] = (Button) findViewById(R.id.numberButton5);
    button[5] = (Button) findViewById(R.id.numberButton6);
    button[6] = (Button) findViewById(R.id.numberButton7);
    button[7] = (Button) findViewById(R.id.numberButton8);
    button[8] = (Button) findViewById(R.id.numberButton9);
    button[9] = (Button) findViewById(R.id.numberButtonDot);
    button[10] = (Button) findViewById(R.id.numberButton0);
    button[11] = (Button) findViewById(R.id.numberButtonEqual);
    button[12] = (Button) findViewById(R.id.numberButtonClear);
    button[13] = (Button) findViewById(R.id.numberButtonPlus);
    button[14] = (Button) findViewById(R.id.numberButtonMinus);
    button[15] = (Button) findViewById(R.id.numberButtonMultiply);
    button[16] = (Button) findViewById(R.id.numberButtonDivide);
    button[12].setOnLongClickListener(new OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
//        ansText.setText("");
//        ansText2.setText("");
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(500);

        fadeOut.setAnimationListener(new AnimationListener() {
          public void onAnimationEnd(Animation animation) {
            ansText.setText("");
            ansText2.setText("");
          }

          public void onAnimationRepeat(Animation animation) {
          }

          public void onAnimationStart(Animation animation) {
          }
        });
        ansText.startAnimation(fadeOut);
        ansText2.startAnimation(fadeOut);
        return false;
      }
    });

    ansText = (TextView) findViewById(R.id.ansTextView);
    ansText2 = (TextView) findViewById(R.id.ansTextView2);


    for (int i = 0; i < 17; i++) {

      button[i].setOnClickListener(this);

    }
    ansText.getText();

  }

  @Override
  public void onClick(View v) {

    String s = ansText.getText().toString();
    if (v.getId() == R.id.numberButtonEqual) {
      ansText.setText(ansText2.getText().toString());
      //ansText2.setText("");
    } else if (v.getId() == R.id.numberButtonClear) {

      if (s != null && s.length() > 0) {
        ansText.setText(s.substring(0, s.length() - 1));
      }
      if (!validOperation(ansText.getText().toString())) {
        if (ansText.getText().toString().length() > 0) {
          ansText2.setText( String.valueOf(Calculations.doCalculations(
              ansText.getText().toString().substring(0, ansText.getText().toString().length() - 1))));
          ;
        }
      }

      if (ansText.getText().length() == 0) {
        ansText2.setText("");
      }
    } else {

      if (s != null) {
        ansText.setText(ansText.getText().toString() + ((Button) v).getText().toString());
      } else {
        ansText.setText(((Button) v).getText().toString());
      }

    }
    s = ansText.getText().toString();
    if (validOperation(s)) {
      ansText2.setText(String.valueOf(Calculations.doCalculations(s)));
    }


  }


  boolean validOperation(String s) {
    if (s.length() > 0 && s.charAt(s.length() - 1) != '+' && s.charAt(s.length() - 1) != '-'
        && s.charAt(s.length() - 1) != '*' && s.charAt(s.length() - 1) != '/') {
      return true;
    }
    return false;
  }



  /*void doCalculation(String s) {

    if (s == null) {
      return;
    }
    int i = 0;
    for (i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '+' || s.charAt(i) == '/' || s.charAt(i) == '*' || s.charAt(i) == '-') {
        c = s.charAt(i);
        break;
      }

    }
    if (s.length() < 3 || i == s.length() || i == s.length() - 1) {
      ansText2.setText("");
      return;
    }

    a = Float.parseFloat(s.substring(0, i));
    Log.d("TEST", s.substring(i + 1, s.length()));
    b = Float.parseFloat(s.substring(i + 1, s.length()));
//b=1;else
    if (c == '+') {
      ansText2.setText(String.valueOf(a + b));
    } else if (c == '-') {
      ansText2.setText(String.valueOf(a - b));

    } else if (c == '*') {
      ansText2.setText(String.valueOf(a * b));
    } else if (c == '/') {
      if (b == 0) {
        ansText2.setText("");
      } else {
        ansText2.setText(String.valueOf(a / b));
      }
    }
  }
*/

}
