package com.example.tipaekzamen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {

    EditText email, password;
    TextView goToSignUp;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        init();

        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, SignUp.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString()))
                {
                    ShowDialogWindow("Fields are empty!");
                }
                else{
                    com.example.tipaekzamen.LoginRequest loginRequest = new com.example.tipaekzamen.LoginRequest();
                    loginRequest.setEmail(email.getText().toString());
                    loginRequest.setPassword(password.getText().toString());
                    LoginUser(loginRequest);
                }
            }
        });
    }

    public void LoginUser(com.example.tipaekzamen.LoginRequest loginRequest)
    {
        Call<LoginResponse> loginResponseCall = com.example.tipaekzamen.ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(SignIn.this, com.example.tipaekzamen.MainActivity.class));
                }
                else{
                    ShowDialogWindow("Something wrong, pls, try again later...");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                ShowDialogWindow(t.getLocalizedMessage());
            }
        });
    }

    public void ShowDialogWindow(String text){
        final AlertDialog alertDialog = new AlertDialog.Builder(SignIn.this).setMessage(text).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create();
        alertDialog.show();
    }

    public void init(){
        email = findViewById(R.id.edEmailSignIn);
        password = findViewById(R.id.edPasswordSignIn);
        goToSignUp = findViewById(R.id.tvGoToSignUp);
        signIn = findViewById(R.id.btnSignIn);
    }
}