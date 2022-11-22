package id.ac.itn.latihansharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.itn.latihansharedpref.api.ApiClient;
import id.ac.itn.latihansharedpref.model.UserLoginResponse;
import id.ac.itn.latihansharedpref.utils.UserPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;

public class LoginActivity extends AppCompatActivity {

    private EditText etUserMail, etUserPass;
    private Button btnLogin;
    ProgressDialog progressDialog;
    UserPrefManager userPrefManager;
    private Boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserMail = findViewById(R.id.edtUserName);
        etUserPass = findViewById(R.id.edtPassword);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait.....");
        progressDialog.setCancelable(false);
        btnLogin = findViewById(R.id.btnLogin);
        userPrefManager = UserPrefManager.getInstance(getApplicationContext());
        isLoggedIn = userPrefManager.isLoggedIn();
        if(isLoggedIn){
            goToMain();
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        boolean statusInput = true;
        // cek inputan form yang kosong
        if (etUserMail.getText().toString().trim().equalsIgnoreCase("")) {
            etUserMail.setError("Masukkan Email Anda");
            etUserMail.requestFocus();
            statusInput = false;
            return;
        }
        if (etUserPass.getText().toString().trim().equalsIgnoreCase("")) {
            etUserPass.setError("Masukkan kata sandi Anda");
            etUserPass.requestFocus();
            statusInput = false;
            return;
        }

        if (statusInput) {
            progressDialog.show();
            checkLogin(etUserMail.getText().toString().trim(), etUserPass.getText().toString().trim());
        }
    }

    private void checkLogin(String userId, String userPass) {
        ApiClient.getInstance().getApi().signIn(userId, userPass)
                .enqueue(new Callback<UserLoginResponse>() {
                    @Override
                    public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                        // cek response jika hasil 200 ok
                        progressDialog.dismiss();
                        if (response.isSuccessful()) {
                            // cek status response = true
                            if (response.body().isStatus()) {
                                // login benar ambil data dan kirimkan ke new activity
                                Toast.makeText(LoginActivity.this, "Login Success: Email dan Kata Sandi Anda cocok", Toast.LENGTH_SHORT).show();
                                userPrefManager.setUserLogin(response.body().getData(),true);
                                goToMain();
                            } else {
                                // login salah tampilkan pesan kesalahan
                                Toast.makeText(LoginActivity.this, "Login Failed: Email dan Kata Sandi Anda tidak cocok", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            progressDialog.dismiss();
                            // tangani kesalahan / error
                            Toast.makeText(LoginActivity.this, "Login Failed: Email dan Kata Sandi Anda tidak cocok", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        // tangani error
                        Toast.makeText(LoginActivity.this, "Login Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void goToMain(){
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }
}