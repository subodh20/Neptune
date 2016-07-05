package com.ansoft.neptune;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by subodh on 31/05/2016.
 */
public class LoginActivity extends Activity {

    EditText userNameField;
    EditText passwordField;
    Button loginBtn;
    TextView tvCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameField=(EditText)findViewById(R.id.emailField);
        passwordField=(EditText)findViewById(R.id.passwordField);
        loginBtn=(Button)findViewById(R.id.loginBtn);
        tvCreateAccount=(TextView)findViewById(R.id.tvCreateAccount);
        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pd=ProgressDialog.show(LoginActivity.this, "Loging in", "Please wait...");
                Log.e("Username", userNameField.getText().toString());
                Log.e("Password", passwordField.getText().toString());
                ParseUser.logInInBackground(userNameField.getText().toString(), passwordField.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {
                        pd.dismiss();
                        if(e==null)
                        {
                            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }else{

                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
