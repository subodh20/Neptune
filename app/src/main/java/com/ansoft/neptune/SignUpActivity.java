package com.ansoft.neptune;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ansoft.neptune.Constants.PC;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {


    EditText fullNameField, emailField, phField, pwField, confirmPwField;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fullNameField = (EditText) findViewById(R.id.fullNameField);
        emailField = (EditText) findViewById(R.id.emailField);
        phField = (EditText) findViewById(R.id.phNumberField);
        pwField = (EditText) findViewById(R.id.pwField);
        confirmPwField = (EditText) findViewById(R.id.confirmPwField);
        signUpBtn = (Button) findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pd=ProgressDialog.show(SignUpActivity.this, "Signing Up", "Please wait...");
                if (fullNameField.getText().toString().isEmpty() ||
                        emailField.getText().toString().isEmpty() ||
                        phField.getText().toString().isEmpty() ||
                        pwField.getText().toString().isEmpty() ||
                        confirmPwField.getText().toString().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                } else if (!pwField.getText().toString().equalsIgnoreCase(confirmPwField.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }else {
                    ParseUser user=new ParseUser();
                    user.setEmail(emailField.getText().toString());
                    user.setUsername(phField.getText().toString());
                    user.setPassword(pwField.getText().toString());
                    user.put(PC.KEY_FULLNAME, fullNameField.getText().toString());
                    user.put(PC.KEY_PHONE_NUMBER, phField.getText().toString());
                    user.signUpInBackground(new SignUpCallback() {

                        @Override
                        public void done(ParseException e) {
                            pd.dismiss();
                            if (e==null){
                                Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
