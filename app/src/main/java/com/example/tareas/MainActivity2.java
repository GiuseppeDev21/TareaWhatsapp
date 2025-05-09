package com.example.tareas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private static final String HOME_ACTIVITY_TAG = MainActivity2.class.getSimpleName();
    private EditText editText;
    String defaultLocal = "+507";
    int limiteCodeLocal = defaultLocal.length();
    int limitePhone = 8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Centrado de los objetos
        Button btnBack = findViewById(R.id.btn2);
        Button btnSend = findViewById(R.id.btn3);

        EditText editText = findViewById(R.id.editT2);

        // Centramos los objetos

        btnBack.setGravity(View.TEXT_ALIGNMENT_CENTER);
        btnSend.setGravity(View.TEXT_ALIGNMENT_CENTER);
        editText.setGravity(View.TEXT_ALIGNMENT_CENTER);

        // Añadir el listener al botón para regresar a la MainActivity
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLog("Volviendo a la MainActivity...");
                finish(); // Termina la actividad y regresa a la anterior
            }
        });

        // Añadir el listener al botón para enviar el mensaje
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageWhatsapp(editText.getText().toString());
            }
        });

        // Ajustar la ventana para tener en cuenta las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Función para mostrar logs con el tag de la actividad
    private void showLog(String text) {
        Log.d(HOME_ACTIVITY_TAG, text); // Mostrar log con el tag de la actividad
    }

    // Función para mostrar logs de depuración
    private void showDebugLog(String text) {
        Log.d(HOME_ACTIVITY_TAG, text);
    }

    // Función para mostrar logs informativos
    private void showInfoLog(String text) {
        Log.i(HOME_ACTIVITY_TAG, text);
    }

    // Función para mostrar logs de advertencia
    private void showWarningLog(String text) {
        Log.w(HOME_ACTIVITY_TAG, text);
    }

    // Función para mostrar logs de error
    private void showErrorLog(String text) {
        Log.e(HOME_ACTIVITY_TAG, text);
    }

    // Función para mostrar logs detallados
    private void showVerboseLog(String text) {
        Log.v(HOME_ACTIVITY_TAG, text);
    }

    // Función para mostrar logs de fallos fatales
    private void showWtfLog(String text) {
        Log.wtf(HOME_ACTIVITY_TAG, text);
    }

    private void sendMessageWhatsapp(String phone){
        Toast.makeText(this,phone,Toast.LENGTH_LONG).show();
        if (phone.length() == (limiteCodeLocal + limitePhone)) {
            String numeroPhone = phone.substring(phone.lastIndexOf(defaultLocal));
            System.out.println("numeroPhone = " + numeroPhone);
            Handler handler = new Handler();

            handler.post(new Runnable() {
                public void run() {
                    String url = "https://api.whatsapp.com/send?phone=" + phone;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                    finish();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "FAVOR INGRESE UN CONTACTO VÁLIDO", Toast.LENGTH_SHORT).show();
        }
    }
}
