package com.example.silhouette.homeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button register;
    private Button login;
    private TextView user;
    private TextView pw;
    static String token;
    private  static  boolean success = true;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (TextView)findViewById(R.id.username);
        pw = (TextView)findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                final String userName = user.getText().toString();
                final String password = pw.getText().toString();
                if(userName.equals("")) {
                    Toast.makeText(MainActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                } else if(password.equals("")){
                    Toast.makeText(MainActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(userName.equals("Sak") && password.equals("123")){
                    Intent intent=new Intent();
                    intent.setClass(MainActivity.this,FragmentActivity.class);
                    //intent.putExtra("token",token);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}
