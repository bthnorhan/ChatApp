package tr.bthnorhan.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextView tv_login_notregistered;
    EditText et_login_email,et_login_password;
    Button btn_login_login;
    String email,password;
    FirebaseAuth fAuth;
    FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
        if (fUser != null)
        {
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            finish();
        }

        tv_login_notregistered = (TextView) findViewById(R.id.tv_login_notregistered);
        et_login_email = (EditText) findViewById(R.id.et_login_email);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        btn_login_login = (Button) findViewById(R.id.btn_login_login);

        tv_login_notregistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = et_login_email.getText().toString().trim();
                password = et_login_password.getText().toString().trim();

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            fUser = fAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }
}
