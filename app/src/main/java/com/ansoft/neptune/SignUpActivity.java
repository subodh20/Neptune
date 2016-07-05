package com.ansoft.neptune;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ansoft.neptune.Data.Ourdata;

public class SignUpActivity extends AppCompatActivity {


    EditText firstNameField, lastNameField;
    Button signUpBtn1;
    String firstName;
    String lastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstNameField = (EditText) findViewById(R.id.firstNameField);
        lastNameField = (EditText) findViewById(R.id.lastNameField);
        signUpBtn1 = (Button) findViewById(R.id.signUpBtn1);
        signUpBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting enetered value from firstName and lastName field
                firstName = firstNameField.getText().toString();
                lastName = lastNameField.getText().toString();
                firstName = firstName.trim();
                lastName = lastName.trim();
                if (firstName.equals("") || lastName.equals("")) {
                    Toast.makeText(SignUpActivity.this, "fill all things properly", Toast.LENGTH_LONG).show();
                } else {
                    Ourdata.setcFn(firstName);
                    Ourdata.setcLn(lastName);
                    Intent intent = new Intent(SignUpActivity.this, SignUp2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

        /*
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
        */
    }
}
