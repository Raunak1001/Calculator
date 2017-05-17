package com.codeasylums.calculator;

import android.util.Log;
import java.util.Stack;

/**
 * Created by raunak on 17/5/17.
 */

public class Calculations {

  static Stack<Double>    val= new Stack<>();
  static Stack<Character> operator=new Stack<>();

  static double doCalculations(String expression) {

    Log.d("CalculationACtivity",expression);
    for (int i = 0; i < expression.length(); i++) {
      //String s = "";
      Log.d("CHARAT",expression.charAt(i)+"");
      if (expression.charAt(i) != '+' && expression.charAt(i) != '-' && expression.charAt(i) != '*'
          && expression.charAt(i) != '/') {

        String s = "";
        while (expression.charAt(i) != '+' && expression.charAt(i) != '-'
            && expression.charAt(i) != '*'
            && expression.charAt(i) != '/') {

          s = s + expression.charAt(i);
          i++;
          if(i>=expression.length()){
            break;
          }
        }
        i--;
        val.push(Double.parseDouble(s));
       // Log.d("VALUE",s);
        s="";

      } else {
        if (expression.charAt(i) == '+') {
          while(i<expression.length() && !operator.isEmpty() && !val.isEmpty() && operator.peek()!='+' ){
            doOperation();
          }

        }else if(expression.charAt(i) == '-' ) {
          while (i < expression.length() && !operator.isEmpty() && !val.isEmpty()
              && operator.peek() != '+' && operator.peek() != '-') {
            doOperation();
          }
        }
        operator.push(expression.charAt(i));

      }


    }

    while (!operator.isEmpty())
    {
      doOperation();

    }
return val.peek();
  }

  static void doOperation(){

    char tempchar=operator.peek();
    operator.pop();
 //   Log.d("CHAR",tempchar+"");
    if(val.isEmpty()){
      return;
    }
    Double secondOperand=val.peek();
    val.pop();
    if(val.isEmpty()){
      return;
    }
    Double firstOperrand=val.peek();
    val.pop();
    if(tempchar=='+'){
      val.push(firstOperrand+secondOperand);
    }else if(tempchar=='-')
    {
      val.push(firstOperrand-secondOperand);
    }else if(tempchar=='*'){
      val.push(firstOperrand*secondOperand);
    }else {
      val.push(firstOperrand/secondOperand);
    }

    Log.d("TEST",String.valueOf(val.peek()));
  }


}
