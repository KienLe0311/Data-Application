package com.example.dataapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String PREF_NAME = "MyLoginPrefs";
    private Button btnDangNhap;
    private EditText edUser, edPass;
    private CheckBox chkGhiNho;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDangNhap = findViewById(R.id.btnLogin);
        edUser = findViewById(R.id.edUsername);
        edPass = findViewById(R.id.edPassword);
        chkGhiNho = findViewById(R.id.chkGhiNho);

        btnDangNhap.setOnClickListener(v -> {
            String username = edUser.getText().toString().trim();
            String password = edPass.getText().toString().trim();

            if (validateInput(username, password)) {
                savingPreferences();
                Intent mh = new Intent(MainActivity.this, LoginSuccessActivity.class);
                mh.putExtra("user", username);
                startActivity(mh);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        savingPreferences();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoringPreferences();
    }

    private void savingPreferences() {
        SharedPreferences pre = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        String user = edUser.getText().toString().trim();
        String pwd = edPass.getText().toString().trim();
        boolean bchk = chkGhiNho.isChecked();
        if (!bchk) {
            editor.clear();
        } else {
            editor.putString("user", user);
            editor.putString("pwd", pwd);
            editor.putBoolean("checked", bchk);
        }
        editor.apply();
    }

    private void restoringPreferences() {
        SharedPreferences pre = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        if (pre != null) {
            boolean bchk = pre.getBoolean("checked", false);
            if (bchk) {
                String user = pre.getString("user", "");
                String pwd = pre.getString("pwd", "");
                edUser.setText(user);
                edPass.setText(pwd);
            }
            chkGhiNho.setChecked(bchk);
        }
    }

    private boolean validateInput(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            edUser.setError(username.isEmpty() ? "Tên đăng nhập là bắt buộc" : null);
            edPass.setError(password.isEmpty() ? "Mật khẩu là bắt buộc" : null);
            return false;
        }
        return true;
    }
}
