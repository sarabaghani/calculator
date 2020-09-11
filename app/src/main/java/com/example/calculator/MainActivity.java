package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.*;

//(5*85)+87:screen height ,
public class MainActivity extends AppCompatActivity {

    public static final String BUNDLE_SCREEN_DISPLAYED_CONTENT = "screen displayed content";
    public static final String BUNDLE_TO_SHOW = "to show";
    public static final String BUNDLE_TEMP_MEM = "temp mem";
    public static final String BUNDLE_FLAG = "flag";

    private Button mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8, mButton9, mButton0;
    private Button mButtonPlus, mButtonSub, mButtonDot, mButtonEqual;
    private ImageButton mButtonDiv, mButtonMul;
    private Button mButtonDelete;
    private EditText mText1;

    private int resId;
    private String toShow = "";
    private String mContent;
    private boolean flag = true;
    private double mTempMem;
    private double mElem1;
    private double mElem2;
    private char operation;
    Calc mCalc = new Calc();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            flag = savedInstanceState.getBoolean(BUNDLE_FLAG);
            mContent = savedInstanceState.getString(BUNDLE_SCREEN_DISPLAYED_CONTENT);
            mTempMem = savedInstanceState.getDouble(BUNDLE_TEMP_MEM);
            toShow = savedInstanceState.getString(BUNDLE_TO_SHOW);
        }
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mContent != null)
            mText1.setText(mContent);
    }

    private void setListeners() {
        mButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resId = R.string._0;
                toastAndText();
            }
        });
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resId = R.string._1;
                toastAndText();
            }
        });
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resId = R.string._2;
                toastAndText();
            }
        });
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resId = R.string._3;
                toastAndText();
            }
        });
        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resId = R.string._4;
                toastAndText();
            }
        });
        mButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resId = R.string._5;
                toastAndText();
            }
        });
        mButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resId = R.string._6;
                toastAndText();
            }
        });
        mButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resId = R.string._7;
                toastAndText();
            }
        });
        mButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resId = R.string._8;
                toastAndText();
            }
        });
        mButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resId = R.string._9;
                toastAndText();
            }
        });
        mButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resId = R.string.plus;
                toastAndTextSpaced();
            }
        });
        mButtonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resId = R.string.sub;
                toastAndTextSpaced();
            }
        });
        mButtonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toShow = toShow + " * ";
                mText1.setText(toShow);
                Toast toast = Toast.makeText(MainActivity.this, toShow, LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
                toast.show();
            }
        });
        mButtonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toShow = toShow + " / ";
                mText1.setText(toShow);
                Toast toast = Toast.makeText(MainActivity.this, toShow, LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
                toast.show();
            }
        });
        mButtonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resId = R.string.dot;
                toastAndText();
            }
        });
        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mText1.getText().clear();
                toShow = "";
                mTempMem = 0;
                flag = true;
            }
        });
        mButtonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toProcess = mText1.getText().toString();
                mText1.getText().clear();
                String[] elements = toProcess.split(" ");
                try {
                    mElem1 = Double.parseDouble(elements[0]);
                    operation = elements[1].charAt(0);
                    mElem2 = Double.parseDouble(elements[2]);
                    mTempMem = doCalcs(mElem1, mElem2, operation);
                    if (!flag) {
                        warningMSG();
                    }
                    for (int i = 3; i < elements.length; i++) {
                        operation = elements[i].charAt(0);
                        if (i != elements.length - 1) {
                            mElem2 = Double.parseDouble(elements[i + 1]);
                            mTempMem = doCalcs(mTempMem, mElem2, operation);
                            i++;
                            if (!flag) {
                                warningMSG();
                            }
                        }
                    }
                    if (flag) {
                        mText1.getText().clear();
                        Toast toast = Toast.makeText(MainActivity.this,
                                String.valueOf(mTempMem), LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
                        toast.show();
                        mText1.setText(String.valueOf(mTempMem));
                        toShow = String.valueOf(mTempMem);
                    }
                } catch (Exception e) {
                    warningMSG();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mContent = mText1.getText().toString();
        outState.putString(BUNDLE_SCREEN_DISPLAYED_CONTENT, mContent);
        outState.putString(BUNDLE_TO_SHOW, toShow);
        outState.putDouble(BUNDLE_TEMP_MEM, mTempMem);
        outState.putBoolean(BUNDLE_FLAG, flag);
    }

    private void warningMSG() {
        Toast toast = Toast.makeText(MainActivity.this, R.string.error_warning,
                LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
        toast.show();
        mText1.getText().clear();
        mTempMem = 0;
        mText1.setText(R.string.error_warning);
    }

    private double doCalcs(double e1, double e2, char c) {
        switch (c) {
            case '+':
                mTempMem = mCalc.add(e1, e2);
                break;
            case '-':
                mTempMem = mCalc.sub(e1, e2);
                break;
            case '/':
                if (mCalc.div(e1, e2).equals(R.string.error)) {
                    warningMSG();
                    flag = false;
                    break;
                } else {
                    mTempMem = Double.parseDouble(mCalc.div(e1, e2));
                    break;
                }
            case '*':
                mTempMem = mCalc.mul(e1, e2);
                break;
        }
        return mTempMem;
    }

    private void toastAndTextSpaced() {
        String string = this.getResources().getString(resId);
        toShow = toShow + " " + string + " ";
        mText1.setText(toShow);
        Toast toast = Toast.makeText(MainActivity.this, toShow, LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
        toast.show();
    }

    private void toastAndText() {
        String string = this.getResources().getString(resId);
        toShow = toShow + string;
        mText1.setText(toShow);
        Toast toast = Toast.makeText(MainActivity.this, toShow, LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
        toast.show();
    }

    private void findViews() {
        mButton0 = findViewById(R.id.btn0);
        mButton1 = findViewById(R.id.btn1);
        mButton2 = findViewById(R.id.btn2);
        mButton3 = findViewById(R.id.btn3);
        mButton4 = findViewById(R.id.btn4);
        mButton5 = findViewById(R.id.btn5);
        mButton6 = findViewById(R.id.btn6);
        mButton7 = findViewById(R.id.btn7);
        mButton8 = findViewById(R.id.btn8);
        mButton9 = findViewById(R.id.btn9);
        mButtonDot = findViewById(R.id.btn_dot);
        mButtonEqual = findViewById(R.id.btn_equal);
        mButtonSub = findViewById(R.id.btn_sub);
        mButtonPlus = findViewById(R.id.btn_plus);
        mButtonDelete = findViewById(R.id.delete);
        mButtonDiv = findViewById(R.id.div);
        mButtonMul = findViewById(R.id.mul);
        mText1 = findViewById(R.id.txt_screen);
    }
}


