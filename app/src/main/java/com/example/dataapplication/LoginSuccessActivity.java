package com.example.dataapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginSuccessActivity extends AppCompatActivity {
    private TextView txtMsg;
    private Button btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        txtMsg = findViewById(R.id.txtmsg);
        btnThoat = findViewById(R.id.btnThoat);

        Intent intent = getIntent();
        String user = intent.getStringExtra("user");

        if (user != null && !user.isEmpty()) {
            txtMsg.setText("Hello: " + user);
        } else {
            Log.e("LoginSuccessActivity", "User data is null or empty");
            txtMsg.setText("Hello: Guest");
        }

        btnThoat.setOnClickListener(v -> {
            Intent mainIntent = new Intent(LoginSuccessActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        });
    }
}
