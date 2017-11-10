package com.agold.userswitchanimation;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by root on 17-11-10.
 */

public class PasswordActivity extends Activity {

    private EditText mainSystemVisible;
    private EditText secureSystemVisible;
    private EditText mainSystem;
    private EditText secureSystem;
    private Button confirmButton;
    private Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.password_activity);
        mainSystemVisible = (EditText) findViewById(R.id.main_system_pw_edit);
        secureSystemVisible = (EditText) findViewById(R.id.secure_system_pw_edit);
        mainSystem = (EditText) findViewById(R.id.confirm_main_system_pw_edit);
        secureSystem = (EditText) findViewById(R.id.confirm_secure_system_pw_edit);
        confirmButton = (Button) findViewById(R.id.button2);

        InputFilter[] inputFilter = new InputFilter[]{new InputFilter.LengthFilter(6)};
        mainSystemVisible.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        mainSystemVisible.setFilters(inputFilter);
        secureSystemVisible.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        secureSystemVisible.setFilters(inputFilter);
        mainSystem.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        mainSystem.setFilters(inputFilter);
        secureSystem.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        secureSystem.setFilters(inputFilter);

        setAutoJump();
        setHint();
        setTextSize(13);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mainPassword;
                String mainPasswordConfirm;
                String securePassword;
                String securePasswordConfirm;
                if (mainSystemVisible.getText() == null
                        || secureSystemVisible.getText() == null
                        || mainSystem.getText() == null
                        || secureSystem.getText() == null) {
                    Toast.makeText(mContext, R.string.password_error, Toast.LENGTH_SHORT).show();
                    clearPassword();
                } else {
                    mainPassword = mainSystemVisible.getText().toString();
                    mainPasswordConfirm = mainSystem.getText().toString();
                    securePassword = secureSystemVisible.getText().toString();
                    securePasswordConfirm = secureSystem.getText().toString();
                    if (mainPassword.length() < 6
                            || mainPasswordConfirm.length() < 6
                            || securePassword.length() < 6
                            || securePasswordConfirm.length() < 6) {
                        Toast.makeText(mContext, R.string.password_not_long, Toast.LENGTH_SHORT).show();
                        clearPassword();
                    } else {
                        if (!mainPassword.equals(mainPasswordConfirm) || !securePassword.equals(securePasswordConfirm)) {
                            Toast.makeText(mContext, R.string.password_confirm_failed, Toast.LENGTH_SHORT).show();
                            clearPassword();
                        } else {
                            if (mainPassword.equals(securePasswordConfirm)) {
                                Toast.makeText(mContext, R.string.password_error_same, Toast.LENGTH_SHORT).show();
                                clearPassword();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setMessage(getString(R.string.password_set_successfully) + mainPasswordConfirm
                                        + "\n" + getString(R.string.secure_password) + securePasswordConfirm + "\n" + getString(R.string.confirm_and_start));
                                builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        clearPassword();
                                    }
                                });
                                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        clearPassword();
                                    }
                                });
                                builder.create().show();
                            }
                        }
                    }
                }
            }
        });
    }

    private void clearPassword() {
        mainSystemVisible.setText("");
        secureSystemVisible.setText("");
        mainSystem.setText("");
        secureSystem.setText("");
    }

    private void setAutoJump() {
        mainSystemVisible.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 6) {
                    mainSystem.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mainSystem.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 6) {
                    secureSystemVisible.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        secureSystemVisible.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 6) {
                    secureSystem.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setHint() {
        mainSystemVisible.setHint("请输入6位密码");
        mainSystem.setHint("请确认初始系统密码");
        secureSystemVisible.setHint("请输入6位密码（不能与初始系统相同）");
        secureSystem.setHint("请确认安全系统密码");
    }

    private void setTextSize(int i) {
        mainSystemVisible.setTextSize(i);
        mainSystem.setTextSize(i);
        secureSystemVisible.setTextSize(i);
        secureSystem.setTextSize(i);
    }
}
