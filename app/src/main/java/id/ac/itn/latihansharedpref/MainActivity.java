package id.ac.itn.latihansharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.ac.itn.latihansharedpref.model.UserLoginModel;
import id.ac.itn.latihansharedpref.utils.UserPrefManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView tvWelcome;
    private Button btnLogout;
    UserPrefManager userPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWelcome = findViewById(R.id.tvWelcome);
        userPrefManager = UserPrefManager.getInstance(getApplicationContext());
        UserLoginModel userLoginModel = userPrefManager.getUserLogin();
        tvWelcome.setText("Selamat datang " + userLoginModel.getUserName());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Dashboard");
        }
        if (!userPrefManager.isLoggedIn()) {
            goToLogin();
        }
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPrefManager.logout();
                goToLogin();
            }

        });
    }

    private void goToLogin() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }
}