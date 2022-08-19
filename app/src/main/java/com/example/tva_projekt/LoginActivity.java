package com.example.tva_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompat {

    EditText username, password;
    Button login;
    DBHelper DB;
    Button btnEN;
    Button btnSI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn=findViewById(R.id.textViewSignup);
        LanguageManager lang=new LanguageManager(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        username=(EditText) findViewById(R.id.inputUsername1);
        password=(EditText)  findViewById(R.id.inputPassword1);
        login=(Button) findViewById(R.id.Login);
        DB=new DBHelper(this);
        btnEN=findViewById(R.id.btn_en);
        btnSI=findViewById(R.id.btn_slo);



        btnEN.setOnClickListener(view ->{
            lang.updateResources("en");
            recreate();
        });

        btnSI.setOnClickListener(view ->{
            lang.updateResources("sl");
            recreate();
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user=username.getText().toString();
                String pass=password.getText().toString();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this,"Please enter all fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                    if (checkuserpass==true){
                        Toast.makeText(LoginActivity.this,"Sign in succesfull",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
                    }
                    if (user.equals("admin")&&pass.equals("admin")){
                        Toast.makeText(LoginActivity.this,"Admin succesfull",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Admin.class);
                        startActivity(intent);
                    }else{
                         checkuserpass=DB.checkusernamepassword(user,pass);
                    }
                }
            }
        });

    }

}