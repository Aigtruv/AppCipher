package com.example.cipherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    private static EditText textView;
    private static Button decryptButton;
    private static Button encryptButton;

    private static Button cleanButton;

    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "770A8A65DA156D24EE2A093277530142".getBytes();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.cipherText);
        decryptButton = findViewById(R.id.decryptButton);
        encryptButton = findViewById(R.id.encryptButton);
        cleanButton = findViewById(R.id.cleanButton);


        decryptButton.setOnClickListener(view -> {
           String text = textView.getText().toString();
           text = decrypt(text);
           textView.setText(text);
        });


        encryptButton.setOnClickListener(view -> {
            String text = textView.getText().toString();
            text = encrypt(text);
            textView.setText(text);
        });

        cleanButton.setOnClickListener(view -> {
            textView.setText("");
        });
    }

    public String encrypt(String value) {
        try {
            Key key = new SecretKeySpec(KEY, ALGORITHM);
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, key);

            byte[] encValue = c.doFinal(value.getBytes());
            String encryptedValue = new String(Base64.encode(encValue, Base64.DEFAULT));
            return encryptedValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String value) {
        try {
            Key key = new SecretKeySpec(KEY, ALGORITHM);
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);

            byte[] decodedValue = Base64.decode(value.getBytes(), Base64.DEFAULT);
            byte[] decValue = c.doFinal(decodedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}