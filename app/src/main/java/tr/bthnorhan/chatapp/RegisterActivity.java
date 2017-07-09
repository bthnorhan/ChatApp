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

public class RegisterActivity extends AppCompatActivity {

    TextView tv_register_registered;
    EditText et_register_email,et_register_password,et_register_repassword;
    Button btn_register_register;
    FirebaseAuth fAuth;
    FirebaseUser fUser;

    String email,password,repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();

        tv_register_registered = (TextView) findViewById(R.id.tv_register_registered);
        et_register_email = (EditText) findViewById(R.id.et_register_email);
        et_register_password = (EditText) findViewById(R.id.et_register_password);
        et_register_repassword = (EditText) findViewById(R.id.et_register_repassword);
        btn_register_register = (Button) findViewById(R.id.btn_register_register);

        tv_register_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = et_register_email.getText().toString().trim();
                password = et_register_password.getText().toString().trim();
                repassword = et_register_repassword.getText().toString().trim();

                if (password.equals(repassword))
                {
                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                fUser = fAuth.getCurrentUser();
                                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }
}
