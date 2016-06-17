package sample.fabrics.io.study;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mNameView;
    private View mProgressView;
    private View mLoginFormView;
    private View focusView;
    String mPassword;
    String mEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mNameView = (EditText) findViewById(R.id.name);
        mPasswordView = (EditText) findViewById(R.id.password);
        Button mEmailSignUpButton = (Button) findViewById(R.id.email_sign_up_button);
        mEmailSignUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptLogin();
                attemptLogin();
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

    }



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mNameView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String name = mNameView.getText().toString();

        boolean cancel = false;
        focusView = null;

        // Check for a valid password, if the user entered one.
        if(!isNameValid(name)){
            mNameView.setError(getString(R.string.error_invalid_name));
            focusView = mNameView;
            cancel = true;
        }
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        else {
            ParseUser user = new ParseUser();
            user.setUsername(email);
            user.setPassword(password);
            user.put("name", name);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e==null){
                        Intent i = new Intent(RegisterActivity.this, TimerActivity.class);
                        startActivity(i);
                    } else {
                        //look at parse exception
                        mEmailView.setError(getString(R.string.error_registration));
                        if(mPasswordView.getText().toString().length() == 0){
                            mEmailView.setError(getString(R.string.error_no_password));
                        }
                        focusView = mEmailView;
                        focusView.requestFocus();

                    }
                }
            });

        }
    }


    private boolean isEmailValid(String email) {
        //TODO: Replace this with more logic / justifications
        //but this basically says it is an email if it contains
        //an "@"
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with more justification
        //this basically states that it is not a password if length is less than 4
        return password.length() > 4;
    }



    private boolean isNameValid(String name){
        return name.length() > 0;
    }
}
